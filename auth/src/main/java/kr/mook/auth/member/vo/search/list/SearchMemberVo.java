package kr.mook.auth.member.vo.search.list;

import kr.mook.auth.member.dto.search.MemberSearchDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 회원 목록 검색을 위한 VO<br/>
 * - 회원 계정, 회원 이름을 기준으로 회원 목록 검색
 * 
 * @since 2026. 05. 16.
 * @version 0.1
 * @author Inmook, Jeong
 */
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class SearchMemberVo {
	
	// 회원 계정
	private String account;
	
	// 회원 이름
	private String name;
	
	// MemberSearchDto의 내용을 SearchMemberVo으로 변환
	public void fromMemberSearchDto(final MemberSearchDto memberSearchDto) {
		this.account = memberSearchDto.getAccount();
		this.name = memberSearchDto.getName();
	}
}
