package kr.mook.auth.member.search.service;

import java.util.List;
import java.util.Locale;

import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;

import kr.mook.auth.common.dto.ResponseDto;
import kr.mook.auth.common.enumeration.ResponseTypeEnum;
import kr.mook.auth.common.http.RestfulApiHttpStatusUtil;
import kr.mook.auth.member.dto.search.MemberDto;
import kr.mook.auth.member.dto.search.MemberSearchDto;
import kr.mook.auth.member.dto.search.list.MembersDto;
import kr.mook.auth.member.search.persistence.SearchMemberMapper;
import kr.mook.auth.member.vo.MemberVo;
import kr.mook.auth.member.vo.search.list.SearchMemberVo;
import kr.mook.auth.terms.util.TermsUtil;
import lombok.RequiredArgsConstructor;

/**
 * 회원 검색을 위한 서비스 구현<br/>
 * - 회원 계정을 통한 상세 종보 조회 기능 제공<br/>
 * 
 * @since 2026. 03. 27.
 * @version 0.1
 * @author Inmook, Jeong
 */
@Service
@RequiredArgsConstructor
public class SearchMemberServiceImpl implements SearchMemberService {
	
	/* 다국어 처리를 위한 MessageSource */
	private final MessageSource _messageSource;
	
	/* Mapper */
	private final SearchMemberMapper _searchMemberMapper;

	/**
	 * 계정을 통한 화원 정보 상세 조회<br/>
	 * 
	 * @since 2026. 04. 05.
	 * @param account : 회원 계정
	 * @param locale : 다국어 처리를 위한 언어 정보
	 * @Return 회원 정보 상세 조회 결과 데이터
	 */
	@Override
	public ResponseDto searchByAccount(final String account, final Locale locale) throws Exception {
		ResponseDto responseDto = ResponseDto.builder()
											.locale(locale)
											.build();
		
		MemberVo memberVo = this._searchMemberMapper.findByAccount(account);
		
		// 계정이 가입되어 있지 않은 경우
		if(memberVo == null) {
			return this._getResponseDtoForNotFound(responseDto, "account", locale);
		}
		
		MemberDto memberDto = MemberDto.builder().build();
		memberDto.fromMemberVo(memberVo);
		
		responseDto.setHttpStatusCode(RestfulApiHttpStatusUtil.OK_CODE_STRING);
		responseDto.setStatusCode("MEM-SER-001");
		responseDto.setStatus("SEARCH");
		responseDto.setResult(memberDto);
		responseDto.setResultType(ResponseTypeEnum.OBJECT);
		
		return responseDto;
	}
	
	/**
	 * 회원 아이디(숫자 형식)를 통한 화원 정보 상세 조회<br/>
	 * 
	 * @since 2026. 04. 09.
	 * @param memberId : 회원 아이디(숫자 형식)
	 * @param locale : 다국어 처리를 위한 언어 정보
	 * @Return 회원 정보 상세 조회 결과 데이터
	 */
	@Override
	public ResponseDto searchByMemberId(long memberId, Locale locale) throws Exception {
		ResponseDto responseDto = ResponseDto.builder()
											.locale(locale)
											.build();
		
		MemberVo memberVo = this._searchMemberMapper.findByMemberId(memberId);
		
		// 계정이 가입되어 있지 않은 경우
		if(memberVo == null) {
			return this._getResponseDtoForNotFound(responseDto, "id", locale);
		}
		
		MemberDto memberDto = MemberDto.builder().build();
		memberDto.fromMemberVo(memberVo);
		
		responseDto.setHttpStatusCode(RestfulApiHttpStatusUtil.OK_CODE_STRING);
		responseDto.setStatusCode("MEM-SER-002");
		responseDto.setStatus("SEARCH");
		responseDto.setResult(memberDto);
		responseDto.setResultType(ResponseTypeEnum.OBJECT);
		
		return responseDto;
	}
	
	/**
	 * 회원 목록 조회<br/>
	 * 
	 * @since 2026. 04. 17.
	 * @param memberSearchDto : 검색을 위한 값을 담고있는 DTO
	 * @param locale : 다국어 처리를 위한 언어 정보
	 * @Return 회원 목록 조회 결과 데이터
	 */
	@Override
	public ResponseDto searchMembers(MemberSearchDto memberSearchDto, Locale locale) throws Exception {
		ResponseDto responseDto = ResponseDto.builder()
											.locale(locale)
											.build();
		List<MemberVo> members = null;
		
		try {
			SearchMemberVo searchMemberVo = SearchMemberVo.builder().build();
			searchMemberVo.fromMemberSearchDto(memberSearchDto);
			members = this._searchMemberMapper.findMembers(searchMemberVo);
		} catch (Exception e) {
			return this._getResponseDtoForServerError(responseDto, locale);
		}
		
		if(members == null || members.size() == 0) {
			return this._getResponseDtoForListNotFound(responseDto, locale);
		}
		
		// 목록 조회 결과 반환
		return this._getResponseDtoByMemberList(responseDto, members, locale);
	}
	
