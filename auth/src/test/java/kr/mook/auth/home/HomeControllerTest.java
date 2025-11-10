package kr.mook.auth.home;

import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.documentationConfiguration;
import static org.springframework.restdocs.payload.PayloadDocumentation.fieldWithPath;
import static org.springframework.restdocs.payload.PayloadDocumentation.responseFields;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Locale;

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
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import kr.mook.auth.common.enumeration.LanguageEnum;
import kr.mook.auth.common.enumeration.ResponseTypeEnum;
import kr.mook.auth.config.MessageSourceConfig;
import kr.mook.auth.home.controller.HomeController;
import kr.mook.auth.home.service.impl.HomeServiceImpl;

@AutoConfigureRestDocs(outputDir = "target/snippets")
@WebMvcTest(HomeController.class)
@ExtendWith(RestDocumentationExtension.class)
@Import({HomeServiceImpl.class, MessageSourceConfig.class})
public class HomeControllerTest {
	
	@Autowired
	private MockMvc mockMvc;
	
	
	@BeforeEach
	void setUp(WebApplicationContext webApplicationContext, RestDocumentationContextProvider restDocumentation) {
		this.mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext)
				.apply(documentationConfiguration(restDocumentation))
				.build();
	}
	
	@Test
	void home() throws Exception {
		
		mockMvc.perform(get("/api/home")
				.accept(MediaType.APPLICATION_JSON)
				.header("Accept-Language", Locale.KOREAN))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.statusCode").value("200"))
				.andExpect(jsonPath("$.status").value("HOME_ACCESS"))
				.andExpect(jsonPath("$.resultType").value(ResponseTypeEnum.STRING.name()))
				.andExpect(jsonPath("$.result").value("접속을 환영합니다."))
				.andExpect(jsonPath("$.language").value(LanguageEnum.KOREAN.name()))
				.andDo(print())
				.andDo(document(
						"home-test",
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
