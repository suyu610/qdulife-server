package qdu.life.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import qdu.life.model.PO.WeatherPO;
import qdu.life.mapper.WeatherMapper;
//import qdu.life.utils.RedisUtil;
import qdu.life.utils.WeatherUtils;

import java.io.IOException;
import java.util.Date;

/**
 * @ClassName WeatherService
 * @Description TODO
 * @Author uuorb
 * @Date 2020/12/234:07 上午
 * @Version 0.1
 **/
@Service
@Transactional
public class WeatherService {

  @Autowired
  WeatherMapper mapper;

  public WeatherPO getTodayWeather() throws IOException {
    WeatherPO weatherPO = mapper.getTodayWeather();
    // 说明今天还没采集
    if(weatherPO == null){
      Date date = new Date();
      System.out.println(date);
      mapper.insertNewWeather(WeatherUtils.test());
      weatherPO = mapper.getTodayWeather();
    }
    return mapper.getTodayWeather();
  }

  public void insertNewWeather() throws IOException {
    mapper.insertNewWeather(WeatherUtils.test());
  }
}
