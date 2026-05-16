package kr.mook.auth.member.dto.search;

import lombok.Data;
import lombok.RequiredArgsConstructor;

/**
 * 회원 검색을 위한 DTO<br/>
 * - 회원 계정, 회원 이름 등의 정보를 기준으로 회원 검색
 * 
 * @since 2026. 03. 03.
 * @version 0.1
 * @author Inmook, Jeong
 */
@Data
@RequiredArgsConstructor
public class MemberSearchDto {
	
	// 회원 계정
	private String account;
	
	// 회원 이름
	private String name;
}
