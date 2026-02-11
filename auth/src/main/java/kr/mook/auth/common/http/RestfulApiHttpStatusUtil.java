package kr.mook.auth.common.http;

import org.springframework.http.HttpStatus;

/**
 * RESTful API를 통해 전달되는 HTTP 상태 코드를 관리
 * 
 * @since 2025. 12. 17.
 * @version 0.1
 * @author Inmook, Jeong
 */
public class RestfulApiHttpStatusUtil {
	
	// ######################################
	// #####    2XX HTTP STATUS CODE    #####
	// ######################################
	
	/**
	 * OK(HTTP STATUS CODE : 200)의 명칭
	 */
	public static String OK_NAME = HttpStatus.OK.name();
	
	/**
	 * OK(HTTP STATUS CODE : 200)의 상태코드 값(문자열) 반환
	 */
	public static String OK_CODE_STRING = String.valueOf(HttpStatus.OK.value());
	
	/**
	 * OK(HTTP STATUS CODE : 200)의 상태코드 값(숫자) 반환
	 */
	public static int OK_CODE_NUMBER = HttpStatus.OK.value();
	
	/**
	 * CREATED(HTTP STATUS CODE : 201)의 명칭
	 */
	public static String CREATE_NAME = HttpStatus.CREATED.name();
	
	/**
	 * CREATED(HTTP STATUS CODE : 201)의 상태코드 값(문자열) 반환
	 */
	public static String CREATE_CODE_STRING = String.valueOf(HttpStatus.CREATED.value());
	
	/**
	 * CREATED(HTTP STATUS CODE : 201)의 상태코드 값(숫자) 반환
	 */
	public static int CREATE_CODE_NUMBER = HttpStatus.CREATED.value();
	
	// ######################################
	// #####    4XX HTTP STATUS CODE    #####
	// ######################################
	
	/**
	 * BAD REQUEST(HTTP STATUS CODE : 400)의 명칭
	 */
	public static String BAD_REQUEST_NAME = HttpStatus.BAD_REQUEST.name();
	
	/**
	 * BAD REQUEST(HTTP STATUS CODE : 400)의 상태코드 값(문자열) 반환
	 */
	public static String BAD_REQUEST_CODE_STRING = String.valueOf(HttpStatus.BAD_REQUEST.value());
	
	/**
	 * BAD REQUEST(HTTP STATUS CODE : 400)의 상태코드 값(숫자) 반환
	 */
	public static int BAD_REQUEST_CODE_NUMBER = HttpStatus.BAD_REQUEST.value();
	
	/**
	 * FORBIDDEN(HTTP STATUS CODE : 403)의 명칭
	 */
	public static String FORBIDDEN_NAME = HttpStatus.FORBIDDEN.name();
	
	/**
	 * FORBIDDEN(HTTP STATUS CODE : 403)의 상태코드 값(문자열) 반환
	 */
	public static String FORBIDDEN_CODE_STRING = String.valueOf(HttpStatus.FORBIDDEN.value());
	
	/**
	 * FORBIDDEN(HTTP STATUS CODE : 403)의 상태코드 값(숫자) 반환
	 */
	public static int FORBIDDEN_CODE_NUMBER = HttpStatus.FORBIDDEN.value();
	
	/**
	 * NOT FOUND(HTTP STATUS CODE : 404)의 명칭
	 */
	public static String NOT_FOUND_NAME = HttpStatus.NOT_FOUND.name();
	
	/**
	 * NOT FOUND(HTTP STATUS CODE : 404)의 상태코드 값(문자열) 반환
	 */
	public static String NOT_FOUND_CODE_STRING = String.valueOf(HttpStatus.NOT_FOUND.value());
	
	/**
	 * NOT FOUND(HTTP STATUS CODE : 404)의 상태코드 값(숫자) 반환
	 */
	public static int NOT_FOUND_CODE_NUMBER = HttpStatus.NOT_FOUND.value();
	
	// ######################################
	// #####    5XX HTTP STATUS CODE    #####
	// ######################################
	
	/**
	 * NOT FOUND(HTTP STATUS CODE : 500)의 상태코드 값(문자열) 반환
	 */
	public static String INTERNAL_SERVER_ERROR_CODE_STRING = String.valueOf(HttpStatus.INTERNAL_SERVER_ERROR.value());
	
	/**
	 * NOT FOUND(HTTP STATUS CODE : 500)의 상태코드 값(숫자) 반환
	 */
	public static int INTERNAL_SERVER_ERROR_CODE_NUMBER = HttpStatus.INTERNAL_SERVER_ERROR.value();
}
