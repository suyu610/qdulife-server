package qdu.life.model.BO.EatWhat;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * @ClassName Food
 * @Description TODO
 * @Author uuorb
 * @Date 2021/5/22 2:06 上午
 * @Version 0.1
 **/

@Data
public class Food implements Serializable {
  int id;
  int windowId;
  String windowName;
  String canteenName;
  int canteenId;
  String foodName;
  String descFull;
  String descShort;
  String weight;
  // 楼层
  int floor;
  //  校区名字
  String cName;
  int waitTime;
  List<FoodComponent> foodComponentList;
  List<FoodImage> foodImageList;
  List<FoodPrice> foodPriceList;
  List<FoodTag> foodTagList;
  List<FoodTaste> foodTasteList;
  List<Like> likeList;
  List<Comment> commentList;

  // 评论和点赞数
  int likeCount;
  int commentCount;
}
