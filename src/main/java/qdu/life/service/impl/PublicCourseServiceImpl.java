package qdu.life.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import qdu.life.model.BO.Course.PublicCourseBO;
import qdu.life.mapper.PublicCourseMapper;
import qdu.life.model.PO.UserCoursePO;
import qdu.life.service.PublicCourseService;

import java.util.HashMap;
import java.util.List;

/**
 * @ClassName PublicCourseServiceImpl
 * @Description 公共课程搜索
 * @Author uuorb
 * @Date 2021/3/293:48 下午
 * @Version 0.1
 **/

@Service("PublicCourseServiceImpl")
public class PublicCourseServiceImpl implements PublicCourseService {
  @Autowired
  PublicCourseMapper mapper;
  /*
    seq:0123,0为全天，1上午 2下午 3晚上
    week: 0为不筛选星期
  */
  @Override
  public HashMap getCourseByName(String courseName,int seq,int week,int campus_id,int pageIndex) {
    System.out.println("test");
    PageHelper.startPage(pageIndex,14);
    // 将seq->________
    String seqStr = "_____________";
    String weekStr = "_";
    String campusStr = "%_%";

    if(campus_id == 1709)  campusStr = "1709";
    if(campus_id == 13041) campusStr = "13041";

    if(seq == 1){
      seqStr = "_____00000000";
    }
    if(seq==2){
      seqStr = "00000_____000";
    }

    if(seq==3){
      seqStr = "0000000000___";
    }

    if(week!=0){
      weekStr = String.valueOf(week);
    }
    // 将高等数学拆分为 -> 高%等%数%学
    StringBuffer formateStr = new StringBuffer();
    formateStr.append("%");
    for (int i = 0; i < courseName.length(); i++) {
      formateStr.append(courseName.charAt(i));
      if (i < courseName.length() - 1) {
        formateStr.append("%");
      }
    }
    formateStr.append("%");
    List<PublicCourseBO> publicCourseBOList =  mapper.getCourseByName(formateStr.toString(),seqStr,weekStr,campusStr);

    PageInfo pageInfo=new PageInfo(publicCourseBOList);
    int totalPages = pageInfo.getPages();
    long totalCourses = pageInfo.getTotal();
    HashMap hashMap = new HashMap();
    hashMap.put("course",publicCourseBOList);
    hashMap.put("totalPages",totalPages);
    hashMap.put("totalCourses",totalCourses);
    return hashMap;

  }
  // 往用户课表中，插入课程
  @Override
  public void insertPublicCourse(String openid, PublicCourseBO bo) {
    UserCoursePO userCoursePO = mapper.getCourseByInfoAndSeqAndCourse(bo.getInfo_str(),bo.getKey_course(),bo.getKey_seq());
    userCoursePO.setOpenid(openid);
    userCoursePO.setTerm(1);
    userCoursePO.setYear(41);
    mapper.insertPublicCourse(userCoursePO);
  }
}
