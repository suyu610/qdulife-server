package qdu.life.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import qdu.life.common.Result;
import qdu.life.service.RoomScheduleService;
import qdu.life.utils.ResultUtils;


/**
 * @ClassName RoomSheduleController
 * @Description TODO
 * @Author uuorb
 * @Date 2021/3/109:05 下午
 * @Version 0.1
 **/

@RestController
@RequestMapping("/api/roomshedule")
public class RoomScheduleController {
  @Autowired
  private RoomScheduleService service;

  @PostMapping("/single/{roomname}/{whichweek}")
  public Result singleRoomAndSingleWeek(@PathVariable("roomname") String roomname, @PathVariable("whichweek") int whichWeek){
    return ResultUtils.ok(service.singleRoomAndSingleWeek(roomname,whichWeek));
  }
}
