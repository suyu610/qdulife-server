package qdu.life.model.BO.User;

import lombok.*;

/**
 * @ClassName AutoReplyPostDataBO
 * @Description TODO
 * @Author uuorb
 * @Date 2021/4/1012:15 下午
 * @Version 0.1
 **/


@Data
@Setter
@Getter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AutoReplyPostDataBO {
  // 开发者微信号
  private String ToUserName;
  // 发送方帐号（一个OpenID）
  private String FromUserName;
  // 消息创建时间 （整型）
  private long CreateTime;
  // 消息类型（text/image/location/link）
  private String MsgType;
  // 消息id，64位整型
  private long MsgId;
}
