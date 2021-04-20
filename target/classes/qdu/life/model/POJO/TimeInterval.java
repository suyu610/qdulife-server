package qdu.life.model.POJO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

/**
 * @ClassName TimeInterval
 * @Description TODO
 * @Author uuorb
 * @Date 2020/12/145:14 下午
 * @Version 0.1
 **/
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class TimeInterval implements Serializable {
  int seqIndex;
  int StartHour;
  int StartMinute;
  int EndHour;
  int EndMinute;
  static TimeInterval[] intervals = {
    new TimeInterval(1,6,0,8,45),
    new TimeInterval(2,8,45,9,40),
    new TimeInterval(3,9,40,10,40),
    new TimeInterval(4,10,40,11,35),
    new TimeInterval(5,11,35,12,30),
    new TimeInterval(6,12,30,14,15),
    new TimeInterval(7,14,15,15,10),
    new TimeInterval(8,15,10,16,10),
    new TimeInterval(9,16,10,17,05),
    new TimeInterval(10,17,05,18,0),
    new TimeInterval(11,18,0,19,15),
    new TimeInterval(12,19,15,20,10),
    new TimeInterval(13,20,10,21,5),
    new TimeInterval(14,21,5,22,30),
  };

  static int getSequence(int hour,int minute) {
    for (TimeInterval interval : intervals) {
      if (hour >= interval.getStartHour() &&
        minute >= (interval.getStartHour()-hour)*60 +  interval.getStartMinute() &&
        hour <= interval.getEndHour() &&
        minute <= (interval.getEndHour()-hour)*60 +  interval.getEndMinute()
      ) {
        return interval.getSeqIndex();
      }
    }
    return -1;
  }

  public static void main(String[] args) {
    System.out.println(getSequence(9,45));
  }
}
