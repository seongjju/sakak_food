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


}
