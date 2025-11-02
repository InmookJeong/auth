package kr.mook.auth.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;

@MapperScan("kr.mook.auth.**.persistence")
@Configuration
public class AuthAppConfig {

}
