package qdu.life.model.BO.Flag;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

/**
 * @ClassName FlagShowBo
 * @Description TODO
 * @Author uuorb
 * @Date 2021/3/2310:46 上午
 * @Version 0.1
 **/
@Data
public class FlagShowBO {
  // 根据用户openid,得到这个值
  @JsonProperty("isLike")
  boolean isLike;
  int flagId;
  int showCount;
  int likeCount;
  // 收到点赞后的回复信息
  String reply;
  // 微信名
  String nickname;
  // 真名
  String realname;
  //  多少级
  int year;
  String content;
}
