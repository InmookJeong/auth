package kr.mook.auth.terms.save.service;

import java.util.Locale;

import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;

import kr.mook.auth.common.dto.ResponseDto;
import kr.mook.auth.common.enumeration.ResponseTypeEnum;
import kr.mook.auth.terms.dto.TermsDto;
import kr.mook.auth.terms.save.dto.SaveResultDto;
import kr.mook.auth.terms.save.persistence.SaveTermsMapper;
import kr.mook.auth.terms.vo.TermsVo;
import lombok.RequiredArgsConstructor;

/**
 * 이용약관 정보 저장을 위한 서비스 구현<br/>
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
	
	/* Mapper */
	private final SaveTermsMapper _saveTermsMapper;

	@Override
	public ResponseDto saveHandler(final TermsDto termsDto, final Locale locale) {
		ResponseDto responseDto = ResponseDto.builder()
											.locale(locale)
											.build();
		
		// 전달된 이용약관 정보의 값이 올바르지 않으면 오류 메시지 반환
		if(_isNotValid(termsDto))
			return this._returnErrorResponseDto(responseDto, termsDto, locale);
		
		return _save(responseDto, termsDto, locale);
	}
	
	/**
	 * 저장할 이용약관 정보에 오류가 있는지 검증<br/>
	 * - 이용약관 정보가 Null인 경우<br/>
	 * - 이용약관 정보 중 제목이 입력되지 않은 경우<br/>
	 * - 이용약관 정보 중 내용이 입력되지 않은 경우<br/>
	 * 
	 * @param termsDto : 저장할 이용약관 정보
	 * @return
	 * 		&emsp; true : 저장하기 위해 전달된 이용약관 정보에 문제가 있는 경우<br/>
	 * 		&emsp; false : 저장하기 위해 전달된 이용약관 정보가 올바른 경우
	 */
	public boolean _isNotValid(final TermsDto termsDto) {
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
	 * @param responseDto : 저장 결과에 대한 응답 정보
	 * @param termsDto : 저장할 이용약관 정보
	 * @param locale : 다국어 처리를 위한 언어 정보
	 * @return 이용약관 정보 저장 오류 결과 데이터
	 */
	private ResponseDto _returnErrorResponseDto(ResponseDto responseDto, final TermsDto termsDto, final Locale locale) {
		
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
	 * @param responseDto : 저장 결과에 대한 응답 정보
	 * @param locale : 다국어 처리를 위한 언어 정보
	 * @return responseDto = {<br/>
	 * 				&emsp; "httpStatusCode" : "400",<br/>
	 * 				&emsp; "statusCode" : "ERR-TMS-SAV-001",<br/>
	 * 				&emsp; "staus" : "SAVE ERROR",<br/>
	 * 				&emsp; "resultType" : "string",<br/>
	 * 				&emsp; "result" : "${locale에 따른 에러 메시지}"<br/>
	 * 			}
	 */
	private ResponseDto _setStatusByNullError(ResponseDto responseDto, final Locale locale) {
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
	 * @param responseDto : 저장 결과에 대한 응답 정보
	 * @param targetFieldName : 값을 검증할 항목 이름
	 * @param locale : 다국어 처리를 위한 언어 정보
	 * @return responseDto = {<br/>
	 * 				&emsp; "httpStatusCode" : "400",<br/>
	 * 				&emsp; "statusCode" : "ERR-TMS-SAV-002",<br/>
	 * 				&emsp; "staus" : "SAVE ERROR",<br/>
	 * 				&emsp; "resultType" : "string",<br/>
	 * 				&emsp; "result" : "${locale에 따른 에러 메시지}"<br/>
	 * 			}
	 */
	private ResponseDto _setStatusByNoDataError(ResponseDto responseDto, final String targetFieldName, final Locale locale) {
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
	 * @param responseDto : 저장 결과에 대한 응답 정보
	 * @param locale : 다국어 처리를 위한 언어 정보
	 * @return responseDto = {<br/>
	 * 				&emsp; "httpStatusCode" : "400",<br/>
	 * 				&emsp; "statusCode" : "ERR-TMS-SAV-004",<br/>
	 * 				&emsp; "staus" : "SAVE ERROR",<br/>
	 * 				&emsp; "resultType" : "string",<br/>
	 * 				&emsp; "result" : "${locale에 따른 에러 메시지}"<br/>
	 * 			}
	 */
	private ResponseDto _setStatusByUnknownError(ResponseDto responseDto, final Locale locale) {
		responseDto.setHttpStatusCode("400");
		responseDto.setStatusCode("ERR-TMS-SAV-004");
		responseDto.setStatus("SAVE ERROR");
		responseDto.setResultType(ResponseTypeEnum.STRING);
		responseDto.setResult(this._messageSource.getMessage("error.terms.save.unknown", null, locale));
		return responseDto;
	}
	
	/**
	 * 이용약관 정보 저장
	 * 
	 * @param responseDto : 저장 결과에 대한 응답 정보
	 * @param termsDto : 저장할 이용약관 정보
	 * @param locale : 다국어 처리를 위한 언어 정보
	 * @return 이용약관 정보 저장 결과 데이터
	 */
	private ResponseDto _save(ResponseDto responseDto, final TermsDto termsDto, final Locale locale) {
		
		// TermsDto의 값을 TermsVo로 복사
		TermsVo termsVo = TermsVo.builder().build();
		termsVo.fromTermsDto(termsDto);
		
		try {
			// create TermsNo
			long termsNo = this._createTermsNo();
			termsVo.setTermsNo(termsNo);
		} catch (Exception e) {
			// 이용약관번호를 발급하는 과정에서 오류가 발생한 경우
			responseDto = this._createTermsNoError(responseDto, locale);
			return responseDto;
		}
		
		// 실제 save / 정상적이면 200 반환, 비정상적인 4xx 반환
		try {
			this._saveTermsMapper.save(termsVo);
			responseDto = this._saveResponse(responseDto, termsVo, locale);
		} catch (Exception e) {
			responseDto = this._saveError(responseDto, locale);
			return responseDto;
		}
		
		return responseDto;
	}
	
	/**
	 * 신규 이용약관번호 생성
	 * 
	 * @return 이용약관번호
	 */
	private long _createTermsNo() {
		return this._saveTermsMapper.nextTermsNo();
	}
	
	/**
	 * 신규 이용약관번호 생성 과정에서 오류가 발생할 경우 반환 정보 전달
	 * 
	 * @param responseDto : 저장 결과에 대한 응답 정보
	 * @param locale : 다국어 처리를 위한 언어 정보
	 * @return responseDto = {<br/>
	 * 				&emsp; "httpStatusCode" : "500",<br/>
	 * 				&emsp; "statusCode" : "ERR-TMS-SAV-005",<br/>
	 * 				&emsp; "staus" : "SAVE ERROR",<br/>
	 * 				&emsp; "resultType" : "string",<br/>
	 * 				&emsp; "result" : "${locale에 따른 에러 메시지}"<br/>
	 * 			}
	 */
	private ResponseDto _createTermsNoError(ResponseDto responseDto, final Locale locale) {
		responseDto.setHttpStatusCode("500");
		responseDto.setStatusCode("ERR-TMS-SAV-005");
		responseDto.setStatus("SAVE ERROR");
		responseDto.setResultType(ResponseTypeEnum.STRING);
		responseDto.setResult(this._messageSource.getMessage("error.terms.save.create-terms-no", null, locale));
		return responseDto;
	}
	
	/**
	 * 이용약관 정보를 저장하는 과정에서 오류가 발생할 경우 반환 정보 전달
	 * 
	 * @param responseDto : 저장 결과에 대한 응답 정보
	 * @param locale : 다국어 처리를 위한 언어 정보
	 * @return responseDto = {<br/>
	 * 				&emsp; "httpStatusCode" : "500",<br/>
	 * 				&emsp; "statusCode" : "ERR-TMS-SAV-006",<br/>
	 * 				&emsp; "staus" : "SAVE ERROR",<br/>
	 * 				&emsp; "resultType" : "string",<br/>
	 * 				&emsp; "result" : "${locale에 따른 에러 메시지}"<br/>
	 * 			}
	 */
	private ResponseDto _saveError(ResponseDto responseDto, final Locale locale) {
		responseDto.setHttpStatusCode("500");
		responseDto.setStatusCode("ERR-TMS-SAV-006");
		responseDto.setStatus("SAVE ERROR");
		responseDto.setResultType(ResponseTypeEnum.STRING);
		responseDto.setResult(this._messageSource.getMessage("error.terms.save", null, locale));
		return responseDto;
	}
	
	/**
	 * 이용약관 저장 성공 시 반환 정보
	 * 
	 * @param responseDto : 저장 결과에 대한 응답 정보
	 * @param termsVo : 저장된 이용약관 정보
	 * @param locale : 다국어 처리를 위한 언어 정보
	 * @return responseDto = {<br/>
	 * 				&emsp; "httpStatusCode" : "200",<br/>
	 * 				&emsp; "statusCode" : "TMS-SAV-001",<br/>
	 * 				&emsp; "staus" : "SAVE",<br/>
	 * 				&emsp; "resultType" : "object",<br/>
	 * 				&emsp; "result" : {<br/>
	 * 				&emsp;&emsp; "termsNo" : ${발급된 이용약관번호}<br/>
	 * 				&emsp;&emsp; "message" : "${locale에 따른 에러 메시지}"<br/>
	 * 				&emsp; }<br/>
	 * 			}
	 */
	private ResponseDto _saveResponse(ResponseDto responseDto, final TermsVo termsVo, final Locale locale) {
		responseDto.setHttpStatusCode("200");
		responseDto.setStatusCode("TMS-SAV-001");
		responseDto.setStatus("SAVE");
		responseDto.setResultType(ResponseTypeEnum.OBJECT);
		
		String message = this._messageSource.getMessage("terms.save", null, locale);
		responseDto.setResult(new SaveResultDto(termsVo.getTermsNo(), message));
		return responseDto;
	}
}
