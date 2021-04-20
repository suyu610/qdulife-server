package qdu.life.model.BO.User;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.beans.BeanUtils;

/**
 * @ClassName AutoReplyTextMsgBO
 * @Description TODO
 * @Author uuorb
 * @Date 2021/4/1012:16 下午
 * @Version 0.1
 **/

@Getter
@Setter
@NoArgsConstructor
public class AutoReplyTextMsgBO extends AutoReplyPostDataBO{
  private String Content;// 文本消息内容
 //用来把基类的属性值复制给子类
 public static AutoReplyTextMsgBO adapt(AutoReplyPostDataBO msg){
   AutoReplyTextMsgBO textMessage = new AutoReplyTextMsgBO();
       BeanUtils.copyProperties(msg, textMessage);
       return textMessage;
   }
}
