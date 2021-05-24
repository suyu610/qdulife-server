package qdu.life.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import qdu.life.model.PO.FeedbackPO;
import qdu.life.service.FeedbackService;

/**
 * @ClassName FeedbackController
 * @Description TODO
 * @Author uuorb
 * @Date 2020/12/252:49 下午
 * @Version 0.1
 **/
@RestController
@RequestMapping("/v1/feedback")
public class FeedbackController {
  @Autowired
  private FeedbackService service;

  @PostMapping("/postnew")
  void postNew(@RequestBody FeedbackPO feedback){
    service.saveFeedback(feedback);
  }
}
