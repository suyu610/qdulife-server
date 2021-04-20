package qdu.life.service;

import org.springframework.stereotype.Service;
import qdu.life.model.BO.Countdown.CountdownBO;

import java.util.List;

@Service
public interface CountdownService {
  List<CountdownBO> getAllAfterTodayCountdown();
}
