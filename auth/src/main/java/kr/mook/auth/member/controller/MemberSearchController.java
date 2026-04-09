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
import kr.mook.auth.common.dto.ResponseDtoUtil;
import kr.mook.auth.member.dto.search.MemberSearchDto;
import kr.mook.auth.member.search.service.SearchMemberService;
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
	
	/* Service */
	private final SearchMemberService _searchMemberService;
	
	/**
	 * 회원 계정을 통해 회원 정보 조회<br/>
	 * 
	 * @param account
	 * @param locale
	 * @return responseDto = {<br/>
	 * 				&emsp; "httpStatusCode" : 200,<br/>
	 * 				&emsp; "statusCode" : MEM-SER-001,<br/>
	 * 				&emsp; "status" : "SEARCH",<br/>
	 * 				&emsp; "resultType" : "object",<br/>
	 * 				&emsp; "result" : {<br/>
	 * 					&emsp;&emsp; "memberId" : 1,<br/>
	 * 					&emsp;&emsp; "account" : "kor2026",<br/>
	 * 					&emsp;&emsp; "active" : "Y",<br/>
	 * 					&emsp;&emsp; "name" : "한국인",<br/>
	 * 					&emsp;&emsp; "birth" : "19901231",<br/>
	 * 					&emsp;&emsp; "gender" : "M",<br/>
	 * 					&emsp;&emsp; "email" : "kor2026@korea.co.kr",<br/>
	 * 					&emsp;&emsp; "phoneNumber" : "01012345678",<br/>
	 * 					&emsp;&emsp; "postNumber" : "01123",<br/>
	 * 					&emsp;&emsp; "address" : "서울시 종로구 종로동 종로1가 1번지"<br/>
	 * 				&emsp; },<br/>
	 * 				&emsp; "language" : "ko-KR"<br/>
	 * 			}
	 */
	@GetMapping("/{account}")
	public ResponseEntity<ResponseDto> searchByAccount(@PathVariable(value = "account") final String account, final Locale locale) throws Exception {
		ResponseDto responseDto = this._searchMemberService.searchByAccount(account, locale);
		
		// 계정으로 회원 정보를 찾을 수 없는 경우
		if(ResponseDtoUtil.isStatusNotFound(responseDto)) {
			return ResponseEntity.status(404).body(responseDto);
		}
		
		// 서버 오류로 인해 회원 정보를 조회할 수 없는 경우
		if(ResponseDtoUtil.isStatusInternalServerError(responseDto)) {
			return ResponseEntity.internalServerError().body(responseDto);
		}
		
		// 회원 조회 성공
		return ResponseEntity.ok(responseDto);
	}
	
	/**
	 * 회원 ID(숫자)를 통해 회원 정보 조회<br/>
	 * 
	 * @param memberId
	 * @param locale
	 * @return responseDto = {<br/>
	 * 				&emsp; "httpStatusCode" : 200,<br/>
	 * 				&emsp; "statusCode" : MEM-SER-002,<br/>
	 * 				&emsp; "status" : "SEARCH",<br/>
	 * 				&emsp; "resultType" : "object",<br/>
	 * 				&emsp; "result" : {<br/>
	 * 					&emsp;&emsp; "memberId" : 1,<br/>
	 * 					&emsp;&emsp; "account" : "kor2026",<br/>
	 * 					&emsp;&emsp; "active" : "Y",<br/>
	 * 					&emsp;&emsp; "name" : "한국인",<br/>
	 * 					&emsp;&emsp; "birth" : "19901231",<br/>
	 * 					&emsp;&emsp; "gender" : "M",<br/>
	 * 					&emsp;&emsp; "email" : "kor2026@korea.co.kr",<br/>
	 * 					&emsp;&emsp; "phoneNumber" : "01012345678",<br/>
	 * 					&emsp;&emsp; "postNumber" : "01123",<br/>
	 * 					&emsp;&emsp; "address" : "서울시 종로구 종로동 종로1가 1번지"<br/>
	 * 				&emsp; },<br/>
	 * 				&emsp; "language" : "ko-KR"<br/>
	 * 			}
	 */
	@GetMapping("/id/{memberId}")
	public ResponseEntity<ResponseDto> searchByMemberId(@PathVariable(value = "memberId") final long memberId, final Locale locale) throws Exception {
		ResponseDto responseDto = this._searchMemberService.searchByMemberId(memberId, locale);
		
		// 회원 아이디로 회원 정보를 찾을 수 없는 경우
		if(ResponseDtoUtil.isStatusNotFound(responseDto)) {
			return ResponseEntity.status(404).body(responseDto);
		}
		
		// 회원 조회 성공
		return ResponseEntity.ok(responseDto);
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
	public ResponseEntity<ResponseDto> members(@RequestBody MemberSearchDto memberSearchDto, final Locale locale) throws Exception {
		
		return null;
	}
}
