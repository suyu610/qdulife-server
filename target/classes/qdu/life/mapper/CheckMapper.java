package qdu.life.mapper;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

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
