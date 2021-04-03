package qdu.life.BO;

/**
 * @ClassName ClassRoomBO
 * @Description TODO
 * @Author uuorb
 * @Date 2020/12/145:18 上午
 * @Version 0.1
 **/



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
public class ClassRoomBO implements Serializable {
  String rName;
  String typeDesc;
  String rChairType;
  int rSpace;
  int rFloor;
  int direction;
  int building_id;
}

