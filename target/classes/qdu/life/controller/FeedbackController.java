package qdu.life.controller;

import com.alibaba.fastjson.JSON;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import qdu.life.model.FeedbackModel;
import qdu.life.service.ClassRoomService;
import qdu.life.service.FeedbackService;

/**
 * @ClassName FeedbackController
 * @Description TODO
 * @Author uuorb
 * @Date 2020/12/252:49 下午
 * @Version 0.1
 **/
@RestController
@RequestMapping("/api/feedback")
public class FeedbackController {
  @Autowired
  private FeedbackService service;

  @PostMapping("/postnew")
  void postNew(@RequestBody FeedbackModel feedback){
    service.saveFeedback(feedback);
  }
}
