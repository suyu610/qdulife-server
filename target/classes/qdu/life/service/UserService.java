package qdu.life.service;

import java.util.*;

import org.springframework.stereotype.Service;
import qdu.life.model.PO.UserPO;
import qdu.life.model.BO.Course.UserCourseBO;
import qdu.life.model.BO.User.UserLoginDeanBO;
import qdu.life.common.Result;

@Service
public interface UserService {
    /*
      将调用python服务器中的教务爬虫，将课表数据爬取下来，并插入数据库
   */
    public String loginDean(UserLoginDeanBO loginDeanBO,String openid) throws Exception;

    public Result wxLogin(String code)  throws Exception;

    public LinkedList<UserCourseBO> getCourseById(String openid, int year, int term);
    public LinkedList<UserCourseBO> getAddCourseById(String openid, int year, int term);

    public LinkedList<UserCourseBO> getFriendCourseById(String openid, int year, int term);

    public UserPO getUserByOpenid(String openid);
    public void dismissFriend(String openid);
    //  检查是否关注了公众号
    public int checkSubscribe(String openid, int hour,int mins);

    //
    public int closeAlarm(String openid);

    //
    public String checkBindFriend(String openid);

    public void updateInfo(String openid,String nickname,String avatar);

    public String bindFriend(String openid,String friendOpenid);

}

