package qdu.life.mapper;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
import qdu.life.BO.*;
import qdu.life.model.ClassRoom;
import qdu.life.model.StatusModel;

import java.util.List;

/**
 * @ClassName ClassRoomMapper
 * @Description TODO
 * @Author uuorb
 * @Date 2020/12/144:45 上午
 * @Version 0.1
 **/

@Repository
public interface ClassRoomMapper {
  List<ClassRoomBO> getClassByBuildId(@Param("buildingId") int buildingId);
  List<ClassRoomBO> getClassByBuildIdAndFloor(@Param("buildingId") int buildingId,@Param("floor") int floor);
  ClassRoomWithBuildingNameBO getClassByName(@Param("className") String className);
  List<StatusBO> getWholeStatusByClassName(@Param("className") String className);
  List<ClassRoomWithWholeInfo> searchByWeekAndSeq(@Param("seq") String seq, @Param("whichweek") int whichweek, @Param("week") int week);
  List<ClassRoomWithWholeInfo> searchByWeekAndbuildingIdAndFloorAndLength(
    @Param("buildingIdArr") List<String> buildingIdArr,
    @Param("floor")    String floor,
    @Param("length") String length,
    @Param("week") int week,
    @Param("whichweek") int whichweek);

  String getFreeStatus(
    @Param("className") String className,
    @Param("week") int week,
    @Param("whichWeekStr") String whichWeekStr);
}
