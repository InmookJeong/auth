package kr.mook.auth.common.enumeration;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

/**
 * 응답 결과의 데이터 유형에 대한 Enum<br/>
 * - 응답 결과로 전달되는 데이터의 유형(type)을 정의하기 위한 정보 제공
 * 
 * @since 2025. 8. 9.
 * @version 0.1
 * @author Inmook, Jeong
 */
@RequiredArgsConstructor
public enum ResponseTypeEnum {
	
	STRING("string"),
	OBJECT("object");
	
	@Getter
	private final String type;
}
