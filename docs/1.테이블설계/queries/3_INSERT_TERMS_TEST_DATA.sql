-- Test data 1
INSERT INTO TERMS (
	TERMS_NO,
    USE_YN,
    REQUIRE_YN,
    ORDER_NO,
    TITLE,
    CONTENTS,
    CREATE_ID,
    CREATE_DATE
) VALUES (
	1,
    1,
    1,
    1,
    '테스트 - 사이트 이용 약관',
    '사이트에 대한 설명과 이용 규칙 및 규정, 광고, 서비스 오류 사항, 운영 정책 등에 대한 내용이 작성됩니다.',
    0,
    NOW()
);

-- Test data 2
INSERT INTO TERMS (
	TERMS_NO,
    USE_YN,
    REQUIRE_YN,
    ORDER_NO,
    TITLE,
    CONTENTS,
    CREATE_ID,
    CREATE_DATE
) VALUES (
	2,
    1,
    0,
    2,
    '테스트 - 개인정보 수집 및 이용',
    '개인정보보호법에 따라 수집되는 개인 정보와 이용 목적을 안내하고, 동의 거부 시 불이익에 대한 내용을 안내합니다.',
    0,
    NOW()
);