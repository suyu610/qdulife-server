package qdu.life.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import qdu.life.mapper.FeedbackMapper;
import qdu.life.mapper.WeatherMapper;
import qdu.life.model.FeedbackModel;

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

  public void saveFeedback(FeedbackModel feedbackModel){
    Date date = new Date();
    feedbackModel.setFeedbacktime(date);
    mapper.insertNewFeedback(feedbackModel);
    notifyFeedback();
  }

  // 用于提醒
  void notifyFeedback(){}
}
