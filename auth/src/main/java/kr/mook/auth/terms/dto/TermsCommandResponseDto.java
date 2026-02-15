package kr.mook.auth.terms.dto;

import lombok.Getter;
import lombok.Setter;

/**
 * 이용약관 정보를 저장/수정/삭제한 후 이용약관 번호와 결과 메시지를 반환하는 DTO<br/>
 * 
 * @since 2025. 12. 2.
 * @version 0.1
 * @author Inmook, Jeong
 */
@Getter
@Setter
public class TermsCommandResponseDto {
	// 이용약관 번호(PK)
	private Long termsNo;
	
	// 저장 결과 메시지
	private String message;
	
	// Constructor
	public TermsCommandResponseDto(Long termsNo, String message) {
		super();
		this.termsNo = termsNo;
		this.message = message;
	}
}
