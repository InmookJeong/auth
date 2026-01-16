package kr.mook.auth.home.controller;

import java.util.Locale;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import kr.mook.auth.common.dto.ResponseDto;
import kr.mook.auth.home.service.HomeService;
import lombok.RequiredArgsConstructor;

/**
 * Home API<br/>
 * - 사용자가 Home API를 호출할 수 있도록 기능 구현
 * 
 * @since 2025. 8. 9.
 * @version 0.1
 * @author Inmook, Jeong
 */
@RestController
@RequiredArgsConstructor
public class HomeController {
	
	private final HomeService _homeService;
	
	/**
	 * auth 프로젝트의 root path API<br/>
	 * - '/api/home'으로 API를 호출될 때 실행
	 * 
	 * @param locale : 다국어 처리를 위한 언어 정보. ex)ko-KR
	 * @return {<br/>
					&emsp;"httpStatusCode":"200",<br/>
	 * 				&emsp;"statusCode":"HOM-ACC-001",<br/>
	 * 				&emsp;"status":"HOME ACCESS",<br/>
	 * 				&emsp;"resultType":"string",<br/>
	 * 				&emsp;"result":"접속을 환영합니다.",<br/>
	 * 				&emsp;"language":"ko-KR"<br/>
				}
	 * @since 2025. 08. 09
	 * @version 0.1
	 * @author Inmook, Jeong
	 */
	@GetMapping(value = "/api/home")
	public ResponseEntity<ResponseDto> Home(final Locale locale) {
		return ResponseEntity.ok(this._homeService.home(locale));
	}
}
