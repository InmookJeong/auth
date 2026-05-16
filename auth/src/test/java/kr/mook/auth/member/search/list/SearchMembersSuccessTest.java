package kr.mook.auth.member.search.list;

import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.payload.PayloadDocumentation.fieldWithPath;
import static org.springframework.restdocs.payload.PayloadDocumentation.responseFields;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.List;
import java.util.Locale;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.restdocs.AutoConfigureRestDocs;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.restdocs.payload.JsonFieldType;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;

import kr.mook.auth.common.enumeration.ResponseTypeEnum;
import kr.mook.auth.member.dto.search.MemberSearchDto;

/**
 * 회원 목록 조회 성공 테스트<br/>
 * - 성공 테스트의 경우 SearchMemberMapper로 인해 정상 동작하지 않아 별도 작성<br/>
 * 
 * @since 2026. 05. 12.
 * @version 0.1
 * @author Inmook, Jeong
 */
@SpringBootTest
@AutoConfigureMockMvc
@AutoConfigureRestDocs
public class SearchMembersSuccessTest {
	
	/* 다국어 테스트를 위한 Locale 정보 */
	private final Locale _LOCALE_KO_KR = Locale.KOREA;
	private final Locale _LOCALE_EN_US = Locale.US;
	private final String _ACCEPT_LANGUAGE_KO_KR = "ko-KR";
	private final String _ACCEPT_LANGUAGE_EN_US = "en-US";
	private final String _EXPECT_RESULT_MESSAGE_KO_KR = "회원 목록 조회에 성공했습니다.";
	private final String _EXPECT_RESULT_MESSAGE_EN_US = "Member list lookup successful.";
	
	@Autowired
	private MockMvc mockMvc;
	
	/**
	 * 전체 회원 목록 조회 성공에 대한 테스트<br/>
	 * - 조회 조건이 없는 경우 전체 목록 조회<br/>
	 * - 결과 메시지는 한글로 출력되도록 다국어 적용
	 * 
	 * @throws Exception
	 */
	@Test
	void testSearchMemberAllWithLocalKoKr() throws Exception {
		
		// 전달할 검색 조건
		MemberSearchDto memberSearchDto = new MemberSearchDto();
		
		// 전체 검색
		String apiDocsDir1 = "member/search/list/all/ko";
		this._testSuccessMembersSearch(memberSearchDto, this._LOCALE_KO_KR, this._ACCEPT_LANGUAGE_KO_KR, 3, this._EXPECT_RESULT_MESSAGE_KO_KR, apiDocsDir1);
	}
	
	/**
	 * 전체 회원 목록 조회 성공에 대한 테스트<br/>
	 * - 조회 조건이 없는 경우 전체 목록 조회<br/>
	 * - 결과 메시지는 영어로 출력되도록 다국어 적용
	 * 
	 * @throws Exception
	 */
	@Test
	void testSearchMemberAllWithLocalEnUs() throws Exception {
		
		// 전달할 검색 조건
		MemberSearchDto memberSearchDto = new MemberSearchDto();
		
		// 전체 검색
		String apiDocsDir1 = "member/search/list/all/en";
		this._testSuccessMembersSearch(memberSearchDto, this._LOCALE_EN_US, this._ACCEPT_LANGUAGE_EN_US, 3, this._EXPECT_RESULT_MESSAGE_EN_US, apiDocsDir1);
	}
	
	/**
	 * 회원 이름으로 회원 목록 조회 시 성공 결과에 대한 테스트<br/>
	 * - 이름에 대한 LIKE 검색 테스트<br/>
	 * - 결과 메시지는 한글로 출력되도록 다국어 적용
	 * 
	 * @throws Exception
	 */
	@Test
	void testSearchMemberByNameWithLocalKoKr() throws Exception {
		// 전달할 검색 조건
		MemberSearchDto memberSearchDto = new MemberSearchDto();
		
		// 특정 사용자 이름을 통한 검색 : 한국
		String apiDocsDir2 = "member/search/list/by-name/ko";
		memberSearchDto.setName("한국");
		this._testSuccessMembersSearch(memberSearchDto, this._LOCALE_KO_KR, this._ACCEPT_LANGUAGE_KO_KR, 1, this._EXPECT_RESULT_MESSAGE_KO_KR, apiDocsDir2);
	}
	
	/**
	 * 회원 이름으로 회원 목록 조회 시 성공 결과에 대한 테스트<br/>
	 * - 이름에 대한 LIKE 검색 테스트<br/>
	 * - 결과 메시지는 영어로 출력되도록 다국어 적용
	 * 
	 * @throws Exception
	 */
	@Test
	void testSearchMemberByNameWithLocalEnUs() throws Exception {
		// 전달할 검색 조건
		MemberSearchDto memberSearchDto = new MemberSearchDto();
		
		// 특정 사용자 이름을 통한 검색 : 한국
		String apiDocsDir2 = "member/search/list/by-name/en";
		memberSearchDto.setName("한국");
		this._testSuccessMembersSearch(memberSearchDto, this._LOCALE_EN_US, this._ACCEPT_LANGUAGE_EN_US, 1, this._EXPECT_RESULT_MESSAGE_EN_US, apiDocsDir2);
	}
	
	/**
	 * 회원 계정으로 회원 목록 조회 시 성공 결과에 대한 테스트<br/>
	 * - 계정에 대한 LIKE 검색 테스트<br/>
	 * - 결과 메시지는 한글로 출력되도록 다국어 적용
	 * 
	 * @throws Exception
	 */
	@Test
	void testSearchMemberByAccountWithLocalKoKr() throws Exception {
		// 전달할 검색 조건
		MemberSearchDto memberSearchDto = new MemberSearchDto();
		
		// 특정 문자가 아이디에 포함되어 있는 사용자 목록 검색 : tes
		String apiDocsDir3 = "member/search/list/by-account/ko";
		memberSearchDto.setAccount("tes");
		this._testSuccessMembersSearch(memberSearchDto, this._LOCALE_KO_KR, this._ACCEPT_LANGUAGE_KO_KR, 2, this._EXPECT_RESULT_MESSAGE_KO_KR, apiDocsDir3);
	}
	
