package kr.mook.auth.terms.remove.service;

import java.util.Locale;

import kr.mook.auth.common.dto.ResponseDto;

/**
 * 이용약관 정보를 수정하기 위한 서비스 인터페이스<br/>
 * 
 * @since 2026. 02. 22.
 * @version 0.1
 * @author Inmook, Jeong
 */
public interface RemoveTermsService {

	/**
	 * 이용약관 정보 삭제
	 * 
	 * @param termsNo : 삭제할 이용약관 번호
	 * @param locale : 다국어 처리를 위한 언어 정보
	 * @return responseDto = {<br/>
	 * 				&emsp; "httpStatusCode" : 200,<br/>
	 * 				&emsp; "statusCode" : "TMS-DEL-001",<br/>
	 * 				&emsp; "status" : "UPDATE",<br/>
	 * 				&emsp; "resultType" : "string",<br/>
	 * 				&emsp; "result" : "이용약관 정보가 삭제되었습니다.",<br/>
	 * 				&emsp; "language" : "ko-KR"<br/>
	 * 			}
	 */
	public ResponseDto removeHandler(final Long termsNo, final Locale locale);
}
