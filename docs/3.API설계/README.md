# API 설계

## 1. HOME
- URL : /
- HTTP Method : GET
- Success Result
  ```json
  {   
      "statusCode" : 200,
      "status" : "HOME_ACCESS",
      "resultType" : "string",
      "result" : "접속을 환영합니다.",
      "language" : "ko-KR"
  }
  ```
- Failed Result
  ```json
  {
      "statusCode" : 400,
      "status" : "HOME_ACCESS_FAIL",
      "resultType" : "string",
      "result" : "접속 오류가 발생하였습니다. 관리자에게 문의해주세요.",
      "language" : "ko-KR"
  }
  ```

## 2. 아이디 중복 확인
- URL : /check/account/{account}
- HTTP Method : GET
- Success Result
  ```json
  {   
      "statusCode" : 200,
      "status" : "NOT_DUPLICATION[ACCOUNT]",
      "resultType" : "string",
      "result" : "사용 가능한 아이디입니다.",
      "language" : "ko-KR"
  }
  ```
- Failed Result
  - 아이디가 중복될 경우
    ```json
    {   
        "statusCode" : 400,
        "status" : "DUPLICATE[ACCOUNT]",
        "resultType" : "string",
        "result" : "이미 사용중인 아이디입니다.",
        "language" : "ko-KR"
    }
    ```
  - 아이디의 입력 형식이 잘못된 경우
    ```json
    {   
        "statusCode" : 400,
        "status" : "NOT_A_VALID_FORMAT[ACCOUNT]",
        "resultType" : "string",
        "result" : "아이디의 입력 형식이 잘못되었습니다. 아이디는 영어 소문자와 숫자로 구성되며, 첫 글자는 영어 소문자로 작성해야 합니다.",
        "language" : "ko-KR"
    }
    ```
  - 서버에서 오류가 발생한 경우
    ```json
    {   
        "statusCode" : 500,
        "status" : "SERVER_ERROR[CHECK_ACCOUNT]",
        "resultType" : "string",
        "result" : "아이디 중복 확인을 위한 서버에서 문제가 발생하였습니다. 관리자에게 문의해주세요.",
        "language" : "ko-KR"
    }
    ```
  - 서버에서 조회 시간이 초과될 경우
    ```json
    {   
        "statusCode" : 508,
        "status" : "REQUEST_TIME_OUT[CHECK_ACCOUNT]",
        "resultType" : "string",
        "result" : "아이디 중복 확인을 위한 요청 시간이 지연되고 있습니다. 관리자에게 문의해주세요.",
        "language" : "ko-KR"
    }
    ```

## 3. 이메일 중복 확인
- URL : /check/email/{email}
- HTTP Method : GET
- Success Result
  ```json
  {   
      "statusCode" : 200,
      "status" : "NOT_DUPLICATION[EMAIL]",
      "resultType" : "string",
      "result" : "사용 가능한 이메일입니다.",
      "language" : "ko-KR"
  }
  ```
- Failed Result
  - 이메일이 중복될 경우
    ```json
    {   
        "statusCode" : 400,
        "status" : "DUPLICATE[EMAIL]",
        "resultType" : "string",
        "result" : "이미 사용중인 이메일입니다.",
        "language" : "ko-KR"
    }
    ```
  - 이메일의 입력 형식이 잘못된 경우
    ```json
    {   
        "statusCode" : 400,
        "status" : "NOT_A_VALID_FORMAT[EMAIL]",
        "resultType" : "string",
        "result" : "이메일의 입력 형식이 잘못되었습니다. 이메일을 다시 확인해주세요. ex) abc@email.com",
        "language" : "ko-KR"
    }
    ```
  - 서버에서 오류가 발생한 경우
    ```json
    {   
        "statusCode" : 500,
        "status" : "SERVER_ERROR[CHECK_EMAIL]",
        "resultType" : "string",
        "result" : "이메일 중복 확인을 위한 서버에서 문제가 발생하였습니다. 관리자에게 문의해주세요.",
        "language" : "ko-KR"
    }
    ```
  - 서버에서 조회 시간이 초과될 경우
    ```json
    {   
        "statusCode" : 508,
        "status" : "REQUEST_TIME_OUT[CHECK_EMAIL]",
        "resultType" : "string",
        "result" : "이메일 중복 확인을 위한 요청 시간이 지연되고 있습니다. 관리자에게 문의해주세요.",
        "language" : "ko-KR"
    }
    ```

## 4. 휴대전화 번호 중복 확인
- URL : /check/phone-number/{phoneNumber}
- HTTP Method : GET
- Success Result
  ```json
  {   
      "statusCode" : 200,
      "status" : "NOT_DUPLICATION[PHONE_NUMBER]",
      "resultType" : "string",
      "result" : "사용 가능한 휴대전화 번호입니다.",
      "language" : "ko-KR"
  }
  ```
- Failed Result
  - 휴대전화 번호가 중복될 경우
    ```json
    {   
        "statusCode" : 400,
        "status" : "DUPLICATE[PHONE_NUMBER]",
        "resultType" : "string",
        "result" : "이미 사용중인 휴대전화 번호입니다.",
        "language" : "ko-KR"
    }
    ```
  - 휴대전화 번호의 입력 형식이 잘못된 경우
    ```json
    {   
        "statusCode" : 400,
        "status" : "NOT_A_VALID_FORMAT[PHONE_NUMBER]",
        "resultType" : "string",
        "result" : "휴대전화 번호의 입력 형식이 잘못되었습니다. 휴대전화 번호를 다시 확인해주세요. ex) 01012345678",
        "language" : "ko-KR"
    }
    ```
  - 서버에서 오류가 발생한 경우
    ```json
    {   
        "statusCode" : 500,
        "status" : "SERVER_ERROR[CHECK_PHONE_NUMBER]",
        "resultType" : "string",
        "result" : "휴대전화 번호 중복 확인을 위한 서버에서 문제가 발생하였습니다. 관리자에게 문의해주세요.",
        "language" : "ko-KR"
    }
    ```
  - 서버에서 조회 시간이 초과될 경우
    ```json
    {   
        "statusCode" : 508,
        "status" : "REQUEST_TIME_OUT[CHECK_PHONE_NUMBER]",
        "resultType" : "string",
        "result" : "휴대전화 번호 중복 확인을 위한 요청 시간이 지연되고 있습니다. 관리자에게 문의해주세요.",
        "language" : "ko-KR"
    }
    ```

