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
	 * 회원 정보 조회 실패 테스트<br/>
	 * - 저장되지 않은 계정을 통해 API를 실행할 경우, 조회되는 데이터가 없다는 에러 메시지가 출력되는지 테스트<br/>
	 * - 결과 메시지는 한글로 출력되도록 다국어 적용
	 * 
	 * @throws Exception
	 */
	@Test
	void testMemberNotFoundWithLocaleKoKr() throws Exception {
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
	 * 회원 정보 조회 실패 테스트<br/>
	 * - 저장되지 않은 계정을 통해 API를 실행할 경우, 조회되는 데이터가 없다는 에러 메시지가 출력되는지 테스트<br/>
	 * - 결과 메시지는 영어로 출력되도록 다국어 적용
	 * 
	 * @throws Exception
	 */
	@Test
	void testMemberNotFoundWithLocaleEnUs() throws Exception {
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
	 * 회원 정보 상세 조회 오류 테스트<br/>
	 * - 가입되어 있지 않은 계정으로 회원 정보를 검색하였을 오류가 발생하는지 테스트<br/>
	 * 
	 * @param account : 회원 계정
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

}
