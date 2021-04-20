package qdu.life.common;

/**
 * @ClassName ResultCode
 * @Description TODO
 * @Author uuorb
 * @Date 2021/3/188:42 上午
 * @Version 0.1
 **/


import java.io.Serializable;

/**
 * 响应码枚举
 */
public enum ResultCode implements Serializable {
  SUCCESS("200"),               //成功
  FAIL("400"),                  //失败
  UNAUTHORIZED("502"),          //未认证（签名错误）
  NOT_FOUND("404"),             //接口不存在
  INTERNAL_SERVER_ERROR("500"), //服务器内部错误
  EXCEPTION_ERROR("555");       //异常

  private final String code;

  ResultCode(String code) {
    this.code = code;
  }

  public String code() {
    return code;
  }

}
