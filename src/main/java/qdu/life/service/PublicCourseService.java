package qdu.life.service;

import org.springframework.stereotype.Service;
import qdu.life.model.BO.Course.PublicCourseBO;

import java.util.HashMap;

@Service
public interface PublicCourseService {
  HashMap getCourseByName(String courseName, int seq, int week, int campus_id, int indexPage);
  void insertPublicCourse(String openid, PublicCourseBO bo);
}
