package kr.mook.auth.member.search.service;

import java.util.Locale;

import kr.mook.auth.common.dto.ResponseDto;

/**
 * 회원 검색을 위한 서비스 인터페이스<br/>
 * - 회원 계정(account)을 통해 회원 정보 상세 조회 Service 제공<br/>
 * 
 * @since 2026. 03. 22.
 * @version 0.1
 * @author Inmook, Jeong
 */
public interface SearchMemberService {
	
	/**
	 * 계정을 통한 회원 정보 상세 조회<br/>
	 * 
	 * @param account : 회원 계정
	 * @param locale : 다국어 처리를 위한 언어 정보
	 * @return responseDto = {<br/>
	 * 				&emsp; "httpStatusCode" : 200,<br/>
	 * 				&emsp; "statusCode" : MEM-SER-001,<br/>
	 * 				&emsp; "status" : "SEARCH",<br/>
	 * 				&emsp; "resultType" : "object",<br/>
	 * 				&emsp; "result" : {<br/>
	 * 					&emsp;&emsp; "memberId" : 1,<br/>
	 * 					&emsp;&emsp; "account" : "kor2026",<br/>
	 * 					&emsp;&emsp; "active" : "Y",<br/>
	 * 					&emsp;&emsp; "name" : "한국인",<br/>
	 * 					&emsp;&emsp; "birth" : "19901231",<br/>
	 * 					&emsp;&emsp; "gender" : "M",<br/>
	 * 					&emsp;&emsp; "email" : "kor2026@korea.co.kr",<br/>
	 * 					&emsp;&emsp; "phoneNumber" : "01012345678",<br/>
	 * 					&emsp;&emsp; "postNumber" : "01123",<br/>
	 * 					&emsp;&emsp; "address" : "서울시 종로구 종로동 종로1가 1번지"<br/>
	 * 				&emsp; },<br/>
	 * 				&emsp; "language" : "ko-KR"<br/>
	 * 			}
	 * @throws Exception
	 */
	public ResponseDto searchByAccount(final String account, final Locale locale) throws Exception;
	
	/**
	 * 회원 아이디(숫자 형식)를 통한 회원 정보 상세 조회<br/>
	 * 
	 * @param memberId
	 * @param locale
	 * @return responseDto = {<br/>
	 * 				&emsp; "httpStatusCode" : 200,<br/>
	 * 				&emsp; "statusCode" : MEM-SER-002,<br/>
	 * 				&emsp; "status" : "SEARCH",<br/>
	 * 				&emsp; "resultType" : "object",<br/>
	 * 				&emsp; "result" : {<br/>
	 * 					&emsp;&emsp; "memberId" : 1,<br/>
	 * 					&emsp;&emsp; "account" : "kor2026",<br/>
	 * 					&emsp;&emsp; "active" : "Y",<br/>
	 * 					&emsp;&emsp; "name" : "한국인",<br/>
	 * 					&emsp;&emsp; "birth" : "19901231",<br/>
	 * 					&emsp;&emsp; "gender" : "M",<br/>
	 * 					&emsp;&emsp; "email" : "kor2026@korea.co.kr",<br/>
	 * 					&emsp;&emsp; "phoneNumber" : "01012345678",<br/>
	 * 					&emsp;&emsp; "postNumber" : "01123",<br/>
	 * 					&emsp;&emsp; "address" : "서울시 종로구 종로동 종로1가 1번지"<br/>
	 * 				&emsp; },<br/>
	 * 				&emsp; "language" : "ko-KR"<br/>
	 * 			}
	 * @throws Exception
	 */
	public ResponseDto searchByMemberId(final long memberId, final Locale locale) throws Exception;
}
