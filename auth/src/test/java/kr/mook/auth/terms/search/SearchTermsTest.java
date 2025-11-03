package kr.mook.auth.terms.search;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.documentationConfiguration;
import static org.springframework.restdocs.payload.PayloadDocumentation.fieldWithPath;
import static org.springframework.restdocs.payload.PayloadDocumentation.responseFields;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.restdocs.AutoConfigureRestDocs;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.restdocs.RestDocumentationContextProvider;
import org.springframework.restdocs.RestDocumentationExtension;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import kr.mook.auth.common.dto.ResponseDto;
import kr.mook.auth.common.enumeration.LanguageEnum;
import kr.mook.auth.common.enumeration.ResponseTypeEnum;
import kr.mook.auth.terms.controller.TermsController;
import kr.mook.auth.terms.persistence.search.SearchTermsMapper;
import kr.mook.auth.terms.service.search.SearchTermsServiceImpl;

/**
 * 이용약관 조회 테스트
 * 
 * @since 2025. 10. 31.
 * @version 0.1
 * @author Inmook, Jeong
 */
@AutoConfigureRestDocs(outputDir = "target/snippets")
@WebMvcTest(TermsController.class)
@ExtendWith(RestDocumentationExtension.class)
@Import(SearchTermsServiceImpl.class)	// MockitoBean으로 정의하면 가짜 Service를 주입하기 때문에 원하는 Method를 실행할 수 없으므로, @Import를 통해 실제 ServiceImpl을 실행할 수 있도록 작성
class SearchTermsTest {
	
	@Autowired
	private MockMvc mockMvc;
	
	@MockitoBean
	private SearchTermsMapper _searchTermsMapper;
	
	@BeforeEach
	void setUp(WebApplicationContext webApplicationContext, RestDocumentationContextProvider restDocumentation) {
		this.mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext)
				.apply(documentationConfiguration(restDocumentation))
				.build();
	}
	
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
		
		ResponseDto expectedResponseDto = ResponseDto.builder()
													.statusCode("400")
													.status("SEARCH[THE TERMS_NO IS GAREATER THAN ZERO]")
													.resultType(ResponseTypeEnum.STRING)
													.result("이용약관 번호가 올바르지 않습니다. 이용약관 번호는 1이상의 숫자를 입력해주세요.")
													.language(LanguageEnum.KOREAN)
													.build();
		
		ResponseDto actualResponseDto = new SearchTermsServiceImpl(this._searchTermsMapper).searchByTermsNo(termsNo);
		assertEquals(expectedResponseDto, actualResponseDto);
		
		mockMvc.perform(get("/api/terms/" + termsNo)
				.accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isBadRequest())
				.andExpect(jsonPath("$.statusCode").exists())
				.andExpect(jsonPath("$.status").exists())
				.andExpect(jsonPath("$.resultType").exists())
				.andExpect(jsonPath("$.result").exists())
				.andExpect(jsonPath("$.language").exists())
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
		
		ResponseDto expectedResponseDto = ResponseDto.builder()
													.statusCode("404")
													.status("SEARCH[NOT FOUND TERMS]")
													.resultType(ResponseTypeEnum.STRING)
													.result("이용약관 정보를 찾을 수 없습니다. 이용약관 번호를 다시 확인해주세요.")
													.language(LanguageEnum.KOREAN)
													.build();
		
		ResponseDto actualResponseDto = new SearchTermsServiceImpl(this._searchTermsMapper).searchByTermsNo(termsNo);
		assertEquals(expectedResponseDto, actualResponseDto);
		
		mockMvc.perform(get("/api/terms/" + termsNo)
				.accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isNotFound())
				.andExpect(jsonPath("$.statusCode").exists())
				.andExpect(jsonPath("$.status").exists())
				.andExpect(jsonPath("$.resultType").exists())
				.andExpect(jsonPath("$.result").exists())
				.andExpect(jsonPath("$.language").exists())
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
}
