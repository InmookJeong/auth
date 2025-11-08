package kr.mook.auth.terms.search;

import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.payload.PayloadDocumentation.fieldWithPath;
import static org.springframework.restdocs.payload.PayloadDocumentation.responseFields;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

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

import kr.mook.auth.common.enumeration.LanguageEnum;
import kr.mook.auth.common.enumeration.ResponseTypeEnum;

/**
 * 이용약관 조회 테스트
 * 
 * @since 2025. 10. 31.
 * @version 0.1
 * @author Inmook, Jeong
 */
@SpringBootTest
@AutoConfigureMockMvc
@AutoConfigureRestDocs
class SearchTermsTest {
	
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
	 * 이용약관 번호 오류 테스트<br/>
	 * - 적합하지 않은 이용약관 번호를 전달하였을 경우, 이용약관 번호가 올바르지 않다는 메시지가 출력되는지 테스트<br/>
	 * - TermsN가 0 이하의 숫자인 경우 400 에러 발생
	 * 
	 * @throws Exception
	 */
	@Test
	void testSearchByUnvaildTermsNo() throws Exception {
		long termsNo = 0L;
		
		mockMvc.perform(get("/api/terms/{termsNo}", termsNo)
				.accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isBadRequest())
				.andExpect(jsonPath("$.statusCode").value("400"))
				.andExpect(jsonPath("$.status").value("SEARCH[THE TERMS_NO IS GAREATER THAN ZERO]"))
				.andExpect(jsonPath("$.resultType").value(ResponseTypeEnum.STRING.name()))
				.andExpect(jsonPath("$.result").value("이용약관 번호가 올바르지 않습니다. 이용약관 번호는 1이상의 숫자를 입력해주세요."))
				.andExpect(jsonPath("$.language").value(LanguageEnum.KOREAN.name()))
				.andDo(print())
				.andDo(document(
						"terms/search/one/search-by-unvaild-terms-no",
						responseFields(
								fieldWithPath("statusCode").description("결과 상태 코드"),
								fieldWithPath("status").description("상태코드 명칭(설명)"),
								fieldWithPath("resultType").description("결과 타입(ex. Number, String)"),
								fieldWithPath("result").description("결과 메시지"),
								fieldWithPath("language").description("사용 언어")
								)
						));
	}
	
	/**
	 * 이용약관 정보 조회 실패 테스트<br/>
	 * - 저장되지 않은 이용약관 번호를 통해 API를 실행할 경우, 조회되는 데이터가 없다는 에러 메시지가 출력되는지 테스트
	 * 
	 * @throws Exception
	 */
	@Test
	void testTermsNotFound() throws Exception {
		long termsNo = 10L;
		
		mockMvc.perform(get("/api/terms/{termsNo}", termsNo)
				.accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isNotFound())
				.andExpect(jsonPath("$.statusCode").value("404"))
				.andExpect(jsonPath("$.status").value("SEARCH[NOT FOUND TERMS]"))
				.andExpect(jsonPath("$.resultType").value(ResponseTypeEnum.STRING.name()))
				.andExpect(jsonPath("$.result").value("이용약관 정보를 찾을 수 없습니다. 이용약관 번호를 다시 확인해주세요."))
				.andExpect(jsonPath("$.language").value(LanguageEnum.KOREAN.name()))
				.andDo(print())
				.andDo(document(
						"terms/search/one/terms-not-found",
						responseFields(
								fieldWithPath("statusCode").description("결과 상태 코드"),
								fieldWithPath("status").description("상태코드 명칭(설명)"),
								fieldWithPath("resultType").description("결과 타입(ex. Number, String)"),
								fieldWithPath("result").description("결과 메시지"),
								fieldWithPath("language").description("사용 언어")
								)
						));
	}
	
	/**
	 * 이용약관 정보 조회 성공 테스트<br/>
	 * - 전달된 이용약관 번호를 통해 데이터가 조회되었을 경우, 조회된 이용약관 데이터가 전달되는지 테스트
	 * 
	 * @throws Exception
	 */
	@Test
	void testSearchTerms() throws Exception {
		long termsNo = 1L;
		
		mockMvc.perform(get("/api/terms/{termsNo}", termsNo)
				.accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.statusCode").value("200"))
				.andExpect(jsonPath("$.status").value("SEARCH[TERMS]"))
				.andExpect(jsonPath("$.resultType").value(ResponseTypeEnum.OBJECT.name()))
				.andExpect(jsonPath("$.language").value(LanguageEnum.KOREAN.name()))
				.andExpect(jsonPath("$.result.termsNo").value(1L))
				.andExpect(jsonPath("$.result.useYn").value(true))
				.andExpect(jsonPath("$.result.requireYn").value(true))
				.andExpect(jsonPath("$.result.orderNo").value(1L))
				.andExpect(jsonPath("$.result.title").value("테스트 - 사이트 이용 약관"))
				.andExpect(jsonPath("$.result.contents").value("사이트에 대한 설명과 이용 규칙 및 규정, 광고, 서비스 오류 사항, 운영 정책 등에 대한 내용이 작성됩니다."))
				.andExpect(jsonPath("$.result.createId").value(0L))
				.andExpect(jsonPath("$.result.createDate").exists())
				.andExpect(jsonPath("$.result.updateId").isEmpty())
				.andExpect(jsonPath("$.result.updateDate").isEmpty())
				.andDo(print())
				.andDo(document(
						"terms/search/one/search-terms",
						responseFields(
								fieldWithPath("statusCode").description("결과 상태 코드"),
								fieldWithPath("status").description("상태코드 명칭(설명)"),
								fieldWithPath("resultType").description("결과 타입(ex. Number, String)"),
								fieldWithPath("language").description("사용 언어"),
								fieldWithPath("result.termsNo").description("이용약관 번호"),
								fieldWithPath("result.useYn").description("사용 여부"),
								fieldWithPath("result.requireYn").description("필수 여부"),
								fieldWithPath("result.orderNo").description("출력 순번"),
								fieldWithPath("result.title").description("이용약관 제목"),
								fieldWithPath("result.contents").description("이용약관 상세 내용"),
								fieldWithPath("result.createId").description("이용약관 생성자 아이디"),
								fieldWithPath("result.createDate").description("이용약관 생성일시"),
								fieldWithPath("result.updateId").description("이용약관 수정자 아이디"),
								fieldWithPath("result.updateDate").description("이용약관 수정일시")
								)
						));
	}
}
