package sakak.food.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class FoodDTO {
    @NotNull(message = "Food code is required")
    private String foodCd;

    private String groupName;

    @NotNull(message = "Food name is required")
    private String foodName;

    @NotNull(message = "Research year is required")
    private String researchYear;

    @NotNull(message = "Maker name is required")
    private String makerName;

    private String refName;
    private Integer servingSize;
    private Double calorie;
    private Double carbohydrate;
    private Double protein;
    private Double province;
    private Double sugars;
    private Double salt;
    private Double cholesterol;
    private Double saturatedFattyAcids;
    private Double transFat;
}
