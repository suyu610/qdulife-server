package qdu.life.mapper;

import org.springframework.stereotype.Repository;
import qdu.life.model.PO.WeatherPO;

/**
 * @ClassName WeatherMapper
 * @Description TODO
 * @Author uuorb
 * @Date 2020/12/234:23 上午
 * @Version 0.1
 **/
@Repository
public interface WeatherMapper {
  WeatherPO getTodayWeather();
  void insertNewWeather(WeatherPO weatherPO);
}
