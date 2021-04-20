package qdu.life.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import qdu.life.model.BO.User.UserLoginDeanBO;
import qdu.life.model.BO.User.WxUserInfoBO;
import qdu.life.common.Result;
import qdu.life.service.UserService;
import qdu.life.service.impl.WechatServiceImpl;
import qdu.life.utils.JwtTokenUtils;
import qdu.life.utils.ResultUtils;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;


/**
 * @ClassName UserController
 * @Description TODO
 * @Author uuorb
 * @Date 2021/3/123:13 上午
 * @Version 0.1
 **/

@RestController
@RequestMapping("/api/user")
public class UserController {
  @Autowired
  private UserService userService;
  @Autowired
  private WechatServiceImpl wechatService;

  @PostMapping("/logindean")
  public Result loginDean(@RequestBody UserLoginDeanBO userLoginDeanBO,HttpServletRequest request) throws Exception {
    String openid = (String)request.getAttribute("openid");
    //这里要把Cookie处理一下
    return ResultUtils.ok(userService.loginDean(userLoginDeanBO,openid));
  }

  // 在微信小程序启动时，用code登陆
  // 如果在微信服务器中，得到的是成功的openid,则返回他openid以及key
  // 否则就不是从微信小程序端正常访问
  @PostMapping("/login/{code}")
  public Result login(@PathVariable("code") String code)throws Exception{
    return userService.wxLogin(code);
  }
  //
  // 判断用户是否已经绑定好友，若已绑定，则返回-1，如果没有，则返回一串临时字符串。
  @GetMapping("/checkBindFriend")
  public Result checkBindFriend(HttpServletRequest request){
    String openid = (String)  request.getAttribute("openid");
    return ResultUtils.ok( userService.checkBindFriend(openid));
  }

  @GetMapping("/getCourse")
  public Result getCourse(HttpServletRequest request){
    String openid = (String)  request.getAttribute("openid");
    return ResultUtils.ok( userService.getCourseById(openid,41,1));
  }

  @GetMapping("/getAddCourse")
  public Result getAddCourse(HttpServletRequest request){
    String openid = (String)  request.getAttribute("openid");
    return ResultUtils.ok( userService.getAddCourseById(openid,41,1));
  }
  @GetMapping("dismissFriend")
  public Result dismissFriend(HttpServletRequest request){
    String openid = (String)  request.getAttribute("openid");
    userService.dismissFriend(openid);
    return ResultUtils.ok();
  }

  @PostMapping("/checkTokenAndBind")
  public Result checkTokenAndBind(HttpServletRequest request,@RequestBody String tokenStr){
    String openid = (String)  request.getAttribute("openid");
    JSONObject object= JSON.parseObject(tokenStr);
    String token = (String)object.get("token");
    Map<String,Object> map = JwtTokenUtils.getClaims(token);
    //  无效的token
    if(map == null){
      return ResultUtils.ok("-1");
    }
    String friendOpenid = (String) map.get("openid");

    return ResultUtils.ok( userService.bindFriend(openid,friendOpenid));
  }


  @GetMapping("/getCourse/friend")
  public Result getFriendCourse(HttpServletRequest request){
    String openid = (String)  request.getAttribute("openid");
    return ResultUtils.ok( userService.getFriendCourseById(openid,41,1));
  }

  @PostMapping("/updateInfo")
  public Result updateInfo(HttpServletRequest request, @RequestBody WxUserInfoBO wxUserInfoBO){
    String openid = (String)  request.getAttribute("openid");
    userService.updateInfo(openid,wxUserInfoBO.getNickname(),wxUserInfoBO.getAvatar());
    return ResultUtils.ok();
  }


  @GetMapping("/checkSubscribe/{hour}/{mins}")
  public Result checkSubscribe(HttpServletRequest request,@PathVariable("hour") int hour,@PathVariable("mins") int mins){
    String openid = (String)  request.getAttribute("openid");
    return ResultUtils.ok( userService.checkSubscribe(openid,hour,mins));
  }

  @GetMapping("closeAlarm")
  public Result closeAlarm(HttpServletRequest request){
    String openid = (String)  request.getAttribute("openid");
    return ResultUtils.ok( userService.closeAlarm(openid));
  }
}
