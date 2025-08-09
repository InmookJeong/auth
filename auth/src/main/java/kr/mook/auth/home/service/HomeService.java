package kr.mook.auth.home.service;

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
	 * @return {"statusCode":"200","status":"HOME_ACCESS","resultType":"string","result":"접속을 환영합니다.","language":"ko-KR"}
	 */
	public ResponseDto home();

}
