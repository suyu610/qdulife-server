package qdu.life.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import qdu.life.common.Result;
import qdu.life.service.WechatService;
import qdu.life.utils.ResultUtils;

import java.io.IOException;

/**
 * @ClassName WechatController
 * @Description TODO
 * @Author uuorb
 * @Date 2021/3/147:52 下午
 * @Version 0.1
 **/
@RestController
@RequestMapping("/api/wechat")
public class WechatController {
  @Autowired
  private WechatService service;
  @GetMapping("/accesstoken")
  public Result getLatestAccessToken() throws IOException {
    return ResultUtils.ok(service.getLatestAccessToken());
    //json
  }

  @GetMapping("/openid/{openid}")
  public Result getOpenid(@PathVariable("openid") String openid) {
    String result = service.getOpenid(openid);
    System.out.println(result);
    if(result=="none"){
      return ResultUtils.errorMsg("错误");
    }
    return ResultUtils.ok(result);
  }
}
