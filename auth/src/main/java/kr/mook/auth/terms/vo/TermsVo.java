package kr.mook.auth.terms.vo;

import java.time.LocalDateTime;

import kr.mook.auth.terms.dto.TermsDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 이용약관 데이터 전송을 위한 객체(VO)
 * 
 * @since 2025. 11. 3.
 * @version 0.1
 * @author Inmook, Jeong
 */
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class TermsVo {
	
	// 이용약관 번호(PK)
	private long termsNo;
	
	// 사용 여부(사용: 1, 미사용: 0)
	private boolean useYn;
	
	// 필수 여부(필수: 1, 선택: 0)
	private boolean requireYn;
	
	// 출력 순번
	private long orderNo;
	
	// 이용약관 제목
	private String title;
	
	// 이용약관 상세 내용
	private String contents;
	
	// 이용약관 생성자 아이디
	private long createId;
	
	// 이용약관 생성일시
	private LocalDateTime createDate;
	
	// 이용약관 수정자 아이디
	private Long updateId;
	
	// 이용약관 수정일시
	private LocalDateTime updateDate;
	
	/**
	 * TermsDto(이용약관 정보)에 저장된 값을 TermsVo로 복사
	 * @param termsDto : 저장할 이용약관 정보
	 */
	public void fromTermsDto(final TermsDto termsDto) {
		this.termsNo = termsDto.getTermsNo();
		this.useYn = termsDto.isUseYn();
		this.requireYn = termsDto.isRequireYn();
		this.orderNo = termsDto.getOrderNo();
		this.title = termsDto.getTitle();
		this.contents = termsDto.getContents();
		this.createId = termsDto.getCreateId();
		this.createDate = termsDto.getCreateDate();
		this.updateId = termsDto.getUpdateId();
		this.updateDate = termsDto.getUpdateDate();
	}

}
