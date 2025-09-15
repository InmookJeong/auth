# 1. Spring REST Docs란?

![Swagger Logo](../../images/매뉴얼/개발자매뉴얼/3.SpringRestDocs_설정/springrestdocs.png)
<br/><br/>

`Spring REST Docs`는 **Spring MVC**를 기반으로 `RESTful API 문서`를 자동 생성해주는 라이브러리입니다.

이 라이브러리는 `테스트 주도 방식`으로 API 문서를 작성하며, 특히 `테스트가 성공해야 문서가 생성`되기 때문에 정확한 정보를 제공할 수 있습니다.

그렇기 때문에 API 문서를 만들기 위해 `테스트 코드를 필수로 작성`해야 합니다.
<br/><br/>

Spring REST Docs를 통해 만들어진 API 문서는 `Asciidoctor` 또는 `Markdown` 형식으로 작성되어 있습니다.

이 때 생성된 문서를 `스닛펫`이라 하며, 여러 스니펫을 조합하여 사용자에게 제공할 수 있는 API 문서(웹 기반 문서)를 만들 수 있습니다.
- `Asciidoctor`는 Spring REST Docs의 기본 출력 형식으로, 복잡한 문서 구조와 스타일링을 지원합니다.
- `Markdown`은 간단한 문서 작성에 적합하지만 테이블과 같은 복잡한 구조를 표현하는데 제한이 있습니다.
<br/><br/>

다음은 Spring REST Docs의 주요 특징입니다.
- `테스트 기반 문서화`
    - 테스트가 성공해야 문서가 생성되므로 문서의 정확성을 보장합니다.
- `Asciidoctor` 또는 `Markdown` 지원
    - 기본적으로 Asciidoctor를 사용합니다.
    - 필요에 따라 markdown으로도 설정 가능합니다.
- `Swagger`와의 차별화
    - Swagger는 코드에 주석을 추가하여 문서를 생성하는 라이브러리입니다.
    - 단순히 주석을 추가하는 방식이기 때문에 코드 수정 후 주석을 변경하지 않아도 RESTful API 문서 생성에는 아무런 문제가 발생하지 않습니다.
    - 반면 Spring REST Docs는 테스트를 통해 문서를 생성하기 때문에 코드와 문서의 일관성을 유지할 수 있습니다.
- `유연한 문서화`
    - JSON, XML, Form 데이터 등 다양한 요청과 응답 형식을 지원합니다.
    - 또한 하이퍼미디어 링크도 RESTful API 문서에 표현되도록 지원합니다.
<br/><br/>

# 2. 왜 Swagger가 아닌 Spring REST Docs를 적용하였는가?

`Swagger`는 Spring REST Docs와 더불어 API 문서화를 위해 가장 많이 사용되는 라이브러리입니다.

그런데 왜 Swagger가 아닌 Spring REST Docs를 적용한 것일까요?

그 것은 테스트 케이스와 연동되어 API 문서를 작성해주기 때문입니다.
<br/><br/>

![Swagger Logo](../../images/매뉴얼/개발자매뉴얼/3.SpringRestDocs_설정/swagger.png)
<br/><br/>

먼저 Swagger의 특징에 대해 간단히 알아보겠습니다.
- `Annotation 기반 문서화`
    - Swagger는 `@Operation`, `@Parameter`, `@ApiResponse` 등 Annotation을 사용하여 API 문서를 만듭니다.
    - Annotation은 실제 동작하는 프로덕트 코드에 적용하게 됩니다.
    - 단, Annotation 기반으로 문서를 생성하기 때문에 Spring REST Docs보다 구현하기 쉽습니다.
- `테스트 코드`와의 독립성
    - Spring REST Docs와 달리 테스트 크드와는 별개로 API를 문서화합니다.
    - 심지어 테스트 코드가 아예 없어도 문서화를 진행합니다.
- UI를 통한 `인터랙티브 문서` 제공
    - Swagger는 **Swagger UI**를 통해 API 문서를 `시각적으로 제공`합니다.
    - 제공된 인터랙티브 문서를 통해 웹 환경에서 API를 직접 테스트할 수 있으며, 이를 통해 API 사용성을 높일 수 있습니다.
<br/><br/>

즉, Swagger는 프로덕트 코드에 직접 Annotation을 추가하기 때문에 소스코드의 가독성을 떨어뜨릴 수 있습니다.

또한 테스트 코드와 독립적이기 때문에 APi 문서에 대한 신뢰성이 높지 않습니다.

소스 코드는 수정하였지만 API 문서화를 위한 Annotation 정보는 그대로 두어도 API 문서가 만들어지기 때문입니다.
<br/><br/>

