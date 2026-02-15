package kr.mook.auth.terms.update.persistence;

import kr.mook.auth.terms.vo.TermsVo;

/**
 * 이용약관 정보 수정을 위한 매퍼<br/>
 * 
 * @since 2026. 02. 15.
 * @version 0.1
 * @author Inmook, Jeong
 */
public interface UpdateTermsMapper {
	
	/**
	 * 이용약관 정보 수정
	 * 
	 * @param temrsVo
	 * @return 수정된 이용약관 정보 개수<br/>
	 * 			&emsp; - 0 : 저장되지 않음<br/>
	 * 			&emsp; - 1 : 저장됨
	 */
	public int update(TermsVo temrsVo);

}
