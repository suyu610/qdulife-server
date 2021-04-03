package qdu.life.BO;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;

/**
 * @ClassName StatusBO
 * @Description TODO
 * @Author uuorb
 * @Date 2020/12/142:06 下午
 * @Version 0.1
 **/
// 只包含周，星期，和状态
  @Getter
  @Setter
  @ToString
public class StatusBO implements Serializable {
  String status;
  int whichweek;
  int week;
}