이러한 이유를 근거로 API 문서의 신뢰도를 높이기 위해 `Spring REST Docs 라이브러리`를 적용하였습니다.
<br/><br/>

# 3. build.gradle에 Spring Rest Docs를 위한 구성 추가하기
```gradle
plugins {
	id 'java'
	id 'org.springframework.boot' version '3.5.4'
	id 'io.spring.dependency-management' version '1.1.7'
	id 'org.asciidoctor.jvm.convert' version '3.3.2'        // (1) 
}

group = 'kr.mook'
version = '0.0.1-SNAPSHOT'

java {
	toolchain {
		languageVersion = JavaLanguageVersion.of(17)
	}
}

configurations {
	compileOnly {
		extendsFrom annotationProcessor
	}
	asciidoctorExt      // (2)
}

repositories {
	mavenCentral()
}

ext {
	set('snippetsDir', file("build/generated-snippets"))        // (3)
}

dependencies {
	implementation 'org.springframework.boot:spring-boot-starter-web'
	compileOnly 'org.projectlombok:lombok:1.18.38'
    annotationProcessor 'org.projectlombok:lombok:1.18.38'
	testImplementation 'org.springframework.boot:spring-boot-starter-test'
	testImplementation 'org.springframework.restdocs:spring-restdocs-mockmvc'   // (4)
	asciidoctorExt 'org.springframework.restdocs:spring-restdocs-asciidoctor'   // (5)
	testRuntimeOnly 'org.junit.platform:junit-platform-launcher'
}

tasks.named('test') {
	outputs.dir snippetsDir     // (6)
	useJUnitPlatform()
}

// (7)
tasks.named('asciidoctor') {
	inputs.dir snippetsDir
	dependsOn test
	configurations 'asciidoctorExt'
}

// (8)
tasks.register('copyDocument', Copy) {
	dependsOn asciidoctor
	from layout.buildDirectory.dir("docs/asciidoc")
	into layout.projectDirectory.dir("src/main/resources/static/docs")
}

// (9)
tasks.named('build') {
	dependsOn copyDocument
}
```

① `asciidoctor`에 대한 플러그인을 추가합니다.
- 이 플러그인은 AsciiDoc(.adoc) 파일을 변환하고 build 폴더에 복사하는 역할을 담당합니다.

② `asciidoctorExt`를 Configuration에 추가합니다.
- Asciidoctor의 확장 파일을 등록해줍니다.

③ `Snippets` 파일들이 저장될 경로를 설정해줍니다.
- 테스트 코드를 통과하면 Snippets 파일들이 지정된 경로에 생성됩니다.

④ 테스트 코드 작성을 위한 `MockMvc`를 `Spring REST Docs`에서 사용할 수 있도록 Dependency를 추가합니다.

⑤ `Spring REST Docs`에서 제공하는 `asciidoc(.adoc)` 파일을 생성하기 위해 Dependency를 추가합니다. 

⑥ 테스트 후 생성된 adoc 파일이 `③에서 지정한 경로에 저장`되도록 설정을 추가합니다.

⑦ `asciidoctor`와 관련된 설정을 작성합니다.
- `inputs.dir`은 asciidoctor를 통해` RESTful API를 문서화할 때 사용(입력)할 Snippets의 경로`를 지정합니다.
- `dependsOn test`는 태스크 진행 순서를 지정합니다.
    - 즉, build시 asciidoctor 태스크가 진행되기 전 `test` 태스크를 실행하라는 의미입니다.
    - `test` 태스크의 정보는 `⑥` 부분에 작성되어 있습니다.
- `configurations 'asciidoctorExt'`는 asciidoctor에서 asciidoctorExt을 `configurations로 사용`하도록 설정하는 부분입니다.

⑧ REST Docs로 만든 API 문서를 실제 배포/서비스 가능한 위치로 옮겨주는 태스크 정보를 작성합니다.
- 태스크의 이름은 `copyDocument`로 등록됩니다.
- `Copy`는 Gradle에서 제공하는 `내장 태스크 타입`으로 파일 복사 작업을 담당합니다.
- `dependsOn asciidoctor`는 copyDocument가 실행되기 전에 `asciidoctor 태스크를 실행`하도록 지정합니다.
- `from layout.buildDirectory.dir("docs/asciidoc")`는 asciidoctor 태스크가 실행되면 build/docs/asciidoc 폴더 안에 HTML 문서가 생성되는데 이 때 `"docs/asciidoc"`에 있는 파일을 가져오도록 설정합니다.
- `into layout.projectDirectory.dir("src/main/resources/static/docs")`는 복사한 파일을 어느 경로에 저장할 것인지 지정합니다.
    - `"src/main/resources/static/docs"`에 저장하기 때문에 서버가 실행된 후 `http://localhost:8080/docs/...` 형태로 API 문서에 접근할 수 있습니다.

