# API 설계

## 1. HOME
- URL : /
- HTTP Method : GET
- Success Result
  ```json
  {   
      "status-code" : 200,
      "status" : "HOME_ACCESS_SUCCESS",
      "result-type" : "string",
      "result" : "접속을 환영합니다.",
      "language" : "ko-KR"
  }
  ```
- Failed Result
  ```json
  {
      "status-code" : 400,
      "status" : "HOME_ACCESS_FAIL",
      "result-type" : "string",
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
      "status-code" : 200,
      "status" : "NOT_DUPLICATION[ACCOUNT]",
      "result-type" : "string",
      "result" : "사용 가능한 아이디입니다.",
      "language" : "ko-KR"
  }
  ```
- Failed Result
  - 아이디가 중복될 경우
    ```json
    {   
        "status-code" : 400,
        "status" : "DUPLICATE[ACCOUNT]",
        "result-type" : "string",
        "result" : "이미 사용중인 아이디입니다.",
        "language" : "ko-KR"
    }
    ```
  - 아이디의 입력 형식이 잘못된 경우
    ```json
    {   
        "status-code" : 400,
        "status" : "NOT_A_VALID_FORMAT[ACCOUNT]",
        "result-type" : "string",
        "result" : "아이디의 입력 형식이 잘못되었습니다. 아이디는 영어 소문자와 숫자로 구성되며, 첫 글자는 영어 소문자로 작성해야 합니다.",
        "language" : "ko-KR"
    }
    ```
  - 서버에서 오류가 발생한 경우
    ```json
    {   
        "status-code" : 500,
        "status" : "SERVER_ERROR[CHECK_ACCOUNT]",
        "result-type" : "string",
        "result" : "아이디 중복 확인을 위한 서버에서 문제가 발생하였습니다. 관리자에게 문의해주세요.",
        "language" : "ko-KR"
    }
    ```
  - 서버에서 조회 시간이 초과될 경우
    ```json
    {   
        "status-code" : 508,
        "status" : "REQUEST_TIME_OUT[CHECK_ACCOUNT]",
        "result-type" : "string",
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
      "status-code" : 200,
      "status" : "NOT_DUPLICATION[EMAIL]",
      "result-type" : "string",
      "result" : "사용 가능한 이메일입니다.",
      "language" : "ko-KR"
  }
  ```
- Failed Result
  - 이메일dl 중복될 경우
    ```json
    {   
        "status-code" : 400,
        "status" : "DUPLICATE[EMAIL]",
        "result-type" : "string",
        "result" : "이미 사용중인 이메일입니다.",
        "language" : "ko-KR"
    }
    ```
  - 이메일의 입력 형식이 잘못된 경우
    ```json
    {   
        "status-code" : 400,
        "status" : "NOT_A_VALID_FORMAT[EMAIL]",
        "result-type" : "string",
        "result" : "이메일의 입력 형식이 잘못되었습니다. 이메일을 다시 확인해주세요. ex) abc@email.com",
        "language" : "ko-KR"
    }
    ```
  - 서버에서 오류가 발생한 경우
    ```json
    {   
        "status-code" : 500,
        "status" : "SERVER_ERROR[CHECK_EMAIL]",
        "result-type" : "string",
        "result" : "이메일 중복 확인을 위한 서버에서 문제가 발생하였습니다. 관리자에게 문의해주세요.",
        "language" : "ko-KR"
    }
    ```
  - 서버에서 조회 시간이 초과될 경우
    ```json
    {   
        "status-code" : 508,
        "status" : "REQUEST_TIME_OUT[CHECK_EMAIL]",
        "result-type" : "string",
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
      "status-code" : 200,
      "status" : "NOT_DUPLICATION[PHONE_NUMBER]",
      "result-type" : "string",
      "result" : "사용 가능한 휴대전화 번호입니다.",
      "language" : "ko-KR"
  }
  ```
