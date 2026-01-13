package kr.mook.auth.terms.controller;

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
import kr.mook.auth.terms.dto.TermsDto;
import kr.mook.auth.terms.service.save.SaveTermsService;
import kr.mook.auth.terms.service.search.SearchTermsService;
import lombok.RequiredArgsConstructor;

/**
 * 플랫폼 이용약관 API<br/>
 * - 플랫폼 이용약관 데이터를 저장, 수정, 삭제, 조회하기 위한 API 제공<br/>
 * 
 * @since 2025. 10. 20.
 * @version 0.1
 * @author Inmook, Jeong
 */
@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/api/terms", name = "Terms API")
public class TermsController {
	
	/* Service */
	private final SearchTermsService _serchTermsService;
	private final SaveTermsService _saveTermsService;
	
	/**
	 * 이용약관 번호(Terms No)를 통해 이용약관 정보 조회
	 * 
	 * @param termsNo : 이용약관 번호
	 * @return responseDto = {<br/>
	 * 				&emsp; "httpStatusCode" : 200,<br/>
	 * 				&emsp; "statusCode" : TMS-SER-001,<br/>
	 * 				&emsp; "status" : "SEARCH",<br/>
	 * 				&emsp; "resultType" : "object",<br/>
	 * 				&emsp; "result" : {<br/>
	 * 					&emsp;&emsp; "termsNo" : 1,<br/>
	 * 					&emsp;&emsp; "requireYn" : 1,<br/>
	 * 					&emsp;&emsp; "useYn" : 1,<br/>
	 * 					&emsp;&emsp; "orderNo" : 1,<br/>
	 * 					&emsp;&emsp; "title" : "사이트 이용 약관",<br/>
	 * 					&emsp;&emsp; "content" : "사이트 이용 약관에 대한 상세 설명입니다.",<br/>
	 * 					&emsp;&emsp; "createId" : 0<br/>
	 * 					&emsp;&emsp; "createDate" : "20250101 12:00:00"<br/>
	 * 					&emsp;&emsp; "updateId" : null<br/>
	 * 					&emsp;&emsp; "updateDate" : null<br/>
	 * 				&emsp; },<br/>
	 * 				&emsp; "language" : "ko-KR"<br/>
	 * 			}
	 */
	@GetMapping("/{termsNo}")
	public ResponseEntity<ResponseDto> searchByTermsNo(@PathVariable(value = "termsNo") final long termsNo, final Locale locale) {
		ResponseDto responseDto = this._serchTermsService.searchByTermsNo(termsNo, locale);
		
		// 잘못된 TermsNo가 전달된 경우
		if("400".equals(responseDto.getHttpStatusCode())) {
			return ResponseEntity.badRequest().body(responseDto);
		}
		
		// 올바른 TermsNo가 전달되었지만 데이터가 없는 경우
		if("404".equals(responseDto.getHttpStatusCode())) {
			return ResponseEntity.status(404).body(responseDto);
		}
		
		// 데이터를 찾은 경우
		return ResponseEntity.ok(responseDto);
	}
	
	/**
	 * 이용약관 정보 저장
	 * 
	 * @param termsDto : 저장할 이용약관 정보
	 * @param locale
	 * @return responseDto = {<br/>{<br/>
	 * 				&emsp; "httpStatusCode" : 200,<br/>
	 * 				&emsp; "statusCode" : TMS-SAV-001,<br/>
	 * 				&emsp; "status" : "SAVE",<br/>
	 * 				&emsp; "resultType" : "object",<br/>
	 * 				&emsp; "result" : {<br/>
	 * 					&emsp;&emsp; "message" : "이용약관 정보가 저장되었습니다.",<br/>
	 * 					&emsp;&emsp; "termsNo" : 1<br/>
	 * 				&emsp; },<br/>
	 * 				&emsp; "language" : "ko-KR"<br/>
	 * 			}
	 */
	@PostMapping
	public ResponseEntity<ResponseDto> save(@RequestBody(required = false) TermsDto termsDto, final Locale locale) {
		ResponseDto responseDto = this._saveTermsService.saveHandler(termsDto, locale);
		
		if(ResponseDtoUtil.isStatusBadRequest(responseDto)) {
			return ResponseEntity.badRequest().body(responseDto);
		}
		
		// 저장이 된 경우
		return ResponseEntity.ok(responseDto);
	}
}
