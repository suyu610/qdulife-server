package qdu.life.exception;

/**
 * @ClassName GlobalExceptionHandler
 * @Description TODO
 * @Author uuorb
 * @Date 2021/3/172:09 下午
 * @Version 0.1
 **/

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import qdu.life.common.Result;
import qdu.life.utils.LogUtils;
import qdu.life.utils.ResultUtils;

import java.util.Enumeration;

@RestControllerAdvice
public class GlobalExceptionHandler {
  Logger log = LogUtils.getExceptionLogger();

  @ExceptionHandler(value = Exception.class)
  public Result defaultErrorHandler(HttpServletRequest request, final Exception e, HttpServletResponse response) throws Exception {
    response.setStatus(HttpStatus.BAD_REQUEST.value());
    log.error("==========================");
    log.error("发生异常:"+e.getMessage());
    log.error("URL:"+request.getRequestURL());
    Enumeration enu=request.getParameterNames();
    while(enu.hasMoreElements()){
      String paraName=(String)enu.nextElement();
      log.error(paraName+": "+request.getParameter(paraName));
    }
    log.error("==========================");
    return ResultUtils.errorException(e.getMessage());
  }
}
