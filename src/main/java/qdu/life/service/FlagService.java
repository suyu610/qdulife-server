package qdu.life.service;

import org.springframework.stereotype.Service;
import qdu.life.model.BO.Flag.FlagBO;
import qdu.life.model.BO.Flag.FlagPrivateBO;
import qdu.life.model.BO.Flag.FlagShowBO;
import qdu.life.model.PO.FlagPO;

import java.util.List;

@Service
public interface FlagService {
  FlagShowBO getRandomPublicFlag(String openid);
  // 这个是给以后浏览大家的flag做准备的
  List<FlagBO> getAllPublicFlag(String openid);
  int insertNewFlag(String openid, FlagPO flagPO);
  void toggleLikeFlag(String openid, String flag_id);
  // 获取个人所有的flag
  List<FlagPrivateBO> getAllPrivateFlag(String openid);
  // 翻转，返回改变的id
  int togglePrivateFlag(String openid, int flag_id) throws Exception;
  // 删除,返回改变的id
  int deleteFlag(String openid, int flag_id) throws Exception;
  // 判断flag_id是否属于openid
  boolean isOwner(String openid, int flag_id);
  }
