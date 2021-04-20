package qdu.life.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.*;
import qdu.life.model.BO.Course.PublicCourseBO;
import qdu.life.common.Result;
import qdu.life.service.PublicCourseService;
import qdu.life.utils.ResultUtils;

import javax.servlet.http.HttpServletRequest;

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
  public Result getCourseByName(@PathVariable("course_name") String course_name,@PathVariable("indexPage") int indexPage,@PathVariable("seq")int seq,@PathVariable("week")int week,@PathVariable("campus_id")int campus_id){
    return ResultUtils.ok(service.getCourseByName(course_name,seq,week,campus_id,indexPage));
  }
  @PostMapping("/addPublicCourse")
  public Result addPublicCourse(@RequestBody PublicCourseBO publicCourseBO, HttpServletRequest request){
    String openid = (String)request.getAttribute("openid");
    service.insertPublicCourse(openid,publicCourseBO);
    return ResultUtils.ok();
  }
}
