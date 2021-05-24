package qdu.life.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Value;
import qdu.life.model.BO.EatWhat.*;

import java.util.List;

/**
 * @ClassName EatWhatMapper
 * @Description TODO
 * @Author uuorb
 * @Date 2021/5/24 6:26 上午
 * @Version 0.1
 **/
@Mapper
public interface EatWhatMapper {
  List<Food> findAllFood(@Param("canteenId")int canteenId, @Param("windowId") int windowId);
  List<FoodPrice> findPriceByFoodId(int foodId);
  List<FoodTag> findTagByFoodId(int foodId);
  List<FoodTaste> findTasteByFoodId(int foodId);
  List<FoodImage> findImageByFoodId(int foodId);
  List<FoodComponent> findComponentByFoodId(int foodId);

  int selectIdByPriceName(String priceInfo);
  int selectIdByTagName(String tagInfo);
  int selectIdByTasteName(String tasteInfo);
  int selectIdByComponentName(String componentInfo);

  int findLikeCountByFoodId(int foodId);
  int findCommentCountByFoodId(int foodId);
}
