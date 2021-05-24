package qdu.life.model.BO.EatWhat;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @ClassName FoodTag
 * @Description 标签
 * @Author uuorb
 * @Date 2021/5/22 2:08 上午
 * @Version 0.1
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
public class FoodTag implements Serializable {
  int id;
  int foodId;
  String info;
  public FoodTag(String info) {
    this.info = info;
  }
}
