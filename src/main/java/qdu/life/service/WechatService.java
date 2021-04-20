package qdu.life.service;

public interface WechatService{

  public String getLatestAccessToken();

  public String getOpenid(String js_code);
}
