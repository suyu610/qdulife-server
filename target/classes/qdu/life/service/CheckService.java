package qdu.life.service;

import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import qdu.life.BO.ClassRoomWithWholeInfo;
import qdu.life.BO.StatusBO;
import qdu.life.mapper.CheckMapper;

/**
 * @ClassName CheckService
 * @Description TODO
 * @Author uuorb
 * @Date 2021/3/214:32 下午
 * @Version 0.1
 **/

@Service
@Transactional
public class CheckService {
  @Autowired
  CheckMapper mapper;
  public String getFreeStatus(String className, int week, int whichweek) {
    return mapper.getFreeStatus(className,week,whichweek);
  }
  public String getFreeDetailStatus(String className, int week, int whichweek) throws Exception {
    // 将whichweek转为
    // _____ __1__ _____ __
    String whichweekStr = convertWhichWeekInt_To_Seq(whichweek);
    String[] seq = mapper.getFreeDetailStatus(className,week,whichweekStr);
    // 这里要转一下
    // 比如 000010 + 001001 = 001011
    //    00110 00000 000
    //    13位
    // 如果有相同的，说明在同一个教室，上两节课，那就太夸张了
    // 去他妈的
    long result = 0;

    for (String s : seq) {
      long a = Long.parseLong(s);
      System.out.println(a);
      result = a+result;
    }

    // 0 代表前面补充0
    // 4 代表长度为4
    // d 代表参数为正数型
    return String.format("%013d", result);
  }

  static String convertWhichWeekInt_To_Seq(int whichWeek) throws Exception {
    String s;
    switch (whichWeek){
      case 1:
        s = "1________________";
        break;
      case 2:
        s = "_1_______________";
        break;
      case 3:
        s = "__1______________";
        break;
      case 4:
        s = "___1_____________";
        break;
      case 5:
        s = "____1____________";
        break;
      case 6:
        s = "_____1___________";
        break;
      case 7:
        s = "______1__________";
        break;
      case 8:
        s = "_______1_________";
        break;
      case 9:
        s = "________1________";
        break;
      case 10:
        s = "_________1_______";
        break;
      case 11:
        s = "__________1______";
        break;
      case 12:
        s = "___________1_____";
        break;
      case 13:
        s = "____________1____";
        break;
      case 14:
        s = "_____________1___";
        break;
      case 15:
        s = "______________1__";
        break;
      case 16:
        s = "_______________1_";
        break;
      case 17:
        s = "________________1";
        break;
      default:
        throw new Exception("周有问题");
    }


    return s;

  }


}
