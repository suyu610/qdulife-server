package qdu.life.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.web.bind.annotation.*;
import qdu.life.common.Result;
import qdu.life.service.DialogService;
import qdu.life.service.UserService;
import qdu.life.utils.ResultUtils;

/**
 * @ClassName DialogController
 * @Description 进入index页面，
 * 首先getDialog，会返回id title content ，
 * 和本地存储的dialog-id进行判断，如果返回的id>本地id,则不弹窗，否则进行弹窗，
 * 当用户关闭后，将此id保存到本地，防止下一次弹出。
 * @Author uuorb
 * @Date 2020/12/2511:58 上午
 * @Version 0.1
 **/
@RestController
@RequestMapping("/v1/dialog")
public class DialogController {
  @Autowired
  private DialogService service;

  @Autowired
  private UserService userService;

  //  返回最新的公告
  @GetMapping("/getNewDialog")
  public Result getNewDialog(){
    return ResultUtils.ok(service.getNewDialog());
  }

  @GetMapping("/test")
  public Result test(){
    return  ResultUtils.ok(userService.getUserByOpenid("orGq35M1vYYqy38PADVDbq-Q-isw"));
  }
}

