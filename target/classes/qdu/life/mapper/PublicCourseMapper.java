package qdu.life.mapper;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
import qdu.life.BO.PublicCourseBO;

import java.util.List;

@Repository
public interface PublicCourseMapper {


  List<PublicCourseBO> getCourseByName(@Param("course_name")String course_name,@Param("seq")String seq,@Param("week") String week,@Param("campus_id")String campus_id);
}
