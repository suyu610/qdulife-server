package qdu.life.utils;

import com.alibaba.fastjson.JSONObject;
import lombok.extern.log4j.Log4j2;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.bouncycastle.jce.provider.BouncyCastleProvider;
import org.python.apache.xerces.impl.dv.util.Base64;
import org.slf4j.Logger;
import org.springframework.context.annotation.Bean;
import qdu.life.common.WeChatUrl;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.security.AlgorithmParameters;
import java.security.Security;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @ClassName Jcode2SessionUtil
 * @Description TODO
 * @Author uuorb
 * @Date 2021/3/1812:08 下午
 * @Version 0.1
 **/
public class Jcode2SessionUtil {
  /**
   * 请求微信后台获取用户数据
   * @param code wx.login获取到的临时code
   * @return 请求结果
   * @throws Exception
   */
  public static String jscode2session(String appid,String secret,String code,String grantType)throws Exception{
    //定义返回的json对象
    JSONObject result = new JSONObject();
    //创建请求通过code换取session等数据
    HttpPost httpPost = new HttpPost(WeChatUrl.JS_CODE_2_SESSION.getUrl());
    List<NameValuePair> params=new ArrayList<NameValuePair>();
    //建立一个NameValuePair数组，用于存储欲传送的参数
    params.add(new BasicNameValuePair("appid",appid));
    params.add(new BasicNameValuePair("secret",secret));
    params.add(new BasicNameValuePair("js_code",code));
    params.add(new BasicNameValuePair("grant_type",grantType));
    //设置编码
    httpPost.setEntity(new UrlEncodedFormEntity(params));//添加参数
    return EntityUtils.toString(new DefaultHttpClient().execute(httpPost).getEntity());
  }
  /**
   * 解密用户敏感数据获取用户信息
   * @param sessionKey 数据进行加密签名的密钥
   * @param encryptedData 包括敏感数据在内的完整用户信息的加密数据
   * @param iv 加密算法的初始向量
   * @return
   */
  public static String getUserInfo(String encryptedData,String sessionKey,String iv)throws Exception{
    // 被加密的数据
    byte[] dataByte = Base64.decode(encryptedData);
    // 加密秘钥
    byte[] keyByte = Base64.decode(sessionKey);
    // 偏移量
    byte[] ivByte = Base64.decode(iv);
    // 如果密钥不足16位，那么就补足.  这个if 中的内容很重要
    int base = 16;
    if (keyByte.length % base != 0) {
      int groups = keyByte.length / base + (keyByte.length % base != 0 ? 1 : 0);
      byte[] temp = new byte[groups * base];
      Arrays.fill(temp, (byte) 0);
      System.arraycopy(keyByte, 0, temp, 0, keyByte.length);
      keyByte = temp;
    }
    // 初始化
    Security.addProvider(new BouncyCastleProvider());
    Cipher cipher = Cipher.getInstance("AES/CBC/PKCS7Padding","BC");
    SecretKeySpec spec = new SecretKeySpec(keyByte, "AES");
    AlgorithmParameters parameters = AlgorithmParameters.getInstance("AES");
    parameters.init(new IvParameterSpec(ivByte));
    cipher.init(Cipher.DECRYPT_MODE, spec, parameters);// 初始化
    byte[] resultByte = cipher.doFinal(dataByte);
    if (null != resultByte && resultByte.length > 0) {
      String result = new String(resultByte, "UTF-8");
      // 保存到日志里
      LogUtils.getBussinessLogger().info(result);
      return result;
    }
    return null;
  }

  /**
   * 获取微信接口调用凭证
   * @param appid
   * @param secret
   * @return 返回String 可转JSON
   */
  public static String getAccessToken(String appid,String secret){
    JSONObject params = new JSONObject();
    params.put("grant_type","client_credential");//获取接口调用凭证
    params.put("appid",appid);
    params.put("secret",secret);
    return HttpUtils.sendGet(WeChatUrl.GET_ACCESS_TOKEN.getUrl()+"?grant_type=client_credential&appid=" + appid + "&secret=" + secret);
  }
}
