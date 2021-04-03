package qdu.life.mapper;

import qdu.life.BO.WeatherBO;
import qdu.life.model.FeedbackModel;

/**
 * @ClassName FeedbackMapper
 * @Description TODO
 * @Author uuorb
 * @Date 2020/12/253:17 下午
 * @Version 0.1
 **/

public interface FeedbackMapper {
  void insertNewFeedback(FeedbackModel feedbackModel);
}
