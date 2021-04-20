package qdu.life.utils;

import java.io.IOException;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.Scheduled;
import qdu.life.model.PO.WeatherPO;
import qdu.life.model.BO.WeatherApiResultBO;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.SocketTimeoutException;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.zip.GZIPInputStream;
@Configuration
public class WeatherUtils {

  public static String getTodayWeather()
    throws IOException {
    // 连接和风天气的API
    String url1 = "https://devapi.qweather.com/v7/weather/24h?location=101120202&key=cc64806b6d8a48859919cdd23fa9f21f";
    URL url = new URL(url1);
    URLConnection connectionData = url.openConnection();
    connectionData.setConnectTimeout(1000);
    BufferedReader br = null;
    try {
      br = new BufferedReader(new InputStreamReader(
        new GZIPInputStream(
          connectionData.getInputStream())
      ));

      StringBuilder sb = new StringBuilder();
      String line = null;
      while ((line = br.readLine()) != null) {
        sb.append(line);
      }

      String json = sb.toString();
      return json;
      //利用google的Gson解析，尝试用fastJson解析失败，因为fastJson解析时属性名开头必须小写
      //而和风api返回的json都是 "{"HeWeather6": "xxxx"}"
//      WeatherReportInfo weatherReportInfo = new Gson().fromJson(json, WeatherReportInfo.class);
//      return weatherReportInfo;
    } catch (SocketTimeoutException e) {
      throw new RuntimeException("连接超时");
    } finally {
    }
  }

  // 设置为定时
  // 每天的0:15分触发
  @Scheduled(cron = "0 28 3 * * ?")
  public static List<WeatherApiResultBO> getWeatherToDatabase() throws IOException {
    String responseStr = WeatherUtils.getTodayWeather();
    List<WeatherApiResultBO> list = new ArrayList<WeatherApiResultBO>();
    JSONObject outJson = JSONObject.parseObject(responseStr);
    if(outJson.get("code").equals("200")){
      JSONArray jsonArrayStr = outJson.getJSONArray("hourly");
      list = JSONObject.parseArray(jsonArrayStr.toString(), WeatherApiResultBO.class);
    }
    return list;
  }

//  weatherId
//suggest
//shorttext
//rain
//dayt
//nightt
//iconcode
//time
  public static WeatherPO test() throws IOException {
    WeatherPO weatherPO = new WeatherPO();
    //icon=150, fxTime=1608753600, pop=0, precip=0.0, dew=-5, humidity=76, text=晴, windSpeed=20, windScale=3-4)
    double lowTemp = 999;
    double highTemp = -99;
    double pop = 0;
    String suggest = "";
    List<WeatherApiResultBO> list = WeatherUtils.getWeatherToDatabase();
    for (WeatherApiResultBO weatherApiResultBO : list) {
      if(lowTemp> weatherApiResultBO.getTemp()){
        lowTemp = weatherApiResultBO.getTemp();
      }
      if(highTemp< weatherApiResultBO.getTemp()){
        highTemp = weatherApiResultBO.getTemp();
      }

      pop += weatherApiResultBO.getPop();
    }
    // 这里先简单的写一些建议
    if(pop/ list.size() != 0){
      suggest+="有可能下雨,记得带伞";
    }

    if(list.get(6).getText() == "多云"){
      suggest+="多云天气，正适合呆在教室学习";
    }

    if(lowTemp<=0){
      suggest+="有点冷，注意多穿衣服";
    }

    if(suggest.length() == 0) {
      suggest += "晴，祝你一天开心";
    }

    weatherPO.setRain(pop/list.size());
    weatherPO.setDayt(highTemp);
    weatherPO.setNightt(lowTemp);
    weatherPO.setSuggest(suggest);
    weatherPO.setShorttext(list.get(6).getText());
    weatherPO.setIconcode(list.get(6).getIcon());
    weatherPO.setCreatetime(new Date());
    return weatherPO;
  }
}
