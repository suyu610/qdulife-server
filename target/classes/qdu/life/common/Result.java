package qdu.life.common;

import lombok.Data;

/**
 * @Description : 自定义响应数据结构
 * 				这个类是提供给门户，ios，安卓，微信商城用的
 * 				门户接受此类数据后需要使用本类的方法转换成对于的数据类型格式（类，或者list）
 * 				其他自行处理,它主要是做一些响应的状态以及消息进行返回，并且你也需要包装一些自己的的数据，都是没有问题，
 */

import com.alibaba.fastjson.JSON;
import java.io.Serializable;

@Data

public class Result<T> implements Serializable {
  private T data;
  ResultError status;

  public Result( T data,ResultError status) {
    this.status = status;
    this.data = data;
  }

  @Override
  public String toString() {
    return JSON.toJSONString(this);
  }
}
