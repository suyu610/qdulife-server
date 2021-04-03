package qdu.life.mapper;

import org.springframework.stereotype.Repository;
import qdu.life.model.DialogModel;

/**
 * @ClassName DialogMapper
 * @Description TODO
 * @Author uuorb
 * @Date 2020/12/2512:07 下午
 * @Version 0.1
 **/
@Repository
public interface DialogMapper {
  DialogModel getNewDialog();
}
