package qdu.life.mapper;

import org.springframework.stereotype.Repository;
import qdu.life.model.BO.System.AccessToken;

import java.util.List;

/**
 * @ClassName WechatMapper
 * @Description TODO
 * @Author uuorb
 * @Date 2021/3/147:33 下午
 * @Version 0.1
 **/

@Repository
public interface WechatMapper {
  public void insertNewAccessToken(AccessToken accessToken);
  public AccessToken getLatestAccessToken();
  public List<String> selectAll();
}
