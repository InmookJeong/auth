package kr.mook.auth.member.search.service;

import java.util.Locale;

import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;

import kr.mook.auth.common.dto.ResponseDto;
import kr.mook.auth.common.enumeration.ResponseTypeEnum;
import kr.mook.auth.common.http.RestfulApiHttpStatusUtil;
import kr.mook.auth.member.dto.search.MemberDto;
import kr.mook.auth.member.search.persistence.SearchMemberMapper;
import kr.mook.auth.member.vo.MemberVo;
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
	 *  @since 2026. 04. 05.
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
		
		return null;
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
}