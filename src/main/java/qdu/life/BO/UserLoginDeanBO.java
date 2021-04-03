package qdu.life.BO;

import lombok.Data;

import java.io.Serializable;

/**
 * @ClassName UserLoginDeanBO
 * @Description TODO
 * @Author uuorb
 * @Date 2021/3/175:35 上午
 * @Version 0.1
 **/

@Data
public class UserLoginDeanBO implements Serializable {
  String num;
  String pwd;
  String code;
  String cookie;
}
