package qdu.life.model.BO.Classroom;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;

/**
 * @ClassName ClassRoomWithWholeInfo
 * @Description TODO
 * @Author uuorb
 * @Date 2020/12/152:05 上午
 * @Version 0.1
 **/
@Getter
@Setter
@ToString
public class ClassRoomWithWholeInfo implements Serializable {
  String bName;
  String rName;
  String typeDesc;
  int rFloor;
  int rSpace;
  String status;
}