- Failed Result
  - 휴대전화 번호가 중복될 경우
    ```json
    {   
        "status-code" : 400,
        "status" : "DUPLICATE[PHONE_NUMBER]",
        "result-type" : "string",
        "result" : "이미 사용중인 휴대전화 번호입니다.",
        "language" : "ko-KR"
    }
    ```
  - 휴대전화 번호의 입력 형식이 잘못된 경우
    ```json
    {   
        "status-code" : 400,
        "status" : "NOT_A_VALID_FORMAT[PHONE_NUMBER]",
        "result-type" : "string",
        "result" : "휴대전화 번호의 입력 형식이 잘못되었습니다. 휴대전화 번호를 다시 확인해주세요. ex) 01012345678",
        "language" : "ko-KR"
    }
    ```
  - 서버에서 오류가 발생한 경우
    ```json
    {   
        "status-code" : 500,
        "status" : "SERVER_ERROR[CHECK_PHONE_NUMBER]",
        "result-type" : "string",
        "result" : "휴대전화 번호 중복 확인을 위한 서버에서 문제가 발생하였습니다. 관리자에게 문의해주세요.",
        "language" : "ko-KR"
    }
    ```
  - 서버에서 조회 시간이 초과될 경우
    ```json
    {   
        "status-code" : 508,
        "status" : "REQUEST_TIME_OUT[CHECK_PHONE_NUMBER]",
        "result-type" : "string",
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
      "status-code" : 200,
      "status" : "SAVE[TERMS_OF_USE]",
      "result-type" : "string",
      "result" : "이용 약관을 저장하였습니다.",
      "language" : "ko-KR"
  }
  ```
- Failed Result
  - 필수 입력 값을 입력하지 않았을 경우
    ```json
    {   
        "status-code" : 400,
        "status" : "REQUIRE_DATA_ERROR[TERMS_OF_USE]",
        "result-type" : "string",
        "result" : "필수 입력 값 {항목}을(를) 입력해주세요.",
        "language" : "ko-KR"
    }
    ```
  - 저장 과정에서 오류가 발생한 경우
    ```json
    {   
        "status-code" : 400,
        "status" : "SAVE_ERROR[TERMS_OF_USE]",
        "result-type" : "string",
        "result" : "이용 약관 등록에 실패하였습니다.",
        "language" : "ko-KR"
    }
    ```
  - 서버에서 오류가 발생한 경우
    ```json
    {   
        "status-code" : 500,
        "status" : "SERVER_ERROR[TERMS_OF_USE SAVE]",
        "result-type" : "string",
        "result" : "이용 약관을 등록하기 위한 서버에서 문제가 발생하였습니다. 관리자에게 문의해주세요.",
        "language" : "ko-KR"
    }
    ```
  - 서버에서 조회 시간이 초과될 경우
    ```json
    {   
        "status-code" : 508,
        "status" : "REQUEST_TIME_OUT[TERMS_OF_USE SAVE]",
        "result-type" : "string",
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
      "status-code" : 200,
      "status" : "UPDATE[TERMS_OF_USE]",
      "result-type" : "string",
      "result" : "이용 약관을 수정하였습니다.",
      "language" : "ko-KR"
  }
  ```
- Failed Result
  - 필수 입력 값을 입력하지 않았을 경우
    ```json
    {   
        "status-code" : 400,
        "status" : "REQUIRE_DATA_ERROR[TERMS_OF_USE UPDATE]",
        "result-type" : "string",
        "result" : "필수 입력 값 {항목}을(를) 입력해주세요.",
        "language" : "ko-KR"
    }
    ```
  - 수정 과정에서 오류가 발생한 경우
    ```json
    {   
        "status-code" : 400,
        "status" : "UPDATE_ERROR[TERMS_OF_USE]",
        "result-type" : "string",
        "result" : "이용 약관 수정에 실패하였습니다.",
        "language" : "ko-KR"
    }
    ```
  - 서버에서 오류가 발생한 경우
    ```json
    {   
        "status-code" : 500,
        "status" : "SERVER_ERROR[TERMS_OF_USE UPDATE]",
        "result-type" : "string",
        "result" : "이용 약관을 수정하기 위한 서버에서 문제가 발생하였습니다. 관리자에게 문의해주세요.",
        "language" : "ko-KR"
    }
    ```
  - 서버에서 조회 시간이 초과될 경우
    ```json
    {   
        "status-code" : 508,
        "status" : "REQUEST_TIME_OUT[TERMS_OF_USE UPDATE]",
        "result-type" : "string",
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
      "status-code" : 200,
      "status" : "DELETE[TERMS_OF_USE]",
      "result-type" : "string",
      "result" : "이용 약관을 삭제하였습니다.",
      "language" : "ko-KR"
  }
  ```
