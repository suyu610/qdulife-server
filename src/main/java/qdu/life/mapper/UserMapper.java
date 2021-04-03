package qdu.life.mapper;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
import qdu.life.BO.TemplateCourseBO;
import qdu.life.BO.UserBO;
import qdu.life.BO.UserCourseBO;
import qdu.life.BO.UserTokenBO;

import java.util.Date;
import java.util.LinkedList;
import java.util.List;

@Repository
public interface UserMapper {
  boolean isExistOpenid(String openid);
  boolean insertNewUser(@Param("openid") String openid, @Param("now") Date now);
  UserTokenBO getUserTokenBoByOpenid(String openid);
  UserBO getUserByOpenid(String openid);
  String getFriendOpenidByOpenid(String openid);
  void setClock(@Param("openid")String openid,@Param("status") int status);
  LinkedList<UserCourseBO> getCourseByOpenid(@Param("openid")String openid, @Param("year")int year, @Param("term")int term);
  LinkedList<TemplateCourseBO> getTemplateCourseByOpenid(@Param("openid")String openid,@Param("week")int week,@Param("whichweek")String whichweek, @Param("year")int year, @Param("term")int term);
  List<UserBO> getAllUserOpenidOpenClock();
  String getUserRealNameByOpenid(String openid);
  String getUserRelatedOpenid(@Param("openid")String openid);
  void updateInfo(@Param("openid")String openid,@Param("nickname")String nickname,@Param("avatar")String avatar);
  void bindFriend(@Param("openid")String openid,@Param("friendOpenid")String friendOpenid);
}
