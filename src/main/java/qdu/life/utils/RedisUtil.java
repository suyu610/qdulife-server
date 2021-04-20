package qdu.life.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.data.redis.core.RedisTemplate;

import java.util.HashMap;
import java.util.Set;
import java.util.concurrent.TimeUnit;

/**
 * redis工具类
 * @author: jamesluozhiwei
 */
@Component
public class RedisUtil {

  @Value("${token.expirationMilliSeconds}")
  private long expirationMilliSeconds;

  //@Autowired
  //private StringRedisTemplate redisTemplate;

  @Autowired
  private StringRedisTemplate stringRedisTemplate;

  /**
   * 查询key,支持模糊查询
   * @param key
   * */
  public Set<String> keys(String key){
    return stringRedisTemplate.keys(key);
  }

  /**
   * 字符串获取值
   * @param key
   * */
  public Object get(String key){
    return stringRedisTemplate.opsForValue().get(key);
  }

  /**
   * 字符串存入值
   * 默认过期时间为2小时
   * @param key
   * */
  public void set(String key, String value){
    set(key,value,expirationMilliSeconds);
  }

  /**
   * 字符串存入值
   * @param expire 过期时间（毫秒计）
   * @param key
   * */
  public void set(String key, String value,long expire){
    stringRedisTemplate.opsForValue().set(key,value, expire,TimeUnit.MILLISECONDS);
  }

  /**
   * 删出key
   * 这里跟下边deleteKey（）最底层实现都是一样的，应该可以通用
   * @param key
   * */
  public void delete(String key){
    stringRedisTemplate.opsForValue().getOperations().delete(key);
  }

  /**
   * 添加单个
   * @param key    key
   * @param filed  filed
   * @param domain 对象
   */
  public void hset(String key,String filed,Object domain){
    hset(key,filed,domain,expirationMilliSeconds);
  }

  /**
   * 添加单个
   * @param key    key
   * @param filed  filed
   * @param domain 对象
   * @param expire 过期时间（毫秒计）
   */
  public void hset(String key,String filed,Object domain,long expire){
    stringRedisTemplate.opsForHash().put(key, filed, domain);
    setKeyExpire(key,expirationMilliSeconds);
  }

  /**
   * 添加HashMap
   *
   * @param key    key
   * @param hm    要存入的hash表
   */
  public void hset(String key, HashMap<String,Object> hm){
    stringRedisTemplate.opsForHash().putAll(key,hm);
    setKeyExpire(key,expirationMilliSeconds);
  }

  /**
   * 如果key存在就不覆盖
   * @param key
   * @param filed
   * @param domain
   */
  public void hsetAbsent(String key,String filed,Object domain){
    stringRedisTemplate.opsForHash().putIfAbsent(key, filed, domain);
  }

  /**
   * 查询key和field所确定的值
   * @param key 查询的key
   * @param field 查询的field
   * @return HV
   */
  public Object hget(String key,String field) {
    return stringRedisTemplate.opsForHash().get(key, field);
  }

  /**
   * 查询该key下所有值
   * @param key 查询的key
   * @return Map<HK, HV>
   */
  public Object hget(String key) {
    return stringRedisTemplate.opsForHash().entries(key);
  }

  /**
   * 删除key下所有值
   *
   * @param key 查询的key
   */
  public void deleteKey(String key) {
    stringRedisTemplate.opsForHash().getOperations().delete(key);
  }

  /**
   * 添加set集合
   * @param key
   * @param set
   * @param expire
   */
  /// 没看懂
  public void sset(String key,Set<?> set,long expire){
    for (Object o : set) {
      stringRedisTemplate.opsForSet().add(key, (String) o);
    }

    setKeyExpire(key,expire);
  }

  /**
   * 添加set集合
   * @param key
   * @param set
   */
  public void sset(String key,Set<?> set){
    sset(key, set,expirationMilliSeconds);
  }

  /**
   * 判断key和field下是否有值
   * @param key 判断的key
   * @param field 判断的field
   */
  public Boolean hasKey(String key,String field) {
    return stringRedisTemplate.opsForHash().hasKey(key,field);
  }

  /**
   * 判断key下是否有值
   * @param key 判断的key
   */
  public Boolean hasKey(String key) {
    return stringRedisTemplate.opsForHash().getOperations().hasKey(key);
  }

  /**
   * 更新key的过期时间
   * @param key
   * @param expire
   */
  public void setKeyExpire(String key,long expire){
    stringRedisTemplate.expire(key,expire,TimeUnit.MILLISECONDS);
  }

}