## 6. 이용 약관 등록
- URL : /terms-of-use
- HTTP Method : POST
- BODY
  ```json
  {
      "title" : "{title}",
      "content" : "{content}",
      "requireYn" : true,
      "useYn" : true
  }
  ```
- Success Result
  ```json
  {   
      "statusCode" : 200,
      "status" : "SAVE[TERMS_OF_USE]",
      "resultType" : "string",
      "result" : "이용 약관을 저장하였습니다.",
      "language" : "ko-KR"
  }
  ```
- Failed Result
  - 필수 입력 값을 입력하지 않았을 경우
    ```json
    {   
        "statusCode" : 400,
        "status" : "REQUIRE_DATA_ERROR[TERMS_OF_USE]",
        "resultType" : "string",
        "result" : "필수 입력 값 {항목}을(를) 입력해주세요.",
        "language" : "ko-KR"
    }
    ```
  - 저장 과정에서 오류가 발생한 경우
    ```json
    {   
        "statusCode" : 400,
        "status" : "SAVE_ERROR[TERMS_OF_USE]",
        "resultType" : "string",
        "result" : "이용 약관 등록에 실패하였습니다.",
        "language" : "ko-KR"
    }
    ```
  - 서버에서 오류가 발생한 경우
    ```json
    {   
        "statusCode" : 500,
        "status" : "SERVER_ERROR[TERMS_OF_USE SAVE]",
        "resultType" : "string",
        "result" : "이용 약관을 등록하기 위한 서버에서 문제가 발생하였습니다. 관리자에게 문의해주세요.",
        "language" : "ko-KR"
    }
    ```
  - 서버에서 조회 시간이 초과될 경우
    ```json
    {   
        "statusCode" : 508,
        "status" : "REQUEST_TIME_OUT[TERMS_OF_USE SAVE]",
        "resultType" : "string",
        "result" : "이용 약관을 등록하기 위한 요청 시간이 지연되고 있습니다. 관리자에게 문의해주세요.",
        "language" : "ko-KR"
    }
    ```

## 7. 이용 약관 수정
- URL : /terms-of-use
- HTTP Method : PUT
- BODY
  ```json
  {
      "termsOfUseNo" : 1,
      "title" : "{title}",
      "content" : "{content}",
      "requireYn" : true,
      "useYn" : true
  }
  ```
- Success Result
  ```json
  {   
      "statusCode" : 200,
      "status" : "UPDATE[TERMS_OF_USE]",
      "resultType" : "string",
      "result" : "이용 약관을 수정하였습니다.",
      "language" : "ko-KR"
  }
  ```
- Failed Result
  - 필수 입력 값을 입력하지 않았을 경우
    ```json
    {   
        "statusCode" : 400,
        "status" : "REQUIRE_DATA_ERROR[TERMS_OF_USE UPDATE]",
        "resultType" : "string",
        "result" : "필수 입력 값 {항목}을(를) 입력해주세요.",
        "language" : "ko-KR"
    }
    ```
  - 수정 과정에서 오류가 발생한 경우
    ```json
    {   
        "statusCode" : 400,
        "status" : "UPDATE_ERROR[TERMS_OF_USE]",
        "resultType" : "string",
        "result" : "이용 약관 수정에 실패하였습니다.",
        "language" : "ko-KR"
    }
    ```
  - 서버에서 오류가 발생한 경우
    ```json
    {   
        "statusCode" : 500,
        "status" : "SERVER_ERROR[TERMS_OF_USE UPDATE]",
        "resultType" : "string",
        "result" : "이용 약관을 수정하기 위한 서버에서 문제가 발생하였습니다. 관리자에게 문의해주세요.",
        "language" : "ko-KR"
    }
    ```
  - 서버에서 조회 시간이 초과될 경우
    ```json
    {   
        "statusCode" : 508,
        "status" : "REQUEST_TIME_OUT[TERMS_OF_USE UPDATE]",
        "resultType" : "string",
        "result" : "이용 약관을 수정하기 위한 요청 시간이 지연되고 있습니다. 관리자에게 문의해주세요.",
        "language" : "ko-KR"
    }
    ```

## 8. 이용 약관 삭제
- URL : /terms-of-use/{termsOfUseNo}
- HTTP Method : DELETE
- Success Result
  ```json
  {   
      "statusCode" : 200,
      "status" : "DELETE[TERMS_OF_USE]",
      "resultType" : "string",
      "result" : "이용 약관을 삭제하였습니다.",
      "language" : "ko-KR"
  }
  ```
- Failed Result
  - 삭제 과정에서 오류가 발생한 경우
    ```json
    {   
        "statusCode" : 400,
        "status" : "DELETE_ERROR[TERMS_OF_USE]",
        "resultType" : "string",
        "result" : "이용 약관 삭제에 실패하였습니다.",
        "language" : "ko-KR"
    }
    ```
  - 서버에서 오류가 발생한 경우
    ```json
    {   
        "statusCode" : 500,
        "status" : "SERVER_ERROR[TERMS_OF_USE DELETE]",
        "resultType" : "string",
        "result" : "이용 약관을 삭제하기 위한 서버에서 문제가 발생하였습니다. 관리자에게 문의해주세요.",
        "language" : "ko-KR"
    }
    ```
  - 서버에서 조회 시간이 초과될 경우
    ```json
    {   
        "statusCode" : 508,
        "status" : "REQUEST_TIME_OUT[TERMS_OF_USE DELETE]",
        "resultType" : "string",
        "result" : "이용 약관을 삭제하기 위한 요청 시간이 지연되고 있습니다. 관리자에게 문의해주세요.",
        "language" : "ko-KR"
    }
    ```

## 9. 이용 약관 목록 조회
- URL : /terms-of-use/list
- HTTP Method : POST
- BODY
  ```json
  {
      "title" : "{title}",
      "useYn" : "{useYn}"
  }
  ```
- Success Result
  - 조회된 이용 약관 목록이 있는 경우
    ```json
    {   
        "statusCode" : 200,
        "status" : "SEARCH_LIST[TERMS_OF_USE]",
        "resultType" : "object",
        "result" : {
          "count" : 10,
          "list" : [
            {
              "termsOfUseNo" : 1,
              "title" : "사이트 이용 약관",
              "content" : "사이트 이용 약관에 대한 상세 설명입니다.",
              "requireYn" : true,
              "useYn" : true,
              "createDate" : "20250101 12:00:00"
            }, {
              "termsOfUseNo" : 2,
              "title" : "정보 제공 동의 약관",
              "content" : "정보 제공 동의에 대한 상세 설명입니다.",
              "requireYn" : true,
              "useYn" : true,
              "createDate" : "202050203 13:27:45"
            } {
              ...
            }
          ]
        },
        "language" : "ko-KR"
    }
    ```
  - 조회된 이용 약관 목록이 없는 경우
    ```json
    {
        "statusCode" : 404,
        "status" : "EMPTY_SEARCH_LIST[TERMS_OF_USE]",
        "resultType" : "object",
        "result" : {
          "count" : 0,
          "list" : []
        },
        "language" : "ko-KR"
    }
    ```
