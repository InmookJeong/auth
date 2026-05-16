package kr.mook.auth.member.dto.search.list;

import java.util.List;

import kr.mook.auth.member.vo.MemberVo;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

/**
 * 회원 목록 검색 결과를 위한 DTO<br/>
 * - 회원 계정, 회원 아이디, 회원 명 등의 정보를 기준으로 회원 검색
 * 
 * @since 2026. 05. 16.
 * @version 0.1
 * @author Inmook, Jeong
 */
@RequiredArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class MembersDto {
	
	// 목록 조회 결과 건수
	private long count;
	
	// 조회된 회원 목록
	private List<MemberVo> members;
	
	// 조회 결과 메시지
	private String message;
	
}
