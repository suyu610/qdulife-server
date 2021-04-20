package qdu.life.service.impl;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import qdu.life.mapper.BuildingMapper;
import qdu.life.model.BO.Classroom.*;
import qdu.life.mapper.ClassRoomMapper;
import qdu.life.service.ClassRoomService;

import java.util.*;

@Service
@Transactional
public class ClassRoomServiceImpl implements ClassRoomService {
  @Autowired
  ClassRoomMapper mapper;
  @Autowired
  BuildingMapper buildingMapper;

  // 搜索教室状态，放在list里
  public List<BuildingBO> getClassStatus(ClassRoomStatusPostDataBO bo){
    // 如果此字段为true,则需要检查expectStatus的教室，如果为false，则无须排查教室状态，直接返回该天该教学楼所有的教室
    boolean isOnlyFree = bo.isOnlyFree();

    if(isOnlyFree == false){
      bo.setExpectStatus("_____________");
    }

    // 默认校区为中心校区 , 金家岭为13041
    if(bo.getCampuseId()==-1){
      bo.setCampuseId(1709);
    }

    // 注意 校区和教学楼id要匹配
    List<String> buildingIdArr;
    if(bo.getBuildingIdList().equals("-1") ){
      System.out.println("教学楼为-1");
      // 获取该校区所有教学楼的id
      buildingIdArr = buildingMapper.getCampuseBuildingId(bo.getCampuseId());
    }else {
      // 把buildingId转成数组
      // 如果不为空，则根据-进行切割
       buildingIdArr = Arrays.asList(bo.getBuildingIdList().split("-"));
    }

    List<ClassRoomWithWholeInfo> matchedClass = mapper.searchMultiClassFreeStatusByWeekAndSeq(
      buildingIdArr,bo.getExpectStatus(),bo.getWhichweek(), bo.getWeek(),bo.getCampuseId()
    );

    // 2.按buildingID整理输出
    Map<String,BuildingBO> buildingMap = new HashMap<>();
    for (ClassRoomWithWholeInfo tmp : matchedClass){
      String bName = tmp.getBName();
      // 判断BuildingId是否在List中，如果在则数量+1，并且加入到他的名字中
      if(buildingMap.containsKey(bName)){
        int count = buildingMap.get(bName).getMatchedCount();
        buildingMap.get(bName).setMatchedCount(++count);
        List tmpList = buildingMap.get(bName).getClassList();
        tmpList.add(tmp);
      }else{
        // 如果是第一次新增一个BO，放进hashMap中
        BuildingBO buildingBO = new BuildingBO();
        // 博文楼的classList
        List<ClassRoomWithWholeInfo> classList = new LinkedList<>();
        classList.add(tmp);
        buildingBO.setBName(bName);
        buildingBO.setMatchedCount(1);
        buildingBO.setClassList(classList);
        buildingMap.putIfAbsent(bName,buildingBO);
      }
    }

    // 把map转list
    Collection<BuildingBO> valueCollection = buildingMap.values();
    List<BuildingBO> valueList = new ArrayList<BuildingBO>(valueCollection);
    return valueList;
  }

  public List<ClassBO> getClassByBuildId(int buildingId){
    List classList = new LinkedList();
    classList = mapper.getClassByBuildId(buildingId);
    return classList;
  }

  public List<ClassBO> getClassByBuildIdAndFloor(int buildingId, int floor){
    List classList = new LinkedList();
    classList = mapper.getClassByBuildIdAndFloor(buildingId,floor);
    return classList;
  }

  public ClassRoomWithBuildingNameBO getClassByName(String className){
    return mapper.getClassByName(className);
  }

  public ClassRoomWithTables getSingleClassWithTables(String className){
    ClassRoomWithTables classRoomWithTables = new ClassRoomWithTables();
    List<StatusBO> statusModels = new LinkedList<>();
    statusModels = mapper.getWholeStatusByClassName(className);
    classRoomWithTables.setClassRoomWithBuildingNameBO(mapper.getClassByName(className));
    classRoomWithTables.setStatus(statusModels);
    return  classRoomWithTables;
  }

