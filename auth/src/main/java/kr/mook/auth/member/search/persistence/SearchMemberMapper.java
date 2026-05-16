package kr.mook.auth.member.search.persistence;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import kr.mook.auth.member.dto.search.MemberSearchDto;
import kr.mook.auth.member.vo.MemberVo;
import kr.mook.auth.member.vo.search.list.SearchMemberVo;

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
	
	/**
	 * 회원 계정 또는 회원 이름을 통한 회원 목록 조회<br/>
	 * 
	 * @param memberSearchDto : 회원 목록 조회 조건(회원 계정, 회원 이름 등)
	 * @return : 조회된 회원 목록
	 */
	public List<MemberVo> findMembers(final SearchMemberVo searchMemberVo);
}
