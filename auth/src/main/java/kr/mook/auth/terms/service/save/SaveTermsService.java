package kr.mook.auth.terms.service.save;

import java.util.Locale;

import kr.mook.auth.common.dto.ResponseDto;
import kr.mook.auth.terms.dto.TermsDto;

/**
 * 이용약관 저장을 위한 서비스 인터페이스<br/>
 * 
 * @since 2025. 11. 21.
 * @version
 * @author Inmook, Jeong
 */
public interface SaveTermsService {
	
	/**
	 * 이용약관 저장
	 * 
	 * @param termsDto : 저장할 이용약관 정보
	 * @return
	 */
	public ResponseDto save(TermsDto termsDto, Locale locale);

}
