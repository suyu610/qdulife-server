package qdu.life.controller;

import org.slf4j.Logger;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import qdu.life.common.Result;
import qdu.life.model.BO.User.AutoReplyPostDataBO;
import qdu.life.model.BO.User.AutoReplyTextMsgBO;
import qdu.life.utils.LogUtils;
import qdu.life.utils.MessageUtil;
import qdu.life.utils.ResultUtils;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.Map;

/**
 * @ClassName AutoReplyController
 * @Description TODO
 * @Author uuorb
 * @Date 2021/4/1011:58 上午
 * @Version 0.1
 **/
@RestController
@RequestMapping("/api/autoreply")
public class AutoReplyController {
  @GetMapping("/authorize")
  public String authorize(HttpServletRequest request){
    String signature = request.getParameter("signature");
    String timestamp = request.getParameter("timestamp");
    String echostr = request.getParameter("echostr");
    String nonce = request.getParameter("nonce");
    System.out.println(signature);
    System.out.println(timestamp);
    System.out.println(echostr);
    System.out.println(nonce);
    // 验证
    if (true) {
    // 验证正确之后，把echostr原封不动返回给微信就行了
      return echostr;
    } else {
    // 验证错误的话也要返回信息，告诉微信不要再尝试请求了，微信官方建议直接返回success字符串，当然返回空也是可以的
      return "success";
    }
  }

  @PostMapping("/authorize")
  public String autoreply(HttpServletRequest request){
    //1. 获取微信服务器发送的消息，转换成map对象
    Map<String, String> map = MessageUtil.parseXmlToMap(request);
    // 2. 获取详细的信息
    // 发送方帐号（open_id）
    String fromUserName = map.get("FromUserName");
    // 公众帐号
    String toUserName = map.get("ToUserName");
    // 消息类型
    String msgType = map.get("MsgType");
    // 消息内容
    String content = map.get("Content");
    // 消息id
    String msgId = map.get("MsgId");

    //3. 定义回复消息对象
    String respMsg = "";

    // 也可以用new，然后一个属性一个属性的set
    // 微信消息的基类
    //set属性值的时候，注意：ToUserName 和 FromUserName的值要反过来！是坑!是坑!是坑!
    AutoReplyPostDataBO msg = AutoReplyPostDataBO.builder().FromUserName(toUserName).ToUserName(fromUserName).MsgType(msgType).MsgId(Long.parseLong(msgId))
            .CreateTime(new Date().getTime()).build();

    if ("text".equals(msgType)){ //文本消息
      //要回复的消息内容

      String resultContent = "<a data-miniprogram-appid=\"wx607821b428f6d5d1\" data-miniprogram-path=\"pages/empty/empty?url=report\" href=\"https://www.qdu.life\">如果没能解决你的问题，点此处跟我们进行反馈哦</a>";
      AutoReplyTextMsgBO textMessage = AutoReplyTextMsgBO.adapt(msg);
      textMessage.setContent(resultContent);
      respMsg = MessageUtil.parseMsgToXml(textMessage, AutoReplyTextMsgBO.class);
    } else if ("image".equals(msgType)) { // 图片消息
      // todo 处理图片消息
    } else if ("voice".equals(msgType)) { //语音消息
      // todo 处理语音消息
    } else if ("video".equals(msgType)) { // 视频消息
      // todo 处理视频消息
    } else if ("shortvideo".equals(msgType)) { // 小视频消息
      // todo 处理小视频消息
    } else if ("location".equals(msgType)) { // 地理位置消息
      // todo 处理地理位置消息
    } else if ("link".equals(msgType)) { // 链接消息
      // todo 处理链接消息
    }

    return respMsg;
    }

}
