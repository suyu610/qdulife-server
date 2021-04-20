package qdu.life.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import qdu.life.mapper.FeedbackMapper;
import qdu.life.model.PO.FeedbackPO;

import java.util.Date;

/**
 * @ClassName FeedbackService
 * @Description TODO
 * @Author uuorb
 * @Date 2020/12/253:13 下午
 * @Version 0.1
 **/
@Service
@Transactional
public class FeedbackService {
  @Autowired
  FeedbackMapper mapper;

  public void saveFeedback(FeedbackPO feedbackPO){
    Date date = new Date();
    feedbackPO.setFeedbacktime(date);
    mapper.insertNewFeedback(feedbackPO);
    notifyFeedback();
  }

  // 用于提醒
  void notifyFeedback(){}
}
