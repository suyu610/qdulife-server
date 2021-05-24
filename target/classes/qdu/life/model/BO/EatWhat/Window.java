package qdu.life.model.BO.EatWhat;

import lombok.Data;

import java.io.Serializable;

/**
 * @ClassName Window
 * @Description 食堂的各个窗口
 * @Author uuorb
 * @Date 2021/5/22 2:07 上午
 * @Version 0.1
 **/

@Data
public class Window implements Serializable {
  int id;
  //  几号窗口
  String windowName;
  String windowDesc;
  // 几楼
  int floor;
  int canteenId;
  // 对应的路线图
  String routeUrl;
  // 餐厅名字
  String canteenName;
  // 校区名字
  String cName;
  //  坐标
  String point;
}
