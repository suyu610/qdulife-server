package qdu.life.BO;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;
import java.util.List;

/**
 * @ClassName SearchResultBO
 * @Description TODO
 * @Author uuorb
 * @Date 2020/12/143:23 下午
 * @Version 0.1
 **/
@Getter
@Setter
@ToString
public class SearchResultBO implements Serializable {
  List<BuildingBO> buildingBOList;
}
