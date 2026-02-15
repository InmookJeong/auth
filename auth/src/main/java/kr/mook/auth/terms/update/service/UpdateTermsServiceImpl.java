package kr.mook.auth.terms.update.service;

import java.util.Locale;

import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;

import kr.mook.auth.common.dto.ResponseDto;
import kr.mook.auth.common.http.RestfulApiHttpStatusUtil;
import kr.mook.auth.terms.dto.TermsDto;
import kr.mook.auth.terms.util.TermsUtil;
import lombok.RequiredArgsConstructor;

/**
 * 이용약관 정보 수정을 위한 서비스 구현<br/>
 * 
 * @since 2025. 11. 21.
 * @version 0.1
 * @author Inmook, Jeong
 */
@Service
@RequiredArgsConstructor
public class UpdateTermsServiceImpl implements UpdateTermsService {

	/* 다국어 처리를 위한 MessageSource */
	private final MessageSource _messageSource;

	
	@Override
	public ResponseDto updateHandler(final TermsDto termsDto, final Locale locale) {
		ResponseDto responseDto = ResponseDto.builder()
				.locale(locale)
				.build();
		
		// 전달된 이용약관 정보의 값이 올바르지 않으면 오류 메시지 반환
		if(TermsUtil.isNotValid(termsDto)) {
			return this._returnErrorResponseDto(responseDto, termsDto, locale);
		}
		
		return responseDto;
	}
	
	/**
	 * 전달된 이용약관 정보의 값이 올바르지 않으면 오류 정보를 응답(Response) 정보에 저장<br/>
	 * - 전달된 TermsDto가 Null인 경우 오류 메시지 반환<br/>
	 * - 전달된 TermsDto의 이용약관 번호가 Null이거나 0 이하인 경우 오류 메시지 반환<br/>
	 * - 전달된 TermsDto의 제목이 입력되지 않은 경우 오류 메시지 반환<br/>
	 * - 전달된 TermsDto의 내용이 입력되지 않은 경우 오류 메시지 반환
	 * 
	 * @param responseDto : 수정 결과에 대한 응답 정보
	 * @param termsDto : 수정할 이용약관 정보
	 * @param locale : 다국어 처리를 위한 언어 정보
	 * @return 이용약관 정보 수정 오류 결과 데이터
	 */
	private ResponseDto _returnErrorResponseDto(ResponseDto responseDto, final TermsDto termsDto, final Locale locale) {
		
		// 전달된 TermsDto가 Null인 경우
		if(termsDto == null)
			return this._setStatusByNullError(responseDto, locale);
		
		// 전달된 TermsDto의 이용약관 번호(TermsNo)가 Null이거나 0 이하의 숫자인 경우
		if(termsDto.getTermsNo() == null || termsDto.getTermsNo().compareTo(1L) == -1)
			return this._setStatusByTermsNoError(responseDto, locale);
			
		
		return null;
	}
	
	/**
	 * 수정할 이용약관 정보의 오류가 Null인 경우 ResponseDto에 상태 저장
	 * 
	 * @param responseDto : 수정 결과에 대한 응답 정보
	 * @param locale : 다국어 처리를 위한 언어 정보
	 * @return responseDto = {<br/>
	 * 				&emsp; "httpStatusCode" : "400",<br/>
	 * 				&emsp; "statusCode" : "ERR-TMS-UPD-001",<br/>
	 * 				&emsp; "staus" : "UPDATE ERROR",<br/>
	 * 				&emsp; "resultType" : "string",<br/>
	 * 				&emsp; "result" : "${locale에 따른 에러 메시지}"<br/>
	 * 			}
	 */
	private ResponseDto _setStatusByNullError(ResponseDto responseDto, final Locale locale) {
		return TermsUtil.getResponseDtoByErrorMessage(
					responseDto,
					RestfulApiHttpStatusUtil.BAD_REQUEST_CODE_STRING,
					"ERR-TMS-UPD-001",
					"UPDATE ERROR",
					this._messageSource.getMessage("error.terms.update.terms-is-empty", null, locale)
				);
	}
	
	/**
	 * 수정할 이용약관 정보의 이용약관 번호(TermsNo)가 입력되지 않았거나 0 이하의 숫자가 입력된 경우 ResponseDto에 상태 저장
	 * 
	 * @param responseDto : 수정 결과에 대한 응답 정보
	 * @param locale : 다국어 처리를 위한 언어 정보
	 * @return responseDto = {<br/>
	 * 				&emsp; "httpStatusCode" : "400",<br/>
	 * 				&emsp; "statusCode" : "ERR-TMS-UPD-002",<br/>
	 * 				&emsp; "staus" : "UPDATE ERROR",<br/>
	 * 				&emsp; "resultType" : "string",<br/>
	 * 				&emsp; "result" : "${locale에 따른 에러 메시지}"<br/>
	 * 			}
	 */
	private ResponseDto _setStatusByTermsNoError(ResponseDto responseDto, final Locale locale) {
		return TermsUtil.getResponseDtoByErrorMessage(
				responseDto,
				RestfulApiHttpStatusUtil.BAD_REQUEST_CODE_STRING,
				"ERR-TMS-UPD-002",
				"UPDATE ERROR",
				this._messageSource.getMessage("error.terms.update.terms-no-less-than-or-equals-to-zero", null, locale)
				);
		
	}
}
