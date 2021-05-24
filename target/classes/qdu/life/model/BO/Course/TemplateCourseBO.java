package qdu.life.model.BO.Course;

import lombok.Data;

import java.io.Serializable;

/**
 * @ClassName TemplateCourseBO
 * @Description TODO
 * @Author uuorb
 * @Date 2021/3/314:54 上午
 * @Version 0.1
 **/

@Data
public class TemplateCourseBO implements Serializable {
  String courseName;
  String className;
  String seq;
  int direction;
  int startIndex;
}
