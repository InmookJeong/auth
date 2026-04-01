package kr.mook.auth.member.vo;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 회원 데이터 전송을 위한 객체(VO)
 * 
 * @since 2026. 03. 27.
 * @version 0.1
 * @author Inmook, Jeong
 */
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class MemberVo {
	
	// 회원 아이디(PK)
	private long memberId;
	
	// 회원 계정
	private String account;
	
	// 비밀번호
	private String password;

	// 활성화(Y/N)
	private char active;
	
	// 회원 이름
	private String name;
	
	// 회원 생년월일(ex. 19901231)
	private String birth;
	
	// 성별(M/W)
	private char gender;
	
	// 이메일(ex. test@gmail.com)
	private String email;
	
	// 휴대전화 번호(ex. 01012345678)
	private String phoneNumber;
	
	// 우편번호(ex.01234)
	private String postNumber;
	
	// 주소(서울시 관악구 신림동 1번길 12)
	private String address;
	
	// 회원 생성자 아이디(관리자가 생성할 수도 있음)
	private long createId;
	
	// 회원 생성일시
	private LocalDateTime createDate;
	
	// 회원 수정자 아이디(관리자가 수정할 수도 있음)
	private Long updateId;
	
	// 회원 수정일시
	private LocalDateTime updateDate;
	
	// 로그인 실패 횟수
	private int loginFailedCount;
	
	// 마지막 로그아웃 날짜
	private LocalDateTime latestLogoutDate;
}
