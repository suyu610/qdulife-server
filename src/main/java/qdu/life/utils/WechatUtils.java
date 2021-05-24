package qdu.life.utils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;
import qdu.life.model.BO.System.MiniprogramResult;
import qdu.life.model.BO.Course.TemplateCourseBO;
import qdu.life.service.WechatService;

import java.util.*;

/**
 * @ClassName WechatUtils
 * @Description TODO
 * @Author uuorb
 * @Date 2021/3/303:00 下午
 * @Version 0.1
 **/

public class WechatUtils {
  @Autowired
  WechatService service;
  public static final String[] weekDays = {"星期日", "星期一", "星期二", "星期三", "星期四", "星期五", "星期六"};
  public static Calendar calendar = Calendar.getInstance();
  public static void main(String[] args) {
//    System.out.println(SendCheckSetClockWechatMsg(
//      "orGq35M1vYYqy38PADVDbq-Q-isw",
//      "43_UnmODST5rCC6u4hF9HUmYJoI-mP5lT1_1JRKqj2mL0ET7iGD7fMHxuRAl11BdX573DHgV68bTj6iH07t7i0AzV63bnRxYG5PACz_lIH02ETm5SQ3BUVodhStUpn86YrcwD2FvFNYZrzjjTWbEUJiAGASIX",
//      "22:00"));

    List<TemplateCourseBO> userCourseBOList = new LinkedList<>();
    SendTomorrowCourseClockWechatMsg(
      "皇甫素素",
      userCourseBOList,
      "orGq35M1vYYqy38PADVDbq-Q-isw",
      "43_XV_ARDBBKeJq2YGk3Fypu_-MftDh9FHY3KMrl2zpxNUTWw8ArQVEqwRr63od9xtR6CXxukRxdNj4a3f0cXVanWuI3BECl9C8jXM3wh6xfX9m5LEMHMh3kvkhWkqRyHLp7ZqoYpgYwXeO_NSxBQNaAHAUJG"
      );
  }

  // 明日课程提醒
  public static int SendTomorrowCourseClockWechatMsg(String username, List<TemplateCourseBO> userCourseBOList, String user_openid, String access_token ) {

    JSONObject params = new JSONObject();
    JSONObject first = new JSONObject();
    // 日期
    JSONObject keyword1 = new JSONObject();

    // 上课节次
    JSONObject keyword2 = new JSONObject();

    // 备注
    JSONObject remark = new JSONObject();

    StringBuffer firstStr = new StringBuffer(username+"同学,明天你有"+userCourseBOList.size()+"节课\r\n\r\n");
    // 遍历course_list,拼接字符串
    StringBuffer seq = new StringBuffer();

    for (TemplateCourseBO userCourse : userCourseBOList) {
      seq.append(userCourse.getSeq()+"节; ");
      if(userCourse.getDirection() == 1){
        firstStr.append(userCourse.getSeq()+"节\r\n"+userCourse.getClassName() +"    "+ userCourse.getClassName() + "  ←" + "\r\n\r\n");
      }else if(userCourse.getDirection() == 2){
        firstStr.append(userCourse.getSeq()+"节\r\n"+userCourse.getClassName()+"    "+ userCourse.getClassName()+"  →" +"\r\n\r\n");
      }else{
        firstStr.append(userCourse.getSeq()+"节\r\n"+userCourse.getClassName()+"  " + userCourse.getClassName()+"\r\n\r\n");
      }
    }

    first.put("value",firstStr.subSequence(0,firstStr.length()-4).toString());
    keyword1.put("value",weekDays[calendar.get(Calendar.DAY_OF_WEEK)]);
    keyword2.put("value",seq.toString());
    remark.put("value","点击查看附近的空教室、详细课表、关闭提醒");

    params.put("first",first);
    params.put("keyword1",keyword1);
    params.put("keyword2",keyword2);
    params.put("remark",remark);

    // 第三层
    JSONObject top_3 = new JSONObject();
    top_3.put("path","course");
    // 小程序的id
    top_3.put("appid","wx607821b428f6d5d1");

    // 第二层
    JSONObject top_2 = new JSONObject();
    // 这个是公众号的id
    top_2.put("appid","wxe6726f96155ab814");
    top_2.put("template_id","edyeePR1wbiM7lQtRqluz1a1kx1yQk_RQJKi5f_9o5Q");
    top_2.put("miniprogram",top_3);
    top_2.put("data",params);

    // 最顶层
    JSONObject top = new JSONObject();
    // 用户openid
    top.put("touser",user_openid);
    top.put("mp_template_msg",top_2);

    return sendUniformMessage(top.toJSONString(),access_token);
  }
    /*
   * @desc: 发送测试模版信息，如果发送失败，说明未关注公众号
   * @param
   *  clockTime : 用户自定义的提醒时间
   * @return
   *  0 -> 未关注公众号
   *  1 -> 关注了公众号
   */

