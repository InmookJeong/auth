package kr.mook.auth.member.search.list;

import static org.mockito.BDDMockito.given;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.payload.PayloadDocumentation.fieldWithPath;
import static org.springframework.restdocs.payload.PayloadDocumentation.responseFields;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Locale;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.restdocs.AutoConfigureRestDocs;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.MessageSource;
import org.springframework.http.MediaType;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.Sql.ExecutionPhase;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultMatcher;

import com.fasterxml.jackson.databind.ObjectMapper;

import kr.mook.auth.common.enumeration.ResponseTypeEnum;
import kr.mook.auth.member.dto.search.MemberSearchDto;
import kr.mook.auth.member.search.persistence.SearchMemberMapper;
import kr.mook.auth.member.vo.search.list.SearchMemberVo;

/**
 * 회원 목록 조회 테스트<br/>
 * - 회원 목록 조회 성공 테스트의 경우 SearchMembersSuccessTest 클래스에 작성<br/>
 * - 성공 테스트의 경우 SearchMemberMapper로 인해 정상 동작하지 않아 별도 작성<br/>
 * 
 * @since 2026. 05. 12.
 * @version 0.1
 * @author Inmook, Jeong
 */
@SpringBootTest
@AutoConfigureMockMvc
@AutoConfigureRestDocs
class SearchMembersTest {
	
	/* 다국어 테스트를 위한 Locale 정보 */
	private final Locale _LOCALE_KO_KR = Locale.KOREA;
	private final Locale _LOCALE_EN_US = Locale.US;
	private final String _ACCEPT_LANGUAGE_KO_KR = "ko-KR";
	private final String _ACCEPT_LANGUAGE_EN_US = "en-US";
	
	@Autowired
	private MessageSource _messageSource;
	
	@Autowired
	private MockMvc mockMvc;
	
	@MockBean
	private SearchMemberMapper _searchMemberMapper;
	
	/**
	 * 각 테스트 코드 수행 전 테이블 생성 및 테스트 데이터 저장
	 */
	@Sql(
		statements = {
			"CREATE TABLE MEMBER (" +
			"	MEMBER_ID BIGINT NOT NULL," +
			"	ACCOUNT VARCHAR(100) NOT NULL," +
			"	PASSWORD VARCHAR(1000) NOT NULL," +
			"	ACTIVE CHAR(1) NOT NULL DEFAULT 'Y'," +
			"	NAME VARCHAR(1000) NOT NULL," +
			"	BIRTH DATETIME NOT NULL," +
			"	GENDER CHAR(1) NOT NULL," +
			"	EMAIL VARCHAR(1000) UNIQUE NOT NULL" +
			"	PHONE_NUMBER VARCHAR(100) UNIQUE NOT NULL" +
			"	POST_NUMBER VARCHAR(50)," +
			"	ADDRESS VARCHAR(4000)," +
			"	CREATE_ID BIGINT NOT NULL," +
			"	CREATE_DATE DATETIME NOT NULL," +
			"	UPDATE_ID BIGINT," +
			"	UPDATE_DATE DATETIME," +
			"	LOGIN_FAILED_COUNT INT DEFAULT 0," +
			"	LATEST_LOGOUT_DATE DATETIME," +
			"	CONSTRAINT MEMBER_PK PRIMARY KEY(MEMBER_ID)" +
			");",
			"INSERT INTO MEMBER (MEMBER_ID, ACCOUNT, PASSWORD, ACTIVE, NAME, BIRTH, GENDER, " +
				"EMAIL, PHONE_NUMBER, POST_NUMBER, ADDRESS, CREATE_ID, CREATE_DATE)" + 
			"VALUES (1, 'kor2026', 'pwd2026', 'Y', '한국인', '19901207', 'M', " +
				"'kor2026@korea.co.kr', '01012345678', '01123', '서울시 종로구 종로동 종로1가 1번지', 1, NOW());" + 
			"INSERT INTO MEMBER (MEMBER_ID, ACCOUNT, PASSWORD, ACTIVE, NAME, BIRTH, GENDER, " +
				"EMAIL, PHONE_NUMBER, POST_NUMBER, ADDRESS, CREATE_ID, CREATE_DATE)" + 
			"VALUES (2, 'james_1024', '_1024james', 'Y', 'James Park', '19820211', 'W', " +
				"'james1024@usa.com', '01045679345', '11111', '1/2 Well street in USA', 1, NOW());"
		},
		executionPhase = ExecutionPhase.BEFORE_TEST_METHOD
	)
	@BeforeEach
	void createTempTableAndData() {}
	
