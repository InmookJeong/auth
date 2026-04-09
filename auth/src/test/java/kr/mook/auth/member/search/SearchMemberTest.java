package kr.mook.auth.member.search;

import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.payload.PayloadDocumentation.fieldWithPath;
import static org.springframework.restdocs.payload.PayloadDocumentation.responseFields;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
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
import org.springframework.http.MediaType;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.Sql.ExecutionPhase;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultMatcher;

import kr.mook.auth.common.enumeration.ResponseTypeEnum;

/**
 * 회원 조회 테스트
 * 
 * @since 2026. 03. 31.
 * @version 0.1
 * @author Inmook, Jeongr
 */
@SpringBootTest
@AutoConfigureMockMvc
@AutoConfigureRestDocs
public class SearchMemberTest {
	
	/* 다국어 테스트를 위한 Locale 정보 */
	private final Locale _LOCALE_KO_KR = Locale.KOREA;
	private final Locale _LOCALE_EN_US = Locale.US;
	private final String _ACCEPT_LANGUAGE_KO_KR = "ko-KR";
	private final String _ACCEPT_LANGUAGE_EN_US = "en-US";
	
	@Autowired
	private MockMvc mockMvc;
	
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
	
	/**
	 * 계정을 통한 회원 정보 조회 실패 테스트<br/>
	 * - 저장되지 않은 계정을 통해 API를 실행할 경우, 조회되는 데이터가 없다는 에러 메시지가 출력되는지 테스트<br/>
	 * - 결과 메시지는 한글로 출력되도록 다국어 적용
	 * 
	 * @throws Exception
	 */
	@Test
	void testMemberNotFoundByAccountWithLocaleKoKr() throws Exception {
		String account = "korean2026";
		String httpStatusCode = "404";
		String statusCode = "ERR-MEM-SER-001";
		String status = "SEARCH ERROR";
		String resultMessage = "회원 정보를 찾을 수 없습니다. 계정을(를) 다시 확인해주세요.";
		String apiDocsDir = "member/search/account/member-not-found/ko";
		ResultMatcher resultMatcher = status().isNotFound();
		
		this._testSearchByNotValidData(account, this._LOCALE_KO_KR, this._ACCEPT_LANGUAGE_KO_KR, httpStatusCode, statusCode, status, resultMessage, apiDocsDir, resultMatcher);
	}
	
	/**
	 * 계정을 통한 회원 정보 조회 실패 테스트<br/>
	 * - 저장되지 않은 계정을 통해 API를 실행할 경우, 조회되는 데이터가 없다는 에러 메시지가 출력되는지 테스트<br/>
	 * - 결과 메시지는 영어로 출력되도록 다국어 적용
	 * 
	 * @throws Exception
	 */
	@Test
	void testMemberNotFoundByAccountWithLocaleEnUs() throws Exception {
		String account = "korean2026";
		String httpStatusCode = "404";
		String statusCode = "ERR-MEM-SER-001";
		String status = "SEARCH ERROR";
		String resultMessage = "Member information could not be found. Please check your account again.";
		String apiDocsDir = "member/search/account/member-not-found/en";
		ResultMatcher resultMatcher = status().isNotFound();
		
		this._testSearchByNotValidData(account, this._LOCALE_EN_US, this._ACCEPT_LANGUAGE_EN_US, httpStatusCode, statusCode, status, resultMessage, apiDocsDir, resultMatcher);
	}
	
	/**
	 * 아이디를 통한 회원 정보 조회 실패 테스트<br/>
	 * - 저장되지 않은 회원 아이디를 통해 API를 실행할 경우, 조회되는 데이터가 없다는 에러 메시지가 출력되는지 테스트<br/>
	 * - 결과 메시지는 한글로 출력되도록 다국어 적용
	 * 
	 * @throws Exception
	 */
	@Test
	void testMemberNotFoundByMemberIdWithLocaleKoKr() throws Exception {
		long memberId = 10L;
		String httpStatusCode = "404";
		String statusCode = "ERR-MEM-SER-002";
		String status = "SEARCH ERROR";
		String resultMessage = "회원 정보를 찾을 수 없습니다. 아이디을(를) 다시 확인해주세요.";
		String apiDocsDir = "member/search/member-id/member-not-found/ko";
		ResultMatcher resultMatcher = status().isNotFound();
		
		this._testSearchByNotValidData2(memberId, this._LOCALE_KO_KR, this._ACCEPT_LANGUAGE_KO_KR, httpStatusCode, statusCode, status, resultMessage, apiDocsDir, resultMatcher);
	}
	
