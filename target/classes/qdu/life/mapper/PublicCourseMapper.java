package qdu.life.mapper;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
import qdu.life.model.BO.Course.PublicCourseBO;
import qdu.life.model.PO.UserCoursePO;

import java.util.List;

@Repository
public interface PublicCourseMapper {
  UserCoursePO getCourseByInfoAndSeqAndCourse(
    @Param("info_str")String info_str,
    @Param("key_course")String key_course,
    @Param("key_seq")String key_seq
  );
  void insertPublicCourse(UserCoursePO userCourseModel);
  List<PublicCourseBO> getCourseByName(@Param("course_name")String course_name,@Param("seq")String seq,@Param("week") String week,@Param("campus_id")String campus_id);
}