- Failed Result
  - 목록 조회 과정에서 오류가 발생한 경우
    ```json
    {   
        "statusCode" : 400,
        "status" : "SEARCH_LIST_ERROR[TERMS_OF_USE]",
        "resultType" : "string",
        "result" : "이용 약관 목록 조회에 실패하였습니다.",
        "language" : "ko-KR"
    }
    ```
  - 서버에서 오류가 발생한 경우
    ```json
    {   
        "statusCode" : 500,
        "status" : "SERVER_ERROR[TERMS_OF_USE LIST]",
        "resultType" : "string",
        "result" : "이용 약관 목록을 조회하기 위한 서버에서 문제가 발생하였습니다. 관리자에게 문의해주세요.",
        "language" : "ko-KR"
    }
    ```
  - 서버에서 조회 시간이 초과될 경우
    ```json
    {   
        "statusCode" : 508,
        "status" : "REQUEST_TIME_OUT[TERMS_OF_USE LIST]",
        "resultType" : "string",
        "result" : "이용 약관 목록을 조회하기 위한 요청 시간이 지연되고 있습니다. 관리자에게 문의해주세요.",
        "language" : "ko-KR"
    }
    ```

## 10. 이용 약관 상세 조회
- URL : /terms-of-use/{termsOfUseNo}
- HTTP Method : GET
- Success Result
  ```json
  {   
      "statusCode" : 200,
      "status" : "SEARCH[TERMS_OF_USE]",
      "resultType" : "object",
      "result" : {
        "termsOfUseNo" : 1,
        "title" : "사이트 이용 약관",
        "content" : "사이트 이용 약관에 대한 상세 설명입니다.",
        "requireYn" : true,
        "useYn" : true,
        "createDate" : "20250101 12:00:00"
      },
      "language" : "ko-KR"
  }
  ```
- Failed Result
  - 상세 정보 조회 과정에서 오류가 발생한 경우
    ```json
    {   
        "statusCode" : 400,
        "status" : "SEARCH_ERROR[TERMS_OF_USE]",
        "resultType" : "string",
        "result" : "이용 약관 상세 조회에 실패하였습니다.",
        "language" : "ko-KR"
    }
    ```
  - 서버에서 오류가 발생한 경우
    ```json
    {   
        "statusCode" : 500,
        "status" : "SERVER_ERROR[TERMS_OF_USE DETAIL]",
        "resultType" : "string",
        "result" : "이용 약관 상세 정보를 조회하기 위한 서버에서 문제가 발생하였습니다. 관리자에게 문의해주세요.",
        "language" : "ko-KR"
    }
    ```
  - 서버에서 조회 시간이 초과될 경우
    ```json
    {   
        "statusCode" : 508,
        "status" : "REQUEST_TIME_OUT[TERMS_OF_USE DETAIL]",
        "resultType" : "string",
        "result" : "이용 약관 상세 정보를 조회하기 위한 요청 시간이 지연되고 있습니다. 관리자에게 문의해주세요.",
        "language" : "ko-KR"
    }
    ```

## 11. 회원가입
- URL : /join
- HTTP Method : POST
- BODY
  ```json
  {
      "memberAccount" : "{memberAccount}",
      "activeYn" : "{activeYn}",
      "password" : "{password}",
      "firstName" : "{firstName}",
      "lastName" : "{lastName}",
      "birth" : "{birth}",
      "gender" : "{gender}",
      "email" : "{email}",
      "phoneNumber" : "{phoneNumber}",
      "address" : "{address}"
  }
  ```
- Success Result
  ```json
  {   
      "statusCode" : 200,
      "status" : "SAVE[MEMBER]",
      "resultType" : "string",
      "result" : "회원 정보를 저장하였습니다.",
      "language" : "ko-KR"
  }
  ```
- Failed Result
  - 회원가입 과정에서 오류가 발생한 경우
    ```json
    {   
        "statusCode" : 400,
        "status" : "SAVE_ERROR[MEMBER]",
        "resultType" : "string",
        "result" : "회원가입에 실패하였습니다.",
        "language" : "ko-KR"
    }
    ```
  - 서버에서 오류가 발생한 경우
    ```json
    {   
        "statusCode" : 500,
        "status" : "SERVER_ERROR[MEMBER]",
        "resultType" : "string",
        "result" : "회원가입을 위한 서버에서 문제가 발생하였습니다. 관리자에게 문의해주세요.",
        "language" : "ko-KR"
    }
    ```
  - 서버에서 조회 시간이 초과될 경우
    ```json
    {   
        "statusCode" : 508,
        "status" : "REQUEST_TIME_OUT[MEMBER]",
        "resultType" : "string",
        "result" : "회원가입을 위한 요청 시간이 지연되고 있습니다. 관리자에게 문의해주세요.",
        "language" : "ko-KR"
    }
    ```

## 12. 회원탈퇴
- URL : /secession
- HTTP Method : POST
- BODY
  ```json
  {
      "memberAccount" : "{memberAccount}",
      "password" : "{password}"
  }
  ```
- Success Result
  ```json
  {   
      "statusCode" : 200,
      "status" : "SECESSION",
      "resultType" : "string",
      "result" : "회원탈퇴에 성공하였습니다.",
      "language" : "ko-KR"
  }
  ```
- Failed Result
  - 회원탈퇴 과정에서 오류가 발생한 경우
    ```json
    {   
        "statusCode" : 400,
        "status" : "SECESSION_ERROR",
        "resultType" : "string",
        "result" : "회원탈퇴에 실패하였습니다.",
        "language" : "ko-KR"
    }
    ```
  - 서버에서 오류가 발생한 경우
    ```json
    {   
        "statusCode" : 500,
        "status" : "SERVER_ERROR[MEMBER SECESSION]",
        "resultType" : "string",
        "result" : "회원탈퇴를 위한 서버에서 문제가 발생하였습니다. 관리자에게 문의해주세요.",
        "language" : "ko-KR"
    }
    ```
  - 서버에서 조회 시간이 초과될 경우
    ```json
    {   
        "statusCode" : 508,
        "status" : "REQUEST_TIME_OUT[MEMBER SECESSION]",
        "resultType" : "string",
        "result" : "회원탈퇴를 위한 요청 시간이 지연되고 있습니다. 관리자에게 문의해주세요.",
        "language" : "ko-KR"
    }
    ```

