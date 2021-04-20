package qdu.life.model.BO.Classroom;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;
import java.util.List;

/**
 * @ClassName ClassRoomWithTables
 * @Description TODO
 * @Author uuorb
 * @Date 2020/12/145:36 上午
 * @Version 0.1
 **/
@Setter
@Getter
@ToString
public class ClassRoomWithTables implements Serializable {
  ClassRoomWithBuildingNameBO classRoomWithBuildingNameBO;
  List<StatusBO> status;
}
