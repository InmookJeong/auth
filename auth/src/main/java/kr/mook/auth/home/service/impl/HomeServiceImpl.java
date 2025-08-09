package kr.mook.auth.home.service.impl;

import org.springframework.stereotype.Service;

import kr.mook.auth.common.dto.ResponseDto;
import kr.mook.auth.common.enumeration.LanguageEnum;
import kr.mook.auth.common.enumeration.ResponseTypeEnum;
import kr.mook.auth.home.service.HomeService;

/**
 * Home service Implement<br/>
 * - Home API와 관련하여 로직을 수행하는 Service 구현
 * 
 * @since 2025. 8. 9.
 * @version 0.1
 * @author Inmook, Jeong
 */
@Service
public class HomeServiceImpl implements HomeService {

	@Override
	public ResponseDto home() {
		try {
			return ResponseDto
						.builder()
						.statusCode("200")
						.status("HOME_ACCESS")
						.resultType(ResponseTypeEnum.STRING)
						.result("접속을 환영합니다.")
						.language(LanguageEnum.KOREAN)
						.build();
		} catch (Exception e) {
			return ResponseDto
					.builder()
					.statusCode("400")
					.status("HOME_ACCESS_FAIL")
					.resultType(ResponseTypeEnum.STRING)
					.result("접속 오류가 발생하였습니다. 관리자에게 문의해주세요.")
					.language(LanguageEnum.KOREAN)
					.build();
		}
	}
}
