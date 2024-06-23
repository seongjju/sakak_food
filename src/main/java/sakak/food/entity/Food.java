package sakak.food.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Food {
    //(): 엑셀표 기준 기입
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; //id (NO)

    @Column(name = "food_cd")
    private String foodCd; //식품코드

    @Column(name = "group_name")
    private String groupName; //식품군 (상용제품)

    @Column(name = "food_name")
    private String foodName; //식품이름 (식품명)

    @Column(name = "research_year")
    private String researchYear; //조사년도 (연도)

    @Column(name = "maker_name")
    private String makerName; //지역/제조사

    @Column(name = "ref_name")
    private String refName; //자료출처 (성분표출처)

    @Column(name = "serving_size")
    private Integer servingSize; //1회 제공량

    private Double calorie; //열량(kcal)(1회제공량당) (에너지(㎉))

    private Double carbohydrate; //탄수화물(g)(1회제공량당)

    private Double protein; //단백질(g)(1회제공량당)

    private Double province; //지방(g)(1회제공량당)

    private Double sugars; //총당류(g)(1회제공량당)

    private Double salt; //나트륨(mg)(1회제공량당)

    private Double cholesterol; //콜레스테롤(mg)(1회제공량당)

    @Column(name = "saturated_fatty_acids")
    private Double saturatedFattyAcids; //포화지방산(g)(1회제공량당) (총 포화 지방산(g))

    @Column(name = "trans_fat")
    private Double transFat; //트랜스지방(g)(1회제공량당) (트랜스 지방산(g))

}