## 13. 로그인
- URL : /login
- HTTP Method : POST
- BODY
  ```json
  {
      "memberAccount" : "{memberAccount}",
      "password" : "{password}"
  }
  ```
- Success Result
  ```json
  {   
      "statusCode" : 200,
      "status" : "LOGIN",
      "resultType" : "string",
      "result" : "로그인에 성공하였습니다.",
      "language" : "ko-KR"
  }
  ```
- Failed Result
  - 로그인 과정에서 오류가 발생한 경우
    ```json
    {   
        "statusCode" : 400,
        "status" : "LOGIN_ERROR",
        "resultType" : "string",
        "result" : "아이디 또는 비밀번호를 다시 확인해주세요.",
        "language" : "ko-KR"
    }
    ```
  - 서버에서 오류가 발생한 경우
    ```json
    {   
        "statusCode" : 500,
        "status" : "SERVER_ERROR[MEMBER LOGIN]",
        "resultType" : "string",
        "result" : "로그인을 위한 서버에서 문제가 발생하였습니다. 관리자에게 문의해주세요.",
        "language" : "ko-KR"
    }
    ```
  - 서버에서 조회 시간이 초과될 경우
    ```json
    {   
        "statusCode" : 508,
        "status" : "REQUEST_TIME_OUT[MEMBER LOGIN]",
        "resultType" : "string",
        "result" : "로그인을 위한 요청 시간이 지연되고 있습니다. 관리자에게 문의해주세요.",
        "language" : "ko-KR"
    }
    ```

## 14. 로그아웃
- URL : /logout
- HTTP Method : GET
- Success Result
  ```json
  {   
      "statusCode" : 200,
      "status" : "LOGOUT",
      "resultType" : "string",
      "result" : "로그인에 성공하였습니다.",
      "language" : "ko-KR"
  }
  ```
- Failed Result
  - 로그아웃 과정에서 오류가 발생한 경우
    ```json
    {   
        "statusCode" : 400,
        "status" : "LOGOUT_ERROR",
        "resultType" : "string",
        "result" : "로그아웃에 실패했습니다. 관리자에게 문의해주세요.",
        "language" : "ko-KR"
    }
    ```
  - 서버에서 오류가 발생한 경우
    ```json
    {   
        "statusCode" : 500,
        "status" : "SERVER_ERROR[MEMBER LOGOUT]",
        "resultType" : "string",
        "result" : "로그아웃을 위한 서버에서 문제가 발생하였습니다. 관리자에게 문의해주세요.",
        "language" : "ko-KR"
    }
    ```
  - 서버에서 조회 시간이 초과될 경우
    ```json
    {   
        "statusCode" : 508,
        "status" : "REQUEST_TIME_OUT[MEMBER LOGOUT]",
        "resultType" : "string",
        "result" : "로그아웃을 위한 요청 시간이 지연되고 있습니다. 관리자에게 문의해주세요.",
        "language" : "ko-KR"
    }
    ```

## 15. 회원 목록 조회
- URL : /members
- HTTP Method : POST
- BODY
  ```json
  {
    "memberAccount" : "{memberAccount}",
    "firstName" : "{firstName}",
    "lastName" : "{lastName}",
    "email" : "{email}"
  }
  ```
- Success Result
  - 조회된 회원이 있는 경우
    ```json
    {   
        "statusCode" : 200,
        "status" : "SEARCH_MEMBERS",
        "resultType" : "object",
        "result" : {
          "count" : 10,
          "list": [
            {
              "memberAccount" : "{memberAccount}",
              "firstName" : "{firstName}",
              "lastName" : "{lastName}",
              "gender" : "{gender}",
              "email" : "{email}",
              "activeYn" : "{activeYn}"
            }, {
              "memberAccount" : "{memberAccount}",
              "firstName" : "{firstName}",
              "lastName" : "{lastName}",
              "gender" : "{gender}",
              "email" : "{email}",
              "activeYn" : "{activeYn}"
            }, {
              ...
            }
          ]
        },
        "language" : "ko-KR"
    }
    ```
  - 조회된 회원이 없는 경우
    ```json
    {   
        "statusCode" : 404,
        "status" : "EMPTY_SEARCH_MEMBERS",
        "resultType" : "object",
        "result" : {
          "count" : 0,
          "list" : []
        },
        "language" : "ko-KR"
    }
    ```
- Failed Result
  - 회원 목록 조회 과정에서 오류가 발생한 경우
    ```json
    {   
        "statusCode" : 400,
        "status" : "SEARCH_MEMBER_ERROR",
        "resultType" : "string",
        "result" : "회원 목록 조회에 실패했습니다. 관리자에게 문의해주세요.",
        "language" : "ko-KR"
    }
    ```
  - 서버에서 오류가 발생한 경우
    ```json
    {   
        "statusCode" : 500,
        "status" : "SERVER_ERROR[SEARCH MEMBER]",
        "resultType" : "string",
        "result" : "회원 목록 조회를 위한 서버에서 문제가 발생하였습니다. 관리자에게 문의해주세요.",
        "language" : "ko-KR"
    }
    ```
  - 서버에서 조회 시간이 초과될 경우
    ```json
    {   
        "statusCode" : 508,
        "status" : "REQUEST_TIME_OUT[SEARCH MEMBER]",
        "resultType" : "string",
        "result" : "회원 목록 조회를 위한 요청 시간이 지연되고 있습니다. 관리자에게 문의해주세요.",
        "language" : "ko-KR"
    }
    ```

## 16. 회원 정보 상세 조회
- URL : /member/{id} 또는 /member/{memberAccount}
- HTTP Method : GET
- Success Result
  ```json
  {   
      "statusCode" : 200,
      "status" : "SEARCH_MEMBERS",
      "resultType" : "object",
      "result" : {
        "id" : "{id}",
        "memberAccount" : "{memberAccount}",
        "activeYn" : "{activeYn}",
        "firstName" : "{firstName}",
        "lastName" : "{lastName}",
        "birth" : "{birth}",
        "gender" : "{gender}",
        "email" : "{email}",
        "phoneNumber" : "{phoneNumber}",
        "address" : "{address}",
        "createDate" : "{createDate}",
        "loginFailedCount" : "{loginFailedCount}",
        "latestLogoutDate" : "{latestLogoutDate}",
      },
      "language" : "ko-KR"
  }
  ```
