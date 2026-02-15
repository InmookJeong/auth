package kr.mook.auth.terms.update.service;

import java.util.Locale;

import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;

import kr.mook.auth.common.dto.ResponseDto;
import kr.mook.auth.common.http.RestfulApiHttpStatusUtil;
import kr.mook.auth.terms.dto.TermsCommandResponseDto;
import kr.mook.auth.terms.dto.TermsDto;
import kr.mook.auth.terms.update.persistence.UpdateTermsMapper;
import kr.mook.auth.terms.util.TermsUtil;
import kr.mook.auth.terms.vo.TermsVo;
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
	
	/* Mapper */
	private final UpdateTermsMapper _updateTermsMapper;
	
	@Override
	public ResponseDto updateHandler(final TermsDto termsDto, final Locale locale) {
		ResponseDto responseDto = ResponseDto.builder()
				.locale(locale)
				.build();
		
		// 전달된 이용약관 정보의 값이 올바르지 않으면 오류 메시지 반환
		if(TermsUtil.isNotValid(termsDto)) {
			return this._returnErrorResponseDto(responseDto, termsDto, locale);
		}
		
		return this._update(responseDto, termsDto, locale);
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
		
		// 전달된 TermsDto에 제목이 입력되지 않은 경우
		if(termsDto.getTitle() == null || termsDto.getTitle().isBlank())
			return this._setStatusByNoDataError(responseDto, "title", locale);
		
		// 전달된 TermsDto에 내용이 입력되지 않은 경우
		if(termsDto.getContents() == null || termsDto.getContents().isBlank())
			return this._setStatusByNoDataError(responseDto, "contents", locale);
		
		// 그 외 알 수 없는 오류가 발생한 경우
		return this._setStatusByUnknownError(responseDto, locale);
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
	
	/**
	 * 수정할 이용약관 정보 중 title, contents 등과 같은 필수 입력 항목의 값이 없는 경우 ResponseDto에 상태 저장
	 * 
	 * @param responseDto : 수정 결과에 대한 응답 정보
	 * @param targetFieldName : 값을 검증할 항목 이름
	 * @param locale : 다국어 처리를 위한 언어 정보
	 * @return responseDto = {<br/>
	 * 				&emsp; "httpStatusCode" : "400",<br/>
	 * 				&emsp; "statusCode" : "ERR-TMS-UPD-003",<br/>
	 * 				&emsp; "staus" : "UPDATE ERROR",<br/>
	 * 				&emsp; "resultType" : "string",<br/>
	 * 				&emsp; "result" : "${locale에 따른 에러 메시지}"<br/>
	 * 			}
	 */
	private ResponseDto _setStatusByNoDataError(ResponseDto responseDto, final String targetFieldName, final Locale locale) {
		String fieldName = this._messageSource.getMessage(targetFieldName, null, locale);
		String errorMessageNo = targetFieldName.equalsIgnoreCase("title") ? "003" : "004";
		return TermsUtil.getResponseDtoByErrorMessage(
					responseDto,
					RestfulApiHttpStatusUtil.BAD_REQUEST_CODE_STRING,
					"ERR-TMS-UPD-" + errorMessageNo,
					"UPDATE ERROR",
					this._messageSource.getMessage("error.terms.update.terms-data-is-empty", new String[] {fieldName}, null, locale)
				);
	}
	
	/**
	 * 수정할 이용약관 정보에 오류가 발생하였으나 오류 원인을 알 수 없는 경우 ResponseDto에 상태 저장
	 * 
	 * @param responseDto : 수정 결과에 대한 응답 정보
	 * @param locale : 다국어 처리를 위한 언어 정보
	 * @return responseDto = {<br/>
	 * 				&emsp; "httpStatusCode" : "400",<br/>
	 * 				&emsp; "statusCode" : "ERR-TMS-UPD-005",<br/>
	 * 				&emsp; "staus" : "UPDATE ERROR",<br/>
	 * 				&emsp; "resultType" : "string",<br/>
	 * 				&emsp; "result" : "${locale에 따른 에러 메시지}"<br/>
	 * 			}
	 */
	private ResponseDto _setStatusByUnknownError(ResponseDto responseDto, final Locale locale) {
		return TermsUtil.getResponseDtoByErrorMessage(
					responseDto,
					RestfulApiHttpStatusUtil.BAD_REQUEST_CODE_STRING,
					"ERR-TMS-UPD-005",
					"UPDATE ERROR",
					this._messageSource.getMessage("error.terms.save", null, locale)
				);
	}
	
	/**
	 * 이용약관 정보 수정
	 * 
	 * @param responseDto : 수정 결과에 대한 응답 정보
	 * @param termsDto : 수정할 이용약관 정보
	 * @param locale : 다국어 처리를 위한 언어 정보
	 * @return 이용약관 정보 수정 결과 데이터
	 */
	private ResponseDto _update(ResponseDto responseDto, final TermsDto termsDto, final Locale locale) {
		
		// TermsDto의 값을 TermsVo로 복사
		TermsVo termsVo = TermsVo.builder().build();
		termsVo.fromTermsDto(termsDto);
		
		// 실제 update / 정상적이면 200 반환, 비정상적인 경우 500 반환
		try {
			this._updateTermsMapper.update(termsVo);
			responseDto = this._updateResponse(responseDto, termsVo, locale);
		} catch (Exception e) {
			responseDto = this._updateError(responseDto, locale);
		}
		
		return responseDto;
	}
	
	/**
	 * 이용약관 정보를 수정하는 과정에서 오류가 발생할 경우 반환 정보 전달
	 * 
	 * @param responseDto : 수정 결과에 대한 응답 정보
	 * @param locale : 다국어 처리를 위한 언어 정보
	 * @return responseDto = {<br/>
	 * 				&emsp; "httpStatusCode" : "500",<br/>
	 * 				&emsp; "statusCode" : "ERR-TMS-UPD-006",<br/>
	 * 				&emsp; "staus" : "UPDATE ERROR",<br/>
	 * 				&emsp; "resultType" : "string",<br/>
	 * 				&emsp; "result" : "${locale에 따른 에러 메시지}"<br/>
	 * 			}
	 */
	private ResponseDto _updateError(ResponseDto responseDto, final Locale locale) {
		return TermsUtil.getResponseDtoByErrorMessage(
					responseDto,
					RestfulApiHttpStatusUtil.INTERNAL_SERVER_ERROR_CODE_STRING,
					"ERR-TMS-UPD-006",
					"UPDATE ERROR",
					this._messageSource.getMessage("error.terms.update", null, locale)
				);
	}
	
	/**
	 * 이용약관 정보 수정 성공 시 반환 정보
	 * 
	 * @param responseDto : 수정 결과에 대한 응답 정보
	 * @param termsVo : 수정된 이용약관 정보
	 * @param locale : 다국어 처리를 위한 언어 정보
	 * @return responseDto = {<br/>
	 * 				&emsp; "httpStatusCode" : "200",<br/>
	 * 				&emsp; "statusCode" : "TMS-UPD-001",<br/>
	 * 				&emsp; "staus" : "UPDATE",<br/>
	 * 				&emsp; "resultType" : "object",<br/>
	 * 				&emsp; "result" : {<br/>
	 * 				&emsp;&emsp; "termsNo" : ${수정한 이용약관번호}<br/>
	 * 				&emsp;&emsp; "message" : "${locale에 따른 에러 메시지}"<br/>
	 * 				&emsp; }<br/>
	 * 			}
	 */
	private ResponseDto _updateResponse(ResponseDto responseDto, final TermsVo termsVo, final Locale locale) {
		String message = this._messageSource.getMessage("terms.update", null, locale);
		return TermsUtil.getResponseDtoByResultObject(
					responseDto,
					RestfulApiHttpStatusUtil.OK_CODE_STRING,
					"TMS-UPD-001",
					"UPDATE",
					new TermsCommandResponseDto(termsVo.getTermsNo(), message)
				);
	}
}
