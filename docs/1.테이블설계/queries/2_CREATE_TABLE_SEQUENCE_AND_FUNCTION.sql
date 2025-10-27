/* 시퀀스를 저장할 테이블 생성 */
CREATE TABLE TABLE_SEQUENCES (
    name VARCHAR(100) COMMENT '시퀀스 이름',
    currval BIGINT UNSIGNED COMMENT '현재 시퀀스 값'
) ENGINE=INNODB COMMENT='시퀀스를 저장하기 위한 테이블';

-- 프로시저 중간에 세미콜론(;)을 사용해야 하므로 프로시저 작성 쿼리를 구분하기 위해 구분자를 '$$'로 정의
DELIMITER $$

/* 시퀀스 생성을 위한 CREATE_SEQ 프로시저 정의 */
CREATE PROCEDURE CREATE_SEQ (in seq_name VARCHAR(50))
MODIFIES SQL DATA
DETERMINISTIC
BEGIN
    DELETE FROM TABLE_SEQUENCES WHERE name = seq_name;
    INSERT INTO TABLE_SEQUENCES VALUES(seq_name, 0);
END
$$

/* 함수를 생성할 수 있도록 권한 부여 */
show global variables like 'log_bin_trust_function_creators'; -- off
set global log_bin_trust_function_creators=ON;

/* NEXTVAL 함수 생성 */
CREATE FUNCTION NEXTVAL (seq_name VARCHAR(50))
RETURNS BIGINT UNSIGNED
MODIFIES SQL DATA
DETERMINISTIC
BEGIN
    DECLARE RET BIGINT UNSIGNED;
    UPDATE TABLE_SEQUENCES SET currval = currval + 1 WHERE name = seq_name;
    SELECT currval INTO RET FROM TABLE_SEQUENCES WHERE name = seq_name;
    RETURN RET;
END
$$

/* CURRVAL 함수 생성 */
CREATE FUNCTION CURRVAL (seq_name VARCHAR(50))
RETURNS BIGINT UNSIGNED
MODIFIES SQL DATA
DETERMINISTIC
BEGIN
    DECLARE RET BIGINT UNSIGNED;
    SELECT currval INTO RET FROM TABLE_SEQUENCES WHERE name = seq_name;
    RETURN RET;
END
$$

-- 프로시저 작성이 종료되었으므로 다시 쿼리의 구분자를 세미콜론(;)으로 정의
DELIMITER ;