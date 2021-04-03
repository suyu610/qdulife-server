package qdu.life.utils;

/**
 * @ClassName LogUtils
 * @Description TODO
 * @Author uuorb
 * @Date 2021/3/1710:23 下午
 * @Version 0.1
 **/

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
/**
 * 本地日志参考类
 * @author Administrator
 *
 */
public class LogUtils {


  /**
   * 获取业务日志logger
   *
   * @return
   */
  public static Logger getBussinessLogger() {
    return LoggerFactory.getLogger(LogEnum.BUSSINESS.getCategory());
  }

  /**
   * 获取平台日志logger
   *
   * @return
   */
  public static Logger getPlatformLogger() {
    return LoggerFactory.getLogger(LogEnum.PLATFORM.getCategory());
  }

  /**
   * 获取数据库日志logger
   *
   * @return
   */
  public static Logger getDBLogger() {
    return LoggerFactory.getLogger(LogEnum.DB.getCategory());
  }


  /**
   * 获取异常日志logger
   *
   * @return
   */
  public static Logger getExceptionLogger() {
    return LoggerFactory.getLogger(LogEnum.EXCEPTION.getCategory());
  }


}
