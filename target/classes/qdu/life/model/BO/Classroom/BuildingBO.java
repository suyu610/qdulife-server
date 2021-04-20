package qdu.life.model.BO.Classroom;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;
import java.util.List;

/**
 * @ClassName BuildingBO
 * @Description TODO
 * @Author uuorb
 * @Date 2020/12/143:24 下午
 * @Version 0.1
 **/
@Getter
@Setter
@ToString
public class BuildingBO implements Serializable {
  String bName;
  int matchedCount; //符合条件的教室数量
  List<ClassRoomWithWholeInfo> classList;
}