	/**
	 * 아이디를 통한 회원 정보 조회 실패 테스트<br/>
	 * - 저장되지 않은 회원 아이디 통해 API를 실행할 경우, 조회되는 데이터가 없다는 에러 메시지가 출력되는지 테스트<br/>
	 * - 결과 메시지는 영어로 출력되도록 다국어 적용
	 * 
	 * @throws Exception
	 */
	@Test
	void testMemberNotFoundByMemberIdWithLocaleEnUs() throws Exception {
		long memberId = 10L;
		String httpStatusCode = "404";
		String statusCode = "ERR-MEM-SER-002";
		String status = "SEARCH ERROR";
		String resultMessage = "Member information could not be found. Please check your ID again.";
		String apiDocsDir = "member/search/member-id/member-not-found/en";
		ResultMatcher resultMatcher = status().isNotFound();
		
		this._testSearchByNotValidData2(memberId, this._LOCALE_EN_US, this._ACCEPT_LANGUAGE_EN_US, httpStatusCode, statusCode, status, resultMessage, apiDocsDir, resultMatcher);
	}
	
	/**
	 * 회원 정보 상세 조회 오류 테스트<br/>
	 * - 가입되어 있지 않은 계정으로 회원 정보를 검색하였을 오류가 발생하는지 테스트<br/>
	 * 
	 * @param url : 회원 계정 및 아이디 등 API URL을 통해 전달되어야 하는 데이터
	 * @param locale : 다국어
	 * @param acceptLanguage : 다국어 정보(ex. ko-KR 또는 en-US)
	 * @param statusCode : 처리 상태 코드(ex. 400, 404)
	 * @param status : 처리 결과 상태 구문
	 * @param resultMessage : 처리 결과 메시지
	 * @param apiDocsDir : API 문서 경로
	 * @param resultMatcher : 예상되는 HTTP 상태
	 * @throws Exception
	 */
	private void _testSearchByNotValidData(String account, Locale locale, String acceptLanguage, String httpStatusCode, String statusCode, String status, String resultMessage, String apiDocsDir, ResultMatcher resultMatcher) throws Exception {
		mockMvc.perform(get("/api/member/{account}", account)
				.header("Accept-Language", acceptLanguage)
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
	
	private void _testSearchByNotValidData2(long memberId, Locale locale, String acceptLanguage, String httpStatusCode, String statusCode, String status, String resultMessage, String apiDocsDir, ResultMatcher resultMatcher) throws Exception {
		mockMvc.perform(get("/api/member/id/{memberId}", memberId)
				.header("Accept-Language", acceptLanguage)
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
	
	/**
	 * 계정을 통한 회원 정보 조회 성공 테스트<br/>
	 * - 전달된 계정을 통해 회원이 조회되었을 경우, 조회된 회원 정보가 정상적으로 전달되는지 테스트<br/>
	 * - 다국어(한국어, 영어)에 관계없이 동일한 결과를 반환하므로 '한국어'에 대한 테스트만 진행
	 * 
	 * @throws Exception
	 */
	@Test
	void testSearchMemberByAccount() throws Exception {
		String account = "kor2026";
		String httpStatusCode = "200";
		String statusCode = "MEM-SER-001";
		String status = "SEARCH";
		String apiDocsDir = "member/search/account/search-member";
		
		mockMvc.perform(get("/api/member/{account}", account)
				.header("Accept-Language", this._ACCEPT_LANGUAGE_KO_KR)
				.accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.httpStatusCode").value(httpStatusCode))
				.andExpect(jsonPath("$.statusCode").value(statusCode))
				.andExpect(jsonPath("$.status").value(status))
				.andExpect(jsonPath("$.resultType").value(ResponseTypeEnum.OBJECT.name()))
				.andExpect(jsonPath("$.locale").value(this._LOCALE_KO_KR.toString()))
				.andExpect(jsonPath("$.result.memberId").value(1L))
				.andExpect(jsonPath("$.result.account").value(account))
				.andExpect(jsonPath("$.result.active").value("Y"))
				.andExpect(jsonPath("$.result.name").value("한국인"))
				.andExpect(jsonPath("$.result.birth").value("19901207"))
				.andExpect(jsonPath("$.result.gender").value("M"))
				.andExpect(jsonPath("$.result.email").value("kor2026@korea.co.kr"))
				.andExpect(jsonPath("$.result.phoneNumber").value("01012345678"))
				.andExpect(jsonPath("$.result.postNumber").value("01123"))
				.andExpect(jsonPath("$.result.address").value("서울시 종로구 종로동 종로1가 1번지"))
				.andDo(print())
				.andDo(document(
						apiDocsDir,
						responseFields(
								fieldWithPath("httpStatusCode").description("HTTP 응답 상태 코드"),
								fieldWithPath("statusCode").description("결과 상태 코드"),
								fieldWithPath("status").description("상태코드 명칭(설명)"),
								fieldWithPath("resultType").description("결과 타입(ex. Number, String)"),
								fieldWithPath("locale").description("사용 언어"),
								fieldWithPath("result.memberId").description("회원 아이디(형식 : 숫자)"),
								fieldWithPath("result.account").description("회원 계정"),
								fieldWithPath("result.active").description("활성화 여부(Y:활성화, N:비활성화)"),
								fieldWithPath("result.name").description("회원 이름"),
								fieldWithPath("result.birth").description("회원 생년월일(형식 : yyyyMMdd)"),
								fieldWithPath("result.gender").description("성별(M:남성, W:여성)"),
								fieldWithPath("result.email").description("이메일(ex. test@korea.co.kr)"),
								fieldWithPath("result.phoneNumber").description("휴대전화 번호(ex. 01012345678)"),
								fieldWithPath("result.postNumber").description("우편번호"),
								fieldWithPath("result.address").description("주소")
								)
						));
	}
	
	/**
	 * 회원 아이디를 통한 회원 정보 조회 성공 테스트<br/>
	 * - 전달된 회원 아이디를 통해 회원이 조회되었을 경우, 조회된 회원 정보가 정상적으로 전달되는지 테스트<br/>
	 * - 다국어(한국어, 영어)에 관계없이 동일한 결과를 반환하므로 '한국어'에 대한 테스트만 진행
	 * 
	 * @throws Exception
	 */
	@Test
	void testSearchMemberByMemberId() throws Exception {
		long memberId = 1L;
		String httpStatusCode = "200";
		String statusCode = "MEM-SER-002";
		String status = "SEARCH";
		String apiDocsDir = "member/search/member-id/search-member";
		
		mockMvc.perform(get("/api/member/id/{memberId}", memberId)
				.header("Accept-Language", this._ACCEPT_LANGUAGE_KO_KR)
				.accept(MediaType.APPLICATION_JSON))
		.andExpect(status().isOk())
		.andExpect(jsonPath("$.httpStatusCode").value(httpStatusCode))
		.andExpect(jsonPath("$.statusCode").value(statusCode))
		.andExpect(jsonPath("$.status").value(status))
		.andExpect(jsonPath("$.resultType").value(ResponseTypeEnum.OBJECT.name()))
		.andExpect(jsonPath("$.locale").value(this._LOCALE_KO_KR.toString()))
		.andExpect(jsonPath("$.result.memberId").value(memberId))
		.andExpect(jsonPath("$.result.account").value("kor2026"))
		.andExpect(jsonPath("$.result.active").value("Y"))
		.andExpect(jsonPath("$.result.name").value("한국인"))
		.andExpect(jsonPath("$.result.birth").value("19901207"))
		.andExpect(jsonPath("$.result.gender").value("M"))
		.andExpect(jsonPath("$.result.email").value("kor2026@korea.co.kr"))
		.andExpect(jsonPath("$.result.phoneNumber").value("01012345678"))
		.andExpect(jsonPath("$.result.postNumber").value("01123"))
		.andExpect(jsonPath("$.result.address").value("서울시 종로구 종로동 종로1가 1번지"))
		.andDo(print())
		.andDo(document(
				apiDocsDir,
				responseFields(
						fieldWithPath("httpStatusCode").description("HTTP 응답 상태 코드"),
						fieldWithPath("statusCode").description("결과 상태 코드"),
						fieldWithPath("status").description("상태코드 명칭(설명)"),
						fieldWithPath("resultType").description("결과 타입(ex. Number, String)"),
						fieldWithPath("locale").description("사용 언어"),
						fieldWithPath("result.memberId").description("회원 아이디(형식 : 숫자)"),
						fieldWithPath("result.account").description("회원 계정"),
						fieldWithPath("result.active").description("활성화 여부(Y:활성화, N:비활성화)"),
						fieldWithPath("result.name").description("회원 이름"),
						fieldWithPath("result.birth").description("회원 생년월일(형식 : yyyyMMdd)"),
						fieldWithPath("result.gender").description("성별(M:남성, W:여성)"),
						fieldWithPath("result.email").description("이메일(ex. test@korea.co.kr)"),
						fieldWithPath("result.phoneNumber").description("휴대전화 번호(ex. 01012345678)"),
						fieldWithPath("result.postNumber").description("우편번호"),
						fieldWithPath("result.address").description("주소")
						)
				));
	}

}
