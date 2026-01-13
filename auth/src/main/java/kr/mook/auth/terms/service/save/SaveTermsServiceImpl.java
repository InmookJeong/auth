package kr.mook.auth.terms.service.save;

import java.util.Locale;

import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;

import kr.mook.auth.common.dto.ResponseDto;
import kr.mook.auth.common.enumeration.ResponseTypeEnum;
import kr.mook.auth.terms.dto.TermsDto;
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
	public ResponseDto saveHandler(TermsDto termsDto, Locale locale) {
		ResponseDto responseDto = ResponseDto.builder()
											.locale(locale)
											.build();
		
		// 전달된 이용약관 정보의 값이 올바르지 않으면 오류 메시지 반환
		if(_isNotValid(termsDto))
			return this._returnErrorResponseDto(responseDto, termsDto, locale);
		
		return responseDto;
	}
	
	/**
	 * 저장할 이용약관 정보에 오류가 있는지 검증<br/>
	 * - 이용약관 정보가 Null인 경우<br/>
	 * - 이용약관 정보 중 제목이 입력되지 않은 경우<br/>
	 * - 이용약관 정보 중 내용이 입력되지 않은 경우<br/>
	 * 
	 * @param termsDto
	 * @return
	 * 		&emsp; true : 저장하기 위해 전달된 이용약관 정보에 문제가 있는 경우<br/>
	 * 		&emsp; false : 저장하기 위해 전달된 이용약관 정보가 올바른 경우
	 */
	public boolean _isNotValid(TermsDto termsDto) {
		if(termsDto == null) return true;
		if(termsDto.getTitle() == null || termsDto.getTitle().isBlank()) return true;
		if(termsDto.getContents() == null || termsDto.getContents().isBlank()) return true;

		// 저장할 Dto에 문제가 없으면 Validation 통과
		return false;
	}
	
	/**
	 * 전달된 이용약관 정보의 값이 올바르지 않으면 오류 정보를 응답(Response) 정보에 저장<br/>
	 * - 전달된 TermsDto가 Null인 경우 오류 메시지 반환<br/>
	 * - 전달된 TermsDto의 제목이 입력되지 않은 경우 오류 메시지 반환<br/>
	 * - 전달된 TermsDto의 내용이 입력되지 않은 경우 오류 메시지 반환
	 * 
	 * @param termsDto
	 * @return 이용약관 정보 저장 오류 결과 데이터
	 */
	private ResponseDto _returnErrorResponseDto(ResponseDto responseDto, TermsDto termsDto, Locale locale) {
		
		// 전달된 TermsDto가 Null인 경우
		if(termsDto == null)
			return this._setStatusByNullError(responseDto, locale);
		
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
	 * 저장할 이용약관 정보의 오류가 Null인 경우 ResponseDto에 상태 저장
	 * 
	 * @param responseDto
	 * @param locale
	 * @return responseDto = {<br/>
	 * 				&emsp; "httpStatusCode" : "400",<br/>
	 * 				&emsp; "statusCode" : "ERR-TMS-SAV-001",<br/>
	 * 				&emsp; "staus" : "SAVE ERROR",<br/>
	 * 				&emsp; "resultType" : "string",<br/>
	 * 				&emsp; "result" : "${locale에 따른 에러 메시지}"<br/>
	 * 			}
	 */
	private ResponseDto _setStatusByNullError(ResponseDto responseDto, Locale locale) {
		responseDto.setHttpStatusCode("400");
		responseDto.setStatusCode("ERR-TMS-SAV-001");
		responseDto.setStatus("SAVE ERROR");
		responseDto.setResultType(ResponseTypeEnum.STRING);
		responseDto.setResult(this._messageSource.getMessage("error.terms.save.terms-is-empty", null, locale));
		return responseDto;
	}
	
	/**
	 * 저장할 이용약관 정보 중 title, contents 등과 같은 필수 입력 항목의 값이 없는 경우 ResponseDto에 상태 저장
	 * 
	 * @param responseDto
	 * @param targetFieldName 값을 검증할 항목 이름
	 * @param locale
	 * @return responseDto = {<br/>
	 * 				&emsp; "httpStatusCode" : "400",<br/>
	 * 				&emsp; "statusCode" : "ERR-TMS-SAV-002",<br/>
	 * 				&emsp; "staus" : "SAVE ERROR",<br/>
	 * 				&emsp; "resultType" : "string",<br/>
	 * 				&emsp; "result" : "${locale에 따른 에러 메시지}"<br/>
	 * 			}
	 */
	private ResponseDto _setStatusByNoDataError(ResponseDto responseDto, String targetFieldName, Locale locale) {
		String fieldName = this._messageSource.getMessage(targetFieldName, null, locale);
		String errorMessageNo = targetFieldName.equalsIgnoreCase("title") ? "002" : "003";
		responseDto.setHttpStatusCode("400");
		responseDto.setStatusCode("ERR-TMS-SAV-" + errorMessageNo);
		responseDto.setStatus("SAVE ERROR");
		responseDto.setResultType(ResponseTypeEnum.STRING);
		responseDto.setResult(this._messageSource.getMessage("error.terms.save.terms-data-is-empty", new String[] {fieldName}, null, locale));
		return responseDto;
	}
	
	/**
	 * 저장할 이용약관 정보에 오류가 발생하였으나 오류 원인을 알 수 없는 경우 ResponseDto에 상태 저장
	 * 
	 * @param responseDto
	 * @param locale
	 * @return responseDto = {<br/>
	 * 				&emsp; "httpStatusCode" : "400",<br/>
	 * 				&emsp; "statusCode" : "ERR-TMS-SAV-004",<br/>
	 * 				&emsp; "staus" : "SAVE ERROR",<br/>
	 * 				&emsp; "resultType" : "string",<br/>
	 * 				&emsp; "result" : "${locale에 따른 에러 메시지}"<br/>
	 * 			}
	 */
	private ResponseDto _setStatusByUnknownError(ResponseDto responseDto, Locale locale) {
		responseDto.setHttpStatusCode("400");
		responseDto.setStatusCode("ERR-TMS-SAV-004");
		responseDto.setStatus("SAVE ERROR");
		responseDto.setResultType(ResponseTypeEnum.STRING);
		responseDto.setResult(this._messageSource.getMessage("error.terms.save.unknown", null, locale));
		return responseDto;
	}
}
