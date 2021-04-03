package qdu.life.BO;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;
import java.util.List;

/**
 * @ClassName UserBO
 * @Description TODO
 * @Author uuorb
 * @Date 2021/3/152:56 上午
 * @Version 0.1
 **/
@Data
@ToString
@Getter
@Setter
public class UserBO implements Serializable {
  // 这个作为主键，是一个自增的属性
  private int userid;
  private String openid;
   // 是否关注了公众号0未关注， 1已关注，发送订阅消息时，可以检测一下，或者只发送一次？
  private int subscribe_on;
   // 开启课程提醒
  private int clock_on;
  private String nickname;
  private String avatar_url;
   // 关联课表的openid，是一个字符串数组
  private List<String> relatedOpenid;
  private String ssnumber;
   // 学院名
  private int college_id;
   // 专业
  private String major;
  private String real_name;
   // 班级名字
  private String class_name;
   // 教务头像
  private String jw_avatar;
   // 手机号
  private String tel;
   // 身份证号
  private String id_number;
   // 地址
  private String address;
  // 在收到别人的点赞时，返回的话
  private String flag_reply;
}
