package qdu.life.model.PO;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.Date;

/**
 * @ClassName WeatherBO
 * @Description TODO
 * @Author uuorb
 * @Date 2020/12/233:31 上午
 * @Version 0.1
 **/
//      weatherId
//      suggest:"今日降雨概率为0，但风很大",
//      shorttext:"多云",
//      rain:0,
//      dayt:3,
//      nightt:0,
//      iconcode:408,
//      time

@Data
@Getter
@Setter
@JsonSerialize
public class WeatherPO implements Serializable{
  int weatherId;
  String suggest;
  String shorttext;
  double rain;
  double dayt;
  double nightt;
  int iconcode;
  Date createtime;
}
