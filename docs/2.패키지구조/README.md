# 패키지 구조
```bash
kr.imj
├─── user : 사용자 관련
│     ├─── controller
│     ├─── service
│     ├─── dao
│     ├─── dto
│     └─── vo
│
├─── member : 회원 관련
│     ├─── login : 로그인/로그아웃
│     │      ├─── controller
│     │      ├─── service
│     │      ├─── dao
│     │      ├─── dto
│     │      └─── vo
│     │
│     ├─── join : 회원가입/탈퇴
│     │      ├─── controller
│     │      ├─── service
│     │      ├─── dao
│     │      ├─── dto
│     │      └─── vo
│     │
│     ├─── info : 회원 상세조회/수정/비밀번호 수정
│     │      ├─── controller
│     │      ├─── service
│     │      ├─── dao
│     │      ├─── dto
│     │      └─── vo
│     │
│     └─── find : 아이디 찾기 / 비밀번호 찾기
│            ├─── controller
│            ├─── service
│            ├─── dao
│            ├─── dto
│            └─── vo
│
├─── terms_of_use : 이용약관 관련
│     ├─── controller
│     ├─── service
│     ├─── dao
│     ├─── dto
│     └─── vo
│
├─── auth_number : 인증번호 관련
│     ├─── controller
│     ├─── service
│     ├─── dao
│     ├─── dto
│     └─── vo
│
├─── history : 이력 관련
│     ├─── login : 로그인/로그아웃 이력
│     │      ├─── controller
│     │      ├─── service
│     │      ├─── dao
│     │      ├─── dto
│     │      └─── vo
│     │
│     └── api : API 사용 이력
│            ├─── controller
│            ├─── service
│            ├─── dao
│            ├─── dto
│            └─── vo
│
├─── settings : 이력 관련
│     ├─── activation : 회원 활성화 관련
│     │      ├─── controller
│     │      ├─── service
│     │      ├─── dao
│     │      ├─── dto
│     │      └─── vo
│     │
│     ├─── login : 로그인 관련(실패 횟수 등)
│     │      ├─── controller
│     │      ├─── service
│     │      ├─── dao
│     │      ├─── dto
│     │      └─── vo
│     │
│     └─── password : 비밀번호 관련(비밀번호 구성 등)
│            ├─── controller
│            ├─── service
│            ├─── dao
│            ├─── dto
│            └─── vo
│
└─── util : 공통 util 기능
      ├─── SpringUtil : 문자열 관련
      └─── RegexUtil : 정규식 관련
```