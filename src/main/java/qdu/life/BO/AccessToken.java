package qdu.life.BO;

import lombok.Data;

import java.io.Serializable;

/**
 * @ClassName AccessToken
 * @Description TODO
 * @Author uuorb
 * @Date 2021/3/147:30 下午
 * @Version 0.1
 **/
@Data
public class AccessToken implements Serializable {
  String access_token;
  int expires_in;
}