	/**
	 * 회원 데이터를 찾을 수 없음을 반환하도록 DTO 작성
	 * 
	 * @param responseDto : 저장 결과에 대한 응답 정보
	 * @param targetFieldName : 회원 정보를 조회하기 위해 기준이 되는 값(계정 또는 아이디)
	 * @param locale : 다국어 처리를 위한 언어 정보
	 * @return responseDto = {<br/>
	 * 				&emsp; "httpStatusCode" : "404",<br/>
	 * 				&emsp; "statusCode" : "ERR-MEM-SER-001",<br/>
	 * 				&emsp; "staus" : "SEARCH ERROR",<br/>
	 * 				&emsp; "resultType" : "string",<br/>
	 * 				&emsp; "result" : "${locale에 따른 에러 메시지}"<br/>
	 * 			}
	 */
	private ResponseDto _getResponseDtoForNotFound(ResponseDto responseDto, final String targetFieldName, final Locale locale) {
		String fieldName = this._messageSource.getMessage(targetFieldName, null, locale);
		String errorMessageNo = targetFieldName.equalsIgnoreCase("account") ? "001" : "002";
		
		return TermsUtil.getResponseDtoByMessage(
					responseDto,
					RestfulApiHttpStatusUtil.NOT_FOUND_CODE_STRING,
					"ERR-MEM-SER-" + errorMessageNo,
					"SEARCH ERROR",
					this._messageSource.getMessage("error.member.search.member-not-found", new String[] {fieldName}, locale)
				);
	}
	
	/**
	 * 조회된 목록이 없음을 반환하도록 DTO 작성
	 * 
	 * @param responseDto : 저장 결과에 대한 응답 정보
	 * @param locale : 다국어 처리를 위한 언어 정보
	 * @return responseDto = {<br/>
	 * 				&emsp; "httpStatusCode" : "404",<br/>
	 * 				&emsp; "statusCode" : "ERR-MEM-LST-001",<br/>
	 * 				&emsp; "staus" : "LIST ERROR",<br/>
	 * 				&emsp; "resultType" : "string",<br/>
	 * 				&emsp; "result" : "${locale에 따른 에러 메시지}"<br/>
	 * 			}
	 */
	private ResponseDto _getResponseDtoForListNotFound(ResponseDto responseDto, final Locale locale) {
		String resultMessage = this._messageSource.getMessage("member.search.list.empty", null, locale);
		MembersDto membersDto = MembersDto.builder()
											.count(0)
											.message(resultMessage)
											.build();
		
		return TermsUtil.getResponseDtoByResultObject(
					responseDto,
					RestfulApiHttpStatusUtil.NOT_FOUND_CODE_STRING,
					"ERR-MEM-LST-001",
					"LIST ERROR",
					membersDto
				);
	}
	
	/**
	 * 목록 조회 시 서버 오류가 발생하였음을 반환하도록 DTO 작성
	 * 
	 * @param responseDto : 저장 결과에 대한 응답 정보
	 * @param locale : 다국어 처리를 위한 언어 정보
	 * @return responseDto = {<br/>
	 * 				&emsp; "httpStatusCode" : "500",<br/>
	 * 				&emsp; "statusCode" : "ERR-MEM-LST-002",<br/>
	 * 				&emsp; "staus" : "LIST ERROR",<br/>
	 * 				&emsp; "resultType" : "string",<br/>
	 * 				&emsp; "result" : "${locale에 따른 에러 메시지}"<br/>
	 * 			}
	 */
	private ResponseDto _getResponseDtoForServerError(ResponseDto responseDto, final Locale locale) {
		return TermsUtil.getResponseDtoByMessage(
					responseDto,
					RestfulApiHttpStatusUtil.INTERNAL_SERVER_ERROR_CODE_STRING,
					"ERR-MEM-LST-002",
					"LIST ERROR",
					this._messageSource.getMessage("error.member.search.list.server-error", null, locale)
				);
	}
	
	/**
	 * 회원 목록 조회 성공 결과를 반환하도록 DTO 작성
	 * 
	 * @param responseDto : 저장 결과에 대한 응답 정보
	 * @param members : 조회된 회원 목록 결과
	 * @param locale : 다국어 처리를 위한 언어 정보
	 * @return responseDto = {<br/>
	 * 				&emsp; "httpStatusCode" : "500",<br/>
	 * 				&emsp; "statusCode" : "ERR-MEM-LST-002",<br/>
	 * 				&emsp; "staus" : "LIST ERROR",<br/>
	 * 				&emsp; "resultType" : "string",<br/>
	 * 				&emsp; "result" : "${locale에 따른 에러 메시지}"<br/>
	 * 			}
	 */
	private ResponseDto _getResponseDtoByMemberList(ResponseDto responseDto, final List<MemberVo> members, final Locale locale) {
		String message = this._messageSource.getMessage("member.search.list", null, locale);
		MembersDto membersDto = MembersDto.builder()
											.count(members.size())
											.members(members)
											.message(message)
											.build();
		
		return TermsUtil.getResponseDtoByResultObject(
					responseDto,
					RestfulApiHttpStatusUtil.OK_CODE_STRING,
					"MEM-LST-001",
					"LIST",
					membersDto
				);
	}
}