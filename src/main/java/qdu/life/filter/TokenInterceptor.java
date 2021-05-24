package qdu.life.filter;

import com.alibaba.fastjson.JSON;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.util.Assert;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;
import qdu.life.utils.JwtTokenUtils;
import qdu.life.utils.RedisUtil;
import qdu.life.utils.ResultUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

/**
 * @ClassName OpenidInterceptor
 * @Description TODO
 * @Author uuorb
 * @Date 2021/3/1710:46 下午
 * @Version 0.1
 **/

public class TokenInterceptor implements HandlerInterceptor {
  @Autowired
  RedisUtil redisUtil;

  @Value("${token.expirationMilliSeconds}")
  private long expirationMilliSeconds;

  @Override
  // 在请求前使用
  // 只有当此项为True,才能访问
  public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object o) throws Exception {
    String authHeader = request.getHeader("Authorization");
    response.setCharacterEncoding("utf-8");

    if (null == authHeader || !authHeader.startsWith("Bearer ")){
      response.setContentType("application/json;charset=utf-8");
      response.getWriter().write(ResultUtils.unAuth().toString());
      return false;
    }

    String authToken = authHeader.substring("Bearer ".length());

    Map<String,Object> map = JwtTokenUtils.getClaims(authToken);
    // 获取在token中的subject，也就是openid
    String openid = (String) map.get("openid");
    String role = (String) map.get("role");
    // token中，解析不到openid,role
    Assert.notNull(openid,ResultUtils.unAuth().toString());
    Assert.notNull(role,ResultUtils.unAuth().toString());

    // 如果redis里不存在这个key , 说明已过期或者是伪造的
    if (!redisUtil.hasKey(openid)){
      response.getWriter().write(ResultUtils.unAuth().toString());
      return false;
    }
    // 如果存在，就要对比一下token和redis中存的token,获取redius缓存中的信息，根据key(openid)去取

    String redisToken = redisUtil.get(openid).toString();

    //  他妈的，这里应该写成equal 别写成==了，这个是比较引用的,21年3月19日 1:07am
    if (!redisToken.equals(authToken)){
      response.getWriter().write(JSON.toJSONString(ResultUtils.unAuth()));
      return false;
    }

    // 从token中获取值
    // 如果学号不为空，就给他设置role为student,
    // 如果为空，则为 anonymous

    if (map.get("ssNumber") == null){
      request.setAttribute("role","anonymous");
    }else{
      request.setAttribute("role","student");
    }

    request.setAttribute("userid",map.get("userid"));
    request.setAttribute("ssNumber",map.get("ssNumber"));
    request.setAttribute("openid",map.get("openid"));


    //更新token过期时间
    redisUtil.setKeyExpire(authToken,expirationMilliSeconds);

    return true;

  }

  @Override
  public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {

  }

  @Override
  public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {

  }
}
