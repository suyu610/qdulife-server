package qdu.life.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import qdu.life.BO.CountdownBO;
import qdu.life.mapper.CountdownMapper;
import qdu.life.service.CountdownService;

import java.util.List;

/**
 * @ClassName CountdownServiceImpl
 * @Description TODO
 * @Author uuorb
 * @Date 2021/3/313:38 上午
 * @Version 0.1
 **/
@Service("CountdownServiceImpl")
public class CountdownServiceImpl implements CountdownService {
  @Autowired
  CountdownMapper mapper;
  @Override
  public List<CountdownBO> getAllAfterTodayCountdown() {
    return mapper.getAllAfterTodayCountdown();
  }
}
