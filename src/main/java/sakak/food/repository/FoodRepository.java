package sakak.food.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import sakak.food.entity.Food;

import java.util.List;

public interface FoodRepository extends JpaRepository<Food, Long> {

    //주어진 인자가 null 이 없기에 따로 null 처리 하지 않음
    @Query("SELECT f FROM Food f " +
            "WHERE f.foodName = :food_name " +
            "AND f.researchYear = :research_year " +
            "AND f.makerName = :maker_name " +
            "AND f.foodCd = :food_code")
    List<Food> searchFoods(@Param("food_name") String food_name,
                           @Param("research_year") String research_year,
                           @Param("maker_name") String maker_name,
                           @Param("food_code") String food_code);


    @Query("SELECT f FROM Food f")
    Page<Food> readFoods(Pageable pageable);

    boolean existsByFoodCd(String foodCd);

}
