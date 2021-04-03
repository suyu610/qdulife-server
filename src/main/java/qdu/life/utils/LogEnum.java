package qdu.life.utils;

/**
 * @ClassName LogEnum
 * @Description TODO
 * @Author uuorb
 * @Date 2021/3/1710:22 下午
 * @Version 0.1
 **/


/**
 * 本地日志枚举
 * @author Administrator
 *
 */
public enum  LogEnum {


  BUSSINESS("bussiness"),

  PLATFORM("platform"),

  DB("db"),

  EXCEPTION("exception"),

  ;


  private String category;


  LogEnum(String category) {
    this.category = category;
  }

  public String getCategory() {
    return category;
  }

  public void setCategory(String category) {
    this.category = category;
  }
}

