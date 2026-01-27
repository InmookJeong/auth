package kr.mook.auth.terms.save.persistence;

/**
 * 이용약관 정보 저장을 위한 매퍼<br/>
 * 
 * @since 2026. 01. 18.
 * @version 0.1
 * @author Inmook, Jeong
 */
public interface SaveTermsMapper {
	
	/**
	 * 가장 최근 발급된 이용약관번호 조회
	 * 
	 * @return 저장 가능한 신규 이용약관번호
	 */
	public long currentTermsNo();
	
	/**
	 * 신규 이용약관번호 생성
	 * 
	 * @return 저장 가능한 신규 이용약관번호
	 */
	public long nextTermsNo();

}
