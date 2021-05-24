package qdu.life.model.BO.EatWhat;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @ClassName FootComponent
 * @Description TODO
 * @Author uuorb
 * @Date 2021/5/24 1:39 上午
 * @Version 0.1
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
public class FoodComponent implements Serializable {
  int id;
  int foodId;
  String info;

  public FoodComponent(String info) {
    this.info = info;
  }
}
