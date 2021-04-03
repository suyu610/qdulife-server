package qdu.life.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageHelper;
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
import qdu.life.BO.AccessToken;
import qdu.life.common.WeChatUrl;
import qdu.life.mapper.WechatMapper;
import qdu.life.service.WechatService;
import qdu.life.utils.RedisUtil;

import javax.xml.crypto.Data;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Date;
import java.util.List;

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
  // 毫秒
  // 90分钟执行一次
  // 从微信服务器中，获取AccessToken,并存到数据库中
  @Scheduled(fixedRate = 1000 * 60 * 90)
  public void getAccessTokenFromWechatServer(){
    // 发送Get,等待返回值，
    CloseableHttpClient httpClient = HttpClientBuilder.create().build();
    // 创建Get请求
    HttpGet httpGet = new HttpGet(WeChatUrl.GET_ACCESS_TOKEN.getUrl()+appid+"&secret="+secret);
    // 响应模型
    CloseableHttpResponse response = null;
    try {
      // 由客户端执行(发送)Get请求
      response = httpClient.execute(httpGet);

      // 从响应模型中获取响应实体
      HttpEntity responseEntity = response.getEntity();
      if (responseEntity != null) {
        // 响应的内容
        String tokenStr = EntityUtils.toString(responseEntity);
        JSONObject accessTokenJson = JSONObject.parseObject(tokenStr);
        AccessToken accessToken = JSON.toJavaObject(accessTokenJson,AccessToken.class);
        if(accessToken.getAccess_token() == null) throw new Exception();
        // 存入数据库，自增id
          wechatMapper.insertNewAccessToken(accessToken);
      }else{
        // 报错
        // todo:发送短信给我
        // Util.sendSMS("获取AccessToken失效")
      }
    }catch (ClientProtocolException e) {
      e.printStackTrace();
    } catch (IOException e) {
      e.printStackTrace();
    } catch (Exception e) {
      e.printStackTrace();
    }
  }
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
