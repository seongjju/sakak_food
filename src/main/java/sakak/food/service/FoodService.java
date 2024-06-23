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

    //페이지별로 조회
    @Transactional(readOnly = true)
    public Page<Food> getTopFoods(int page, int size, String sortBy, String sortOrder) {
        Sort.Direction direction = Sort.Direction.fromString(sortOrder);
        Pageable pageable = PageRequest.of(page, size, Sort.by(direction, sortBy));
        return foodRepository.readFoods(pageable);
    }

    // 새로운 음식 생성 기능
    @Transactional
    public Food createFood(FoodDTO foodDTO) {
        // 이미 존재하는 식품 코드인 경우에는 예외
        // id는 자동부여되는 값이므로 다른 후보키인 foodCd로 식별하였음
        if (foodRepository.existsByFoodCd(foodDTO.getFoodCd())) {
            throw new CustomException(CustomErrorCode.DUPLICATE_FOOD);
        }

        // 새로운 식품 생성
        Food food = Food.builder()
                .foodCd(foodDTO.getFoodCd())
                .groupName(foodDTO.getGroupName())
                .foodName(foodDTO.getFoodName())
                .researchYear(foodDTO.getResearchYear())
                .makerName(foodDTO.getMakerName())
                .refName(foodDTO.getRefName())
                .servingSize(foodDTO.getServingSize())
                .calorie(foodDTO.getCalorie())
                .carbohydrate(foodDTO.getCarbohydrate())
                .protein(foodDTO.getProtein())
                .province(foodDTO.getProvince())
                .sugars(foodDTO.getSugars())
                .salt(foodDTO.getSalt())
                .cholesterol(foodDTO.getCholesterol())
                .saturatedFattyAcids(foodDTO.getSaturatedFattyAcids())
                .transFat(foodDTO.getTransFat())
                .build();

        return foodRepository.save(food);
    }


    // 기존 음식 정보 업데이트 기능
    @Transactional
    public Food updateFood(Long id, FoodDTO foodDTO) {
        // ID로 식품을 검색하고, 검색 결과가 없는 경우 예외
        Food existingFood = foodRepository.findById(id)
                .orElseThrow(() -> new CustomException(CustomErrorCode.FOOD_NOT_FOUND));

        //식품 업데이트
        existingFood.setFoodCd(foodDTO.getFoodCd());
        existingFood.setGroupName(foodDTO.getGroupName());
        existingFood.setFoodName(foodDTO.getFoodName());
        existingFood.setResearchYear(foodDTO.getResearchYear());
        existingFood.setMakerName(foodDTO.getMakerName());
        existingFood.setRefName(foodDTO.getRefName());
        existingFood.setServingSize(foodDTO.getServingSize());
        existingFood.setCalorie(foodDTO.getCalorie());
        existingFood.setCarbohydrate(foodDTO.getCarbohydrate());
        existingFood.setProtein(foodDTO.getProtein());
        existingFood.setProvince(foodDTO.getProvince());
        existingFood.setSugars(foodDTO.getSugars());
        existingFood.setSalt(foodDTO.getSalt());
        existingFood.setCholesterol(foodDTO.getCholesterol());
        existingFood.setSaturatedFattyAcids(foodDTO.getSaturatedFattyAcids());
        existingFood.setTransFat(foodDTO.getTransFat());

        return foodRepository.save(existingFood);
    }



    // 기존 음식 삭제 기능
    @Transactional
    public void deleteFood(Long id) {
        // ID로 식품을 검색하고, 검색 결과가 없는 경우 예외
        Food existingFood = foodRepository.findById(id)
                .orElseThrow(() -> new CustomException(CustomErrorCode.FOOD_NOT_FOUND));

        //검색된 식품 삭제
        foodRepository.delete(existingFood);
    }


}