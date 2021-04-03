package qdu.life.model;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @ClassName DialogModel
 * @Description TODO
 * @Author uuorb
 * @Date 2020/12/2511:59 上午
 * @Version 0.1
 **/
@Data
public class DialogModel implements Serializable {
  String dialogId;
  String title;
  String content;
  Date createtime;
  String author;
}
