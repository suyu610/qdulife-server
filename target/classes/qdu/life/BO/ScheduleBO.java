package qdu.life.BO;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serializable;

/**
 * @ClassName ScheduleBO
 * @Description TODO
 * @Author uuorb
 * @Date 2021/3/1010:09 下午
 * @Version 0.1
 **/
@Data
@AllArgsConstructor
public class ScheduleBO implements Serializable {
  // 周
  int week;
  String seq;
  String course_name;
  String teachers;
}
