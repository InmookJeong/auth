package kr.mook.auth.terms.save;

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
import kr.mook.auth.terms.dto.TermsDto;
import kr.mook.auth.terms.save.persistence.SaveTermsMapper;

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
	
	@MockBean
	private SaveTermsMapper _saveTermsMapper;
	
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
	void testTermsDtoIsNullWithLocaleKoKr() throws Exception {
		TermsDto termsDto = null;
		String httpStatusCode = "400";
		String statusCode = "ERR-TMS-SAV-001";
		String status = "SAVE ERROR";
		String resultMessage = "이용약관 정보가 비어있거나 전달되지 않았습니다. 저장하실 이용약관 정보를 다시 확인해주세요.";
		String apiDocsDir = "terms/save/terms-is-null/ko";
		ResultMatcher resultMatcher = status().isBadRequest();

		_testSaveByNotValidData(termsDto, _LOCALE_KO_KR, _ACCEPT_LANGUAGE_KO_KR, httpStatusCode, statusCode, status, resultMessage, apiDocsDir, resultMatcher);
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
	void testTermsDtoIsNullWithLocaleEnUs() throws Exception {
		TermsDto termsDto = null;
		String httpStatusCode = "400";
		String statusCode = "ERR-TMS-SAV-001";
		String status = "SAVE ERROR";
		String resultMessage = "The Terms of Use information is empty or not provided. Please check the Terms of Use information you wish to save.";
		String apiDocsDir = "terms/save/terms-is-null/en";
		ResultMatcher resultMatcher = status().isBadRequest();
		
		_testSaveByNotValidData(termsDto, _LOCALE_EN_US, _ACCEPT_LANGUAGE_EN_US, httpStatusCode, statusCode, status, resultMessage, apiDocsDir, resultMatcher);
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
	void testTermsTitleIsEmptyWithLocaleKoKr() throws Exception {
		TermsDto termsDto = new TermsDto();
		String titleFieldName = this._messageSource.getMessage("title", null, _LOCALE_KO_KR);
		String httpStatusCode = "400";
		String statusCode = "ERR-TMS-SAV-002";
		String status = "SAVE ERROR";
		String resultMessage = "이용약관의 " + titleFieldName + "은 필수 입력 대상입니다. " + titleFieldName + "을 입력해주세요.";
		String apiDocsDir = "terms/save/terms-title-is-empty/ko";
		ResultMatcher resultMatcher = status().isBadRequest();
		
		_testSaveByNotValidData(termsDto, _LOCALE_KO_KR, _ACCEPT_LANGUAGE_KO_KR, httpStatusCode, statusCode, status, resultMessage, apiDocsDir, resultMatcher);
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
	void testTermsTitleIsEmptyWithLocaleEnUs() throws Exception {
		TermsDto termsDto = new TermsDto();
		String titleFieldName = this._messageSource.getMessage("title", null, _LOCALE_EN_US);
		String httpStatusCode = "400";
		String statusCode = "ERR-TMS-SAV-002";
		String status = "SAVE ERROR";
		String resultMessage = "The " + titleFieldName + " of the Terms of Use is required. Please enter a " + titleFieldName + ".";
		String apiDocsDir = "terms/save/terms-title-is-empty/en";
		ResultMatcher resultMatcher = status().isBadRequest();
		
		_testSaveByNotValidData(termsDto, _LOCALE_EN_US, _ACCEPT_LANGUAGE_EN_US, httpStatusCode, statusCode, status, resultMessage, apiDocsDir, resultMatcher);
	}
	
	/**
	 * TermsDto에 내용(contents)이 없는 경우 오류 테스트<br/>
	 * - 저장할 이용약관 정보 중 내용(contents)이 비어있는 경우, 이용약관 내용(contents)을 입력하라는 메시지가 출력되는지 테스트<br/>
	 * - 저장할 이용약관 정보의 제목이 입력되지 않았을 경우 400 에러 발생<br/>
	 * - 결과 메시지는 한글로 출력되도록 다국어 적용
	 * 
	 * @throws Exception
	 */
	@Test
	void testTermsContentsIsEmptyWithLocaleKoKr() throws Exception {
		TermsDto termsDto = new TermsDto();
		termsDto.setTitle("사이트 이용 약관");
		
		String titleFieldName = this._messageSource.getMessage("contents", null, _LOCALE_KO_KR);
		String httpStatusCode = "400";
		String statusCode = "ERR-TMS-SAV-003";
		String status = "SAVE ERROR";
		String resultMessage = "이용약관의 " + titleFieldName + "은 필수 입력 대상입니다. " + titleFieldName + "을 입력해주세요.";
		String apiDocsDir = "terms/save/terms-contents-is-empty/ko";
		ResultMatcher resultMatcher = status().isBadRequest();
		
		_testSaveByNotValidData(termsDto, _LOCALE_KO_KR, _ACCEPT_LANGUAGE_KO_KR, httpStatusCode, statusCode, status, resultMessage, apiDocsDir, resultMatcher);
	}
	
	/**
	 * TermsDto에 내용(contents)이 없는 경우 오류 테스트<br/>
	 * - 저장할 이용약관 정보 중 내용(contents)이 비어있는 경우, 이용약관 내용(contents)을 입력하라는 메시지가 출력되는지 테스트<br/>
	 * - 저장할 이용약관 정보의 제목이 입력되지 않았을 경우 400 에러 발생<br/>
	 * - 결과 메시지는 영어로 출력되도록 다국어 적용
	 * 
	 * @throws Exception
	 */
	@Test
	void testTermsContentsIsEmptyWithLocaleEnUs() throws Exception {
		TermsDto termsDto = new TermsDto();
		termsDto.setTitle("사이트 이용 약관");
		
		String titleFieldName = this._messageSource.getMessage("contents", null, _LOCALE_EN_US);
		String httpStatusCode = "400";
		String statusCode = "ERR-TMS-SAV-003";
		String status = "SAVE ERROR";
		String resultMessage = "The " + titleFieldName + " of the Terms of Use is required. Please enter a " + titleFieldName + ".";
		String apiDocsDir = "terms/save/terms-contents-is-empty/en";
		ResultMatcher resultMatcher = status().isBadRequest();
		
		_testSaveByNotValidData(termsDto, _LOCALE_EN_US, _ACCEPT_LANGUAGE_EN_US, httpStatusCode, statusCode, status, resultMessage, apiDocsDir, resultMatcher);
	}
	
	/**
	 * TermsDto를 저장하는 과정에서 이용약관 번호가 발급되지 않는 경우<br/>
	 * - 이용약관 정보를 저장하기 위해 이용약관 번호를 발급하는 과정에서 오류가 발생할 경우, 이용약관 번호를 발급할 수 없다는 메시지가 출력되는지 테스트<br/>
	 * - 이용약관 번호가 발급되지 않을 경우 500에러 발생<br/>
	 * - 결과 메시지는 한글로 출력되도록 다국어 적용
	 * 
	 * @throws Exception
	 */
	@Test
	void testNextTermsNoErrorWithLocaleKoKr() throws Exception {
		given(_saveTermsMapper.nextTermsNo()).willThrow(new RuntimeException("Sequence Error"));
		TermsDto termsDto = this._getTermsDto();
		
		String httpStatusCode = "500";
		String statusCode = "ERR-TMS-SAV-005";
		String status = "SAVE ERROR";
		String resultMessage = "이용약관 일련번호를 발급할 수 없습니다. 관리자에게 문의해주세요.";
		String apiDocsDir = "terms/save/next-terms-no-error/ko";
		ResultMatcher resultMatcher = status().is5xxServerError();
		
		_testSaveByNotValidData(termsDto, _LOCALE_KO_KR, _ACCEPT_LANGUAGE_KO_KR, httpStatusCode, statusCode, status, resultMessage, apiDocsDir, resultMatcher);
	}
	
	/**
	 * TermsDto를 저장하는 과정에서 이용약관 번호가 발급되지 않는 경우<br/>
	 * - 이용약관 정보를 저장하기 위해 이용약관 번호를 발급하는 과정에서 오류가 발생할 경우, 이용약관 번호를 발급할 수 없다는 메시지가 출력되는지 테스트<br/>
	 * - 이용약관 번호가 발급되지 않을 경우 500에러 발생<br/>
	 * - 결과 메시지는 영어로 출력되도록 다국어 적용
	 * 
	 * @throws Exception
	 */
	@Test
	void testNextTermsNoErrorWithLocaleEnUs() throws Exception {
		given(this._saveTermsMapper.nextTermsNo()).willThrow(new RuntimeException("Sequence Error"));
		TermsDto termsDto = this._getTermsDto();
		
		String httpStatusCode = "500";
		String statusCode = "ERR-TMS-SAV-005";
		String status = "SAVE ERROR";
		String resultMessage = "Unable to generate Terms of Use number. Please contact your administrator.";
		String apiDocsDir = "terms/save/next-terms-no-error/en";
		ResultMatcher resultMatcher = status().is5xxServerError();
		
		_testSaveByNotValidData(termsDto, _LOCALE_EN_US, _ACCEPT_LANGUAGE_EN_US, httpStatusCode, statusCode, status, resultMessage, apiDocsDir, resultMatcher);
	}
	
	/**
	 * 이용약관 정보 저장 오류 테스트<br/>
	 * 
	 * @param termsDto : 저장할 이용약관 정보
	 * @param locale : 다국어
	 * @param acceptLanguage : 다국어 정보(ex. ko-KR 또는 en-US)
	 * @param httpStatusCode : HTTP 처리 상태 코드(ex. 400, 404)
	 * @param statusCode : 처리 상태 코드(ex. TRM-SAV-001, ERR-TRM-SAV-001)
	 * @param status : 처리 결과 상태 구문
	 * @param resultMessage : 처리 결과 메시지
	 * @param apiDocsDir : API 문서 경로
	 * @param resultMatcher : 예상되는 HTTP 상태
	 * @throws Exception
	 */
	private void _testSaveByNotValidData(TermsDto termsDto, Locale locale, String acceptLanguage, String httpStatusCode, String statusCode, String status, String resultMessage, String apiDocsDir, ResultMatcher resultMatcher) throws Exception{
		ObjectMapper mapper = new ObjectMapper();
		String termsDtoValue = mapper.writeValueAsString(termsDto);
		
		mockMvc.perform(post("/api/terms")
				.header("Accept-Language", acceptLanguage)
				.content(termsDtoValue)
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
	
	/**
	 * 저장 기능을 테스트할 이용약관 정보 샘플
	 * @return 저장할 이용약관 데이터<br/>
	 * termsDto = {<br/>
	 * 		&emsp;"useYn": true,<br/>
	 * 		&emsp;"requireYn": true,<br/>
	 * 		&emsp;"orderNo": 1,<br/>
	 * 		&emsp;"title": "사이트 이용 약관",<br/>
	 * 		&emsp;"contents": "사이트 소개, 이용 방법, 주의 사항과 관련된 안내 정보 및 관련 법률을 작성합니다.",<br/>
	 * 		&emsp;"createId": 1,<br/>
	 * 		&emsp;"createDate": "2026-01-26T21:39:36.722938700"<br/>
	 * }
	 */
	private TermsDto _getTermsDto() {
		TermsDto termsDto = new TermsDto();
		termsDto.setTermsNo(0L);
		termsDto.setUseYn(true);
		termsDto.setRequireYn(true);
		termsDto.setOrderNo(1L);
		termsDto.setTitle("사이트 이용 약관");
		termsDto.setContents("사이트 소개, 이용 방법, 주의 사항과 관련된 안내 정보 및 관련 법률을 작성합니다.");
		termsDto.setCreateId(1L);
		
		return termsDto;
	}

}
