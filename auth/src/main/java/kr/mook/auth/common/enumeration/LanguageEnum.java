package kr.mook.auth.common.enumeration;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

/**
 * 사용되는 언어에 대한 Enum<br/>
 * - 요청이나 응답 데이터가 전달될 때 사용되는 언어 정보 제공
 * 
 * @since 2025. 8. 9.
 * @version 0.1
 * @author Inmook, Jeong
 */
@RequiredArgsConstructor
public enum LanguageEnum {
	
	KOREAN("ko-KR"),
	ENGLISH("en-US");
	
	@Getter
	private final String language;
}
