package qdu.life.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import qdu.life.mapper.DialogMapper;
import qdu.life.mapper.WeatherMapper;
import qdu.life.model.DialogModel;

/**
 * @ClassName DialogService
 * @Description TODO
 * @Author uuorb
 * @Date 2020/12/2512:06 下午
 * @Version 0.1
 **/
@Service
public class DialogService {
  @Autowired
  DialogMapper mapper;

  public DialogModel getNewDialog(){
    return mapper.getNewDialog();
  }
}