- Failed Result
  - 회원 목록 조회 과정에서 오류가 발생한 경우
    ```json
    {   
        "statusCode" : 400,
        "status" : "SEARCH_MEMBER_ERROR",
        "resultType" : "string",
        "result" : "회원 목록 조회에 실패했습니다. 관리자에게 문의해주세요.",
        "language" : "ko-KR"
    }
    ```
  - 서버에서 오류가 발생한 경우
    ```json
    {   
        "statusCode" : 500,
        "status" : "SERVER_ERROR[SEARCH MEMBER]",
        "resultType" : "string",
        "result" : "회원 목록 조회를 위한 서버에서 문제가 발생하였습니다. 관리자에게 문의해주세요.",
        "language" : "ko-KR"
    }
    ```
  - 서버에서 조회 시간이 초과될 경우
    ```json
    {   
        "statusCode" : 508,
        "status" : "REQUEST_TIME_OUT[SEARCH MEMBER]",
        "resultType" : "string",
        "result" : "회원 목록 조회를 위한 요청 시간이 지연되고 있습니다. 관리자에게 문의해주세요.",
        "language" : "ko-KR"
    }
    ```

## 17. 회원 정보 수정
- URL : /member
- HTTP Method : POST
- BODY
  ```json
  {
    "id" : "{id}",
    "memberAccount" : "{memberAccount}",
    "activeYn" : "{activeYn}",
    "address" : "{address}",
  }
  ```
- Success Result
  ```json
  {   
      "statusCode" : 200,
      "status" : "UPDATE[MEMBER]",
      "resultType" : "string",
      "result" : "회원 정보 수정에 성공하였습니다.",
      "language" : "ko-KR"
  }
  ```
- Failed Result
  - 회원 정보 수정 과정에서 오류가 발생한 경우
    ```json
    {   
        "statusCode" : 400,
        "status" : "UPDATE_MEMBER_ERROR",
        "resultType" : "string",
        "result" : "회원 정보 수정에 실패했습니다. 관리자에게 문의해주세요.",
        "language" : "ko-KR"
    }
    ```
  - 서버에서 오류가 발생한 경우
    ```json
    {   
        "statusCode" : 500,
        "status" : "UPDATE_ERROR[UPDATE EMBER]",
        "resultType" : "string",
        "result" : "회원 정보 수정을 위한 서버에서 문제가 발생하였습니다. 관리자에게 문의해주세요.",
        "language" : "ko-KR"
    }
    ```
  - 서버에서 조회 시간이 초과될 경우
    ```json
    {   
        "statusCode" : 508,
        "status" : "REQUEST_TIME_OUT[UPDATE MEMBER]",
        "resultType" : "string",
        "result" : "회원 정보 수정을 위한 요청 시간이 지연되고 있습니다. 관리자에게 문의해주세요.",
        "language" : "ko-KR"
    }
    ```

## 18. 아이디(계정) 찾기
- URL : /member/find/id
- HTTP Method : POST
- BODY
  ```json
  {
    "firstName" : "{firstName}",
    "lastName" : "{lastName}",
    "email" : "{email}",
  }
  ```
- Success Result
  - 가입된 회원 정보가 있는 경우
    ```json
    {   
        "statusCode" : 200,
        "status" : "FINE_ID",
        "resultType" : "string",
        "result" : "가입한 아이디는 ${memberAccount}입니다.",
        "language" : "ko-KR"
    }
    ```
  - 가입된 회원 정보가 없는 경우
    ```json
    {   
        "statusCode" : 404,
        "status" : "FINE_ID",
        "resultType" : "string",
        "result" : "가입된 정보가 없습니다. 이름과 이메일을 다시 확인해주세요.",
        "language" : "ko-KR"
    }
    ```
- Failed Result
  - 아이디(계정) 찾기 과정에서 오류가 발생한 경우
    ```json
    {   
        "statusCode" : 400,
        "status" : "FIND_ID_ERROR",
        "resultType" : "string",
        "result" : "아이디 찾기에 실패했습니다. 관리자에게 문의해주세요.",
        "language" : "ko-KR"
    }
    ```
  - 서버에서 오류가 발생한 경우
    ```json
    {   
        "statusCode" : 500,
        "status" : "FIND_ID_ERROR",
        "resultType" : "string",
        "result" : "아이디 찾기를 위한 서버에서 문제가 발생하였습니다. 관리자에게 문의해주세요.",
        "language" : "ko-KR"
    }
    ```
  - 서버에서 조회 시간이 초과될 경우
    ```json
    {   
        "statusCode" : 508,
        "status" : "REQUEST_TIME_OUT[FIND ID]",
        "resultType" : "string",
        "result" : "아이디 찾기를 위한 요청 시간이 지연되고 있습니다. 관리자에게 문의해주세요.",
        "language" : "ko-KR"
    }
    ```

## 19. 비밀번호 찾기(임시 비밀번호 발급)
- URL : /member/find/password
- HTTP Method : POST
- BODY
  ```json
  {
    "memberAccount" : "{memberAccount}",
    "firstName" : "{firstName}",
    "lastName" : "{lastName}",
    "email" : "{email}",
  }
  ```
- Success Result
  - 가입된 회원 정보가 있는 경우
    ```json
    {   
        "statusCode" : 200,
        "status" : "FINE_PASSWORD",
        "resultType" : "string",
        "result" : "임시 비밀번호는 ${tempPassword}입니다. 로그인 후 비밀번호를 변경해주세요.",
        "language" : "ko-KR"
    }
    ```
  - 가입된 회원 정보가 없는 경우
    ```json
    {   
        "statusCode" : 404,
        "status" : "FINE_PASSWORD",
        "resultType" : "string",
        "result" : "가입된 정보가 없습니다. 아이디, 이름 그리고 이메일을 다시 확인해주세요.",
        "language" : "ko-KR"
    }
    ```
