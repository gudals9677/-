package kr.co.springboard.config;

import lombok.Getter;
import lombok.Setter;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.info.BuildProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@Getter
@Setter
@Configuration
@EnableAspectJAutoProxy
public class RootConfig {

    // 빌드 정보 객체를 주입 받기 위해 build.gradle 파일 맨 밑에 buildInfo() 실행 해야됨
    @Autowired
    private BuildProperties buildProperties;

    @Bean
    public AppInfo appInfo(){
        String name = buildProperties.getName();
        String version = buildProperties.getVersion();
        return new AppInfo(name, version);
    }

    @Bean
    public ModelMapper modelMapper(){

        // Entity의 @Setter 선언없이 바로 private 속성으로 초기화 설정
        ModelMapper modelMapper = new ModelMapper();

        modelMapper.getConfiguration()
                .setFieldAccessLevel(org.modelmapper.config.Configuration.AccessLevel.PRIVATE)
                .setMatchingStrategy(MatchingStrategies.STRICT)
                .setFieldMatchingEnabled(true);

        return modelMapper;
    }

}
