package kr.mook.auth.common.dto;

import kr.mook.auth.common.http.RestfulApiHttpStatusUtil;

/**
 * ResponseDto의 상태와 타입, 언어 등을 검증
 * 
 * @since 2025. 12. 17.
 * @version 0.1
 * @author Inmook, Jeong
 */
public class ResponseDtoUtil {
	
	// ######################################
	// #####    2XX HTTP STATUS CODE    #####
	// ######################################
	
	/**
	 * 응답을 위한 HTTP 상태코드가 OK(CODE:200)인지 확인
	 * 
	 * @param responseDto
	 * @return 응답을 위한 HTTP 상태코드가 OK(CODE:200)이면 true, 아니면 false를 반환
	 */
	public static boolean isStatusOk(ResponseDto responseDto) {
		return RestfulApiHttpStatusUtil.OK_CODE_STRING.equalsIgnoreCase(responseDto.getHttpStatusCode());
	}
	
	/**
	 * 응답을 위한 HTTP 상태코드가 CREATED(CODE:201)인지 확인
	 * 
	 * @param responseDto
	 * @return 응답을 위한 HTTP 상태코드가 CREATED(CODE:201)이면 true, 아니면 false를 반환
	 */
	public static boolean isStatusCreated(ResponseDto responseDto) {
		return RestfulApiHttpStatusUtil.CREATE_CODE_STRING.equalsIgnoreCase(responseDto.getHttpStatusCode());
	}
	
	// ######################################
	// #####    4XX HTTP STATUS CODE    #####
	// ######################################
	
	/**
	 * 응답을 위한 HTTP 상태코드가 BAD REQUEST(CODE:400)인지 확인
	 * 
	 * @param responseDto
	 * @return 응답을 위한 HTTP 상태코드가 BAD REQUEST(CODE:400)이면 true, 아니면 false를 반환
	 */
	public static boolean isStatusBadRequest(ResponseDto responseDto) {
		return RestfulApiHttpStatusUtil.BAD_REQUEST_CODE_STRING.equalsIgnoreCase(responseDto.getHttpStatusCode());
	}
	
	/**
	 * 응답을 위한 HTTP 상태코드가 FORBIDDEN(CODE:403)인지 확인
	 * 
	 * @param responseDto
	 * @return 응답을 위한 HTTP 상태코드가 FORBIDDEN(CODE:403)이면 true, 아니면 false를 반환
	 */
	public static boolean isStatusForbidden(ResponseDto responseDto) {
		return RestfulApiHttpStatusUtil.FORBIDDEN_CODE_STRING.equalsIgnoreCase(responseDto.getHttpStatusCode());
	}
	
	/**
	 * 응답을 위한 HTTP 상태코드가 NOT FOUND(CODE:404)인지 확인
	 * 
	 * @param responseDto
	 * @return 응답을 위한 HTTP 상태코드가 NOT FOUND(CODE:404)이면 true, 아니면 false를 반환
	 */
	public static boolean isStatusNotFound(ResponseDto responseDto) {
		return RestfulApiHttpStatusUtil.NOT_FOUND_CODE_STRING.equalsIgnoreCase(responseDto.getHttpStatusCode());
	}
	
	// ######################################
	// #####    5XX HTTP STATUS CODE    #####
	// ######################################
	
	/**
	 * 응답을 위한 HTTP 상태코드가 INTERNAL SERVER ERROR(CODE:500)인지 확인
	 * 
	 * @param responseDto
	 * @return 응답을 위한 HTTP 상태코드가 INTERNAL SERVER ERROR(CODE:500)이면 true, 아니면 false를 반환
	 */
	public static boolean isStatusInternalServerError(ResponseDto responseDto) {
		return RestfulApiHttpStatusUtil.INTERNAL_SERVER_ERROR_CODE_STRING.equalsIgnoreCase(responseDto.getHttpStatusCode());
	}
}
