package qdu.life.constant;

import java.util.concurrent.TimeUnit;

/**
 * @ClassName Status
 * @Description TODO
 * @Author uuorb
 * @Date 2020/12/2611:18 下午
 * @Version 0.1
 **/

public abstract  class Status {

  /**
   * 过期时间相关枚举
   */
  public static enum ExpireEnum{
    //未读消息的有效期为30天
    UNREAD_MSG(30L, TimeUnit.DAYS)
    ;

    /**
     * 过期时间
     */
    private Long time;
    /**
     * 时间单位
     */
    private TimeUnit timeUnit;

    ExpireEnum(Long time, TimeUnit timeUnit) {
      this.time = time;
      this.timeUnit = timeUnit;
    }

    public Long getTime() {
      return time;
    }

    public TimeUnit getTimeUnit() {
      return timeUnit;
    }
  }
}
