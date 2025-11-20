package kr.mook.auth.home.service.impl;

import java.util.Locale;

import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;

import kr.mook.auth.common.dto.ResponseDto;
import kr.mook.auth.common.enumeration.ResponseTypeEnum;
import kr.mook.auth.home.service.HomeService;
import lombok.RequiredArgsConstructor;

/**
 * Home service Implement<br/>
 * - Home API와 관련하여 로직을 수행하는 Service 구현
 * 
 * @since 2025. 8. 9.
 * @version 0.1
 * @author Inmook, Jeong
 */
@Service
@RequiredArgsConstructor
public class HomeServiceImpl implements HomeService {
	
	/* 다국어 처리를 위한 MessageSource */
	private final MessageSource _messageSource;

	@Override
	public ResponseDto home(final Locale locale) {
		try {
			return ResponseDto
						.builder()
						.statusCode("200")
						.status("HOME_ACCESS")
						.resultType(ResponseTypeEnum.STRING)
						.result(this._messageSource.getMessage("home.welcome", null, locale))
						.locale(locale)
						.build();
		} catch (Exception e) {
			return ResponseDto
					.builder()
					.statusCode("400")
					.status("HOME_ACCESS_FAIL")
					.resultType(ResponseTypeEnum.STRING)
					.result(this._messageSource.getMessage("error.home.welcome", null, locale))
					.locale(locale)
					.build();
		}
	}
}
