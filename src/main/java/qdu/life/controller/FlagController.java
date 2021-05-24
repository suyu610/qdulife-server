package qdu.life.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.*;
import qdu.life.model.BO.Flag.FlagShowBO;
import qdu.life.common.Result;
import qdu.life.model.PO.FlagPO;
import qdu.life.service.FlagService;
import qdu.life.utils.ResultUtils;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;

/**
 * @ClassName FlagController
 * @Description TODO
 * @Author uuorb
 * @Date 2021/3/223:16 下午
 * @Version 0.1
 **/
@RestController
@RequestMapping("/v1/flag")
public class FlagController {

  @Autowired
  @Qualifier("FlagServiceImpl")
  private FlagService service;

  @GetMapping("/public/random")
  Result<FlagShowBO> getRandomPublicFlag(HttpServletRequest request){
    return ResultUtils.ok(service.getRandomPublicFlag((String) request.getAttribute("openid")));
  }

  @GetMapping("/public/all")
  Result getAllPublicFlag(HttpServletRequest request){
    return ResultUtils.ok(service.getAllPublicFlag((String) request.getAttribute("openid")));
  }

  @GetMapping("/private/get_all")
  Result GetAllPrivateFlag(HttpServletRequest request){
    String openid = (String)request.getAttribute("openid");
    return ResultUtils.ok(service.getAllPrivateFlag(openid));
  }

  @GetMapping("/private/delete/{flag_id}")
  Result DeleteFlag(HttpServletRequest request,@PathVariable("flag_id") int flag_id) throws Exception {
    String openid = (String)request.getAttribute("openid");
    return ResultUtils.ok(service.deleteFlag(openid,flag_id));
  }

  @PostMapping("/private/insert")
  Result insertPrivateFlag(HttpServletRequest request,@RequestBody FlagPO flagPO){
    String openid = (String)request.getAttribute("openid");
    flagPO.setOpenid(openid);
    flagPO.setCreateDate(new Date());

    return ResultUtils.ok(service.insertNewFlag(openid, flagPO));
  }

  @GetMapping("/public/togglelike/{flag_id}")
  Result toggleLikePublicFlag(HttpServletRequest request,@PathVariable("flag_id") String flag_id){
    String openid = (String)request.getAttribute("openid");
    service.toggleLikeFlag(openid,flag_id);
    return ResultUtils.ok();
  }

  // 改变公开性
  @GetMapping("/private/toggleprivacy/{flag_id}")
  Result togglePrivacyFlag(HttpServletRequest request,@PathVariable("flag_id") int flag_id) throws Exception {
    String openid = (String)request.getAttribute("openid");
    return ResultUtils.ok(service.togglePrivateFlag(openid,flag_id));
  }
}