	/**
	 * 회원 계정으로 회원 목록 조회 시 성공 결과에 대한 테스트<br/>
	 * - 계정에 대한 LIKE 검색 테스트<br/>
	 * - 결과 메시지는 영어로 출력되도록 다국어 적용
	 * 
	 * @throws Exception
	 */
	@Test
	void testSearchMemberByAccountWithLocalEnUs() throws Exception {
		// 전달할 검색 조건
		MemberSearchDto memberSearchDto = new MemberSearchDto();
		
		// 특정 문자가 아이디에 포함되어 있는 사용자 목록 검색 : tes
		String apiDocsDir3 = "member/search/list/by-account/en";
		memberSearchDto.setAccount("tes");
		this._testSuccessMembersSearch(memberSearchDto, this._LOCALE_EN_US, this._ACCEPT_LANGUAGE_EN_US, 2, this._EXPECT_RESULT_MESSAGE_EN_US, apiDocsDir3);
	}
	
	/**
	 * 회원 목록 조회 성공에 대한 테스트 실행<br/>
	 * 
	 * @param memberSearchDto : 검색 조건(이름, 계정 등)
	 * @param locale : 다국어 처리를 위한 언어 정보
	 * @param acceptLanguage : 다국어 정보(ex. ko-KR 또는 en-US)
	 * @param count : 예상 조회 결과 건수
	 * @param resultMessage : 처리 결과 메시지
	 * @param apiDocsDir : API 문서 경로
	 * @throws Exception
	 */
	private void _testSuccessMembersSearch(MemberSearchDto memberSearchDto, Locale locale, String acceptLanguage, long count, String resultMessage, String apiDocsDir) throws Exception {
		ObjectMapper mapper = new ObjectMapper();
		String memberSearchDtoValue = mapper.writeValueAsString(memberSearchDto);
		
		mockMvc.perform(post("/api/member/list")
				.header("Accept-Language", acceptLanguage)
				.content(memberSearchDtoValue)
				.contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.httpStatusCode").value("200"))
				.andExpect(jsonPath("$.statusCode").value("MEM-LST-001"))
				.andExpect(jsonPath("$.status").value("LIST"))
				.andExpect(jsonPath("$.resultType").value(ResponseTypeEnum.OBJECT.name()))
				.andExpect(jsonPath("$.locale").value(locale.toString()))
				.andExpect(jsonPath("$.result.count").value(count))
				.andExpect(jsonPath("$.result.members").exists())
				.andExpect(jsonPath("$.result.message").value(resultMessage))
				.andDo(print())
				.andDo(
					document(
						apiDocsDir,
						responseFields(
							List.of(
								fieldWithPath("httpStatusCode").type(JsonFieldType.STRING).description("HTTP 응답 상태 코드"),
								fieldWithPath("statusCode").type(JsonFieldType.STRING).description("결과 상태 코드"),
								fieldWithPath("status").type(JsonFieldType.STRING).description("상태코드 명칭(설명)"),
								fieldWithPath("resultType").type(JsonFieldType.STRING).description("결과 타입(ex. Number, String)"),
								fieldWithPath("locale").type(JsonFieldType.STRING).description("사용 언어"),
								fieldWithPath("result").type(JsonFieldType.OBJECT).description("조회 결과 데이터"),
								fieldWithPath("result.count").type(JsonFieldType.NUMBER).description("조회된 목록 건수"),
								fieldWithPath("result.message").type(JsonFieldType.STRING).description("조회 결과 메시지"),
								fieldWithPath("result.members").type(JsonFieldType.ARRAY).description("조회된 회원 목록"),
								fieldWithPath("result.members[].memberId").type(JsonFieldType.NUMBER).description("회원 아이디"),
								fieldWithPath("result.members[].account").type(JsonFieldType.STRING).description("회원 계정"),
								fieldWithPath("result.members[].password").type(JsonFieldType.STRING).description("비밀번호"),
								fieldWithPath("result.members[].active").type(JsonFieldType.STRING).description("활성화 여부"),
								fieldWithPath("result.members[].name").type(JsonFieldType.STRING).description("회원 이름"),
								fieldWithPath("result.members[].birth").type(JsonFieldType.STRING).description("생년월일"),
								fieldWithPath("result.members[].gender").type(JsonFieldType.STRING).description("성별"),
								fieldWithPath("result.members[].email").type(JsonFieldType.STRING).description("이메일"),
								fieldWithPath("result.members[].phoneNumber").type(JsonFieldType.STRING).description("휴대전화 번호"),
								fieldWithPath("result.members[].postNumber").type(JsonFieldType.STRING).description("우편번호"),
								fieldWithPath("result.members[].address").type(JsonFieldType.STRING).description("주소"),
								fieldWithPath("result.members[].createId").description("생성자 아이디"),
								fieldWithPath("result.members[].createDate").description("생성일자"),
								fieldWithPath("result.members[].updateId").description("수정자 아이디"),
								fieldWithPath("result.members[].updateDate").description("수정일자"),
								fieldWithPath("result.members[].loginFailedCount").type(JsonFieldType.NUMBER).description("로그인 실패 횟수"),
								fieldWithPath("result.members[].latestLogoutDate").description("마지막 로그인 일자")
							)
						)
					)
				);
	}

}
