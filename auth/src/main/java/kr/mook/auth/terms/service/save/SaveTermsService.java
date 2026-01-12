package kr.mook.auth.terms.service.save;

import java.util.Locale;

import kr.mook.auth.common.dto.ResponseDto;
import kr.mook.auth.terms.dto.TermsDto;

/**
 * 이용약관 저장을 위한 서비스 인터페이스<br/>
 * 
 * @since 2025. 11. 21.
 * @version
 * @author Inmook, Jeong
 */
public interface SaveTermsService {
	
	/**
	 * 이용약관 저장
	 * 
	 * @param termsDto : 저장할 이용약관 정보
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
	public ResponseDto save(TermsDto termsDto, Locale locale);

}
