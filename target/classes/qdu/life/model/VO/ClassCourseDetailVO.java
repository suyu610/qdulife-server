package qdu.life.model.VO;

import lombok.Data;

/**
 * @ClassName ClassDetailVO
 * @Description 用于小程序class页面的教室课表详情
 * @Author uuorb
 * @Date 2021/4/712:45 下午
 * @Version 0.1
 **/

@Data
public class ClassCourseDetailVO {
  int week;
  //
  String seq;
  String course_name;
  String teachers;
}
