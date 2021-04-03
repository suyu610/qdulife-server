package qdu.life.utils;

import qdu.life.common.Result;
import qdu.life.common.ResultCode;
import qdu.life.common.ResultError;

import java.io.Serializable;

/**
 * @ClassName ResultUtils
 * @Description TODO
 * @Author uuorb
 * @Date 2021/3/188:37 上午
 * @Version 0.1
 **/

public class ResultUtils implements Serializable {

  public static Result build(Integer status, String msg, Object data) {
    return new Result(data,new ResultError(ResultCode.SUCCESS.code(), msg));
  }

  public static Result ok(Object data) {
    return new Result(data,ResultError.SUCCESS());
  }

  public static Result ok() {
    return new Result(null,ResultError.SUCCESS());
  }

  public static Result errorMsg(String msg) {
    return new Result(null,ResultError.INNER_ERROR(msg));
  }

  public static Result errorException(String msg) {
    return new Result(null,ResultError.EXCEPTION_INFO(msg) );
  }

  public static Result unAuth() {
    return new Result(null,ResultError.INCOMPLETE_API_AUTH_INFO());
  }

}
