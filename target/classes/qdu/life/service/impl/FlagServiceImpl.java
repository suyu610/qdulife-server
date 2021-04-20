package qdu.life.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import qdu.life.model.BO.Flag.FlagBO;
import qdu.life.model.BO.Flag.FlagPrivateBO;
import qdu.life.model.BO.Flag.FlagShowBO;
import qdu.life.model.PO.FlagPO;
import qdu.life.model.PO.UserPO;
import qdu.life.mapper.FlagMapper;
import qdu.life.service.FlagService;
import qdu.life.service.UserService;
import qdu.life.utils.StrUtil;

import java.util.List;

/**
 * @ClassName FlagServiceImpl
 * @Description TODO
 * @Author uuorb
 * @Date 2021/3/223:17 下午
 * @Version 0.1
 **/
@Service("FlagServiceImpl")
public class FlagServiceImpl implements FlagService {
  @Autowired
  FlagMapper mapper;
  @Autowired
  UserService userService;
  @Override
  public FlagShowBO getRandomPublicFlag(String openid) {
    // 纯随机？
    FlagPO flagPO =mapper.getRandomPublicFlag();
    UserPO userPO = userService.getUserByOpenid(flagPO.getOpenid());
    // 这里预留广告位

    FlagShowBO flagBO = new FlagShowBO();
    flagBO.setContent(flagPO.getContent());
    flagBO.setLike_count(flagPO.getLike_count());
    flagBO.setShow_count(flagPO.getShow_count());
    Boolean containOpenid = false;

    if(null != flagPO.getLike_openid_list()) {
      containOpenid = flagPO.getLike_openid_list().contains(openid);
      flagBO.set_like(containOpenid);
    }
    if(null== userPO.getNickname()){
      flagBO.setNickname("匿名");
    }else{
      flagBO.setNickname(StrUtil.getStringFirstAndEnd(userPO.getNickname()));
    }

    if(null== userPO.getSsnumber()){
      flagBO.setYear(-1);
    }else{
      flagBO.setYear(Integer.parseInt(userPO.getSsnumber().substring(0,4)));
    }
    if(null== userPO.getFlag_reply()){
      flagBO.setReply("谢谢你");
    }else {
      flagBO.setReply(userPO.getFlag_reply());
    }

    flagBO.setFlag_id(flagPO.getFlag_id());
    mapper.incrementShowCount(flagPO.getFlag_id());
    // 根据openid 得到一点东西
    return flagBO;
  }

  @Override
  public List<FlagBO> getAllPublicFlag(String openid) {
    return null;
  }

  @Override
  public int insertNewFlag(String openid,  FlagPO flagPO) {
    // 首先添加，然后再返回他的id
    mapper.insertPrivateFlag(flagPO);
    return mapper.getLatestPrivateFlag(openid);
  }

  @Override
  public void toggleLikeFlag(String openid, String flag_id) {
    String  likeList =  mapper.getLikeList(flag_id);
    // 如果不为空并包含，那么就替换掉，并且like_count - 1
    if(null != likeList && likeList.contains(openid)){
      mapper.decrementLikeCount(flag_id);
      String newLikeList = likeList.replace(openid+",","");
      System.out.println(newLikeList);
      mapper.updateFlagLikeList(newLikeList, flag_id);
      return ;
    }

    // 如果为空
    if(null == likeList){
      mapper.incrementLikeCount(flag_id);
      mapper.updateFlagLikeList(openid+",",flag_id);
      return ;
    }
    // 如果不包含，则增加 并且like_count + 1
    if(!likeList.contains(openid)){
      mapper.incrementLikeCount(flag_id);
      String newLikeList = likeList.concat(openid+",");
      System.out.println(newLikeList);
      mapper.updateFlagLikeList(newLikeList, flag_id);
      return ;
    }
  }
  /*
   首先判断这个 flag是不是本人的
   如果不是，则抛出错误
   如果是，就设置为相反
   */
  @Override
  public int togglePrivateFlag(String openid, int flag_id) throws Exception {
    // 如果不是本人，直接抛出异常
    if(!isOwner(openid,flag_id)){
      throw new Exception("非本人flag");
    }

    mapper.togglePrivateFlag(flag_id);
    return flag_id;
  }

  // 判断这个flag_id,是否为本人的

  @Override
  public int deleteFlag(String openid, int flag_id) throws Exception {
    if ( !isOwner(openid,  flag_id)){
      throw new Exception("非本人flag");
    }else {
      mapper.deletePrivateFlag(flag_id);
      return flag_id;
    }

  }
  /*
   * 判断flag_id是否属于openid
   * 首先应该判断，该flag_id是否存在，如果不存在，直接返回false
   */
  @Override
  public boolean isOwner(String openid, int flag_id) {
    if ( mapper.isOwner(openid,  flag_id) == 1) return true;
    return false;
  }

  @Override
  public List<FlagPrivateBO> getAllPrivateFlag(String openid) {
    return mapper.getAllPrivateFlag(openid);
  }
}
