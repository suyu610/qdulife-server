package qdu.life.utils;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 * @ClassName DateUtil
 * @Description TODO
 * @Author uuorb
 * @Date 2020/12/231:53 上午
 * @Version 0.1
 **/

public class DateUtil {
  /**
   * 日期格式转换yyyy-MM-dd'T'HH:mm:ss (yyyy-MM-dd'T'HH:mm:ss.SSSZ) TO  yyyy-MM-dd HH:mm:ss
   * 2020-04-09T23:00:00.000+08:00 TO 2020-04-09 23:00:00
   * 2020-12-23T00:35     +08:00
   *
   * @throws ParseException
   */
  //  [old] 2020-12-23T02:00+08:00
  //  [new]
  public static String dealDateFormat(String oldDateStr) throws ParseException, ParseException {
    DateFormat df = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm");  //yyyy-MM-dd'T'HH:mm
    Date date = df.parse(oldDateStr);
    DateFormat df2 = new SimpleDateFormat("yyyy-MM-dd HH:mm");
    return df2.format(date);
  }

  public static String date2TimeStamp(String dateStr) throws ParseException {
    String timeStamp = "";
    DateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
    timeStamp = String.valueOf(sdf.parse(dateStr).getTime()/1000);
    return timeStamp;
  }

  public static String timeStampToDate(String timeStamp){
    String date = "";
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
    return sdf.format(new Date(Long.valueOf(timeStamp+"000")));
  }

  /**
   * 日期格式转换 yyyy-MM-dd HH:mm:ss  TO yyyy-MM-dd'T'HH:mm:ss.SSSXXX  (yyyy-MM-dd'T'HH:mm:ss.SSSZ)
   * 2020-04-09 23:00:00 TO 2020-04-09T23:00:00.000+08:00
   * @throws ParseException
   */
  public static String dealDateFormatReverse(String oldDateStr) throws ParseException{
    DateFormat df = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
    DateFormat df2 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    Date date1 =  df2.parse(oldDateStr);
    return df.format(date1);
  }

  public static void main(String[] args) throws ParseException {
    System.out.println(DateUtil.dealDateFormat("2020-12-23T02:00+08:00"));
    System.out.println(DateUtil.date2TimeStamp(DateUtil.dealDateFormat("2020-12-23T02:00+08:00")));
    System.out.println(DateUtil.timeStampToDate(DateUtil.date2TimeStamp(DateUtil.dealDateFormat("2020-12-23T02:00+08:00"))));
  }
}
