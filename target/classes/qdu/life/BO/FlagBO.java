package qdu.life.BO;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @ClassName FlagBO
 * @Description TODO
 * @Author uuorb
 * @Date 2021/3/223:22 下午
 * @Version 0.1
 **/
@Data
public class FlagBO implements Serializable {
  // 根据用户openid,得到这个值
  @JsonProperty("is_like")
  boolean is_like;
  int flag_id;
  int show_count;
  int like_count;
  // 用户是否设置为公开
  int is_public;
  // 是否审核通过
  int is_verify;
  // 点赞的人的名字
  String like_username_list;
  ///// flag所有者的信息
  String openid;
  // 收到点赞后的回复信息
  String reply;
  // 微信名
  String nickname;
  // 真名
  String realname;
  //  多少级
  int year;
  String content;
  Date create_date;

}
