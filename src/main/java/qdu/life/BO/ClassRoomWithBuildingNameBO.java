package qdu.life.BO;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;

/**
 * @ClassName ClassRoomWithBuildingNameBO
 * @Description TODO
 * @Author uuorb
 * @Date 2020/12/145:50 上午
 * @Version 0.1
 **/
@Getter
@Setter
@ToString
public class ClassRoomWithBuildingNameBO implements Serializable {
  String bName;
  String rName;
  String typeDesc;
  String rChairType;
  int rSpace;
}
