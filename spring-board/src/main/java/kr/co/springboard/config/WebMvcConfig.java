package kr.co.springboard.config;

import kr.co.springboard.intercepter.AppInfoInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebMvcConfig implements WebMvcConfigurer {

    @Autowired
    private AppInfo appInfo;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        // 인터셉터 등록 설정
        registry.addInterceptor(new AppInfoInterceptor(appInfo));
    }
}
