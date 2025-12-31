package kr.mook.auth.terms.service.save;

import java.util.Locale;

import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;

import kr.mook.auth.common.dto.ResponseDto;
import kr.mook.auth.common.enumeration.ResponseTypeEnum;
import kr.mook.auth.common.http.RestfulApiStatusUtil;
import kr.mook.auth.terms.terms.TermsDto;
import lombok.RequiredArgsConstructor;

/**
 * 이용약관 저장을 위한 서비스 구현<br/>
 * 
 * @since 2025. 11. 21.
 * @version 0.1
 * @author Inmook, Jeong
 */
@Service
@RequiredArgsConstructor
public class SaveTermsServiceImpl implements SaveTermsService {
	
	/* 다국어 처리를 위한 MessageSource */
	private final MessageSource _messageSource;

	/**
	 * 이용약관 정보 저장
	 * 
	 * @return
	 */
	@Override
	public ResponseDto save(TermsDto termsDto, Locale locale) {
		ResponseDto responseDto = ResponseDto.builder()
											.locale(locale)
											.build();
		
		// 전달된 이용약관 정보의 값이 올바르지 않은지 확인
		responseDto = _setResponseDtoStatus(responseDto, termsDto, locale);
		return responseDto;
	}
	
	/**
	 * 전달된 이용약관 정보의 값이 올바르지 않은지 확인<br/>
	 * - 전달된 TermsDto가 Null인지 확인
	 * 
	 * @param termsDto
	 * @return
	 */
	private ResponseDto _setResponseDtoStatus(ResponseDto responseDto, TermsDto termsDto, Locale locale) {
		
		// 전달된 termsDto가 Null인 경우
		if(termsDto == null) {
			responseDto.setStatus("SAVE[DATA IS NULL]");
			responseDto.setStatusCode(RestfulApiStatusUtil.BAD_REQUEST_CODE_STRING);
			responseDto.setResultType(ResponseTypeEnum.STRING);
			responseDto.setResult(this._messageSource.getMessage("error.terms.save.terms-is-empty", null, locale));
		} else if(termsDto.getTitle() == null || "".equals(termsDto.getTitle())) {
			String fieldName = this._messageSource.getMessage("title", null, locale);
			responseDto.setStatus("SAVE ERROR[TERMS TITLE IS EMPTY]");
			responseDto.setStatusCode(RestfulApiStatusUtil.BAD_REQUEST_CODE_STRING);
			responseDto.setResultType(ResponseTypeEnum.STRING);
			responseDto.setResult(this._messageSource.getMessage("error.terms.save.terms-data-is-empty", new String[] {fieldName}, null, locale));
		}
		
		return responseDto;
	}
}