⑨ Gradle에서 기본적으로 제공하는 `Build 태스크` 설정을 추가합니다.
- `dependsOn copyDocument`는 Build 태스크가 실행되기 전에 `copyDocument` 태스크를 실행하라는 의미입니다.
- 해당 설정을 통해 빌드를 할 때마다 API 문서를 다시 만들어 JAR에 포함하기 때문에 RESTful API 문서의 최신화를 유지할 수 있습니다.

### ※ 전체적인 Build의 흐름
`gradle build`를 실행하게 되면 `build.gradle`에 작성된 내용을 기반으로 아래 작성한 순서에 따라 RESTful API 문서가 생성됩니다.

(1) Test → 스니펫(generated-snippets) 생성
(2) asciidoctor(dependsOn test) 실행 → 문서(build/docs/asciidoc) 생성
(3) copyDocument(dependsOn asciidoctor) 실행 → 문서를 `static/docs`에 복사
(4) build(dependsOn copyDocument) 실행 → 최종 산출물(JAR)에 API 문서 포함
<br/><br/>

# 4. 테스트 코드 작성하기

간단한 로직을 통해 테스트 코드를 작성해보겠습니다.

테스트를 할 API는 `/api/home`입니다.

일반적으로 `home`은 웹사이트 첫 페이지를 출력하지만, 이번에는 API 테스트를 위해 사용하였습니다.

먼저 프로덕트 코드는 `HomeController`, `HomeService`, `HomeServiceImpl`, `ResponseDto`로 구성되어 있으며 아래와 같이 작성하였습니다.

#### > `HomeController 코드`
```java
package kr.mook.auth.home.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import kr.mook.auth.common.dto.ResponseDto;
import kr.mook.auth.home.service.HomeService;
import lombok.RequiredArgsConstructor;

/**
 * Home API<br/>
 * - 사용자가 Home API를 호출할 수 있도록 기능 구현
 * 
 * @since 2025. 8. 9.
 * @version 0.1
 * @author Dev.Mook
 */
@RestController
@RequiredArgsConstructor
public class HomeController {
	
	private final HomeService _homeService;
	
	/**
	 * auth 프로젝트의 root path API<br/>
	 * - '/api/home'으로 API를 호출될 때 실행
	 * 
	 * @return
	 * @since 2025. 08. 09
	 * @version 0.1
	 * @author Dev.Mook
	 */
	@GetMapping(value = "/api/home")
	public ResponseEntity<ResponseDto> Home() {
		return ResponseEntity.ok(this._homeService.home());
	}
}
```

#### > `HomeService 코드(인터페이스)`
```java
package kr.mook.auth.home.service;

import kr.mook.auth.common.dto.ResponseDto;

/**
 * Home service<br/>
 * - Home API와 관련하여 로직을 수행하는 Service
 * 
 * @since 2025. 8. 9.
 * @version 0.1
 * @author Dev.Mook
 */
public interface HomeService {
	
	/**
	 * auth 프로젝트의 root path API에 대한 로직 수행<br/>
	 * 
	 * @return {"statusCode":"200","status":"HOME_ACCESS","resultType":"string","result":"접속을 환영합니다.","language":"ko-KR"}
	 */
	public ResponseDto home();
}
```

#### > `HomeServiceImpl 코드`
```java
package kr.mook.auth.home.service.impl;

import org.springframework.stereotype.Service;

import kr.mook.auth.common.dto.ResponseDto;
import kr.mook.auth.common.enumeration.LanguageEnum;
import kr.mook.auth.common.enumeration.ResponseTypeEnum;
import kr.mook.auth.home.service.HomeService;

/**
 * Home service Implement<br/>
 * - Home API와 관련하여 로직을 수행하는 Service 구현
 * 
 * @since 2025. 8. 9.
 * @version 0.1
 * @author Dev.Mook
 */
@Service
public class HomeServiceImpl implements HomeService {

	@Override
	public ResponseDto home() {
		try {
			return ResponseDto
						.builder()
						.statusCode("200")
						.status("HOME_ACCESS")
						.resultType(ResponseTypeEnum.STRING)
						.result("접속을 환영합니다.")
						.language(LanguageEnum.KOREAN)
						.build();
		} catch (Exception e) {
			return ResponseDto
					.builder()
					.statusCode("400")
					.status("HOME_ACCESS_FAIL")
					.resultType(ResponseTypeEnum.STRING)
					.result("접속 오류가 발생하였습니다. 관리자에게 문의해주세요.")
					.language(LanguageEnum.KOREAN)
					.build();
		}
	}
}
```

