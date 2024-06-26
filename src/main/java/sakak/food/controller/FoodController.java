package sakak.food.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sakak.food.dto.FoodDTO;
import sakak.food.entity.Food;
import sakak.food.exception.CustomErrorCode;
import sakak.food.exception.CustomException;
import sakak.food.exception.ErrorResponse;
import sakak.food.service.FoodService;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/foods")
@Tag(name = "Food API", description = "API for foods")
public class FoodController {

    private final FoodService foodService;

    //Search API
    @Operation(summary = "요청 인자에 대한 출력 항목")
    @GetMapping("/search")
    public ResponseEntity<?> searchFoods(
            @RequestParam(required = false) String food_name,
            @RequestParam(required = false) String research_year,
            @RequestParam(required = false) String maker_name,
            @RequestParam(required = false) String food_code)
    {
        try {
            List<Food> foods = foodService.searchFoods(food_name, research_year, maker_name, food_code);
            return ResponseEntity.ok(foods);
        } catch (CustomException e) {
            ErrorResponse errorResponse = new ErrorResponse(e.getCode(), e.getHttpStatus(), e.getMessage());
            return ResponseEntity.status(e.getHttpStatus().value()).body(errorResponse);
        }
    }


    // Read: 특정 음식 조회 기능
    @Operation(summary = "ID 기준으로 조회")
    @GetMapping("/{id}")
    public ResponseEntity<?> getFoodById(@PathVariable Long id) {
        try {
            Food food = foodService.getFoodById(id);
            return ResponseEntity.ok(food);
        } catch (CustomException e) {
            ErrorResponse errorResponse = new ErrorResponse(e.getCode(), e.getHttpStatus(), e.getMessage());
            return ResponseEntity.status(e.getHttpStatus().value()).body(errorResponse);
        }
    }

    // Read: 페이지 단위로 조회 기능
    @Operation(summary = "페이지 단위로 조회")
    @GetMapping
    public ResponseEntity<?> getTopFoods(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "id") String sortBy,
            @RequestParam(defaultValue = "asc") String sortOrder) {
        try {
            Page<Food> foods = foodService.getTopFoods(page, size, sortBy, sortOrder);
            return ResponseEntity.ok(foods.getContent());
        } catch (CustomException e) {
            ErrorResponse errorResponse = new ErrorResponse(e.getCode(), e.getHttpStatus(), e.getMessage());
            return ResponseEntity.status(e.getHttpStatus().value()).body(errorResponse);
        }
    }



    // Create: 새로운 음식 생성 기능
    @Operation(summary = "새로운 음식 생성")
    @PostMapping
    public ResponseEntity<?> createFood(@Valid @RequestBody FoodDTO foodDTO) {
        try {
            Food food = foodService.createFood(foodDTO);
            return ResponseEntity.ok(food);
        } catch (CustomException e) {
            ErrorResponse errorResponse = new ErrorResponse(e.getCode(), e.getHttpStatus(), e.getMessage());
            return ResponseEntity.status(e.getHttpStatus().value()).body(errorResponse);
        }
    }


    // Update: 기존 음식 업데이트 기능
    @Operation(summary = "기존 음식 업데이트")
    @PutMapping("/{id}")
    public ResponseEntity<?> updateFood(@PathVariable Long id, @Valid @RequestBody FoodDTO foodDTO) {
        try {
            Food food = foodService.updateFood(id, foodDTO);
            return ResponseEntity.ok(food);
        } catch (CustomException e) {
            throw new CustomException(CustomErrorCode.FOOD_NOT_FOUND);
        }
    }



    // Delete: 특정 음식 삭제 기능
    @Operation(summary = "특정 음식 삭제")
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteFood(@PathVariable Long id) {
        try {
            foodService.deleteFood(id);
            String message = String.format("%d 삭제 성공", id);
            return ResponseEntity.ok(message);
        } catch (CustomException e) {
            ErrorResponse errorResponse = new ErrorResponse(e.getCode(), e.getHttpStatus(), e.getMessage());
            return ResponseEntity.status(e.getHttpStatus().value()).body(errorResponse);
        }
    }
}
