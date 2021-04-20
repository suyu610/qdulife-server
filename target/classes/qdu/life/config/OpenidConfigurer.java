package qdu.life.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import qdu.life.filter.TokenInterceptor;

/**
 * @ClassName OpenidConfigurer
 * @Description TODO
 * @Author uuorb
 * @Date 2021/3/1710:47 下午
 * @Version 0.1
 **/

@Configuration
public class OpenidConfigurer extends WebMvcConfigurerAdapter {
  @Bean
  public TokenInterceptor getOpenidInterceptor() {
    return new TokenInterceptor();
  }

  @Override
  public void addInterceptors(InterceptorRegistry registry) {
    // 多个拦截器组成一个拦截器链
    // addPathPatterns 用于添加拦截规则
    // excludePathPatterns 用户排除拦截
    registry.addInterceptor(getOpenidInterceptor()).addPathPatterns("/api/**").excludePathPatterns("/api/user/login/**").excludePathPatterns("/api/roomshedule/**").excludePathPatterns("/api/dialog/**").excludePathPatterns("/api/countdown/**").excludePathPatterns("/api/classroomstatus/**").excludePathPatterns("/api/autoreply/**");
    super.addInterceptors(registry);
  }
}
