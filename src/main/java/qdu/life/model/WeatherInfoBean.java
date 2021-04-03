package qdu.life.model;


import lombok.Data;
import lombok.Setter;
import qdu.life.utils.DateUtil;

import java.io.Serializable;
import java.text.ParseException;
import java.util.Date;

/**
 * @ClassName WeatherInfo
 * @Description TODO
 * @Author uuorb
 * @Date 2020/12/231:35 上午
 * @Version 0.1
 **/
@Data
public class WeatherInfoBean implements Serializable {
/**
 * Copyright 2020 json.cn
 */

  /**
   * Auto-generated: 2020-12-23 1:34:7
   *
   * @author json.cn (i@json.cn)
   * @website http://www.json.cn/java2pojo/
   */

    // 温度
    private double temp;
    private int icon;
    // 时间戳
    private String fxTime;
    // 转成时间戳
    public void setFxTime(String fxTime) throws ParseException {
      this.fxTime = DateUtil.date2TimeStamp(DateUtil.dealDateFormat(fxTime));
    }
    //  降水概率
    private double pop;
    //  降水量
    private String precip;
    //  露点温度
    private String dew;
    //  相对湿度
    private String humidity;
    //  天气状况文字描述
    private String text;
    private String windSpeed;
    //   风力等级
    private String windScale;

}
