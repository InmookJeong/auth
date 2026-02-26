package kr.mook.auth.terms.remove.service;

import java.util.Locale;

import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;

import kr.mook.auth.common.dto.ResponseDto;
import kr.mook.auth.common.http.RestfulApiHttpStatusUtil;
import kr.mook.auth.terms.remove.persistence.RemoveTermsMapper;
import kr.mook.auth.terms.util.TermsUtil;
import lombok.RequiredArgsConstructor;

/**
 * 이용약관 정보 삭제를 위한 서비스 구현<br/>
 * 
 * @since 2026. 02. 25.
 * @version 0.1
 * @author Inmook, Jeong
 */
@Service
@RequiredArgsConstructor
public class RemoveTermsServiceImpl implements RemoveTermsService {
	
	/* 다국어 처리를 위한 MessageSource */
	private final MessageSource _messageSource;
	
	/* Mapper */
	private final RemoveTermsMapper _removeTermsMapper;

	@Override
	public ResponseDto removeHandler(final Long termsNo, final Locale locale) {
		ResponseDto responseDto = ResponseDto.builder()
				.locale(locale)
				.build();

		if(termsNo <= 0L) {
			return this._getResponseDtoForNotValidTermNo(responseDto, locale);
		}
		
		int deleteCount = this._removeTermsMapper.delete(termsNo);
		if(deleteCount == 0) {
			return this._getResponseDtoForNotFoundData(responseDto, locale);
		}

		return this._getResponseDtoForSuccess(responseDto, locale);
	}
	
	/**
	 * 이용약관 번호(TermsNo)를 1 이상의 숫자로 전달하지 않을 경우 잘못된 요청을 하였음을 반환하도록 DTO 작성
	 * 
	 * @param responseDto
	 * @return responseDto = {<br/>
	 * 				&emsp; "httpStatusCode" : "400",<br/>
	 * 				&emsp; "statusCode" : "ERR-TMS-DEL-001",<br/>
	 * 				&emsp; "staus" : "DELETE ERROR",<br/>
	 * 				&emsp; "resultType" : "string",<br/>
	 * 				&emsp; "result" : "${locale에 따른 에러 메시지}"<br/>
	 * 			}
	 */
	private ResponseDto _getResponseDtoForNotValidTermNo(ResponseDto responseDto, final Locale locale) {
		return TermsUtil.getResponseDtoByMessage(
					responseDto,
					RestfulApiHttpStatusUtil.BAD_REQUEST_CODE_STRING,
					"ERR-TMS-DEL-001",
					"DELETE ERROR",
					this._messageSource.getMessage("error.terms.remove.terms-no-is-zero", null, locale)
				);
	}
	
	/**
	 * 이용약관 정보 삭제를 실행하였지만 실제 삭제된 데이터가 없는 경우<br/>
	 * 
	 * @param responseDto
	 * @return responseDto = {<br/>
	 * 				&emsp; "httpStatusCode" : "404",<br/>
	 * 				&emsp; "statusCode" : "ERR-TMS-DEL-002",<br/>
	 * 				&emsp; "staus" : "DELETE ERROR",<br/>
	 * 				&emsp; "resultType" : "string",<br/>
	 * 				&emsp; "result" : "${locale에 따른 에러 메시지}"<br/>
	 * 			}
	 */
	private ResponseDto _getResponseDtoForNotFoundData(ResponseDto responseDto, final Locale locale) {
		return TermsUtil.getResponseDtoByMessage(
					responseDto,
					RestfulApiHttpStatusUtil.NOT_FOUND_CODE_STRING,
					"ERR-TMS-DEL-002",
					"DELETE ERROR",
					this._messageSource.getMessage("error.terms.remove.terms-not-found", null, locale)
				);
	}
	
	/**
	 * 이용약관 데이터를 찾아 결과를 반환하도록 DTO 작성
	 * 
	 * @param responseDto
	 * @param locale
	 * @return responseDto = {<br/>
	 * 				&emsp; "httpStatusCode" : "200",<br/>
	 * 				&emsp; "statusCode" : "TMS-DEL-001",<br/>
	 * 				&emsp; "staus" : "DELETE",<br/>
	 * 				&emsp; "resultType" : "object",<br/>
	 * 				&emsp; "result" : "${locale에 따른 에러 메시지}"<br/>
	 * 			}
	 */
	private ResponseDto _getResponseDtoForSuccess(ResponseDto responseDto, final Locale locale) {
		return TermsUtil.getResponseDtoByMessage(
					responseDto,
					RestfulApiHttpStatusUtil.OK_CODE_STRING,
					"TMS-DEL-001",
					"DELETE",
					this._messageSource.getMessage("terms.remove", null, locale)
				);
	}

}
