package qdu.life.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import qdu.life.common.Result;
import qdu.life.model.DateModel;
import qdu.life.service.*;
import qdu.life.utils.ResultUtils;

import java.text.ParseException;

@RestController
@RequestMapping("/api/class")
public class mainController {
  @Autowired
  private ClassRoomService service;

  @PostMapping("/getClassByBuildId/{buildingId}")
  public Result getClassByBuildId(@PathVariable("buildingId") int buildingId){
    return ResultUtils.ok(service.getClassByBuildId(buildingId));
  }

  @PostMapping("/getClassByBuildId/{buildingId}/{floor}")
  public Result getClassByBuildId(@PathVariable("buildingId") int buildingId, @PathVariable("floor") int floor){
    return ResultUtils.ok(service.getClassByBuildIdAndFloor(buildingId,floor));
  }
  @PostMapping("/single/{className}")
  public Result getSingleClass(@PathVariable("className") String className){
    return ResultUtils.ok(service.getSingleClassWithTables(className));
  }
  // 00000 00000 000
  // 第12节课 ->"2020-12-14" / "11111 00000 000"
  // 满足符合这个空闲时间的教室

  @PostMapping("/sequence/{whichweek}/{week}/{seq}")
  public Result searchBySequence(@PathVariable("seq") String seq, @PathVariable("whichweek") int whichweek, @PathVariable("week") int week){
    return ResultUtils.ok(service.getClassBySearch(whichweek,week,seq));
  }

  @PostMapping("/sequence/today/{seq}")
  public Result searchTodayBySequence(@PathVariable("seq") String seq) throws ParseException {

    //    今天
    DateModel dateModel = new DateModel();
    dateModel = DateModel.getCurrentDate();
    int week = dateModel.getWeek();
    int whichweek = dateModel.getWhichWeek();
    // 临时修补
      if(week == 0){
        week = 7;
      }
    return ResultUtils.ok(service.getClassBySearch(whichweek,week,seq));
  }

  @PostMapping("/today/{buildingIds}/{floor}/{length}")
  public Result searchTodayByDetail(
    @PathVariable("buildingIds") String buildingIds,
    @PathVariable("floor") String floor,
    @PathVariable("length") String length
  ) throws ParseException {

    //    今天
    DateModel dateModel = new DateModel();
    dateModel = DateModel.getCurrentDate();
    int week = dateModel.getWeek();
    // 临时修补
    if(week == 0){
      week = 7;
    }
    int whichweek = dateModel.getWhichWeek();

    return ResultUtils.ok(service.getClassByDetail(whichweek,week,buildingIds,floor,length));
  }

}
