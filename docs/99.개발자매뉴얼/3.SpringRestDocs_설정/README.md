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

# 4. 테스트 코드 작성하기

# 5. JUnit 테스트 실행 및 Snippets 파일 생성

# 6. 최종 API 문서(index.adoc) 작성