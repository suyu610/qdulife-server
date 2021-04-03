package qdu.life.BO;

import lombok.Data;

import java.io.Serializable;

/**
 * @ClassName PublicCourseBO
 * @Description TODO
 * @Author uuorb
 * @Date 2021/3/293:44 下午
 * @Version 0.1
 **/

@Data
public class PublicCourseBO implements Serializable {
  String course_name;
  String info_str;
  String teachers;
  String class_name;
  int capacity;
  int stu_number;
  int course_type;
  float score;
  int campus_id;
}
