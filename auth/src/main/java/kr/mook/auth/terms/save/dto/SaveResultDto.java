package kr.mook.auth.terms.save.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SaveResultDto {
	// 이용약관 번호(PK)
	private Long termsNo;
	
	// 저장 결과 메시지
	private String message;
	
	// Constructor
	public SaveResultDto(Long termsNo, String message) {
		super();
		this.termsNo = termsNo;
		this.message = message;
	}
}
