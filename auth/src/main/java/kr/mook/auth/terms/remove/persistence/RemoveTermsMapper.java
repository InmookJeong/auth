package kr.mook.auth.terms.remove.persistence;

import org.apache.ibatis.annotations.Mapper;

/**
 * 이용약관 데이터 검색을 위한 매퍼<br/>
 * 
 * @since 2026. 02. 25.
 * @version 0.1
 * @author Inmook, Jeong
 */
@Mapper
public interface RemoveTermsMapper {

	/**
	 * 이용약관 번호(termsNo)를 이용하여 약관정보 삭제<br/>
	 * 
	 * @param termsNo : 이용약관 번호
	 * @return 삭제된 이용약관 정보 건수
	 */
	public int delete(final Long termsNo);
}
