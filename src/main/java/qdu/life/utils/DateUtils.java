package qdu.life.utils;

import java.util.Calendar;
import java.util.Date;

/**
 * @ClassName DateUtils
 * @Description TODO
 * @Author uuorb
 * @Date 2020/12/143:35 下午
 * @Version 0.1
 **/

public class DateUtils {

  /**
   * date2比date1多的周数
   * @param date1
   * @param date2
   * @return
   */
  public static int differentWeeks(Date date1, Date date2) {
    Calendar cal1 = Calendar.getInstance();
    cal1.setTime(date1);

    Calendar cal2 = Calendar.getInstance();
    cal2.setTime(date2);
    int day1= cal1.get(Calendar.DAY_OF_YEAR);
    int day2 = cal2.get(Calendar.DAY_OF_YEAR);

    int year1 = cal1.get(Calendar.YEAR);
    int year2 = cal2.get(Calendar.YEAR);
    if(year1 != year2)   //不同一年
    {
      int timeDistance = 0 ;
      for(int i = year1 ; i < year2 ; i ++)
      {
        if(i%4==0 && i%100!=0 || i%400==0)    //闰年
        {
          timeDistance += 366;
        }
        else    //不是闰年
        {
          timeDistance += 365;
        }
      }
      return (timeDistance + (day2-day1))  /7 + 1;
    }
    else    //同一年
    {
      return (day2-day1) / 7 +1 ;
    }
  }

  // 由日期，转化为第几周
  public static int getWhichWeekByDate(Date date){
    Calendar startDate = Calendar.getInstance();
    // 开学时间
    startDate.set(2021, Calendar.MARCH,1);
    int whichWeek = DateUtils.differentWeeks(startDate.getTime(),date);
    System.out.println(whichWeek);

    if(whichWeek > 0 && whichWeek < 20){
      return whichWeek;
    }else{
      return -1;
    }
  }
}
