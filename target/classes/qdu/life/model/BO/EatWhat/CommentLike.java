package qdu.life.model.BO.EatWhat;

import lombok.Data;

import java.io.Serializable;

/**
 * @ClassName CommentLike
 * @Description 对评论对点赞
 * @Author uuorb
 * @Date 2021/5/22 2:08 上午
 * @Version 0.1
 **/
@Data
public class CommentLike implements Serializable {
  int id;
  int commentId;
  int userId;
}
