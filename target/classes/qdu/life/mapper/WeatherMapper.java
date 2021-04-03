package qdu.life.mapper;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
import qdu.life.BO.ClassRoomBO;
import qdu.life.BO.WeatherBO;

import java.util.Date;
import java.util.List;

/**
 * @ClassName WeatherMapper
 * @Description TODO
 * @Author uuorb
 * @Date 2020/12/234:23 上午
 * @Version 0.1
 **/
@Repository
public interface WeatherMapper {
  WeatherBO getTodayWeather();
  void insertNewWeather(WeatherBO weatherBO);
}
