package kr.mook.auth.member.search.persistence;

import org.apache.ibatis.annotations.Mapper;

import kr.mook.auth.member.vo.MemberVo;

/**
 * 이용약관 데이터 검색을 위한 매퍼<br/>
 * 
 * @since 2026. 03. 27.
 * @version 0.1
 * @author Inmook, Jeong
 */
@Mapper
public interface SearchMemberMapper {

	/**
	 * 회원 계정을 통한 회원 정보 상세 조회<br/>
	 * 
	 * @param account : 회원 계정
	 * @return : 회원 상세 정보
	 */
	public MemberVo findByAccount(final String account);
	
	/**
	 * 회원 아이디(숫자 형식)를 통한 회원 정보 상세 조회<br/>
	 * 
	 * @param memberId : 회원 아이디(숫자 형식)
	 * @return : 회원 상세 정보
	 */
	public MemberVo findByMemberId(final long memberId);
}
