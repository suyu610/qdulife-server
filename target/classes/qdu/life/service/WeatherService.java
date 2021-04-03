package qdu.life.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import qdu.life.BO.WeatherBO;
import qdu.life.mapper.ClassRoomMapper;
import qdu.life.mapper.WeatherMapper;
//import qdu.life.utils.RedisUtil;
import qdu.life.utils.WeatherUtils;

import javax.annotation.Resource;
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

  public WeatherBO getTodayWeather() throws IOException {
    WeatherBO weatherBO = mapper.getTodayWeather();
    // 说明今天还没采集
    if(weatherBO == null){
      Date date = new Date();
      System.out.println(date);
      mapper.insertNewWeather(WeatherUtils.test());
      weatherBO = mapper.getTodayWeather();
    }
    return mapper.getTodayWeather();
  }

  public void insertNewWeather() throws IOException {
    mapper.insertNewWeather(WeatherUtils.test());
  }
}
