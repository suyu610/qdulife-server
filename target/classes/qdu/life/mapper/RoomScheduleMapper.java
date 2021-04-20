package qdu.life.mapper;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
import qdu.life.model.BO.Course.ScheduleBO;

import java.util.List;

/**
 * @ClassName RoomScheduleMapper
 * @Description TODO
 * @Author uuorb
 * @Date 2021/3/109:41 下午
 * @Version 0.1
 **/
@Repository
public interface RoomScheduleMapper {
  public List<ScheduleBO> getCourseByRoomAndWhichWeek(@Param("roomname") String roomname, @Param("whichWeek") String whichWeek);
}

