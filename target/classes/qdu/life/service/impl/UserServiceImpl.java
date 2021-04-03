package qdu.life.service.impl;

import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import qdu.life.BO.*;
import qdu.life.common.Result;
import qdu.life.mapper.UserMapper;
import qdu.life.service.UserService;
import qdu.life.service.WechatService;
import qdu.life.utils.*;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.DateFormatSymbols;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * @ClassName UserServiceImpl
 * @Description TODO
 * @Author uuorb
 * @Date 2021/3/1910:46 下午
 * @Version 0.1
 **/

@Service
@Configuration
public class UserServiceImpl implements UserService {
  @Value("${weChat.appid}")
  private String appid;

  @Value("${weChat.secret}")
  private String secret;

  @Autowired
  private RedisUtil redisUtil;

  @Autowired
  private UserMapper userMapper;
  @Autowired
  private WechatService wechatService;

  // 调用python服务器，登陆教务
  public String loginDean(UserLoginDeanBO loginDeanBO,String openid){
    String formatCookie = FormatUtils.getCookieValue(loginDeanBO.getCookie(),"JSESSIONID");
    System.out.println(openid);
    String urlStr = "http://localhost:5000/?num="+loginDeanBO.getNum()+"&pwd="+loginDeanBO.getPwd()+"&cha="+loginDeanBO.getCode()+"&cookie="+formatCookie +"&openid="+openid;
    String result = HttpUtils.sendGet(urlStr);

    // 那边去存储数据
    return result.toString();
  }

  // 通过openid获得这学期的课程
  public LinkedList<UserCourseBO> getCourseById(String openid, int year, int term){
    LinkedList<UserCourseBO> userCourseBOList = userMapper.getCourseByOpenid(openid,year,term);
    return userCourseBOList;
  }

  public LinkedList<UserCourseBO> getFriendCourseById(String openid, int year, int term){
    String friendOpenid = userMapper.getFriendOpenidByOpenid(openid);
    LinkedList<UserCourseBO> userCourseBOList = userMapper.getCourseByOpenid(friendOpenid,year,term);
    return userCourseBOList;
  }

  @Override
  public UserBO getUserByOpenid(String openid) {
    return userMapper.getUserByOpenid(openid);
  }

	@Override
	public int checkSubscribe(String openid,int hour,int mins) {
    int isSubscribe = WechatUtils.SendCheckSetClockWechatMsg(openid,wechatService.getLatestAccessToken(),hour,mins);
    // 如果订阅了，就设置为1
    // 然后设置个定时任务，每晚10：30，去检查一遍clock_on = 1的用户，并发送提醒。
    if(isSubscribe == 1){
      userMapper.setClock(openid,1);
    }else{
      userMapper.setClock(openid,0);
    }
		return isSubscribe;
	}

  @Override
  public int closeAlarm(String openid) {
    userMapper.setClock(openid,0);
    return 1;
  }

  @Override
  public String checkBindFriend(String openid) {
    // 首先判断relatedOpenid是否为空, 如果不为空，返回-1
    String relatedOpenid =  userMapper.getUserRelatedOpenid(openid);
    if(relatedOpenid != null){
      return "-1";
    }

    // 如果为空，则生成一个临时的token，这个token要能对应用户
    return JwtTokenUtils.generateBindCourseToken(openid);
  }

  @Override
  public void updateInfo(String openid, String nickname, String avatar) {
    userMapper.updateInfo(openid,nickname,avatar);
  }

  @Override
  public String bindFriend(String openid, String friendOpenid) {


    if(openid.equals(friendOpenid)){
      return "-2";
    }
    //  说明你已经有好友了
    if(userMapper.getUserRelatedOpenid(openid) != null){
      return "-3";
    }

    // 他有好友了
    if(userMapper.getUserRelatedOpenid(friendOpenid) != null){
      return "-4";
    }

    userMapper.bindFriend(openid,friendOpenid);
    userMapper.bindFriend(friendOpenid,openid);
    return userMapper.getUserRealNameByOpenid(friendOpenid);
  }

