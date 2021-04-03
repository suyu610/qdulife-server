package qdu.life.mapper;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
import qdu.life.BO.ClassRoomBO;
import qdu.life.BO.ClassRoomWithWholeInfo;
import qdu.life.BO.StatusBO;

import java.util.List;
@Repository
public interface CheckMapper {
  String getFreeStatus(
    @Param("className") String className,
    @Param("week") int week,
    @Param("whichWeek") int whichWeek);

  String[] getFreeDetailStatus(
    @Param("className") String className,
    @Param("week") int week,
    @Param("whichWeekStr") String whichWeekStr);
}
