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
	 * @return
	 * @throws Exception
	 */
	public ResponseDto searchByAccount(final String account, final Locale locale) throws Exception;
	
}