	/**
	 * 각 테스트 코드 수행 후 테이블 제거
	 */
	@Sql(
		statements = {
			"DROP TABLE MEMBER;"
		},
		executionPhase = ExecutionPhase.AFTER_TEST_METHOD
	)
	@AfterEach
	void dropTable() {}

	/*
	 * TODO
	 * 2. 목록 조회 : 건수 1건 이상
	 * 3. 목록 조회 : 시스템 오류
	 */
	
	/**
	 * 회원 목록 검색 시 조회된 건수가 없는 경우에 대한 테스트<br/>
	 * - 회원 계정 또는 회원 이름을 통해 회원 정보를 조회<br/>
	 * - 조회된 결과가 없는 경우, 즉 조회 건수가 0인 경우에 대한 메시지가 출력되는지 테스트<br/>
	 * - 결과 메시지는 한글로 출력되도록 다국어 적용
	 * 
	 * @throws Exception
	 */
	@Test
	void testMembersNotFoundWithLocaleKoKr() throws Exception {
		String account = "notUser";
		String httpStatusCode = "404";
		String statusCode = "ERR-MEM-LST-001";
		String status = "LIST ERROR";
		String resultMessage = "조회된 회원 목록이 없습니다.";
		String apiDocsDir = "member/search/list/member-list-not-found/ko";
		ResultMatcher resultMatcher = status().isNotFound();
		
		MemberSearchDto memberSearchDto = new MemberSearchDto();
		memberSearchDto.setAccount(account);
		
		this._testMembersNotFound(memberSearchDto, this._LOCALE_KO_KR, this._ACCEPT_LANGUAGE_KO_KR, httpStatusCode, statusCode, status, resultMessage, apiDocsDir, resultMatcher);
	}
	
	/**
	 * 회원 목록 검색 시 조회된 건수가 없는 경우에 대한 테스트<br/>
	 * - 회원 계정 또는 회원 이름을 통해 회원 정보를 조회<br/>
	 * - 조회된 결과가 없는 경우, 즉 조회 건수가 0인 경우에 대한 메시지가 출력되는지 테스트<br/>
	 * - 결과 메시지는 영어로 출력되도록 다국어 적용
	 * 
	 * @throws Exception
	 */
	@Test
	void testMembersNotFoundWithLocaleEnUs() throws Exception {
		String account = "notUser";
		String httpStatusCode = "404";
		String statusCode = "ERR-MEM-LST-001";
		String status = "LIST ERROR";
		String resultMessage = "There is no list of retrieved members.";
		String apiDocsDir = "member/search/list/member-list-not-found/en";
		ResultMatcher resultMatcher = status().isNotFound();
		
		MemberSearchDto memberSearchDto = new MemberSearchDto();
		memberSearchDto.setAccount(account);
		
		this._testMembersNotFound(memberSearchDto, this._LOCALE_EN_US, this._ACCEPT_LANGUAGE_EN_US, httpStatusCode, statusCode, status, resultMessage, apiDocsDir, resultMatcher);
	}
	
