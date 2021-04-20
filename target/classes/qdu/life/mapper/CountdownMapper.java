package qdu.life.mapper;

import org.springframework.stereotype.Repository;
import qdu.life.model.BO.Countdown.CountdownBO;

import java.util.List;

/**
 * @ClassName CountdownMapper
 * @Description TODO
 * @Author uuorb
 * @Date 2021/3/313:41 上午
 * @Version 0.1
 **/

@Repository
public interface CountdownMapper {
  List<CountdownBO> getAllAfterTodayCountdown();
}