  public static int SendCheckSetClockWechatMsg(String user_openid, String access_token , int hour,int mins ) {
    JSONObject params = new JSONObject();
    JSONObject first = new JSONObject();
    // 用户账号
    JSONObject keyword1 = new JSONObject();

    // 本次任务
    JSONObject keyword2 = new JSONObject();

    // 测试结果
    JSONObject keyword3 = new JSONObject();

    first.put("value","如果你能看到这条消息，说明成功开启课程推送");

    keyword1.put("value","本人");

    keyword2.put("value","设定提醒时间 : " + hour +"点"+mins+"分");
    keyword3.put("value","成功");

    params.put("first",first);
    params.put("keyword2",keyword2);
    params.put("keyword3",keyword3);

    // 第三层
    JSONObject top_3 = new JSONObject();
    top_3.put("path","course");
    // 小程序的id
    top_3.put("appid","wx607821b428f6d5d1");

    // 第二层
    JSONObject top_2 = new JSONObject();
    // 这个是公众号的id
    top_2.put("appid","wxe6726f96155ab814");
    top_2.put("template_id","GcliekMoFzo37W3sB6TXYxMP-ptqiB7bFchzTdjCZaA");
    top_2.put("miniprogram",top_3);
    top_2.put("data",params);

    // 最顶层
    JSONObject top = new JSONObject();
    // 用户openid
    top.put("touser",user_openid);
    top.put("mp_template_msg",top_2);

    return sendUniformMessage(top.toJSONString(),access_token);
  }

  /**
   * 发送统一模板消息
   *
   * @param token
   * @param template
   * @return
   *
   */
  public static int sendUniformMessage(String json,String access_token) {
    Logger logger = LogUtils.getPlatformLogger();
    logger.info("发送统一模板消息:" + json);
    int sendResult = 0;
    String requestUrl = "https://api.weixin.qq.com/cgi-bin/message/wxopen/template/uniform_send?access_token="+access_token;
    logger.info("发送统一模板消息到:" + requestUrl);
    RestTemplate restTemplate = new RestTemplate();
    HttpHeaders headers = new HttpHeaders();
    // 请勿轻易改变此提交方式，大部分的情况下，提交方式都是表单提交
    headers.setContentType(MediaType.APPLICATION_JSON_UTF8);

    HttpEntity<String> requestEntity = new HttpEntity<String>(json, headers);
    // 进行网络请求,访问url接口
    ResponseEntity<String> responseEntity = restTemplate.exchange(requestUrl, HttpMethod.POST, requestEntity,
      String.class);
    logger.info("发送统一模板消息后接受数据:" + responseEntity);
    if (responseEntity != null && responseEntity.getStatusCode() == HttpStatus.OK) {
      String sessionData = responseEntity.getBody();
      MiniprogramResult miniprogramResult =  JSON.parseObject(sessionData, MiniprogramResult.class);
      int errorCode = miniprogramResult.getErrcode();
      String errorMessage = miniprogramResult.getErrmsg();
      if (errorCode == 0) {
        sendResult = 1;
      } else {
        logger.error("模板消息发送失败:" + errorCode + "," + errorMessage);
      }
    }
    return sendResult;
  }
}
