package qdu.life.model.BO.EatWhat;

import lombok.Data;

import java.io.Serializable;

/**
 * @ClassName FoodImage
 * @Description TODO
 * @Author uuorb
 * @Date 2021/5/22 2:06 上午
 * @Version 0.1
 **/
@Data
public class FoodImage implements Serializable {
  int id;
  int foodId;
  String imgUrl;
  String title;
}
