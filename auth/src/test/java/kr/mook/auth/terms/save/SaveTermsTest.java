package kr.mook.auth.terms.save;

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
import org.springframework.context.MessageSource;
import org.springframework.http.MediaType;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.Sql.ExecutionPhase;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultMatcher;

import com.fasterxml.jackson.databind.ObjectMapper;

import kr.mook.auth.common.enumeration.ResponseTypeEnum;
import kr.mook.auth.terms.terms.TermsDto;

/**
 * 이용약관 저장 테스트
 * 
 * @since 2025. 12. 2.
 * @version 0.1
 * @author Inmook, Jeong
 */
@SpringBootTest
@AutoConfigureMockMvc
@AutoConfigureRestDocs
public class SaveTermsTest {
	
	/* 다국어 테스트를 위한 Locale 정보 */
	private final Locale _LOCALE_KO_KR = Locale.KOREA;
	private final Locale _LOCALE_EN_US = Locale.US;
	private final String _ACCEPT_LANGUAGE_KO_KR = "ko-KR";
	private final String _ACCEPT_LANGUAGE_EN_US = "en-US";
	
	@Autowired
	private MessageSource _messageSource;
	
	@Autowired
	private MockMvc mockMvc;
	
	/**
	 * 각 테스트 코드 수행 전 테이블 생성 및 테스트 데이터 저장
	 */
	@Sql(
		statements = {
			"CREATE TABLE TERMS (" +
			"	TERMS_NO BIGINT NOT NULL," +
			"	USE_YN TINYINT NOT NULL DEFAULT 1," + 
			"	REQUIRE_YN TINYINT NOT NULL DEFAULT 1," +
			"	ORDER_NO BIGINT NOT NULL DEFAULT 9999," +
			"	TITLE VARCHAR(2000) NOT NULL," +
			"	ONTENTS LONGTEXT NOT NULL," +
			"	CREATE_ID BIGINT NOT NULL," +
			"	CREATE_DATE DATETIME NOT NULL," +
			"	UPDATE_ID BIGINT," +
			"	UPDATE_DATE DATETIME," +
			"	CONSTRAINT TERMS_PK PRIMARY KEY(TERMS_NO)" +
			");",
			"INSERT INTO TERMS (TERMS_NO,USE_YN, REQUIRE_YN, ORDER_NO, TITLE, CONTENTS, CREATE_ID, CREATE_DATE)" + 
			"VALUES ( 1, 1, 1, 1, '테스트 - 사이트 이용 약관', '사이트에 대한 설명과 이용 규칙 및 규정, 광고, 서비스 오류 사항, 운영 정책 등에 대한 내용이 작성됩니다.', 0, NOW() );" + 
			"INSERT INTO TERMS (TERMS_NO,USE_YN, REQUIRE_YN, ORDER_NO, TITLE, CONTENTS, CREATE_ID, CREATE_DATE)" + 
			"VALUES (2, 1, 0, 2, '테스트 - 개인정보 수집 및 이용', '개인정보보호법에 따라 수집되는 개인 정보와 이용 목적을 안내하고, 동의 거부 시 불이익에 대한 내용을 안내합니다.', 0, NOW());"
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
			"DROP TABLE TERMS;"
		},
		executionPhase = ExecutionPhase.AFTER_TEST_METHOD
	)
	@AfterEach
	void dropTable() {}
	
	/**
	 * TermsDto를 Null로 전달한 경우 오류 테스트<br/>
	 * - 저장할 이용약관 정보가 비어있거나 전달되지 않았을 경우, 이용약관 정보를 확인하라는 메시지가 출력되는지 테스트<br/>
	 * - 저장할 이용약관 정보가 비어있거나 전달되지 않았을 경우 400 에러 발생<br/>
	 * - 결과 메시지는 한글로 출력되도록 다국어 적용
	 * 
	 * @throws Exception
	 */
	@Test
	void testTermsDtoIsNullWithLocalKoKr() throws Exception {
		TermsDto termsDto = null;
		String statusCode = "400";
		String status = "SAVE[DATA IS NULL]";
		String resultMessage = "이용약관 정보가 비어있거나 전달되지 않았습니다. 저장하실 이용약관 정보를 다시 확인해주세요.";
		String apiDocsDir = "terms/save/terms-is-null/ko";
		ResultMatcher resultMatcher = status().isBadRequest();

		_testSaveByNotValidData(termsDto, _LOCALE_KO_KR, _ACCEPT_LANGUAGE_KO_KR, statusCode, status, resultMessage, apiDocsDir, resultMatcher);
	}
	
	/**
	 * TermsDto를 Null로 전달한 경우 오류 테스트<br/>
	 * - 저장할 이용약관 정보가 비어있거나 전달되지 않았을 경우, 이용약관 정보를 확인하라는 메시지가 출력되는지 테스트<br/>
	 * - 저장할 이용약관 정보가 비어있거나 전달되지 않았을 경우 400 에러 발생<br/>
	 * - 결과 메시지는 영어로 출력되도록 다국어 적용
	 * 
	 * @throws Exception
	 */
	@Test
	void testTermsDtoIsNullWithLocalEnUs() throws Exception {
		TermsDto termsDto = null;
		String statusCode = "400";
		String status = "SAVE[DATA IS NULL]";
		String resultMessage = "The Terms of Use information is empty or not provided. Please check the Terms of Use information you wish to save.";
		String apiDocsDir = "terms/save/terms-is-null/en";
		ResultMatcher resultMatcher = status().isBadRequest();
		
		_testSaveByNotValidData(termsDto, _LOCALE_EN_US, _ACCEPT_LANGUAGE_EN_US, statusCode, status, resultMessage, apiDocsDir, resultMatcher);
	}
	
