package qdu.life.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import qdu.life.common.Result;
import qdu.life.service.CheckService;
import qdu.life.service.DialogService;
import qdu.life.utils.ResultUtils;

/**
 * @ClassName CheckController
 * @Description TODO
 * @Author uuorb
 * @Date 2021/3/214:28 下午
 * @Version 0.1
 **/
@RestController
//@RequestMapping("/api/check")
public class CheckController {
  @Autowired
  private CheckService service;

//  @PostMapping("/status/{className}/{week}/{whichweek}")
  Result getFreeStatus(@PathVariable("className") String className, @PathVariable("week")int week,@PathVariable("whichweek") int whichweek){
    return ResultUtils.ok(service.getFreeStatus(className,week,whichweek));
  }

//  @PostMapping("/detail/{className}/{week}/{whichweek}")
  Result getFreeDetailStatus(@PathVariable("className") String className, @PathVariable("week")int week,@PathVariable("whichweek") int whichweek) throws Exception {
    return ResultUtils.ok(service.getFreeDetailStatus(className,week,whichweek));
  }


}
