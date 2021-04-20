package qdu.life.model.BO.Classroom;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import qdu.life.model.POJO.DateModel;

import java.io.Serializable;
import java.text.ParseException;

/**
 * @ClassName ClassRoomStatusPostDataBO
 * @Description 用户搜索的请求体
 * @Author uuorb
 * @Date 2021/4/68:53 下午
 * @Version 0.1
 **/
@Data
@AllArgsConstructor
@RequiredArgsConstructor
public class ClassRoomStatusPostDataBO implements Serializable {
  // 由-分割，要搜索的教学楼id
  String buildingIdList;
  // 哪一周
  int whichweek;
  // 周几
  int week;
  // true为显示空闲教室，false显示所有
  boolean onlyFree;
  // 无课区间 用一个13位的字符串来表示，你要搜索的区间 -> 00000 00000 000
  String expectStatus;
  int campuseId;

  // 把日期变为今天
  public static ClassRoomStatusPostDataBO getToday(String buildingIdList,  boolean onlyFree,String expectStatus,int campuseId) throws ParseException {
    DateModel dateModel = DateModel.getCurrentDate();
    int week = dateModel.getWeek();
    int whichweek = dateModel.getWhichWeek();
    // 如果是周日，则转为周天
    if(week == 0){
      week = 7;
    }
    return new ClassRoomStatusPostDataBO(buildingIdList,whichweek,week,onlyFree,expectStatus,campuseId);
  }

  // 把日期变为今天
  public static ClassRoomStatusPostDataBO getTomorrow(String buildingIdList,  boolean onlyFree,String expectStatus,int campuseId) throws ParseException {
    DateModel dateModel = DateModel.getCurrentDate();
    int week = dateModel.getWeek();
    int whichweek = dateModel.getWhichWeek();
    // 如果是周日，则转为周天
    if(week == 0){
      week = 7;
    }

    if(week == 7){
      week = 1;
      whichweek+=1;
    }else{
      week+=1;
    }

    return new ClassRoomStatusPostDataBO(buildingIdList,whichweek,week,onlyFree,expectStatus,campuseId);
  }
}
