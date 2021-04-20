package qdu.life.utils;

/**
 * @ClassName StrUtil
 * @Description TODO
 * @Author uuorb
 * @Date 2021/4/123:47 下午
 * @Version 0.1
 **/

public class StrUtil {
  /**
   * 根据用户名的不同长度，来进行替换 ，达到保密效果
   *
   * @param userName 用户名
   * @return 替换后的用户名
   */

    public static String getStringFirstAndEnd(String info){
      char[] m =  info.toCharArray();
      if(info.length()>2){
        for(int i=0; i<info.length();i++){
          if(i>=1 && i<info.length()-1){
            m[i] = '*';
          }
        }
      }else if(info.length()==2){
        for(int i=0; i<info.length();i++){
          if(i>=1){
            m[i] = '*';
          }
        }
      }
      info =  String.valueOf(m);
      return info;
    }



  public static void main(String[] args) {
    System.out.println(StrUtil.getStringFirstAndEnd("黄"));
  }

}
