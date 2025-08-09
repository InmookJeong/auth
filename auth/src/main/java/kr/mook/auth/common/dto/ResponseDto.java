package kr.mook.auth.common.dto;

import kr.mook.auth.common.enumeration.LanguageEnum;
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
	
	private String statusCode;
	private String status;
	private ResponseTypeEnum resultType;
	private Object result;
	private LanguageEnum language;
}
