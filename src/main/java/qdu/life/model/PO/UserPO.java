package qdu.life.model.PO;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;
import java.util.Date;
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
public class UserPO implements Serializable {
  private String openid;
  // 这个作为主键，是一个自增的属性
  private int userid;
  // 创建时间
  private Date create_time;
  // 登陆时间
  private Date last_login_time;
  private String ssnumber;
  // 学院名
  private int college_id;
  // 专业
  private String major;
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
  // 开启课程提醒
  private int clock_on;
  // 微信昵称
  private String nickname;
  // 微信头像
  private String avatar_url;
  // 是否关注了公众号0未关注， 1已关注，发送订阅消息时，可以检测一下，或者只发送一次？
  private int subscribe_on;
  // 关联课表的openid，是一个字符串数组
  private List<String> relatedOpenid;

  // 在收到别人的点赞时，返回的话
  private String flag_reply;
  // 样式表
  private String style;
  // 姓名
  private String real_name;
  // 教务密码
  private String pwd;
  // 学院的全名
  private String college_intro;
  // 性别 -1为未知，0为女，1为男
  int sex;


}
