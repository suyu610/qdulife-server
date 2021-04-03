package qdu.life.service;

import com.alibaba.fastjson.JSON;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import qdu.life.BO.*;
import qdu.life.mapper.ClassRoomMapper;
import qdu.life.model.ClassRoom;
import qdu.life.model.StatusModel;
import qdu.life.utils.ClassUtils;

import java.util.*;

/**
 * @ClassName ClassRoomService
 * @Description TODO
 * @Author uuorb
 * @Date 2020/12/144:42 上午
 * @Version 0.1
 **/

@Service
@Transactional
public class ClassRoomService {
  @Autowired
  ClassRoomService service;
  @Autowired
  ClassRoomMapper mapper;

  public List<ClassRoomBO> getClassByBuildId(int buildingId){
    List classList = new LinkedList();
    classList = mapper.getClassByBuildId(buildingId);
    return classList;
  }
  public List<ClassRoomBO> getClassByBuildIdAndFloor(int buildingId,int floor){
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
    List<String> buildingIdArr = new LinkedList<>();
    buildingIdArr = Arrays.asList(buildingIds.split("-"));
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
