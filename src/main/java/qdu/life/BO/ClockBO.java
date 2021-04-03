package qdu.life.BO;

/**
 * @ClassName ClockBO
 * @Description TODO
 * @Author uuorb
 * @Date 2021/3/153:15 上午
 * @Version 0.1
 **/

import java.io.Serializable;

/**
 * {
 *     "access_token":"43_mvuSJRdPX12vsid150gfqJHKkFiBO8kfTjTF9eFzJavWa6D6Vx7fzUwh8EIeEv3_GDrJEizZpendoyCjdQXFSLKeQB3P722QtHcwq8pNy4h5aim8fSr_Gf_U5z-66UohEhNsPkMz2JhffSVGXEJbAIADKP",
 *     "touser":"orGq35M1vYYqy38PADVDbq-Q-isw",
 *     "mp_template_msg":{
 *         "appid":"wxe6726f96155ab814",
 *         "template_id":"7uVZqd8s4czcT7EwU00xmQURB9G7nkz7NQCREChN_AQ",
 *         "miniprogram":{
 *             "appid":"wx607821b428f6d5d1",
 *             "pagepath":"pages/course/course"
 *         },
 *         "data":{
 *                 "first": {
 *                 "value":"皇甫素素同学，今天你有4节课\n\n1-2节\n博学楼104 电路原理2\n\n3-4节\n博知楼103 大学物理下 \n\n5-6节\n博远楼303 毛泽东思想概论",
 *                     "color":"#173177"
 *                 },
 *                 "keyword1":{
 *                     "value":"第17周星期一",
 *                     "color":"#173177"
 *                 },
 *                 "keyword2": {
 *                     "value":"1-2节；3-4节；5-6节",
 *                     "color":"#173177"
 *                 },
 *                 "remark":{
 *                     "value":"点击查看附近的空教室或退订。",
 *                     "color":"#173177"
 *                 }
 *         }
 * }}
 */

public class ClockBO implements Serializable {
  final String todayMsgId = "7uVZqd8s4czcT7EwU00xmQURB9G7nkz7NQCREChN_AQ";
  final String tomorrowMsgId = "edyeePR1wbiM7lQtRqluz1a1kx1yQk_RQJKi5f_9o5Q";
  String openid;
  // 姓名
  String name;
  // 课次 + 课程名 + 地点
  //    "first": {
  //                "value":"皇甫素素同学，今天你有4节课\n\n1-2节\n博学楼104 电路原理2\n\n3-4节\n博知楼103 大学物理下 \n\n5-6节\n博远楼303 毛泽东思想概论",
  //                    "color":"#173177"
  //                },
  //                "keyword1":{
  //                    "value":"第17周星期一",
  //                    "color":"#173177"
  //                },
  //                "keyword2": {
  //                    "value":"1-2节；3-4节；5-6节",
  //                    "color":"#173177"
  //                },
  //                "remark":{
  //                    "value":"点击查看附近的空教室或退订。",
  //                    "color":"#173177"
  //                }
  //
  String courseList;

}
