package qdu.life.model.BO.EatWhat;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @ClassName FoodPrice
 * @Description 价格
 * @Author uuorb
 * @Date 2021/5/22 2:08 上午
 * @Version 0.1
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
public class FoodPrice implements Serializable {
  int id;
  int foodId;
  double priceNum;

  public FoodPrice(String priceInfo) {
    this.priceNum = Integer.parseInt(priceInfo);
  }
}
