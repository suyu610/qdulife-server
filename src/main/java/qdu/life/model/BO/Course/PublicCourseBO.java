package qdu.life.model.BO.Course;

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
  String keyCourse;
  String keySeq;
  String courseName;
  String infoStr;
  String teachers;
  String className;
  int capacity;
  int stuNumber;
  int courseType;
  float score;
  int campusId;
}
