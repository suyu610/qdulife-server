package qdu.life.model;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @ClassName FeedbackModel
 * @Description TODO
 * @Author uuorb
 * @Date 2020/12/253:02 下午
 * @Version 0.1
 **/
@Data
public class FeedbackModel implements Serializable {
  String classname;
  String info;
  String ftype;
  Date feedbacktime;
}
