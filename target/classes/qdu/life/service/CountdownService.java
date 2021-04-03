package qdu.life.service;

import org.springframework.stereotype.Service;
import qdu.life.BO.CountdownBO;

import java.util.List;

@Service
public interface CountdownService {
  List<CountdownBO> getAllAfterTodayCountdown();
}
