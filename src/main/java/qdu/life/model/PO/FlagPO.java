package qdu.life.model.PO;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @ClassName FlagModel
 * @Description TODO
 * @Author uuorb
 * @Date 2021/3/223:32 下午
 * @Version 0.1
 **/
@Data
public class FlagPO implements Serializable {
  int flagId;
  int showCount;
  int likeCount;
  // 用户是否设置为公开
  int isPublic;
  // 是否审核通过
  int isCerify;
  // 点赞的人的openid
  String likeOpenidList;
  String openid;
  String content;
  Date createDate;
}