	/**
	 * 회원 목록을 조회하는 과정에서 서버의 오류가 발생하는 경우<br/>
	 * - 회원 목록을 조회하는 과정에서 서버의 오류가 발생할 경우, 오류에 대한 안내 메시지가 출력되는지 테스트<br/>
	 * - 서버 오류가 발생할 경우 500 에러 반환<br/>
	 * - 결과 메시지는 한글로 출력되도록 다국어 적용
	 * 
	 * @throws Exception
	 */
	@Test
	void testSearchMembersServerErrorWithLocaleKoKr() throws Exception {
		SearchMemberVo searchMemberVo = SearchMemberVo.builder().account("notUser").build();
		given(this._searchMemberMapper.findMembers(searchMemberVo)).willThrow(new RuntimeException("Server Error"));
		
		String account = "notUser";
		String httpStatusCode = "500";
		String statusCode = "ERR-MEM-LST-002";
		String status = "LIST ERROR";
		String resultMessage = "회원 목록를 조회할 수 없습니다. 관리자에게 문의해주세요.";
		String apiDocsDir = "member/search/list/server-error/ko";
		ResultMatcher resultMatcher = status().is5xxServerError();
		
		MemberSearchDto memberSearchDto = new MemberSearchDto();
		memberSearchDto.setAccount(account);
		
		this._testMembersServerError(memberSearchDto, this._LOCALE_KO_KR, this._ACCEPT_LANGUAGE_KO_KR, httpStatusCode, statusCode, status, resultMessage, apiDocsDir, resultMatcher);
	}
	
	/**
	 * 회원 목록을 조회하는 과정에서 서버의 오류가 발생하는 경우<br/>
	 * - 회원 목록을 조회하는 과정에서 서버의 오류가 발생할 경우, 오류에 대한 안내 메시지가 출력되는지 테스트<br/>
	 * - 서버 오류가 발생할 경우 500 에러 반환<br/>
	 * - 결과 메시지는 영어로 출력되도록 다국어 적용
	 * 
	 * @throws Exception
	 */
	@Test
	void testSearchMembersServerErrorWithLocaleEnUs() throws Exception {
		SearchMemberVo searchMemberVo = SearchMemberVo.builder().account("notUser").build();
		given(this._searchMemberMapper.findMembers(searchMemberVo)).willThrow(new RuntimeException("Server Error"));
		
		String account = "notUser";
		String httpStatusCode = "500";
		String statusCode = "ERR-MEM-LST-002";
		String status = "LIST ERROR";
		String resultMessage = "Unable to search for member list. Please contact the administrator.";
		String apiDocsDir = "member/search/list/server-error/en";
		ResultMatcher resultMatcher = status().is5xxServerError();
		
		MemberSearchDto memberSearchDto = new MemberSearchDto();
		memberSearchDto.setAccount(account);
		
		this._testMembersServerError(memberSearchDto, this._LOCALE_EN_US, this._ACCEPT_LANGUAGE_EN_US, httpStatusCode, statusCode, status, resultMessage, apiDocsDir, resultMatcher);
	}
	