- Failed Result
  - 비밀번호 찾기 과정에서 오류가 발생한 경우
    ```json
    {   
        "statusCode" : 400,
        "status" : "FIND_PASSWORD_ERROR",
        "resultType" : "string",
        "result" : "비밀번호 찾기에 실패했습니다. 관리자에게 문의해주세요.",
        "language" : "ko-KR"
    }
    ```
  - 서버에서 오류가 발생한 경우
    ```json
    {   
        "statusCode" : 500,
        "status" : "FIND_PASSWORD_ERROR",
        "resultType" : "string",
        "result" : "비밀번호 찾기를 위한 서버에서 문제가 발생하였습니다. 관리자에게 문의해주세요.",
        "language" : "ko-KR"
    }
    ```
  - 서버에서 조회 시간이 초과될 경우
    ```json
    {   
        "statusCode" : 508,
        "status" : "REQUEST_TIME_OUT[FIND PASSWORD]",
        "resultType" : "string",
        "result" : "비밀번호 찾기를 위한 요청 시간이 지연되고 있습니다. 관리자에게 문의해주세요.",
        "language" : "ko-KR"
    }
    ```

## 20. 비밀번호 변경
- URL : /member/password
- HTTP Method : POST
- BODY
  ```json
  {
    "id" : "{id}",
    "memberAccount" : "{memberAccount}",
    "oldPassword" : "{oldPassword}",
    "newPassword" : "{newPassword}"
  }
  ```
- Success Result
  ```json
  {   
      "statusCode" : 200,
      "status" : "UPDATE[MEMBER PASSWORD]",
      "resultType" : "string",
      "result" : "비밀번호를 변경하였습니다.",
      "language" : "ko-KR"
  }
  ```
- Failed Result
  - 비밀번호 변경 과정에서 오류가 발생한 경우
    ```json
    {   
        "statusCode" : 400,
        "status" : "UPDATE_PASSWORD_ERROR",
        "resultType" : "string",
        "result" : "비밀번호 변경에 실패했습니다. 관리자에게 문의해주세요.",
        "language" : "ko-KR"
    }
    ```
  - 서버에서 오류가 발생한 경우
    ```json
    {   
        "statusCode" : 500,
        "status" : "UPDATE_PASSWORD_ERROR",
        "resultType" : "string",
        "result" : "비밀번호 변경을 위한 서버에서 문제가 발생하였습니다. 관리자에게 문의해주세요.",
        "language" : "ko-KR"
    }
    ```
  - 서버에서 조회 시간이 초과될 경우
    ```json
    {   
        "statusCode" : 508,
        "status" : "REQUEST_TIME_OUT[MEMBER PASSWORD]",
        "resultType" : "string",
        "result" : "비밀번호 변경을 위한 요청 시간이 지연되고 있습니다. 관리자에게 문의해주세요.",
        "language" : "ko-KR"
    }
    ```

## 21. 회원 활성화
- URL : /member/{id}/activate 또는 /member/{memberAccount}/activate
- HTTP Method : GET
- Success Result
  ```json
  {   
      "statusCode" : 200,
      "status" : "MEMBER ACTIVATE",
      "resultType" : "string",
      "result" : "계정을 활성화 하였습니다.",
      "language" : "ko-KR"
  }
  ```
- Failed Result
  - 계정 활성화 과정에서 오류가 발생한 경우
    ```json
    {   
        "statusCode" : 400,
        "status" : "ACTIVATE_ERROR",
        "resultType" : "string",
        "result" : "계정 활성화에 실패했습니다. 관리자에게 문의해주세요.",
        "language" : "ko-KR"
    }
    ```
  - 서버에서 오류가 발생한 경우
    ```json
    {   
        "statusCode" : 500,
        "status" : "ACTIVATE_ERROR",
        "resultType" : "string",
        "result" : "계정 활성화를 위한 서버에서 문제가 발생하였습니다. 관리자에게 문의해주세요.",
        "language" : "ko-KR"
    }
    ```
  - 서버에서 조회 시간이 초과될 경우
    ```json
    {   
        "statusCode" : 508,
        "status" : "REQUEST_TIME_OUT[MEMBER ACTIVATE]",
        "resultType" : "string",
        "result" : "계정 활성화를 위한 요청 시간이 지연되고 있습니다. 관리자에게 문의해주세요.",
        "language" : "ko-KR"
    }
    ```

## 22. 회원 비활성화(휴면 계정 등록)
- URL : /member/{id}/deactivate 또는 /member/{memberAccount}/deactivate
- HTTP Method : GET
- Success Result
  ```json
  {   
      "statusCode" : 200,
      "status" : "MEMBER DEACTIVATE",
      "resultType" : "string",
      "result" : "계정을 비활성화 하였습니다.",
      "language" : "ko-KR"
  }
  ```
- Failed Result
  - 계정 비활성화 과정에서 오류가 발생한 경우
    ```json
    {   
        "statusCode" : 400,
        "status" : "DEACTIVATE_ERROR",
        "resultType" : "string",
        "result" : "계정 비활성화에 실패했습니다. 관리자에게 문의해주세요.",
        "language" : "ko-KR"
    }
    ```
  - 서버에서 오류가 발생한 경우
    ```json
    {   
        "statusCode" : 500,
        "status" : "DEACTIVATE_ERROR",
        "resultType" : "string",
        "result" : "계정 비활성화를 위한 서버에서 문제가 발생하였습니다. 관리자에게 문의해주세요.",
        "language" : "ko-KR"
    }
    ```
  - 서버에서 조회 시간이 초과될 경우
    ```json
    {   
        "statusCode" : 508,
        "status" : "REQUEST_TIME_OUT[MEMBER DEACTIVATE]",
        "resultType" : "string",
        "result" : "계정 비활성화를 위한 요청 시간이 지연되고 있습니다. 관리자에게 문의해주세요.",
        "language" : "ko-KR"
    }
    ```

## 23. 비밀번호 작성 규칙 저장
- URL : /rule/password
- HTTP Method : POST
- BODY
  ```json
  {
    "rule" : : "{passwordRule}"
  }
  ```
- Success Result
  ```json
  {   
      "statusCode" : 200,
      "status" : "SAVE[PASSWORD RULE]",
      "resultType" : "string",
      "result" : "비밀번호 작성 규칙을 저장하였습니다.",
      "language" : "ko-KR"
  }
  ```
- Failed Result
  - 비밀번호 작성 규칙을 저장 과정에서 오류가 발생한 경우
    ```json
    {   
        "statusCode" : 400,
        "status" : "PASSWORD_RULE_ERROR",
        "resultType" : "string",
        "result" : "비밀번호 작성 규칙 저장에 실패했습니다. 관리자에게 문의해주세요.",
        "language" : "ko-KR"
    }
    ```
  - 서버에서 오류가 발생한 경우
    ```json
    {   
        "statusCode" : 500,
        "status" : "PASSWORD_RULE_ERROR",
        "resultType" : "string",
        "result" : "비밀번호 작성 규칙 저장을 위한 서버에서 문제가 발생하였습니다. 관리자에게 문의해주세요.",
        "language" : "ko-KR"
    }
    ```
  - 서버에서 조회 시간이 초과될 경우
    ```json
    {   
        "statusCode" : 508,
        "status" : "REQUEST_TIME_OUT[PASSWORD RULE]",
        "resultType" : "string",
        "result" : "비밀번호 작성 규칙 저장을 위한 요청 시간이 지연되고 있습니다. 관리자에게 문의해주세요.",
        "language" : "ko-KR"
    }
    ```

