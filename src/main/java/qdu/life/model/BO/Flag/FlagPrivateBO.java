package qdu.life.model.BO.Flag;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @ClassName FlagPrivateBO
 * @Description 用于展示在用户个人flag页面
 * @Author uuorb
 * @Date 2021/3/234:46 下午
 * @Version 0.1
 **/
@Data

public class FlagPrivateBO implements Serializable {
  String flagId;
  int showCount;
  int likeCount;
  // 用户是否设置为公开
  int isPublic;
  // 是否审核通过
  int isVerify;
  // 点赞的人的名字
  String likeUsernameList;
  //  多少级
  int year;
  String content;
  Date createDate;
}
