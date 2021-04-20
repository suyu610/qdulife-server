package qdu.life.mapper;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.PathVariable;
import qdu.life.model.BO.Course.TemplateCourseBO;
import qdu.life.model.PO.UserPO;
import qdu.life.model.BO.Course.UserCourseBO;
import qdu.life.model.BO.User.UserTokenBO;

import java.util.Date;
import java.util.LinkedList;
import java.util.List;

@Repository
public interface UserMapper {
  boolean isExistOpenid(String openid);
  boolean insertNewUser(@Param("openid") String openid, @Param("now") Date now);
  UserTokenBO getUserTokenBoByOpenid(String openid);
  UserPO getUserByOpenid(String openid);
  String getFriendOpenidByOpenid(String openid);
  void setClock(@Param("openid")String openid,@Param("status") int status);
  LinkedList<UserCourseBO> getCourseByOpenid(@Param("openid")String openid, @Param("year")int year, @Param("term")int term);
  LinkedList<UserCourseBO> getAddCourseByOpenid(@Param("openid")String openid, @Param("year")int year, @Param("term")int term);
  void dismissFriend(@PathVariable("openid")String openid);
  LinkedList<TemplateCourseBO> getTemplateCourseByOpenid(@Param("openid")String openid,@Param("week")int week,@Param("whichweek")String whichweek, @Param("year")int year, @Param("term")int term);
  List<UserPO> getAllUserOpenidOpenClock();
  String getUserRealNameByOpenid(String openid);
  String getUserRelatedOpenid(@Param("openid")String openid);
  void updateInfo(@Param("openid")String openid,@Param("nickname")String nickname,@Param("avatar")String avatar);
  void bindFriend(@Param("openid")String openid,@Param("friendOpenid")String friendOpenid);
}
