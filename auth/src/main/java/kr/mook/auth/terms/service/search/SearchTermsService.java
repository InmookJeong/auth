package kr.mook.auth.terms.service.search;

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
	 * @param termsNo
	 * @return {"statusCode":"200","status":"SEARCH[TERMS]","resultType":"object","result":{"termsNo":1, "useYn": "Y", "requireYn": "Y", "orderNo":1, "title": "테스트 - 사이트 이용 약관", "contents": "사이트에 대한 설명과 이용 규칙 및 규정, 광고, 서비스 오류 사항, 운영 정책 등에 대한 내용이 작성됩니다.", "createId": 0, "createDate":"20250928 21:50:08", "updateId":null, "updateDate":""},"language":"ko-KR"}
	 */
	public ResponseDto searchByTermsNo(final long termsNo);

}
