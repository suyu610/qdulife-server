package qdu.life.mapper;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
import qdu.life.model.BO.Classroom.ClassBO;
import qdu.life.model.BO.Classroom.ClassRoomWithBuildingNameBO;
import qdu.life.model.BO.Classroom.ClassRoomWithWholeInfo;
import qdu.life.model.BO.Classroom.StatusBO;

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
  List<ClassBO> getClassByBuildId(@Param("buildingId") int buildingId);
  List<ClassBO> getClassByBuildIdAndFloor(@Param("buildingId") int buildingId, @Param("floor") int floor);
  ClassRoomWithBuildingNameBO getClassByName(@Param("className") String className);
  List<StatusBO> getWholeStatusByClassName(@Param("className") String className);
  List<ClassRoomWithWholeInfo> searchByWeekAndSeq(@Param("seq") String seq, @Param("whichweek") int whichweek, @Param("week") int week);

  List<ClassRoomWithWholeInfo> searchMultiClassFreeStatusByWeekAndSeq(
    @Param("buildingIdArr") List<String> buildingIdArr,
    @Param("seq") String seq,
    @Param("whichweek") int whichweek,
    @Param("week") int week,
    @Param("campuseId") int campuseId
  );

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