## 24. 로그인 이력 등록
- URL : /history/login
- HTTP Method : POST
- BODY
  ```json
  {
    "memberId" : "{memberId}",
    "statusCode" : "{statusCode}",
    "status" : "{status}",
    "type" : "LOGIN/LOGOUT",
    "resultData" : "{resultData}"
  }
  ```
- Success Result
  ```json
  {   
      "statusCode" : 200,
      "status" : "SAVE[LOGIN HISTORY]",
      "resultType" : "string",
      "result" : "로그인 이력을 저장하였습니다.",
      "language" : "ko-KR"
  }
  ```
- Failed Result
  - 로그인 이력을 저장 과정에서 오류가 발생한 경우
    ```json
    {   
        "statusCode" : 400,
        "status" : "LOGIN_HISTORY_ERROR",
        "resultType" : "string",
        "result" : "로그인 이력 저장에 실패했습니다. 관리자에게 문의해주세요.",
        "language" : "ko-KR"
    }
    ```
  - 서버에서 오류가 발생한 경우
    ```json
    {   
        "statusCode" : 500,
        "status" : "LOGIN_HISTORY_ERROR",
        "resultType" : "string",
        "result" : "로그인 이력 저장을 위한 서버에서 문제가 발생하였습니다. 관리자에게 문의해주세요.",
        "language" : "ko-KR"
    }
    ```
  - 서버에서 조회 시간이 초과될 경우
    ```json
    {   
        "statusCode" : 508,
        "status" : "REQUEST_TIME_OUT[LOGIN HISTORY]",
        "resultType" : "string",
        "result" : "로그인 이력 저장을 위한 요청 시간이 지연되고 있습니다. 관리자에게 문의해주세요.",
        "language" : "ko-KR"
    }
    ```

## 25. 로그인 이력 목록 조회
- URL : /history/login/list
- HTTP Method : POST
- BODY
  ```json
  {
    "memberId" : "{memberId}",
    "statusCode" : "{statusCode}",
    "type" : "LOGIN/LOGOUT",
    "startDate" : "{startDate}",
    "endDate" : "{endDate}"
  }
  ```
- Success Result
  ```json
  {   
    "statusCode" : 200,
    "status" : "SEARCH_LIST[LOGIN HISTORY LIST]",
    "resultType" : "object",
    "result" : {
      "count" : 10,
      "list" : [
        {
          "loginHistoryId" : "{loginHistoryId}",
          "memberId" : "{memberId}",
          "statusCode" : "{statusCode}",
          "status" : "{status}",
          "type" : "LOGIN/LOGOUT",
          "resultData" : "{resultData}",
          "createDate" : "20250101 12:00:00"
        }, {
          "loginHistoryId" : "{loginHistoryId}",
          "memberId" : "{memberId}",
          "statusCode" : "{statusCode}",
          "status" : "{status}",
          "type" : "LOGIN/LOGOUT",
          "resultData" : "{resultData}",
          "createDate" : "20250101 12:00:00"
        } {
          ...
        }
      ]
    },
    "language" : "ko-KR"
  }
  ```
- Failed Result
  - 로그인 이력 목록을 조회하는 과정에서 오류가 발생한 경우
    ```json
    {   
        "statusCode" : 400,
        "status" : "LOGIN_HISTORY_LIST_ERROR",
        "resultType" : "string",
        "result" : "로그인 이력 목록 조회에 실패했습니다. 관리자에게 문의해주세요.",
        "language" : "ko-KR"
    }
    ```
  - 서버에서 오류가 발생한 경우
    ```json
    {   
        "statusCode" : 500,
        "status" : "LOGIN_HISTORY_LIST_ERROR",
        "resultType" : "string",
        "result" : "로그인 이력 목록 조회를 위한 서버에서 문제가 발생하였습니다. 관리자에게 문의해주세요.",
        "language" : "ko-KR"
    }
    ```
  - 서버에서 조회 시간이 초과될 경우
    ```json
    {   
        "statusCode" : 508,
        "status" : "REQUEST_TIME_OUT[LOGIN HISTORY LIST]",
        "resultType" : "string",
        "result" : "로그인 이력 목록 조회를 위한 요청 시간이 지연되고 있습니다. 관리자에게 문의해주세요.",
        "language" : "ko-KR"
    }
    ```

## 26. 로그인 이력 상세 조회
- URL : /history/login/{loginHistoryId}
- HTTP Method : GET
- Success Result
  ```json
  {   
    "statusCode" : 200,
    "status" : "SEARCH[LOGIN HISTORY]",
    "resultType" : "object",
    "result" : {
      "count" : 10,
      "list" : {
        "loginHistoryId" : "{loginHistoryId}",
        "memberId" : "{memberId}",
        "statusCode" : "{statusCode}",
        "status" : "{status}",
        "type" : "LOGIN/LOGOUT",
        "resultData" : "{resultData}",
        "createDate" : "20250101 12:00:00"
      },
    },
    "language" : "ko-KR"
  }
  ```
- Failed Result
  - 로그인 상세 이력을 조회하는 과정에서 오류가 발생한 경우
    ```json
    {   
        "statusCode" : 400,
        "status" : "LOGIN_HISTORY_ERROR",
        "resultType" : "string",
        "result" : "로그인 상세 이력 조회에 실패했습니다. 관리자에게 문의해주세요.",
        "language" : "ko-KR"
    }
    ```
  - 서버에서 오류가 발생한 경우
    ```json
    {   
        "statusCode" : 500,
        "status" : "LOGIN_HISTORY_ERROR",
        "resultType" : "string",
        "result" : "로그인 상세 이력 조회를 위한 서버에서 문제가 발생하였습니다. 관리자에게 문의해주세요.",
        "language" : "ko-KR"
    }
    ```
  - 서버에서 조회 시간이 초과될 경우
    ```json
    {   
        "statusCode" : 508,
        "status" : "REQUEST_TIME_OUT[LOGIN HISTORY LIST]",
        "resultType" : "string",
        "result" : "로그인 상세 이력 목록 조회를 위한 요청 시간이 지연되고 있습니다. 관리자에게 문의해주세요.",
        "language" : "ko-KR"
    }
    ```

