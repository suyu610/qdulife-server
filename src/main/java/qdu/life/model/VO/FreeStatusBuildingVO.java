package qdu.life.model.VO;

import lombok.Data;

import java.util.LinkedList;

/**
 * @ClassName FreeStatusBuildingVO
 * @Description 用于小程序index和list页面，搜索时的返回数据,返回一个这样的list
 * @Author uuorb
 * @Date 2021/4/710:32 上午
 * @Version 0.1
 * @Sample
 *
 *  {
 *    "matchedCount":34,
 *    "classList":[
 *      {
 *        "typeDesc":"多媒体教室",
 *        "status":"1111011110110",
 *        "rname":"博远楼101",
 *        "rspace":265,
 *        "rfloor":1,
 *        "bname":"博远楼"
 *      },{}
 *   ],
 *  "bname":"博远楼"
 *  }
 *
 **/

@Data
public class FreeStatusBuildingVO {
  String buildingName;
  int matchedCount;
  LinkedList<FreeStatusClassVO> freeStatusClassVOLinkedList;
}
