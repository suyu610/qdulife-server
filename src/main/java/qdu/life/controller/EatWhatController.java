package qdu.life.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import qdu.life.common.Result;
import qdu.life.service.EatService;
import qdu.life.utils.ResultUtils;

import javax.servlet.http.HttpServletRequest;

/**
 * @ClassName EatWhatController
 * @Description TODO
 * @Author uuorb
 * @Date 2021/5/24 6:18 上午
 * @Version 0.1
 **/
@RestController
@RequestMapping("/v1/eat")
public class EatWhatController {
  @Autowired
  EatService service;
  @GetMapping("allfood")
  Result GetAllFood(){
    return ResultUtils.ok(service.getAllFood());
  }
}