  // 每天22点执行
  @Scheduled(cron = "00 00 22 * * ?")
  public void circleToSendCourseAlarm(){
    Date today = new Date();
    // 明天是星期几
    // 设置为周日
    Calendar cal = Calendar.getInstance();
    boolean isFirstSunday = (cal.getFirstDayOfWeek() ==Calendar.SUNDAY);//获取周几
    cal.add(Calendar.DATE, 1);
    int tomorrowWeek = cal.get(Calendar.DAY_OF_WEEK);//若一周第一天为星期天，则-1
    if(isFirstSunday){
      tomorrowWeek= tomorrowWeek - 1;
      if(tomorrowWeek == 0){
        tomorrowWeek= 7;
      }
    }

    // 明天是第几周 => ______1______
    int whichWeekIndex = DateUtils.getWhichWeekByDate(today);

    // 如果明天是周一，则周数要+1
    // 这里要小心bug，以后再弄吧。。。。。

    if(tomorrowWeek == 1) whichWeekIndex+=1;

    // 由第几周转化为mysql的模糊查询
    StringBuffer whichWeekStrBuffer = new StringBuffer("_________________");
    whichWeekStrBuffer.replace(whichWeekIndex-1, whichWeekIndex, "1");

    // 首先检查所有user中clock_on为1的
    List<UserBO> openClockUserList;
    openClockUserList = userMapper.getAllUserOpenidOpenClock();
    System.out.println(openClockUserList);
    // 应该遍历然后拼接数据，然后发送消息
    for (UserBO userBO:openClockUserList){
      String openid = userBO.getOpenid();
      // 然后根据openid去找课程
      List<TemplateCourseBO> userCourseBOList = new LinkedList<>();
      userCourseBOList = userMapper.getTemplateCourseByOpenid(openid,tomorrowWeek,whichWeekStrBuffer.toString(),41,1);

      // 如果课程数为0，就不发送了
      if(userCourseBOList.size() == 0) continue;

      // 将课序 => 人话，这里要注意t1,t2的影响
      String transSeqArr[] = {"1","2","3","4","t1","5","6","7","8","t2","9","10"};
      // 0000011000  => 第3-4节
      int courseIndex = 0;

      for (TemplateCourseBO templateCourseBO: userCourseBOList){
        String oldSeq = templateCourseBO.getSeq();
        int firstSeqIndex = oldSeq.indexOf('1');
        int secondSeqIndex = oldSeq.lastIndexOf('1');
        userCourseBOList.get(courseIndex).setSeq(transSeqArr[firstSeqIndex] + '-' + transSeqArr[secondSeqIndex]);
        userCourseBOList.get(courseIndex).setStartIndex(firstSeqIndex);
        courseIndex++;
      }

//      userCourseBOList.stream().sorted(Comparator.comparing(TemplateCourseBO::getStartIndex).reversed());

      userCourseBOList.sort(Comparator.comparing(TemplateCourseBO::getStartIndex));


      WechatUtils.SendTomorrowCourseClockWechatMsg(userBO.getReal_name(),userCourseBOList,openid,wechatService.getLatestAccessToken());
    }
  }

