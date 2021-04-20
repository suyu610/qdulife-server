package qdu.life.mapper;

import org.springframework.stereotype.Repository;

import java.util.LinkedList;

/**
 * @ClassName BuildingMapper
 * @Description TODO
 * @Author uuorb
 * @Date 2021/4/711:10 下午
 * @Version 0.1
 **/
@Repository
public interface BuildingMapper {
  LinkedList<String> getCampuseBuildingId(int campuseId);
}
