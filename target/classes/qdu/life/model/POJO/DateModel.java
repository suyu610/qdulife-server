package qdu.life.model.POJO;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import qdu.life.utils.DateUtils;

import java.io.Serializable;
import java.text.ParseException;
import java.util.*;

/**
 * @ClassName DateModel
 * @Description TODO
 * @Author uuorb
 * @Date 2020/12/143:36 下午
 * @Version 0.1
 **/
@Getter
@Setter
@ToString
public class DateModel implements Serializable {
  int whichWeek; // 第几周
  int week; // 星期几
  int seq; //第几节课

  public static DateModel getCurrentDate() throws ParseException {
    DateModel dateModel = new DateModel();

    String[] weekDays = {"星期日", "星期一", "星期二", "星期三", "星期四", "星期五", "星期六"};

    Calendar calendar=Calendar.getInstance();
    calendar.setFirstDayOfWeek(Calendar.MONDAY);

    // 开学时间
    Calendar endDate = Calendar.getInstance();

    // 用来获取当前第几节课
    int currentHour = endDate.get(Calendar.HOUR_OF_DAY);
    int currentMinute = endDate.get(Calendar.MINUTE);
    int seq = TimeInterval.getSequence(currentHour,currentMinute);

    dateModel.setWeek(calendar.get(Calendar.DAY_OF_WEEK)-1);
    dateModel.setWhichWeek(DateUtils.getWhichWeekByDate(endDate.getTime()));
    dateModel.setSeq(seq);

    return dateModel;
  }

  public static void main(String[] args) throws ParseException {
    System.out.println(DateModel.getCurrentDate());
  }
}
