package kr.mook.auth.terms.service.search;

import java.util.Locale;

import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;

import kr.mook.auth.common.dto.ResponseDto;
import kr.mook.auth.common.enumeration.ResponseTypeEnum;
import kr.mook.auth.terms.persistence.search.SearchTermsMapper;
import kr.mook.auth.terms.vo.TermsVo;
import lombok.RequiredArgsConstructor;

/**
 * 이용약관 검색을 위한 서비스 구현<br/>
 * 
 * @since 2025. 10. 24.
 * @version 0.1
 * @author Inmook, Jeong
 */
@Service
@RequiredArgsConstructor
public class SearchTermsServiceImpl implements SearchTermsService {
	
	/* 다국어 처리를 위한 MessageSource */
	private final MessageSource _messageSource;
	
	/* Mapper */
	private final SearchTermsMapper _searchTermsMapper;
	
	/**
	 * 이용약관 번호(Terms NO)를 통해 이용약관 정보 상세 검색
	 * 
	 * @since 2025. 10. 24.
	 * @param termsNo : 이용약관 번호
	 * @param locale : 다국어 처리를 위한 언어 정보
	 * @Return 이용약관 정보 상세 조회 결과 데이터
	 */
	@Override
	public ResponseDto searchByTermsNo(final long termsNo, final Locale locale) {
		ResponseDto responseDto = ResponseDto.builder()
											.locale(locale)
											.build();
				
		if(termsNo <= 0L) {
			return this._getResponseDtoForBadRequest(responseDto, locale);
		}
		
		TermsVo termsVo = this._searchTermsMapper.findOne(termsNo);
		if(termsVo == null) {
			return this._getResponseDtoForNotFound(responseDto, locale);
		}
			
		return this._getResponseDtoForSuccess(responseDto, termsVo, locale);
	}
	
	/**
	 * 이용약관 번호(TermsNo)를 1 이상의 숫자로 전달하지 않을 경우 잘못된 요청을 하였음을 반환하도록 DTO 작성
	 * 
	 * @param responseDto
	 * @return responseDto = {<br/>
	 * 				&emsp; "httpStatusCode" : "400",<br/>
	 * 				&emsp; "statusCode" : "ERR-TMS-SER-001",<br/>
	 * 				&emsp; "staus" : "SEARCH ERROR",<br/>
	 * 				&emsp; "resultType" : "string",<br/>
	 * 				&emsp; "result" : "${locale에 따른 에러 메시지}"<br/>
	 * 			}
	 */
	private ResponseDto _getResponseDtoForBadRequest(ResponseDto responseDto, final Locale locale) {
		responseDto.setHttpStatusCode("400");
		responseDto.setStatusCode("ERR-TMS-SER-001");
		responseDto.setStatus("SEARCH ERROR");
		responseDto.setResultType(ResponseTypeEnum.STRING);
		responseDto.setResult(this._messageSource.getMessage("error.terms.search.terms-no-is-zero", null, locale));
		return responseDto;
	}
	
	/**
	 * 이용약관 데이터를 찾을 수 없음을 반환하도록 DTO 작성
	 * 
	 * @param responseDto
	 * @return responseDto = {<br/>
	 * 				&emsp; "httpStatusCode" : "404",<br/>
	 * 				&emsp; "statusCode" : "ERR-TMS-SER-002",<br/>
	 * 				&emsp; "staus" : "SEARCH ERROR",<br/>
	 * 				&emsp; "resultType" : "string",<br/>
	 * 				&emsp; "result" : "${locale에 따른 에러 메시지}"<br/>
	 * 			}
	 */
	private ResponseDto _getResponseDtoForNotFound(ResponseDto responseDto, final Locale locale) {
		responseDto.setHttpStatusCode("404");
		responseDto.setStatusCode("ERR-TMS-SER-002");
		responseDto.setStatus("SEARCH ERROR");
		responseDto.setResultType(ResponseTypeEnum.STRING);
		responseDto.setResult(this._messageSource.getMessage("error.terms.search.terms-not-found", null, locale));
		return responseDto;
	}
	
	/**
	 * 이용약관 데이터를 찾아 결과를 반환하도록 DTO 작성
	 * 
	 * @param responseDto
	 * @param termsVo
	 * @return responseDto = {<br/>
	 * 				&emsp; "httpStatusCode" : "200",<br/>
	 * 				&emsp; "statusCode" : "TMS-SER-001",<br/>
	 * 				&emsp; "staus" : "SEARCH",<br/>
	 * 				&emsp; "resultType" : "object",<br/>
	 * 				&emsp; "result" : {<br/>
	 * 					&emsp;&emsp; "termsNo" : 1,<br/>
	 * 					&emsp;&emsp; "useYn" : true,<br/>
	 * 					&emsp;&emsp; "requireYn" : true,<br/>
	 * 					&emsp;&emsp; "orderNo" : 1,<br/>
	 * 					&emsp;&emsp; "title" : "사이트 이용 약관",<br/>
	 * 					&emsp;&emsp; "contents" : "회원 모듈(Auth, 이하 서비스)는 애플리케이션 목적에 맞게 사용해야 하며...",<br/>
	 * 					&emsp;&emsp; "createId" : 120,<br/>
	 * 					&emsp;&emsp; "createDate" : "2026-01-01 13:43:21.071",<br/>
	 * 					&emsp;&emsp; "updateId" : NULL,<br/>
	 * 					&emsp;&emsp; "updateDate" : NULL<br/>
	 * 				&emsp; }<br/>
	 * 			}
	 */
	private ResponseDto _getResponseDtoForSuccess(ResponseDto responseDto, final TermsVo termsVo, final Locale locale) {
		responseDto.setHttpStatusCode("200");
		responseDto.setStatusCode("TMS-SER-001");
		responseDto.setStatus("SEARCH");
		responseDto.setResultType(ResponseTypeEnum.OBJECT);
		responseDto.setResult(termsVo);
		return responseDto;
	}
}