## 27. API 사용 이력 등록
- URL : /history/api
- HTTP Method : POST
- BODY
  ```json
  {
    "memberId" : : "{memberId}",
    "apiUrl" : "{apiUrl}",
    "statusCode" : "{statusCode}",
    "status" : "{status}",
    "sendData" : "{sendData}",
    "resultData" : "{resultData}",
    "language" : "{language}"
  }
  ```
- Success Result
  ```json
  {   
      "statusCode" : 200,
      "status" : "SAVE[API USING HISTORY]",
      "resultType" : "string",
      "result" : "API 사용 이력을 저장하였습니다.",
      "language" : "ko-KR"
  }
  ```
- Failed Result
  - API 사용 이력을 저장 과정에서 오류가 발생한 경우
    ```json
    {   
        "statusCode" : 400,
        "status" : "API_USING_HISTORY_ERROR",
        "resultType" : "string",
        "result" : "API 사용 이력 저장에 실패했습니다. 관리자에게 문의해주세요.",
        "language" : "ko-KR"
    }
    ```
  - 서버에서 오류가 발생한 경우
    ```json
    {   
        "statusCode" : 500,
        "status" : "API_USEING_HISTORY_ERROR",
        "resultType" : "string",
        "result" : "API 사용 이력 저장을 위한 서버에서 문제가 발생하였습니다. 관리자에게 문의해주세요.",
        "language" : "ko-KR"
    }
    ```
  - 서버에서 조회 시간이 초과될 경우
    ```json
    {   
        "statusCode" : 508,
        "status" : "REQUEST_TIME_OUT[API_USING HISTORY]",
        "resultType" : "string",
        "result" : "API 사용 이력 저장을 위한 요청 시간이 지연되고 있습니다. 관리자에게 문의해주세요.",
        "language" : "ko-KR"
    }
    ```

## 28. API 이력 목록 조회
- URL : /history/api/list
- HTTP Method : POST
- BODY
  ```json
  {
    "apiUrl" : "{apiUrl}",
    "statusCode" : "{statusCode}",
    "status" : "{status}",
    "memberId" : "{memberId}",
    "startDate" : "{startDate}",
    "endDate" : "{endDate}"
  }
  ```
- Success Result
  ```json
  {   
    "statusCode" : 200,
    "status" : "SEARCH_LIST[API USING LIST]",
    "resultType" : "object",
    "result" : {
      "count" : 10,
      "list" : [
        {
          "apiHistoryId" : "{apiHistoryId}",
          "memberId" : : "{memberId}",
          "apiUrl" : "{apiUrl}",
          "statusCode" : "{statusCode}",
          "status" : "{status}",
          "sendData" : "{sendData}",
          "resultData" : "{resultData}",
          "language" : "{language}",
          "createDate" : "20250101 12:00:00"
        }, {
          "apiHistoryId" : "{apiHistoryId}",
          "memberId" : : "{memberId}",
          "apiUrl" : "{apiUrl}",
          "statusCode" : "{statusCode}",
          "status" : "{status}",
          "sendData" : "{sendData}",
          "resultData" : "{resultData}",
          "language" : "{language}",
          "createDate" : "20250101 12:00:00"
        } {
          ...
        }
      ]
    },
    "language" : "ko-KR"
  }
  ```
- Failed Result
  - API 사용 이력 목록을 조회하는 과정에서 오류가 발생한 경우
    ```json
    {   
        "statusCode" : 400,
        "status" : "API_USING_HISTORY_LIST_ERROR",
        "resultType" : "string",
        "result" : "API 사용 이력 목록 조회에 실패했습니다. 관리자에게 문의해주세요.",
        "language" : "ko-KR"
    }
    ```
  - 서버에서 오류가 발생한 경우
    ```json
    {   
        "statusCode" : 500,
        "status" : "API_USING_HISTORY_LIST_ERROR",
        "resultType" : "string",
        "result" : "API 사용 이력 목록 조회를 위한 서버에서 문제가 발생하였습니다. 관리자에게 문의해주세요.",
        "language" : "ko-KR"
    }
    ```
  - 서버에서 조회 시간이 초과될 경우
    ```json
    {   
        "statusCode" : 508,
        "status" : "REQUEST_TIME_OUT[API USING HISTORY LIST]",
        "resultType" : "string",
        "result" : "API 사용 이력 목록 조회를 위한 요청 시간이 지연되고 있습니다. 관리자에게 문의해주세요.",
        "language" : "ko-KR"
    }
    ```

## 29. API 사용 이력 상세 조회
- URL : /history/api/{apiHistoryId}
- HTTP Method : GET
- Success Result
  ```json
  {   
    "statusCode" : 200,
    "status" : "SEARCH[API USING HISTORY]",
    "resultType" : "object",
    "result" : {
      "count" : 10,
      "list" : {
        "apiHistoryId" : "{apiHistoryId}",
        "memberId" : : "{memberId}",
        "apiUrl" : "{apiUrl}",
        "statusCode" : "{statusCode}",
        "status" : "{status}",
        "sendData" : "{sendData}",
        "resultData" : "{resultData}",
        "language" : "{language}",
        "createDate" : "20250101 12:00:00"
      },
    },
    "language" : "ko-KR"
  }
  ```
- Failed Result
  - API 사용 상세 이력을 조회하는 과정에서 오류가 발생한 경우
    ```json
    {   
        "statusCode" : 400,
        "status" : "API_USING_HISTORY_ERROR",
        "resultType" : "string",
        "result" : "API 사용 상세 이력 조회에 실패했습니다. 관리자에게 문의해주세요.",
        "language" : "ko-KR"
    }
    ```
  - 서버에서 오류가 발생한 경우
    ```json
    {   
        "statusCode" : 500,
        "status" : "API_USING_HISTORY_ERROR",
        "resultType" : "string",
        "result" : "API 사용 상세 이력 조회를 위한 서버에서 문제가 발생하였습니다. 관리자에게 문의해주세요.",
        "language" : "ko-KR"
    }
    ```
  - 서버에서 조회 시간이 초과될 경우
    ```json
    {   
        "statusCode" : 508,
        "status" : "REQUEST_TIME_OUT[API USING HISTORY LIST]",
        "resultType" : "string",
        "result" : "API 사용 상세 이력 목록 조회를 위한 요청 시간이 지연되고 있습니다. 관리자에게 문의해주세요.",
        "language" : "ko-KR"
    }
    ```
