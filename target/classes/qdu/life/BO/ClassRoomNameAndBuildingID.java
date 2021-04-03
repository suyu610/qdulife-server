package qdu.life.BO;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;

/**
 * @ClassName ClassNameAndBuildingID
 * @Description TODO
 * @Author uuorb
 * @Date 2020/12/147:10 下午
 * @Version 0.1
 **/

@Getter
@Setter
@ToString
public class ClassRoomNameAndBuildingID implements Serializable {
  String className;
  int buildingId;
}
