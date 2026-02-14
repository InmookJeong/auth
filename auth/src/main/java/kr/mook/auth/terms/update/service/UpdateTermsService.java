package kr.mook.auth.terms.update.service;

import java.util.Locale;

import kr.mook.auth.common.dto.ResponseDto;
import kr.mook.auth.terms.dto.TermsDto;

/**
 * 이용약관 정보를 수정하기 위한 서비스 인터페이스<br/>
 * 
 * @since 2026. 02. 11.
 * @version 0.1
 * @author Inmook, Jeong
 */
public interface UpdateTermsService {
	
	/**
	 * 이용약관 정보 수정
	 * 
	 * @param termsDto : 수정할 이용약관 정보
	 * @param locale : 다국어 처리를 위한 언어 정보
	 * @return responseDto = {<br/>
	 * 				&emsp; "httpStatusCode" : 200,<br/>
	 * 				&emsp; "statusCode" : "TMS-UPD-001",<br/>
	 * 				&emsp; "status" : "UPDATE",<br/>
	 * 				&emsp; "resultType" : "string",<br/>
	 * 				&emsp; "result" : "이용약관 정보가 수정되었습니다.",<br/>
	 * 				&emsp; "language" : "ko-KR"<br/>
	 * 			}
	 */
	public ResponseDto updateHandler(final TermsDto termsDto, final Locale locale);

}
