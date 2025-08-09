package kr.mook.auth.home.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import kr.mook.auth.common.dto.ResponseDto;
import kr.mook.auth.home.service.HomeService;
import lombok.AllArgsConstructor;

/**
 * Home API<br/>
 * - 사용자가 Home API를 호출할 수 있도록 기능 구현
 * 
 * @since 2025. 8. 9.
 * @version 0.1
 * @author Inmook, Jeong
 */
@RestController
@AllArgsConstructor
public class HomeController {
	
	private HomeService homeService;
	
	/**
	 * auth 프로젝트의 root path API<br/>
	 * - '/' 또는 '/home'으로 API를 호출될 때 실행
	 * 
	 * @return
	 */
	@GetMapping(value = {"/", "/home"})
	public ResponseEntity<ResponseDto> Home() {
		return ResponseEntity.ok(this.homeService.home());
	}
}
