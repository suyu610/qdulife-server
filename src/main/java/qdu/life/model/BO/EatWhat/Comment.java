package qdu.life.model.BO.EatWhat;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * @ClassName comment
 * @Description 对食物的评论
 * @Author uuorb
 * @Date 2021/5/22 2:05 上午
 * @Version 0.1
 **/
@Data
public class Comment implements Serializable {
  int id;
  int foodId;
  String createTime;
  int userId;
  String content;
  List<CommentLike> commentLikeList;
}
