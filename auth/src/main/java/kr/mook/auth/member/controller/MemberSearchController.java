package kr.mook.auth.member.controller;

import java.util.Locale;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import kr.mook.auth.common.dto.ResponseDto;
import kr.mook.auth.member.dto.search.MemberSearchDto;
import lombok.RequiredArgsConstructor;

/**
 * 회원 정보 조회 API<br/>
 * - 회원 정보 상세 조회 및 목록 조회를 하기 위한 API 제공<br/>
 * - 회원 정보를 찾기 위한 아이디 찾기, 비밀번호 찾기 등의 기능 제공<br/>
 * 
 * @since 2026. 03. 03.
 * @version 0.1
 * @author Inmook, Jeong
 */
@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/api/member", name = "Member Search API")
public class MemberSearchController {
	
	/**
	 * 회원 계정을 통해 회원 정보 조회<br/>
	 * 
	 * @param account
	 * @param locale
	 * @return
	 */
	@GetMapping("/{account}")
	public ResponseEntity<ResponseDto> searchByAccount(@PathVariable(value = "account") final String account, final Locale locale) {
		
		return null;
	}
	
	/**
	 * 회원 ID(숫자)를 통해 회원 정보 조회<br/>
	 * 
	 * @param memberId
	 * @param locale
	 * @return
	 */
	@GetMapping("/id/{memberId}")
	public ResponseEntity<ResponseDto> searchByMemberId(@PathVariable(value = "memberId") final long memberId, final Locale locale) {
		
		return null;
	}
	
	/**
	 * 회원 계정, 회원 아이디, 회원 이름 등 검색 조건을 통해 회원 목록 조회<br/>
	 * - 전달된 검색 조건이 없는 경우 모든 회원 목록을 반환
	 * 
	 * @param memberSearchDto
	 * @param locale
	 * @return
	 */
	@PostMapping("/list")
	public ResponseEntity<ResponseDto> members(@RequestBody MemberSearchDto memberSearchDto, final Locale locale) {
		
		return null;
	}
}
