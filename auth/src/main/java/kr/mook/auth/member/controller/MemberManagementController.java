package kr.mook.auth.member.controller;

import org.springframework.cglib.core.Local;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import kr.mook.auth.common.dto.ResponseDto;
import kr.mook.auth.member.dto.management.MemberRegisterDto;
import lombok.RequiredArgsConstructor;

/**
 * 회원 정보 관리 API<br/>
 * - 회원 정보를 등록, 수정, 삭제하기 위한 API 제공<br/>
 * 
 * @since 2026. 03. 03.
 * @version 0.1
 * @author Inmook, Jeong
 */
@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/api/member", name = "Member Management API")
public class MemberManagementController {
	
	/**
	 * 회원 정보 등록<br/>
	 * 
	 * @param memberRegisterDto
	 * @param locle
	 * @return
	 */
	@PostMapping("/")
	public ResponseEntity<ResponseDto> register(@RequestBody MemberRegisterDto memberRegisterDto, final Local locle) {
		return null;
	}
	
	/**
	 * 회원 정보 수정<br/>
	 * 
	 * @param memberRegisterDto
	 * @param locle
	 * @return
	 */
	@PutMapping("/")
	public ResponseEntity<ResponseDto> update(@RequestBody MemberRegisterDto memberRegisterDto, final Local locle) {
		return null;
	}
	
	/**
	 * 회원 정보 삭제<br/>
	 * 
	 * @param memberRegisterDto
	 * @param locle
	 * @return
	 */
	@DeleteMapping("/{memberId}")
	public ResponseEntity<ResponseDto> remove(@PathVariable(value = "memberId") final Long memberId, final Local locle) {
		return null;
	}
	
	/**
	 * 회원 정보 활성화<br/>
	 * 
	 * @param memberId
	 * @param locle
	 * @return
	 */
	@PutMapping("/{memberId}/active")
	public ResponseEntity<ResponseDto> active(@PathVariable(value = "memberId") final Long memberId, final Local locle) {
		return null;
	}
	
	/**
	 * 회원 정보 비활성화<br/>
	 * 
	 * @param memberId
	 * @param locle
	 * @return
	 */
	@PutMapping("/{memberId}/deactive")
	public ResponseEntity<ResponseDto> deactive(@PathVariable(value = "memberId") final Long memberId, final Local locle) {
		return null;
	}

}
