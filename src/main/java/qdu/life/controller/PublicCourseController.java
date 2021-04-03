package qdu.life.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import qdu.life.common.Result;
import qdu.life.service.PublicCourseService;
import qdu.life.service.WeatherService;
import qdu.life.utils.ResultUtils;

import java.io.IOException;

/**
 * @ClassName PublicCourseController
 * @Description TODO
 * @Author uuorb
 * @Date 2021/3/293:42 下午
 * @Version 0.1
 **/
@RestController
@RequestMapping("/api/course")
public class PublicCourseController {
  @Autowired
  @Qualifier("PublicCourseServiceImpl")
  private PublicCourseService service;

  /*
    seq:0123,0为全天，1上午 2下午 3晚上
    week: 0为不筛选星期
   */
  @GetMapping("/getCourseByName/{course_name}/{seq}/{week}/{campus_id}/{indexPage}")
  public Result getTodayWeather(@PathVariable("course_name") String course_name,@PathVariable("indexPage") int indexPage,@PathVariable("seq")int seq,@PathVariable("week")int week,@PathVariable("campus_id")int campus_id){
    return ResultUtils.ok(service.getCourseByName(course_name,seq,week,campus_id,indexPage));
  }
}
