package qdu.life.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import qdu.life.common.Result;
import qdu.life.service.WeatherService;
import qdu.life.utils.ResultUtils;
//import qdu.life.utils.RedisUtil;


import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * @ClassName weatherController
 * @Description TODO
 * @Author uuorb
 * @Date 2020/12/233:29 上午
 * @Version 0.1
 **/

@RestController
@RequestMapping("/v1/weather")
public class WeatherController {

  @Autowired
  private WeatherService service;

  @GetMapping("/today")
  public Result getTodayWeather() throws IOException {
    return ResultUtils.ok(service.getTodayWeather());
    //json
  }


  @GetMapping("/insert")
  public void insertTodayWeather() throws IOException {
    service.insertNewWeather();
  }

}
