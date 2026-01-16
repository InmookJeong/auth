package kr.mook.auth.home.service;

import java.util.Locale;

import kr.mook.auth.common.dto.ResponseDto;

/**
 * Home service<br/>
 * - Home API와 관련하여 로직을 수행하는 Service
 * 
 * @since 2025. 8. 9.
 * @version 0.1
 * @author Inmook, Jeong
 */
public interface HomeService {
	
	/**
	 * auth 프로젝트의 root path API에 대한 로직 수행<br/>
	 * 
	 * @param locale : 다국어 처리를 위한 언어 정보. ex)ko-KR
	 * @return {<br/>
	 * 				&emsp;"httpStatusCode":"200",<br/>
	 * 				&emsp;"statusCode":"HOM-ACC-001",<br/>
	 * 				&emsp;"status":"HOME ACCESS",<br/>
	 * 				&emsp;"resultType":"string",<br/>
	 * 				&emsp;"result":"접속을 환영합니다.",<br/>
	 * 				&emsp;"language":"ko-KR"<br/>
	 * 			}
	 */
	public ResponseDto home(final Locale locale);

}
