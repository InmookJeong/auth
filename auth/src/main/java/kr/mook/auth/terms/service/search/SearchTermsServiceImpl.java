package kr.mook.auth.terms.service.search;

import org.springframework.stereotype.Service;

import kr.mook.auth.common.dto.ResponseDto;
import kr.mook.auth.common.enumeration.LanguageEnum;
import kr.mook.auth.common.enumeration.ResponseTypeEnum;
import kr.mook.auth.terms.persistence.search.SearchTermsMapper;
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
	
	/* Mapper */
	private final SearchTermsMapper _searchTermsMapper;
	
	/**
	 * 이용약관 번호(Terms NO)를 통해 이용약관 상세 검색
	 * 
	 * @since 2025. 10. 24.
	 * @param termsNo : 이용약관 번호
	 * @Return 이용약관 결과 데이터
	 */
	@Override
	public ResponseDto searchByTermsNo(final long termsNo) {
		ResponseDto responseDto = ResponseDto.builder()
											.language(LanguageEnum.KOREAN)
											.build();
				
		if(termsNo <= 0L) {
			return this._getResponseDtoForBadRequest(responseDto);
		}
			
		return null;
	}
	
	/**
	 * 이용약관 번호(TermsNo)를 1 이상의 숫자로 전달하지 않을 경우 잘못된 요청을 하였음을 반환하도록 DTO 작성
	 * 
	 * @param responseDto
	 * @return
	 */
	private ResponseDto _getResponseDtoForBadRequest(ResponseDto responseDto) {
		responseDto.setStatusCode("400");
		responseDto.setStatus("SEARCH[THE TERMS_NO IS GAREATER THAN ZERO]");
		responseDto.setResultType(ResponseTypeEnum.STRING);
		responseDto.setResult("이용약관 번호가 올바르지 않습니다. 이용약관 번호는 1이상의 숫자를 입력해주세요.");
		return responseDto;
	}
}
