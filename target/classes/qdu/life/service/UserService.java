package qdu.life.service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.MessageFormat;
import java.util.*;
import java.util.Map.Entry;

import com.alibaba.fastjson.JSONObject;
import org.apache.http.HeaderIterator;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.ParseException;
import org.apache.http.client.CookieStore;
import org.apache.http.client.config.CookieSpecs;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.protocol.HttpClientContext;
import org.apache.http.config.Registry;
import org.apache.http.config.RegistryBuilder;
import org.apache.http.cookie.CookieSpecProvider;
import org.apache.http.impl.client.BasicCookieStore;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.cookie.BasicClientCookie;
import org.apache.http.impl.cookie.BestMatchSpecFactory;
import org.apache.http.impl.cookie.BrowserCompatSpecFactory;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import qdu.life.BO.UserBO;
import qdu.life.BO.UserCourseBO;
import qdu.life.BO.UserLoginDeanBO;
import qdu.life.common.Result;
import qdu.life.utils.*;

import javax.xml.crypto.Data;

@Service
public interface UserService {
    /*
      将调用python服务器中的教务爬虫，将课表数据爬取下来，并插入数据库
   */
    public String loginDean(UserLoginDeanBO loginDeanBO,String openid) throws Exception;

    public Result wxLogin(String code)  throws Exception;

    public LinkedList<UserCourseBO> getCourseById(String openid, int year, int term);
    public LinkedList<UserCourseBO> getFriendCourseById(String openid, int year, int term);

    public UserBO getUserByOpenid(String openid);

    //  检查是否关注了公众号
    public int checkSubscribe(String openid, int hour,int mins);

    //
    public int closeAlarm(String openid);

    //
    public String checkBindFriend(String openid);

    public void updateInfo(String openid,String nickname,String avatar);

    public String bindFriend(String openid,String friendOpenid);

}

