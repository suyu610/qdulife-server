package qdu.life.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.*;
import qdu.life.model.BO.Classroom.ClassRoomStatusPostDataBO;
import qdu.life.common.Result;
import qdu.life.service.ClassRoomService;
import qdu.life.utils.ResultUtils;

import java.text.ParseException;

/**
 * @ClassName ClassroomFreeStatusController
 * @Description 找空教室相关的api
 * @Author uuorb
 * @Date 2021/4/68:48 下午
 * @Version 0.1
 **/

@RestController
@RequestMapping("/api/classroomstatus")
public class ClassroomFreeStatusController {

  @Qualifier("classRoomServiceImpl")
  @Autowired
  private ClassRoomService service;

  // 有几个维度呢？
  // 1. 教学楼id (可以是列表，通过 - 分割)
  // 2. 周次与星期 (whichweek/week) -> 这个可以引申为今天/明天？
  //  如果是今天或明天，则忽略whichweek 和 week 参数
  // 4. 无课区间 用一个13位的字符串来表示，你要搜索的区间 -> 00000 00000 000

  // 3. 查看全部状态，还是只看空闲onlyFree = 0 / 1
  @PostMapping("/today")
  public Result getTodayClassStatus(@RequestBody ClassRoomStatusPostDataBO bo) throws ParseException {
    bo = ClassRoomStatusPostDataBO.getToday(bo.getBuildingIdList(),bo.isOnlyFree(),bo.getExpectStatus(),bo.getCampuseId());
    return ResultUtils.ok(service.getClassStatus(bo));
  }

  @PostMapping("/tomorrow")
  public Result getTomorrowClassStatus(@RequestBody ClassRoomStatusPostDataBO bo) throws ParseException {
    bo = ClassRoomStatusPostDataBO.getTomorrow(bo.getBuildingIdList(),bo.isOnlyFree(),bo.getExpectStatus(),bo.getCampuseId());
    return ResultUtils.ok(service.getClassStatus(bo));
  }
}