  // 每天的7：30，9：45 ，13：20，15：20，18：20提醒
  //  @Scheduled(cron = "0 0 22 * * ?")
//  public void circleToSendCourseAlarmSeq(){
//    Date today = new Date();
//    // 明天是星期几
//    // 设置为周日
//    Calendar cal = Calendar.getInstance();
//    boolean isFirstSunday = (cal.getFirstDayOfWeek() ==Calendar.SUNDAY);//获取周几
//    int todayWeek = cal.get(Calendar.DAY_OF_WEEK);//若一周第一天为星期天，则-1
//    if(isFirstSunday){
//      todayWeek= todayWeek - 1;
//      if(todayWeek == 0){
//        todayWeek= 7;
//      }
//    }
//
//    // 今天是第几周 => ______1______
//    int whichWeekIndex = DateUtils.getWhichWeekByDate(today);
//
//    // 由第几周转化为mysql的模糊查询
//    StringBuffer whichWeekStrBuffer = new StringBuffer("_________________");
//    whichWeekStrBuffer.replace(whichWeekIndex-1, whichWeekIndex, "1");
//
//    // 首先检查所有user中clock_on为1的
//    List<UserBO> openClockUserList;
//    openClockUserList = userMapper.getAllUserOpenidOpenClock();
//    System.out.println(openClockUserList);
//    // 应该遍历然后拼接数据，然后发送消息
//    for (UserBO userBO:openClockUserList){
//      //
//      String openid = userBO.getOpenid();
//      // 然后根据openid去找课程
//      List<TemplateCourseBO> userCourseBOList = new LinkedList<>();
//      userCourseBOList = userMapper.getTemplateCourseByOpenid(openid,tomorrowWeek,whichWeekStrBuffer.toString(),41,1);
//
//      // 如果课程数为0，就不发送了
//      if(userCourseBOList.size() == 0) continue;
//
//      // 将课序 => 人话，这里要注意t1,t2的影响
//      String transSeqArr[] = {"1","2","3","4","t1","5","6","7","8","t2","9","10"};
//      // 0000011000  => 第3-4节
//      int courseIndex = 0;
//
//      for (TemplateCourseBO templateCourseBO: userCourseBOList){
//        String oldSeq = templateCourseBO.getSeq();
//        int firstSeqIndex = oldSeq.indexOf('1');
//        int secondSeqIndex = oldSeq.lastIndexOf('1');
//        userCourseBOList.get(courseIndex).setSeq(transSeqArr[firstSeqIndex] + '-' + transSeqArr[secondSeqIndex]);
//        courseIndex++;
//      }
//
//      WechatUtils.SendTomorrowCourseClockWechatMsg(userBO.getReal_name(),userCourseBOList,openid,wechatService.getLatestAccessToken());
//    }
//  }



	// 微信登陆请求，如果成功，则返回token
  public Result wxLogin(String code) throws Exception {
    JSONObject sessionInfo = JSONObject.parseObject(jcode2Session(code));
    Assert.notNull(sessionInfo, "code 无效");
    //   保证不出现 errcode=!0 的情况
    Assert.isTrue(null == sessionInfo.getInteger("errcode") || 0 != sessionInfo.getInteger("errcode"), sessionInfo.getString("errmsg"));
    String openid = sessionInfo.getString("openid");
    // 首先判断数据库中有没有这个openid
    boolean isExistOpenid = userMapper.isExistOpenid(openid);

    //  不存在则创建
    if(!isExistOpenid) userMapper.insertNewUser(openid, new Date());

    //  拿到生成token的UserTokenBO
    UserTokenBO user = userMapper.getUserTokenBoByOpenid(openid);
    // 生成token
    String token = JwtTokenUtils.generateToken(user);
    // 在redis中插入{ openid : token }
    redisUtil.set(openid,token);
    String relatedOpenid =  user.getRelatedOpenid();

    // 返回token和avatar和nickname
    HashMap resultMap = new HashMap();
    resultMap.put("avatar",user.getAvatar_url());
    resultMap.put("nickname",user.getNickname());
    resultMap.put("token",token);
    if(relatedOpenid != null){
      String friendReal_name = userMapper.getUserRealNameByOpenid(relatedOpenid);
      if(friendReal_name == null){
        friendReal_name = "他/她未导入教务课表";
      }
      resultMap.put("friend_realname",friendReal_name);
    }else{
      resultMap.put("friend_realname","");
    }
    return ResultUtils.ok(resultMap);
  }

  /**
   * 登录凭证校验
   * @param code,来自于微信端
   * @return (openid 和 session_key)
   * @throws Exception
   */
  private String jcode2Session(String code)throws Exception{
    String sessionInfo = Jcode2SessionUtil.jscode2session(appid,secret,code,"authorization_code");//登录grantType固定
    return sessionInfo;
  }
}