  // 返回教学楼id + 数量 + 符合条件的教室名字 列表？
  public List<BuildingBO> getClassBySearch(int whichweek, int week , String seq){
    Map<String,BuildingBO> buildingMap = new HashMap<String, BuildingBO>();

    // 1.获取当前时间，当前课序的所有课
    List<ClassRoomWithWholeInfo> matchedClass = mapper.searchByWeekAndSeq(seq,whichweek,week);
    // 2.按buildingID整理输出
    for (ClassRoomWithWholeInfo tmp : matchedClass){
      String bName = tmp.getBName();
      // 判断BuildingId是否在List中，如果在则数量+1，并且加入到他的名字中
      if(buildingMap.containsKey(bName)){
        int count = buildingMap.get(bName).getMatchedCount();
        buildingMap.get(bName).setMatchedCount(++count);
        List tmpList = buildingMap.get(bName).getClassList();
        tmpList.add(tmp);
      }else{
        // 如果是第一次新增一个BO，放进hashMap中
        BuildingBO buildingBO = new BuildingBO();
        // 博文楼的classList
        List<ClassRoomWithWholeInfo> classList = new LinkedList<>();
        classList.add(tmp);
        buildingBO.setBName(bName);
        buildingBO.setMatchedCount(1);
        buildingBO.setClassList(classList);
        buildingMap.putIfAbsent(bName,buildingBO);
      }
    }
    // 把map转list
    Collection<BuildingBO> valueCollection = buildingMap.values();
    List<BuildingBO> valueList = new ArrayList<BuildingBO>(valueCollection);
    return valueList;
  }

  //太蠢了，对不起凌晨4点只能这么笨了= =
  //离谱
  public List<BuildingBO> getClassByDetail(int whichweek, int week , String buildingIds,String floor,String length){
    //
    Map<String,BuildingBO> buildingMap = new HashMap<String, BuildingBO>();
    // 把buildingId转成数组
    List<String> buildingIdArr = Arrays.asList(buildingIds.split("-"));
    //  如果没传楼层
    if(floor.equals("-1")){
      floor = "_";
    }

    switch (length){
      case "0":{ length="%0%";break;}
      case "1":{ length="%0%";break; }
      case "2":{ length="%00%";break; }
      case "3":{ length="%000%";break; }
      case "4":{ length="%0000%";break; }
      case "5":{ length="%00000%";break; }
      case "6":{ length="%000000%";break; }
      case "7":{ length="%0000000%";break; }
      case "8":{ length="%00000000%";break; }
      case "9":{ length="%000000000%";break; }
      case "10":{ length="%0000000000%";break; }
      case "11":{ length="%00000000000%";break; }
      case "12":{ length="%000000000000%";break; }
      case "13":{ length="%0000000000000%";break; }
      default:{ length="%0%";break;}
    }

    List<ClassRoomWithWholeInfo> matchedClass = mapper.searchByWeekAndbuildingIdAndFloorAndLength(buildingIdArr,floor,length,week,whichweek);
    // 2.按buildingID整理输出
    for (ClassRoomWithWholeInfo tmp : matchedClass){
      String bName = tmp.getBName();

      // 判断BuildingId是否在List中，如果在则数量+1，并且加入到他的名字中
      if(buildingMap.containsKey(bName)){
        int count = buildingMap.get(bName).getMatchedCount();
        buildingMap.get(bName).setMatchedCount(++count);
        List tmpList = buildingMap.get(bName).getClassList();
        tmpList.add(tmp);
      }else{
        // 如果是第一次新增一个BO，放进hashMap中
        BuildingBO buildingBO = new BuildingBO();
        // 博文楼的classList
        List<ClassRoomWithWholeInfo> classList = new LinkedList<>();
        classList.add(tmp);
        buildingBO.setBName(bName);
        buildingBO.setMatchedCount(1);
        buildingBO.setClassList(classList);
        buildingMap.putIfAbsent(bName,buildingBO);
      }
    }

    Collection<BuildingBO> valueCollection = buildingMap.values();
    List<BuildingBO> valueList = new ArrayList<BuildingBO>(valueCollection);
    return valueList;
  }

}