#### > `ResponseDTO 코드`
```java
package kr.mook.auth.common.dto;

import kr.mook.auth.common.enumeration.LanguageEnum;
import kr.mook.auth.common.enumeration.ResponseTypeEnum;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * API 응답 데이터를 전달하기 위한 DTO
 * 
 * @since 2025. 8. 9.
 * @version 0.1
 * @author Dev.Mook
 */
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class ResponseDto {
	
	private String statusCode;
	private String status;
	private ResponseTypeEnum resultType;
	private Object result;
	private LanguageEnum language;
}
```
<br/><br/>

이제 `/api/home` API를 테스트하기 위한 `테스트 코드`를 작성해보겠습니다.

테스트 코드를 통해 기능이 잘 동작하는지 검증하며, 테스트를 통과해야만 RESTful API 문서가 생성되기 때문에 문서에 대한 신뢰성도 보장할 수 있습니다.

#### > `HomeControllerTest 코드`
```java
package kr.mook.auth.home;

import static org.mockito.Mockito.when;
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
import kr.mook.auth.home.controller.HomeController;
import kr.mook.auth.home.service.HomeService;

@AutoConfigureRestDocs(outputDir = "target/snippets")
@WebMvcTest(HomeController.class)
@ExtendWith(RestDocumentationExtension.class)
public class HomeControllerTest {
	
	@Autowired
	private MockMvc mockMvc;

	@MockitoBean
	private HomeService homeService;
	
	@BeforeEach
	void setUp(WebApplicationContext webApplicationContext, RestDocumentationContextProvider restDocumentation) {
		this.mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext)
				.apply(documentationConfiguration(restDocumentation))
				.build();
	}
	
	@Test
    void home() throws Exception {
		ResponseDto successResponseDto = ResponseDto.builder()
											 .status("200")
											 .statusCode("HOME_ACCESS")
											 .resultType(ResponseTypeEnum.STRING)
											 .result("접속을 환영합니다.")
											 .language(LanguageEnum.KOREAN)
											 .build();
		when(homeService.home()).thenReturn(successResponseDto);
		
        mockMvc.perform(get("/api/home")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
        		.andExpect(jsonPath("$.statusCode").exists())
        		.andExpect(jsonPath("$.status").exists())
        		.andExpect(jsonPath("$.resultType").exists())
        		.andExpect(jsonPath("$.result").exists())
        		.andExpect(jsonPath("$.language").exists())
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
```
<br/><br/>

> `Annotation` 설명

먼저 `@ExtendWith(RestDocumentationExtension.class)`는 Spring Boot Test에서 REST Docs를 `자동 설정`해주는 Annotation입니다.

MVC와 연동되도록 기본 설정을 세팅해줍니다.
또한 `target/snippets'에 생성한 스니펫을 저장합니다.

`@ExtendWith(RestDocumentationExtension.class)`는 JUnit5 확장 기능을 적용하기 위한 Annotation입니다.

`RestDocumentationContextProvider 같은 객체`를 주입받을 수 있게 하는 확장합니다.
<br/><br/>

> `소스 코드` 설명

먼저 `MockMvc`를 가져옵니다.

그리고 `setUp`메소드를 통해 `MockMvc`를 주입받습니다.

이제 `HomeControllerTest`를  그대로 작성합니다.

첫 소스코드는 Controller의 응답을 예상한 뒤 만든 successResponseDto입니다.

이 DTO는 API 호출이 성공할 때 반환되는 결과 값이며, `Mokito 테스트(when 절)`를 통해 예상 값이 전달되는지 테스트합니다.

이제 mockMvc를 이용하여 `/api/home` API를 테스트 하는 코드에 대해 설명하겠습니다.

- `get("/api/home")`는 Get 방식으로 "/api/home" API를 호출한다는 의미입니다.
- `status().isOk()`는 API 호출 결과가 성공했음을 의미합니다.
- `jsonPath`를 통해 반환된 JSON 객체에 특정 Key가 존재하는지 확인합니다.
- `document`에서 첫 번째 인자인 `"home-test"`는 테스트 성공 후 생성될 스니펫의 경로입니다.
	- build.gradle에 작성한 `set('snippetsDir', file("build/generated-snippets"))`을 바탕으로 "build/generated-snippets/home-test"경로에 스니펫이 생성됩니다.
- `responseFields`는 결과 값에 대한 설명을 작성하여 API 문서가 생성될 때 참고할 수 있도록 정보를 제공합니다.
<br/><br/>

# 5. JUnit 테스트 실행 및 Snippets 파일 생성

# 6. 최종 API 문서(index.adoc) 작성