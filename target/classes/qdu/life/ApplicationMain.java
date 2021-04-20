package qdu.life;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.EnableScheduling;

@MapperScan("qdu.life.mapper")
@ComponentScan(basePackages = {"qdu.life.*"})
@EnableScheduling
@SpringBootApplication
@EnableCaching
public class ApplicationMain {
  public static void main(String[] args) {
    SpringApplication.run(ApplicationMain.class, args);
  }
}
