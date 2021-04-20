package qdu.life.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import qdu.life.model.BO.Course.ScheduleBO;
import qdu.life.mapper.ClassRoomMapper;
import qdu.life.mapper.RoomScheduleMapper;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

/**
 * @ClassName RoomScheduleService
 * @Description TODO
 * @Author uuorb
 * @Date 2021/3/109:08 下午
 * @Version 0.1
 **/

@Service
public class RoomScheduleService {

  @Autowired
  RoomScheduleMapper mapper;
  @Autowired
  ClassRoomMapper statusMapper;
  // 获得某个教室，一周的课表
  // 这里要同时把status的表放进去，因为有可能这个表比教室的表多
  // 多的话，就写为未知。
  public HashMap singleRoomAndSingleWeek(String roomname,int whichWeek){
    StringBuffer whichWeekStrBuffer = new StringBuffer("_________________");
    whichWeekStrBuffer.replace(whichWeek-1, whichWeek, "1");
    // 从数据库中拿到该教室的这一周的【 课表 】
    List<ScheduleBO> scheduleList = mapper.getCourseByRoomAndWhichWeek(roomname,whichWeekStrBuffer.toString());
    List<ScheduleBO> status = new LinkedList<>();
    // 从数据库中，拿到该教室这一周的【 状态表 】
    for (int i = 1;i<=7;i++) {
      status.add(new ScheduleBO(i,statusMapper.getFreeStatus(roomname, i , String.valueOf(whichWeek)),"未知","未知"));
    }

    HashMap returnMap = new HashMap();
    // 把他们混合在一起，让状态表比课表多出来的课，显示为未知
    returnMap.put("detail",RoomScheduleService.convertScheduleListToHashMap(scheduleList,status));
    // 解析这个列表，形成符合接口要求的数据
    return returnMap;
  }

  static public HashMap convertScheduleListToHashMap(List<ScheduleBO> scheduleList,List<ScheduleBO> statusList){
    HashMap<Integer,LinkedList> courseWeek = new HashMap<Integer,LinkedList>();
    for (int week=1;week<=7;week++) {
      courseWeek.put(week,new LinkedList());
      System.out.println(statusList.get(week-1));
      courseWeek.get(week).add(statusList.get(week-1));
    }

    for (ScheduleBO bo: scheduleList) {
      courseWeek.get(bo.getWeek()).add(bo);
    }

    return courseWeek;
  }

  public static void main(String[] args) {
    StringBuffer whichWeekStrBuffer = new StringBuffer("_________________");
    whichWeekStrBuffer.replace(4, 4+1, "1");
    System.out.println(whichWeekStrBuffer.toString());
  }

}
