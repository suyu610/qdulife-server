package qdu.life.model.VO;

import lombok.Data;

/**
 * @ClassName FreeStatusClassVO
 * @Description TODO
 * @Author uuorb
 * @Date 2021/4/710:33 上午
 * @Version 0.1
 * @Sample
 * {
 *        "typeDesc":"多媒体教室",
 *        "status":"1111111111110",
 *        "rname":"博学楼302",
 *        "rspace":129,
 *        "rfloor":3,
 *        "bname":"博学楼"
 * }
 **/
@Data
public class FreeStatusClassVO {
  String typeDesc;
  String status;
  String rName;
  int rSpace;
  int rFloor;
  String bName;
}
