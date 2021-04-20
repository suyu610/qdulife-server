package qdu.life.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @ClassName FormatUtils
 * @Description TODO
 * @Author uuorb
 * @Date 2021/3/175:42 上午
 * @Version 0.1
 **/

public class FormatUtils {
  /**
   * 获取cookie的值
   * @param cookies
   * 类似这样的字符串：
   * 	c=1;b=12;a=123
   * @param key
   * @return
   */
  public static String getCookieValue(String cookies, String key) {
    Pattern pattern = Pattern.compile(key + "[\\s]*=[\\s]*([^;\\s]{1,})");
    Matcher matcher = pattern.matcher(cookies);
    if(matcher.find()){
      if(matcher.groupCount() == 1){
        return matcher.group(1);
      }
    }
    return null;
  }
}
