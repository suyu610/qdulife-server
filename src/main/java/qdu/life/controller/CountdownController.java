package qdu.life.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import qdu.life.common.Result;
import qdu.life.service.CountdownService;
import qdu.life.utils.ResultUtils;

/**
 * @ClassName CountdownController
 * @Description TODO
 * @Author uuorb
 * @Date 2021/3/313:25 上午
 * @Version 0.1
 **/
@RestController
@RequestMapping("/v1/countdown")
public class CountdownController {
  @Autowired
  @Qualifier("CountdownServiceImpl")
  private CountdownService service;
  //  返回在今天以后的倒计时
  @GetMapping("/get/after_today")
  public Result getNewDialog(){
    return ResultUtils.ok(service.getAllAfterTodayCountdown());
  }

}
