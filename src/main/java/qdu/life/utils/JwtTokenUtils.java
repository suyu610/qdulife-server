package qdu.life.utils;


import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import qdu.life.model.BO.User.UserTokenBO;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * @ClassName JwtTokenUtils
 * @Description TODO
 * @Author uuorb
 * @Date 2021/3/1812:34 下午
 * @Version 0.1
 **/

public class JwtTokenUtils {
  private static final String SALT = "susu13148085727!!@";//加密解密盐值

  // jti：jwt的唯一身份标识
  public static final String JWT_ID = UUID.randomUUID().toString();

  @Value("${token.expirationMilliSeconds}")
  private static long expirationMilliSeconds;
  @Value("${token.bindCourseTokenExpirationMilliSeconds}")
  private static long bindCourseTokenExpirationMilliSeconds;

  /**
   * 生成token
   * @param user
   * @return
   */
  public static String generateToken(UserTokenBO user){
    long nowTime = System.currentTimeMillis();
    Map<String, Object> claims = new HashMap<String, Object>();
    claims.put("openid",user.getOpenid());
    claims.put("userid",user.getUserid());
    claims.put("ssNumber",user.getSs_number());
    return Jwts.builder()
      .setId(JWT_ID)
      .setExpiration(new Date(nowTime + expirationMilliSeconds))
      .setIssuedAt(new Date(nowTime))
      .setClaims(claims)
      .signWith(SignatureAlgorithm.HS512, SALT)// 不使用公钥私钥
      .compact();
  }

  /**
   * 生成BindCourseToken
   * @param openid
   * @return token
   */
  public static String generateBindCourseToken(String openid){
    long nowTime = System.currentTimeMillis();
    Map<String, Object> claims = new HashMap<String, Object>();
    claims.put("openid",openid);
    return Jwts.builder()
      .setId(JWT_ID)
      .setExpiration(new Date(nowTime + bindCourseTokenExpirationMilliSeconds))
      .setIssuedAt(new Date(nowTime))
      .setClaims(claims)
      .signWith(SignatureAlgorithm.HS512, SALT)// 不使用公钥私钥
      .compact();
  }

  /**
   * 解析token,获得subject中的信息
   * @param token
   * @return
   */

  public static Claims getTokenBody(String token) {
    Claims claims;
    try {
      claims = Jwts.parser().setSigningKey(SALT).parseClaimsJws(token).getBody();
      System.out.println(claims);
    } catch (Exception e) {
      claims = null;
    }
    return claims;
  }

  public static Map<String,Object> getClaims(String token){
    Map<String,Object> claims = null;
    try {
      claims = getTokenBody(token);
    }catch (Exception e) {
    }

    return claims;
  }



}
