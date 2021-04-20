package qdu.life.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import qdu.life.model.BO.Classroom.*;
import qdu.life.mapper.ClassRoomMapper;

import java.util.*;

/**
 * @ClassName ClassRoomService
 * @Description TODO
 * @Author uuorb
 * @Date 2020/12/144:42 上午
 * @Version 0.1
 **/

@Service
public interface ClassRoomService {

  public List<BuildingBO> getClassStatus(ClassRoomStatusPostDataBO bo);

  public List<ClassBO> getClassByBuildId(int buildingId);

  public List<ClassBO> getClassByBuildIdAndFloor(int buildingId, int floor);

  public ClassRoomWithBuildingNameBO getClassByName(String className);

  public ClassRoomWithTables getSingleClassWithTables(String className);

  // 返回教学楼id + 数量 + 符合条件的教室名字 列表？
  public List<BuildingBO> getClassBySearch(int whichweek, int week , String seq);

  public List<BuildingBO> getClassByDetail(int whichweek, int week , String buildingIds,String floor,String length);

}
