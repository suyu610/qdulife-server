package qdu.life.model;


import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.stereotype.Component;

import java.io.Serializable;

@Getter
@Setter
@ToString
@Component
// 与数据库中class对应
public class ClassRoom implements Serializable {
  int roomId;
  int buildingId;
  String rName;
  String typeDesc;
  String rChairType;
  int rSpace;
  int rFloor;
}
