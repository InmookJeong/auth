package kr.mook.auth.terms.dto;

import java.time.LocalDateTime;

import lombok.Data;
import lombok.RequiredArgsConstructor;

/**
 * 이용약관 데이터 전송을 위한 객체(VO)<br/>
 * - Client와 Server 간 데이터 전송을 위해 사용
 * 
 * @since 2025. 11. 21.
 * @version
 * @author Inmook, Jeong
 */
@Data
@RequiredArgsConstructor
public class TermsDto {
	
	// 이용약관 번호(PK)
	private Long termsNo;
		
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
	 * 이용약관 제목의 값이 비어있는지 확인
	 * 
	 * @return 비어있으면 true, 비어있지 않으면 false 반환
	 */
	public boolean isTitleEmpty() {
		if(this.title == null || this.title.isEmpty()) return true;
		return false;
	}
	
	/**
	 * 이용약관 내용의 값이 비어있는지 확인
	 * 
	 * @return 비어있으면 true, 비어있지 않으면 false 반환
	 */
	public boolean isContentsEmpty() {
		if(this.contents == null || this.contents.isEmpty()) return true;
		return false;
	}
}
