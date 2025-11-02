package kr.mook.auth.terms.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import kr.mook.auth.common.dto.ResponseDto;
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
	
	/**
	 * 이용약관 번호(Terms No)를 통해 이용약관 정보 조회
	 * 
	 * @param termsNo : 이용약관 번호
	 * @return {<br/>
					&emsp; "statusCode" : 200,<br/>
					&emsp; "status" : "SEARCH[TERMS_OF_USE]",<br/>
					&emsp; "resultType" : "object",<br/>
					&emsp; "result" : {<br/>
						&emsp;&emsp; "termsNo" : 1,<br/>
						&emsp;&emsp; "requireYn" : 1,<br/>
						&emsp;&emsp; "useYn" : 1,<br/>
						&emsp;&emsp; "orderNo" : 1,<br/>
						&emsp;&emsp; "title" : "사이트 이용 약관",<br/>
						&emsp;&emsp; "content" : "사이트 이용 약관에 대한 상세 설명입니다.",<br/>
						&emsp;&emsp; "createId" : 0<br/>
						&emsp;&emsp; "createDate" : "20250101 12:00:00"<br/>
						&emsp;&emsp; "updateId" : null<br/>
						&emsp;&emsp; "updateDate" : null<br/>
					&emsp; },<br/>
					&emsp; "language" : "ko-KR"<br/>
				}
	 */
	@GetMapping("/{termsNo}")
	public ResponseEntity<ResponseDto> searchByTermsNo(@PathVariable(value = "termsNo") final long termsNo) {
		ResponseDto responseDto = this._serchTermsService.searchByTermsNo(termsNo);
		
		if("400".equals(responseDto.getStatusCode())) {
			return ResponseEntity.badRequest().body(responseDto);
		}
		
		return ResponseEntity.ok(responseDto);
	}
}
