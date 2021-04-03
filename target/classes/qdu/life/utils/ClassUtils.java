package qdu.life.utils;

/**
 * @ClassName ClassUtils
 * @Description TODO
 * @Author uuorb
 * @Date 2020/12/147:14 下午
 * @Version 0.1
 **/

public class ClassUtils {
  // 将      11000 11111 111
  // 转化成   __000 _____ ___
  public static String ConvertClassSeq(String seq){
    return seq.replace('1','_');
  }
}
