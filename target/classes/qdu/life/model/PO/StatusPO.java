package qdu.life.model.PO;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;

/**
 * @ClassName StatusModel
 * @Description TODO
 * @Author uuorb
 * @Date 2020/12/145:37 上午
 * @Version 0.1
 **/
@Getter
@Setter
@ToString
public class StatusPO implements Serializable {
  String className;
  String status;
  int whichweek;
  int week;
}
