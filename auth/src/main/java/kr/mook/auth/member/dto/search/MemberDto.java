package kr.mook.auth.member.dto.search;

import kr.mook.auth.member.vo.MemberVo;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

/**
 * 회원 정보 전달을 위한 DTO<br/>
 * - 회원 목록 조회 또는 상세 조회 시 전달되는 객체
 * 
 * @since 2026. 03. 27.
 * @version 0.1
 * @author Inmook, Jeong
 */
@RequiredArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class MemberDto {
	
	// 회원 아이디(PK)
	private long memberId;
	
	// 회원 계정
	private String account;
	
	// 활성화 여부(Y/N)
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
	
	/**
	 * MemberDto 객체를 MemberVo 객체로 변환
	 * 
	 * @return
	 */
	public MemberVo toMemberVo() {
		MemberVo memberVo = new MemberVo();
		memberVo.setMemberId(this.memberId);
		memberVo.setAccount(this.account);
		memberVo.setActive(this.active);
		memberVo.setName(this.name);
		memberVo.setBirth(this.birth);
		memberVo.setGender(this.gender);
		memberVo.setEmail(this.email);
		memberVo.setPhoneNumber(this.phoneNumber);
		memberVo.setPostNumber(this.postNumber);
		memberVo.setAddress(this.address);
		return memberVo;
	}
	
	/**
	 * MemberVo 객체를 MemberDto 객체로 변환
	 * 
	 * @param memberVo
	 */
	public void fromMemberVo(MemberVo memberVo) {
		this.memberId = memberVo.getMemberId();
		this.account = memberVo.getAccount();
		this.active = memberVo.getActive();
		this.name = memberVo.getName();
		this.birth = memberVo.getBirth();
		this.gender = memberVo.getGender();
		this.email = memberVo.getEmail();
		this.phoneNumber = memberVo.getPhoneNumber();
		this.postNumber = memberVo.getPostNumber();
		this.address = memberVo.getAddress();
	}
}
