package qdu.life.service.impl;

import javatests.Foo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import qdu.life.mapper.EatWhatMapper;
import qdu.life.model.BO.EatWhat.Food;
import qdu.life.service.EatService;

import java.util.List;

/**
 * @ClassName EatServiceImpl
 * @Description TODO
 * @Author uuorb
 * @Date 2021/5/24 6:36 上午
 * @Version 0.1
 **/
@Service
public class EatServiceImpl implements EatService {
  @Autowired
  EatWhatMapper mapper;

  @Override
  public List<Food> getAllFood() {

    List<Food> foodList = mapper.findAllFood(-1,-1);

    for (int i = 0; i < foodList.size(); i++) {
      Food food = foodList.get(i);
      int foodId = food.getId();
      food.setFoodPriceList(mapper.findPriceByFoodId(foodId));
      food.setFoodTagList(mapper.findTagByFoodId(foodId));
      food.setFoodTasteList(mapper.findTasteByFoodId(foodId));
      food.setFoodImageList(mapper.findImageByFoodId(foodId));
      food.setFoodComponentList(mapper.findComponentByFoodId(foodId));
      food.setLikeCount(mapper.findLikeCountByFoodId(foodId));
      food.setCommentCount(mapper.findCommentCountByFoodId(foodId));
    }
    return foodList;

  }
}
