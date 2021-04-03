package qdu.life.model;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @ClassName FlagModel
 * @Description TODO
 * @Author uuorb
 * @Date 2021/3/223:32 下午
 * @Version 0.1
 **/
@Data
public class FlagModel implements Serializable {
  int flag_id;
  int show_count;
  int like_count;
  // 用户是否设置为公开
  int is_public;
  // 是否审核通过
  int is_verify;
  // 点赞的人的openid
  String like_openid_list;
  String openid;
  String content;
  Date create_date;
}
