package qdu.life.model.BO.Countdown;

import lombok.Data;

import java.io.Serializable;
import java.sql.Date;

/**
 * @ClassName CountdownBO
 * @Description TODO
 * @Author uuorb
 * @Date 2021/3/313:39 上午
 * @Version 0.1
 **/

@Data
public class CountdownBO implements Serializable {
  Date startDate;
  // 类型，0为节假，1为考试
  int type;
  // 放几天
  int duration;
  //  报名时间
  Date signDate;
  String name;

}
