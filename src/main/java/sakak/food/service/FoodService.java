package sakak.food.service;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import sakak.food.dto.FoodDTO;
import sakak.food.entity.Food;
import sakak.food.exception.CustomErrorCode;
import sakak.food.exception.CustomException;
import sakak.food.repository.FoodRepository;

import java.util.List;

@Service
@RequiredArgsConstructor

public class FoodService {

    private final FoodRepository foodRepository;

    @Transactional(readOnly = true)
    public List<Food> searchFoods(String food_name, String research_year, String maker_name, String food_code) {
        // 입력값이 하나라도 null 인 경우 유효하지 않은 입력으로 예외
        if (food_name == null || research_year == null || maker_name == null || food_code == null) {
            throw new CustomException(CustomErrorCode.INVALID_INPUT);
        }
        // 입력값을 기준으로 식품을 검색하고, 검색 결과가 없는 경우 예외
        List<Food> foods = foodRepository.searchFoods(food_name, research_year, maker_name, food_code);
        if (foods.isEmpty()) {
            throw new CustomException(CustomErrorCode.FOOD_NOT_FOUND);
        }
        return foods;
    }

    // 특정 음식 조회 기능
    @Transactional(readOnly = true)
    public Food getFoodById(Long id) {
        // ID로 식품을 검색하고, 검색 결과가 없는 경우 예외
        return foodRepository.findById(id)
                .orElseThrow(() -> new CustomException(CustomErrorCode.FOOD_NOT_FOUND));
    }

}