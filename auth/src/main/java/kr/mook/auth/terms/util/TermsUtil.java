package kr.mook.auth.terms.util;

import kr.mook.auth.common.dto.ResponseDto;
import kr.mook.auth.common.enumeration.ResponseTypeEnum;
import kr.mook.auth.terms.dto.TermsDto;

/**
 * 이용약관 정보의 정합성을 확인하기 위한 Util<br/>
 * 
 * @since 2006. 02. 11.
 * @version 0.1
 * @author Inmook, Jeong
 */
public class TermsUtil {
	
	/**
	 * 이용약관 정보에 오류가 있는지 검증<br/>
	 * - 이용약관 정보가 Null인 경우<br/>
	 * - 이용약관 정보 중 제목이 입력되지 않은 경우<br/>
	 * - 이용약관 정보 중 내용이 입력되지 않은 경우<br/>
	 * 
	 * @param termsDto : 이용약관 정보
	 * @return
	 * 		&emsp; true : 전달된 이용약관 정보에 문제가 있는 경우<br/>
	 * 		&emsp; false : 전달된 이용약관 정보가 올바른 경우
	 */
	public static boolean isNotValid(final TermsDto termsDto) {
		if(termsDto == null) return true;
		if(termsDto.getTitle() == null || termsDto.getTitle().isBlank()) return true;
		if(termsDto.getContents() == null || termsDto.getContents().isBlank()) return true;

		// 저장할 Dto에 문제가 없으면 Validation 통과
		return false;
	}
	
	/**
	 * 이용약관 정보에 오류가 없는지 검증<br/>
	 * - 이용약관 정보가 전달되고, 제목과 내용이 입력되어 있는 경우<br/>
	 * 
	 * @param termsDto : 이용약관 정보
	 * @return
	 * 		&emsp; true : 전달된 이용약관 정보가 올바른 경우<br/>
	 * 		&emsp; false : 전달된 이용약관 정보에 문제가 있는 경우
	 */
	public static boolean isValid(final TermsDto termsDto) {
		return !isNotValid(termsDto);
	}
	
	/**
	 * 이용약관 정보를 조회, 저장, 수정, 삭제하는 과정에서 오류가 발생할 경우 적절한 응답(ResponseDto)을 반환<br/>
	 * 
	 * @param responseDto : 이용약관 정보 데이터 처리 결과에 대한 응답 정보
	 * @param httpStatus : HTTP 상태 코드
	 * @param statusCode : 상태코드
	 * @param status : 상태코드명(상태 메시지)
	 * @param resultMessage : 결과 메시지
	 * @return responseDto = {<br/>
	 * 				&emsp; "httpStatusCode" : "400",<br/>
	 * 				&emsp; "statusCode" : "ERR-TMS-SAV-004",<br/>
	 * 				&emsp; "staus" : "SAVE ERROR",<br/>
	 * 				&emsp; "resultType" : "string",<br/>
	 * 				&emsp; "result" : "${locale에 따른 에러 메시지}"<br/>
	 * 			}
	 */
	public static ResponseDto getResponseDtoByErrorMessage(ResponseDto responseDto, final String httpStatus, final String statusCode, final String status, final String resultMessage) {
		// 그 외 알 수 없는 오류가 발생한 경우
		responseDto.setHttpStatusCode(httpStatus);
		responseDto.setStatusCode(statusCode);
		responseDto.setStatus(status);
		responseDto.setResultType(ResponseTypeEnum.STRING);
		responseDto.setResult(resultMessage);
		return responseDto;
	}
	
	/**
	 * 이용약관 정보를 조회, 저장, 수정, 삭제하는 과정에서 오류가 발생할 경우 적절한 응답(ResponseDto)을 반환<br/>
	 * 
	 * @param responseDto : 이용약관 정보 데이터 처리 결과에 대한 응답 정보
	 * @param httpStatus : HTTP 상태 코드
	 * @param statusCode : 상태코드
	 * @param status : 상태코드명(상태 메시지)
	 * @param resultData : 결과 데이터
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
	public static ResponseDto getResponseDtoByResultObject(ResponseDto responseDto, final String httpStatus, final String statusCode, final String status, final Object resultData) {
		// 그 외 알 수 없는 오류가 발생한 경우
		responseDto.setHttpStatusCode(httpStatus);
		responseDto.setStatusCode(statusCode);
		responseDto.setStatus(status);
		responseDto.setResultType(ResponseTypeEnum.OBJECT);
		responseDto.setResult(resultData);
		return responseDto;
	}
}
