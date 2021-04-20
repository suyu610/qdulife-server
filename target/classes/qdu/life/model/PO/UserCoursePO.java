package qdu.life.model.PO;

import lombok.Data;
import org.apache.ibatis.annotations.Param;

/**
 * @ClassName UserCourseModel
 * @Description TODO
 * @Author uuorb
 * @Date 2021/4/54:06 上午
 * @Version 0.1
 **/

@Data
public class UserCoursePO {

  String key_course;
  String key_seq;
  String openid;
  int year;
  int term;
  String course_name;
  float score;
  String teachers;
  String whichweek;
  String week;
  String seq;
  String info_str;
  String class_name;


}
