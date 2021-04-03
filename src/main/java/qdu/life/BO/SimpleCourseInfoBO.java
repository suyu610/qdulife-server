package qdu.life.BO;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serializable;

/**
 * @ClassName SimpleCourseInfo
 * @Description TODO
 * @Author uuorb
 * @Date 2021/3/109:24 下午
 * @Version 0.1
 **/

@Data
@AllArgsConstructor
public class SimpleCourseInfoBO implements Serializable {
  // 课程名
  String n;
  // 课程号
  String i;
  // 课序号
  int s;
}
