package qdu.life.common;

import java.io.Serializable;

/**
 * @ClassName WeChatUrl
 * @Description TODO
 * @Author uuorb
 * @Date 2021/3/1812:04 下午
 * @Version 0.1
 **/

public enum WeChatUrl implements Serializable {

  JS_CODE_2_SESSION("https://api.weixin.qq.com/sns/jscode2session"),
  GET_ACCESS_TOKEN("https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid="),
  SEND_UNIFORM_MESSAGE("https://api.weixin.qq.com/cgi-bin/message/wxopen/template/uniform_send"),
  SUCCESS_TEMPLATE_ID("GcliekMoFzo37W3sB6TXYxMP-ptqiB7bFchzTdjCZaA");

  private String url;

  WeChatUrl() {
  }

  WeChatUrl(String url) {
    this.url = url;
  }

  public String getUrl() {
    return url;
  }

  public WeChatUrl setUrl(String url) {
    this.url = url;
    return this;
  }
}
