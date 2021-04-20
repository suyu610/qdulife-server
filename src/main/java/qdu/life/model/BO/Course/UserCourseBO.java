package qdu.life.model.BO.Course;

import lombok.Data;

import java.io.Serializable;

/**
 * @ClassName UserCourseBO
 * @Description TODO
 * @Author uuorb
 * @Date 2021/3/176:39 上午
 * @Version 0.1
 **/
/*
    cb.course_name,
    cts.class_name,
    cb.teachers,
    cb.score
    cts.week,
    cts.whichweek,
    cts.seq,
        cts.building_id,
        cts.classroom_id,
 */
@Data
public class UserCourseBO implements Serializable {
  String course_name;
  String class_name;
  String teachers;
  int score;
  int week;
  String whichWeek;
  String seq;
  String buildingId;
  int rFloor;
  String roomId;
  // 教室方位
  int direction;
  String info_str;
}
