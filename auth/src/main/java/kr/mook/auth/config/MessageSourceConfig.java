package kr.mook.auth.config;

import java.nio.charset.StandardCharsets;

import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;

/**
 * 다국어 처리를 위한 구성
 * 
 * @since 2025. 11. 10.
 * @version
 * @author Inmook, Jeong
 */
@Configuration
public class MessageSourceConfig {
	
	@Bean
	MessageSource messageSource() {
		ReloadableResourceBundleMessageSource messageSource = new ReloadableResourceBundleMessageSource();
		// 에러메시지를 별도로 관리하기 위해 "classpath:messages/error" 추가
		messageSource.setBasenames("classpath:messages/messages", "classpath:messages/error");
		messageSource.setDefaultEncoding(StandardCharsets.UTF_8.toString());
		return messageSource;
	}
}
