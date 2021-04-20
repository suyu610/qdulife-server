package qdu.life.mapper;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
import qdu.life.model.BO.Flag.FlagPrivateBO;
import qdu.life.model.PO.FlagPO;

import java.util.List;

/**
 * @ClassName FlagMapper
 * @Description TODO
 * @Author uuorb
 * @Date 2021/3/226:51 下午
 * @Version 0.1
 **/
@Repository
public interface FlagMapper {
  FlagPO getRandomPublicFlag();
  void insertPrivateFlag(FlagPO flagPO);
  String getLikeList(@Param("flag_id") String flag_id);
  void decrementLikeCount(String flag_id);
  void incrementLikeCount(String flag_id);
  void incrementShowCount(int flag_id);
  void togglePrivateFlag(int flag_id);
  void deletePrivateFlag(int flag_id);
  int isOwner(@Param("openid") String open_id,@Param("flag_id") int flag_id);
  // 返回用户最新的flag_id
  int getLatestPrivateFlag(String openid);
  void updateFlagLikeList(@Param("newLikeList") String like_list,@Param("flag_id") String flag_id);
  List<FlagPrivateBO> getAllPrivateFlag(String openid);
}
