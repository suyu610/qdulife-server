package qdu.life.model.BO.Classroom;

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
// 拼接好的完整的Class
public class ClassBO implements Serializable {
  String rName;
  String typeDesc;
  String rChairType;
  int rSpace;
  int rFloor;
  int direction;
  int buildingId;
  String buildingName;
}