	/**
	 * 회원 목록 조회 시 0건의 데이터가 조회되는 경우에 대한 테스트<br/>
	 * - 회원 계정 또는 이름을 통해 목록 조회 시 조회된 건수가 0건인 경우에 대한 메시지가 정상 출력되는지 테스트<br/>
	 * 
	 * @param memberSearchDto : 회원 목록 검색을 위한 조건 값을 가지고 있는 DTO
	 * @param locale : 다국어 처리를 위한 언어 정보
	 * @param acceptLanguage : 다국어 정보(ex. ko-KR 또는 en-US)
	 * @param httpStatusCodee : 처리 상태 코드(ex. 400, 404)
	 * @param statusCode : 처리 상태 코드(ex. 400, 404)
	 * @param status : 처리 결과 상태 구문
	 * @param resultMessage : 처리 결과 메시지
	 * @param apiDocsDir : API 문서 경로
	 * @param resultMatcher : 예상되는 HTTP 상태
	 * @throws Exception
	 */
	private void _testMembersNotFound(MemberSearchDto memberSearchDto, Locale locale, String acceptLanguage, String httpStatusCode, String statusCode, String status, String resultMessage, String apiDocsDir, ResultMatcher resultMatcher) throws Exception {
		ObjectMapper mapper = new ObjectMapper();
		String memberSearchDtoValue = mapper.writeValueAsString(memberSearchDto);
		
		mockMvc.perform(post("/api/member/list")
				.header("Accept-Language", acceptLanguage)
				.content(memberSearchDtoValue)
				.contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON))
				.andExpect(resultMatcher)
				.andExpect(jsonPath("$.httpStatusCode").value(httpStatusCode))
				.andExpect(jsonPath("$.statusCode").value(statusCode))
				.andExpect(jsonPath("$.status").value(status))
				.andExpect(jsonPath("$.resultType").value(ResponseTypeEnum.OBJECT.name()))
				.andExpect(jsonPath("$.result.count").value(0))
				.andExpect(jsonPath("$.result.message").value(resultMessage))
				.andExpect(jsonPath("$.locale").value(locale.toString()))
				.andDo(print())
				.andDo(document(
						apiDocsDir,
						responseFields(
								fieldWithPath("httpStatusCode").description("HTTP 응답 상태 코드"),
								fieldWithPath("statusCode").description("결과 상태 코드"),
								fieldWithPath("status").description("상태코드 명칭(설명)"),
								fieldWithPath("resultType").description("결과 타입(ex. Number, String)"),
								fieldWithPath("result.count").description("조회된 목록 건수"),
								fieldWithPath("result.members").description("조회된 목록"),
								fieldWithPath("result.message").description("결과 메시지"),
								fieldWithPath("locale").description("사용 언어")
						)
				));
	}
	
	/**
	 * 회원 목록 조회 시 서버 오류가 발생하는 경우에 대한 테스트<br/>
	 * - 회원 계정 또는 이름을 통해 목록 조회 시 서버에서 오류가 발생할 경우에 대한 메시지가 정상 출력되는지 테스트<br/>
	 * 
	 * @param memberSearchDto : 회원 목록 검색을 위한 조건 값을 가지고 있는 DTO
	 * @param locale : 다국어 처리를 위한 언어 정보
	 * @param acceptLanguage : 다국어 정보(ex. ko-KR 또는 en-US)
	 * @param httpStatusCodee : 처리 상태 코드(ex. 400, 404)
	 * @param statusCode : 처리 상태 코드(ex. 400, 404)
	 * @param status : 처리 결과 상태 구문
	 * @param resultMessage : 처리 결과 메시지
	 * @param apiDocsDir : API 문서 경로
	 * @param resultMatcher : 예상되는 HTTP 상태
	 * @throws Exception
	 */
	private void _testMembersServerError(MemberSearchDto memberSearchDto, Locale locale, String acceptLanguage, String httpStatusCode, String statusCode, String status, String resultMessage, String apiDocsDir, ResultMatcher resultMatcher) throws Exception {
		ObjectMapper mapper = new ObjectMapper();
		String memberSearchDtoValue = mapper.writeValueAsString(memberSearchDto);
		
		mockMvc.perform(post("/api/member/list")
				.header("Accept-Language", acceptLanguage)
				.content(memberSearchDtoValue)
				.contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON))
				.andExpect(resultMatcher)
				.andExpect(jsonPath("$.httpStatusCode").value(httpStatusCode))
				.andExpect(jsonPath("$.statusCode").value(statusCode))
				.andExpect(jsonPath("$.status").value(status))
				.andExpect(jsonPath("$.resultType").value(ResponseTypeEnum.STRING.name()))
				.andExpect(jsonPath("$.result").value(resultMessage))
				.andExpect(jsonPath("$.locale").value(locale.toString()))
				.andDo(print())
				.andDo(document(
						apiDocsDir,
						responseFields(
								fieldWithPath("httpStatusCode").description("HTTP 응답 상태 코드"),
								fieldWithPath("statusCode").description("결과 상태 코드"),
								fieldWithPath("status").description("상태코드 명칭(설명)"),
								fieldWithPath("resultType").description("결과 타입(ex. Number, String)"),
								fieldWithPath("result").description("결과 메시지"),
								fieldWithPath("locale").description("사용 언어")
						)
				));
	}
}
