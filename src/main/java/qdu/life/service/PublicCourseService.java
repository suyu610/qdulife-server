package qdu.life.service;

import org.springframework.stereotype.Service;
import qdu.life.BO.PublicCourseBO;

import java.util.HashMap;
import java.util.List;
@Service
public interface PublicCourseService {
  HashMap getCourseByName(String courseName, int seq, int week, int campus_id, int indexPage);
}
