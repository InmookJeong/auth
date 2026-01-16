package kr.mook.auth.terms.search.persistence;

import org.apache.ibatis.annotations.Mapper;

import kr.mook.auth.terms.vo.TermsVo;

/**
 * 이용약관 데이터 검색을 위한 매퍼<br/>
 * 
 * @since 2025. 10. 24.
 * @version 0.1
 * @author Inmook, Jeong
 */
@Mapper
public interface SearchTermsMapper {
	
	/**
	 * 이용약관 번호(termsNo)를 이용하여 약관정보 상세 조회<br/>
	 * 
	 * @param termsNo : 이용약관 번호
	 * @return 이용약관 상세 정보
	 */
	public TermsVo findOne(final long termsNo);
	
}
