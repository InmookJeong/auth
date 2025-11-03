package kr.mook.auth.terms.vo;

import java.time.LocalDateTime;

import lombok.Data;
import lombok.RequiredArgsConstructor;

/**
 * 이용약관 데이터 전송을 위한 객체(VO)
 * 
 * @since 2025. 11. 3.
 * @version 0.1
 * @author Inmook, Jeong
 */
@Data
@RequiredArgsConstructor
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

}