	/**
	 * TermsDto에 제목(title)이 없는 경우 오류 테스트<br/>
	 * - 저장할 이용약관 정보 중 제목(title)이 비어있는 경우, 이용약관 제목(title)을 입력하라는 메시지가 출력되는지 테스트<br/>
	 * - 저장할 이용약관 정보의 제목이 입력되지 않았을 경우 400 에러 발생<br/>
	 * - 결과 메시지는 한글로 출력되도록 다국어 적용
	 * 
	 * @throws Exception
	 */
	@Test
	void testTermsTitleIsEmptyWithLocalKoKr() throws Exception {
		TermsDto termsDto = new TermsDto();
		String titleFieldName = this._messageSource.getMessage("title", null, _LOCALE_KO_KR);
		String statusCode = "400";
		String status = "SAVE ERROR[TERMS TITLE IS EMPTY]";
		String resultMessage = "이용약관의 " + titleFieldName + "은 필수 입력 대상입니다. " + titleFieldName + "을 입력해주세요.";
		String apiDocsDir = "terms/save/terms-title-is-empty/ko";
		ResultMatcher resultMatcher = status().isBadRequest();
		
		_testSaveByNotValidData(termsDto, _LOCALE_KO_KR, _ACCEPT_LANGUAGE_KO_KR, statusCode, status, resultMessage, apiDocsDir, resultMatcher);
	}
	
	/**
	 * TermsDto에 제목(title)이 없는 경우 오류 테스트<br/>
	 * - 저장할 이용약관 정보 중 제목(title)이 비어있는 경우, 이용약관 제목(title)을 입력하라는 메시지가 출력되는지 테스트<br/>
	 * - 저장할 이용약관 정보의 제목이 입력되지 않았을 경우 400 에러 발생<br/>
	 * - 결과 메시지는 영어로 출력되도록 다국어 적용
	 * 
	 * @throws Exception
	 */
	@Test
	void testTermsTitleIsEmptyWithLocalEnUs() throws Exception {
		TermsDto termsDto = new TermsDto();
		String titleFieldName = this._messageSource.getMessage("title", null, _LOCALE_EN_US);
		String statusCode = "400";
		String status = "SAVE ERROR[TERMS TITLE IS EMPTY]";
		String resultMessage = "The " + titleFieldName + " of the Terms of Use is required. Please enter a " + titleFieldName + ".";
		String apiDocsDir = "terms/save/terms-title-is-empty/en";
		ResultMatcher resultMatcher = status().isBadRequest();
		
		_testSaveByNotValidData(termsDto, _LOCALE_EN_US, _ACCEPT_LANGUAGE_EN_US, statusCode, status, resultMessage, apiDocsDir, resultMatcher);
	}
	
	/**
	 * 이용약관 정보 저장 오류 테스트<br/>
	 * 
	 * @param termsDto : 저장할 이용약관 정보
	 * @param locale : 다국어
	 * @param acceptLanguage : 다국어 정보(ex. ko-KR 또는 en-US)
	 * @param statusCode : 처리 상태 코드(ex. 400, 404)
	 * @param status : 처리 결과 상태 구문
	 * @param resultMessage : 처리 결과 메시지
	 * @param apiDocsDir : API 문서 경로
	 * @param resultMatcher : 예상되는 HTTP 상태
	 * @throws Exception
	 */
	private void _testSaveByNotValidData(TermsDto termsDto, Locale locale, String acceptLanguage, String statusCode, String status, String resultMessage, String apiDocsDir, ResultMatcher resultMatcher) throws Exception{
		ObjectMapper mapper = new ObjectMapper();
		String termsDtoValue = mapper.writeValueAsString(termsDto);
		
		mockMvc.perform(post("/api/terms")
				.header("Accept-Language", acceptLanguage)
				.content(termsDtoValue)
				.contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON))
				.andExpect(resultMatcher)
				.andExpect(jsonPath("$.statusCode").value(statusCode))
				.andExpect(jsonPath("$.status").value(status))
				.andExpect(jsonPath("$.resultType").value(ResponseTypeEnum.STRING.name()))
				.andExpect(jsonPath("$.result").value(resultMessage))
				.andExpect(jsonPath("$.locale").value(locale.toString()))
				.andDo(print())
				.andDo(document(
						apiDocsDir,
						responseFields(
								fieldWithPath("statusCode").description("결과 상태 코드"),
								fieldWithPath("status").description("상태코드 명칭(설명)"),
								fieldWithPath("resultType").description("결과 타입(ex. Number, String)"),
								fieldWithPath("result").description("결과 메시지"),
								fieldWithPath("locale").description("사용 언어")
								)
						));
	}

}
