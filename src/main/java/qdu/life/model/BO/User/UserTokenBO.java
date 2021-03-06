package qdu.life.model.BO.User;

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
  String ssNumber;
  String nickname;
  String avatarUrl;
  String relatedOpenid;
}