- Failed Result
  - 삭제 과정에서 오류가 발생한 경우
    ```json
    {   
        "status-code" : 400,
        "status" : "DELETE_ERROR[TERMS_OF_USE]",
        "result-type" : "string",
        "result" : "이용 약관 삭제에 실패하였습니다.",
        "language" : "ko-KR"
    }
    ```
  - 서버에서 오류가 발생한 경우
    ```json
    {   
        "status-code" : 500,
        "status" : "SERVER_ERROR[TERMS_OF_USE DELETE]",
        "result-type" : "string",
        "result" : "이용 약관을 삭제하기 위한 서버에서 문제가 발생하였습니다. 관리자에게 문의해주세요.",
        "language" : "ko-KR"
    }
    ```
  - 서버에서 조회 시간이 초과될 경우
    ```json
    {   
        "status-code" : 508,
        "status" : "REQUEST_TIME_OUT[TERMS_OF_USE DELETE]",
        "result-type" : "string",
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
  ```json
  {   
      "status-code" : 200,
      "status" : "SUCCESS_SEARCH_LIST[TERMS_OF_USE]",
      "result-type" : "string",
      "result" : "이용 약관 목록을 조회하였습니다.",
      "language" : "ko-KR"
  }
  ```
- Failed Result
  - 목록 조회 과정에서 오류가 발생한 경우
    ```json
    {   
        "status-code" : 400,
        "status" : "SEARCH_LIST_ERROR[TERMS_OF_USE]",
        "result-type" : "string",
        "result" : "이용 약관 목록 조회에 실패하였습니다.",
        "language" : "ko-KR"
    }
    ```
  - 서버에서 오류가 발생한 경우
    ```json
    {   
        "status-code" : 500,
        "status" : "SERVER_ERROR[TERMS_OF_USE LIST]",
        "result-type" : "string",
        "result" : "이용 약관 목록을 조회하기 위한 서버에서 문제가 발생하였습니다. 관리자에게 문의해주세요.",
        "language" : "ko-KR"
    }
    ```
  - 서버에서 조회 시간이 초과될 경우
    ```json
    {   
        "status-code" : 508,
        "status" : "REQUEST_TIME_OUT[TERMS_OF_USE LIST]",
        "result-type" : "string",
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
      "status-code" : 200,
      "status" : "SUCCESS_SEARCH[TERMS_OF_USE]",
      "result-type" : "string",
      "result" : "이용 약관의 상세 정보를 조회하였습니다.",
      "language" : "ko-KR"
  }
  ```
- Failed Result
  - 상세 정보 조회 과정에서 오류가 발생한 경우
    ```json
    {   
        "status-code" : 400,
        "status" : "SEARCH_ERROR[TERMS_OF_USE]",
        "result-type" : "string",
        "result" : "이용 약관 상세 조회에 실패하였습니다.",
        "language" : "ko-KR"
    }
    ```
  - 서버에서 오류가 발생한 경우
    ```json
    {   
        "status-code" : 500,
        "status" : "SERVER_ERROR[TERMS_OF_USE DETAIL]",
        "result-type" : "string",
        "result" : "이용 약관 상세 정보를 조회하기 위한 서버에서 문제가 발생하였습니다. 관리자에게 문의해주세요.",
        "language" : "ko-KR"
    }
    ```
  - 서버에서 조회 시간이 초과될 경우
    ```json
    {   
        "status-code" : 508,
        "status" : "REQUEST_TIME_OUT[TERMS_OF_USE DETAIL]",
        "result-type" : "string",
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
      "status-code" : 200,
      "status" : "SAVE[MEMBER]",
      "result-type" : "string",
      "result" : "회원 정보를 저장하였습니다.",
      "language" : "ko-KR"
  }
  ```
- Failed Result
  - 회원가입 과정에서 오류가 발생한 경우
    ```json
    {   
        "status-code" : 400,
        "status" : "SAVE_ERROR[MEMBER]",
        "result-type" : "string",
        "result" : "회원가입에 실패하였습니다.",
        "language" : "ko-KR"
    }
    ```
  - 서버에서 오류가 발생한 경우
    ```json
    {   
        "status-code" : 500,
        "status" : "SERVER_ERROR[MEMBER]",
        "result-type" : "string",
        "result" : "회원가입을 위한 서버에서 문제가 발생하였습니다. 관리자에게 문의해주세요.",
        "language" : "ko-KR"
    }
    ```
  - 서버에서 조회 시간이 초과될 경우
    ```json
    {   
        "status-code" : 508,
        "status" : "REQUEST_TIME_OUT[MEMBER]",
        "result-type" : "string",
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
      "status-code" : 200,
      "status" : "SECESSION_SUCCESS[MEMBER SECESSION]",
      "result-type" : "string",
      "result" : "회원탈퇴에 성공하였습니다.",
      "language" : "ko-KR"
  }
  ```
- Failed Result
  - 회원탈퇴 과정에서 오류가 발생한 경우
    ```json
    {   
        "status-code" : 400,
        "status" : "SECESSION_ERROR",
        "result-type" : "string",
        "result" : "회원탈퇴에 실패하였습니다.",
        "language" : "ko-KR"
    }
    ```
  - 서버에서 오류가 발생한 경우
    ```json
    {   
        "status-code" : 500,
        "status" : "SERVER_ERROR[MEMBER SECESSION]",
        "result-type" : "string",
        "result" : "회원탈퇴를 위한 서버에서 문제가 발생하였습니다. 관리자에게 문의해주세요.",
        "language" : "ko-KR"
    }
    ```
  - 서버에서 조회 시간이 초과될 경우
    ```json
    {   
        "status-code" : 508,
        "status" : "REQUEST_TIME_OUT[MEMBER SECESSION]",
        "result-type" : "string",
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
      "status-code" : 200,
      "status" : "LOGIN_SUCCESS[MEMBER LOGIN]",
      "result-type" : "string",
      "result" : "로그인에 성공하였습니다.",
      "language" : "ko-KR"
  }
  ```
- Failed Result
  - 로그인 과정에서 오류가 발생한 경우
    ```json
    {   
        "status-code" : 400,
        "status" : "LOGIN_ERROR",
        "result-type" : "string",
        "result" : "아이디 또는 비밀번호를 다시 확인해주세요.",
        "language" : "ko-KR"
    }
    ```
  - 서버에서 오류가 발생한 경우
    ```json
    {   
        "status-code" : 500,
        "status" : "SERVER_ERROR[MEMBER LOGIN]",
        "result-type" : "string",
        "result" : "로그인을 위한 서버에서 문제가 발생하였습니다. 관리자에게 문의해주세요.",
        "language" : "ko-KR"
    }
    ```
  - 서버에서 조회 시간이 초과될 경우
    ```json
    {   
        "status-code" : 508,
        "status" : "REQUEST_TIME_OUT[MEMBER LOGIN]",
        "result-type" : "string",
        "result" : "로그인을 위한 요청 시간이 지연되고 있습니다. 관리자에게 문의해주세요.",
        "language" : "ko-KR"
    }
    ```