package qdu.life.model.BO.EatWhat;

import lombok.Data;
import org.springframework.data.geo.Point;

import java.io.Serializable;

/**
 * @ClassName Canteen
 * @Description 餐厅
 * @Author uuorb
 * @Date 2021/5/22 2:04 上午
 * @Version 0.1
 **/

@Data
public class Canteen implements Serializable{
  int id;
  //  坐标
  String point;
  // 餐厅名字？
  String canteenName;
  // 校区的名字
  String cName;
  // 最高楼层
  int maxFloor;
}
