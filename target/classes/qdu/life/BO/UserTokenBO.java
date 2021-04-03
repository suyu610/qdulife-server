package qdu.life.BO;

import lombok.Data;

import java.io.Serializable;

/**
 * @ClassName UserTokenBO
 * @Description TODO
 * @Author uuorb
 * @Date 2021/3/201:20 上午
 * @Version 0.1
 **/
@Data
public class UserTokenBO implements Serializable {
  String openid;
  String userid;
  String ss_number;
  String nickname;
  String avatar_url;
  String relatedOpenid;
}
