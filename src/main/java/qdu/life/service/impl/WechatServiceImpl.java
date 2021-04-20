package qdu.life.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.apache.http.HttpEntity;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import qdu.life.model.BO.System.AccessToken;
import qdu.life.common.WeChatUrl;
import qdu.life.mapper.WechatMapper;
import qdu.life.service.WechatService;
import qdu.life.utils.RedisUtil;

import java.io.IOException;

/**
 * @ClassName WechatService
 * @Description 放一些和微信相关的操作，比如发送模板消息，获取session
 * @Author uuorb
 * @Date 2021/3/147:01 下午
 * @Version 0.1
 **/

@Service
@Transactional

public class WechatServiceImpl implements WechatService {
  @Autowired
  WechatMapper wechatMapper;
  @Value("${weChat.appid}")
  private String appid;

  @Value("${weChat.secret}")
  private String secret;

  @Autowired
  private RedisUtil redisUtil;
  final String getOpenidUrl = "https://api.weixin.qq.com/sns/jscode2session?secret="+secret+"&grant_type=authorization_code&appid="+appid+"&js_code=";

  /*
    获取最近的AccessToken
   */
  public String getLatestAccessToken(){
    AccessToken token = wechatMapper.getLatestAccessToken();
    System.out.println(token);
    return token.getAccess_token();
  }

  /*
    @Params: 小程序端wx.login()返回的js_code
    @Return: 从微信服务端拿到openid，错误的返回值为none
   */
  public String getOpenid(String js_code){
    // 发送Get,等待返回值，
    CloseableHttpClient httpClient = HttpClientBuilder.create().build();

    // 创建Get请求
    HttpGet httpGet = new HttpGet(getOpenidUrl+js_code);
    // 响应模型

    CloseableHttpResponse response = null;

    try {
      // 由客户端执行(发送)Get请求
      response = httpClient.execute(httpGet);

      // 从响应模型中获取响应实体
      HttpEntity responseEntity = response.getEntity();
      if (responseEntity == null) {
        //
        throw new Exception("未获取到openid");
      }
      // 响应的内容
      String openidStr = EntityUtils.toString(responseEntity);
      JSONObject accessTokenJson = JSONObject.parseObject(openidStr);
      String openid = (String) accessTokenJson.get("openid");
      if(openid != null){
        return openid;
      }else{
        throw new Exception(openidStr);
      }
    }catch (ClientProtocolException e) {
      e.printStackTrace();
    } catch (IOException e) {
      e.printStackTrace();
    } catch (Exception e) {
      e.printStackTrace();
    }

    //  错误的返回值为none
    return "none";
  }

}
