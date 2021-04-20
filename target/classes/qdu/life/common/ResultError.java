package qdu.life.common;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serializable;

/**
 * @ClassName Error
 * @Author uuorb
 * @Date 2021/3/1710:58 下午
 * @Version 0.1
 * @Description
 *	200：表示成功
 *	500：表示错误，错误信息在msg字段中
 *	501：bean验证错误，不管多少个错误都以map形式返回
 *	502：拦截器拦截到用户token出错
 *	555：异常抛出信息
 **/
@Data
@AllArgsConstructor
public class ResultError implements Serializable {
  private String code;
  private String msg;

  public static ResultError INCOMPLETE_API_AUTH_INFO() {
    return new ResultError(ResultCode.UNAUTHORIZED.code(),"未授权");
  }
  public static ResultError SUCCESS(){
    return new ResultError(ResultCode.SUCCESS.code(),"成功");
  }

  public static ResultError INNER_ERROR(String msg){
    return new ResultError(ResultCode.INTERNAL_SERVER_ERROR.code(),msg);
  }
  public static ResultError EXCEPTION_INFO(String exception) {
    return new ResultError(ResultCode.EXCEPTION_ERROR.code(),exception);
  }


}
