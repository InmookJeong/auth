package kr.mook.auth.terms.service.search;

import java.util.Locale;

import kr.mook.auth.common.dto.ResponseDto;

/**
 * 이용약관 검색을 위한 서비스 인터페이스<br/>
 * - 이용약관 상세 검색을 위한 Service 제공<br/>
 * - 이용약관 목록 검색을 위한 Service 제공<br/>
 * 
 * @since 2025. 10. 20.
 * @version 0.1
 * @author Inmook, Jeong
 */
public interface SearchTermsService {
	
	/**
	 * 이용약관 번호(Terms NO)를 통해 이용약관 상세 검색
	 * 
	 * @param termsNo : 이용약관 번호
	 * @param locale : 다국어 처리를 위한 언어 정보
	 * @return {<br/>
	 * 				&emsp; "httpStatusCode" : "200",<br/>
	 * 				&emsp; "statusCode" : "TMS-SER-001",<br/>
	 * 				&emsp; "status" : "SEARCH",<br/>
	 * 				&emsp; "resultType" : "object",<br/>
	 * 				&emsp; "result" : {<br/>
	 * 					&emsp;&emsp; "termsNo" : 1,<br/>
	 * 					&emsp;&emsp; "useYn" : true,<br/>
	 * 					&emsp;&emsp; "requireYn" : true,<br/>
	 * 					&emsp;&emsp; "orderNo" : 1,<br/>
	 * 					&emsp;&emsp; "title" : "테스트 - 사이트 이용 약관",<br/>
	 * 					&emsp;&emsp; "contents" : "사이트에 대한 설명과 이용 규칙 및 규정, 광고, 서비스 오류 사항, 운영 정책 등에 대한 내용이 작성됩니다.",<br/>
	 * 					&emsp;&emsp; "createId" : 0,<br/>
	 * 					&emsp;&emsp; "createDate" : "2025-09-28 21:50:08.071",<br/>
	 * 					&emsp;&emsp; "updateId" : NULL,<br/>
	 * 					&emsp;&emsp; "updateDate" : NULL<br/>
	 * 				&emsp; },<br/>
	 * 				&emsp; "language" : "ko-KR"<br/>
	 * 			}
	 */
	public ResponseDto searchByTermsNo(final long termsNo, final Locale locale);

}
