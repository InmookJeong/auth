package kr.mook.auth.common.dto;

import java.util.Locale;

import kr.mook.auth.common.enumeration.ResponseTypeEnum;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * API 응답 데이터를 전달하기 위한 DTO
 * 
 * @since 2025. 8. 9.
 * @version 0.1
 * @author Inmook, Jeong
 */
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class ResponseDto {
	
	// HTTP 상태 코드(ex. 200)
	private String httpStatusCode;
	
	// 결과 상태코드(ex. TMS-SER-001)
	private String statusCode;
	
	// 결과 상태코드명(ex. HOME_ACCESS)
	private String status;
	
	// 결과타입(ex. string)
	private ResponseTypeEnum resultType;
	
	// 결과(ex. 접속을 환영합니다.)
	private Object result;
	
	// 결과 언어(ex. ko-KR)
	private Locale locale;
}
