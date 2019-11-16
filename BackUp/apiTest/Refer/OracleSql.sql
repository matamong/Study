CREATE USER scott IDENTIFIED BY tiger;  -- 이름 scott, 비밀번호 tiger 인 유저 생성
GRNAT connect TO scott;
GRANT all privileges TO scott;

CREATE TABLE MEMBER(
    EMAIL           VARCHAR2(30) PRIMARY KEY,
    NAME            VARCHAR2(10) NOT NULL,
    NICK_NAME       VARCHAR2(20) UNIQUE NOT NULL,
    PASSWORD        VARCHAR2(20) NOT NULL,
    PHONE           VARCHAR2(11) UNIQUE NOT NULL,
    ADDR            VARCHAR2(30) NOT NULL,
    EDU_STATE       VARCHAR2(5)  DEFAULT 'NO',
    NO_SHOW_CNT     NUMBER(2)    DEFAULT 0,
    GRADE           VARCHAR2(10) DEFAULT '직원'
);

INSERT INTO MEMBER(EMAIL, NAME, NICK_NAME, PASSWORD, PHONE, ADDR)
            VALUES('a@a', 'kim', 'chocobe', 'pw', '010', '직원');

SELECT * FROM MEMBER;
            
DROP TABLE MEMBER;

create table MAP(
    email VARCHAR2(30),
    x varchar2(25) not null,
    y varchar2(25) not null,
    map_address varchar2(50) primary key,
    special varchar2(50),
    title varchar2(15),
    CONSTRAINT fk_map FOREIGN KEY (email)
    REFERENCES member(email) ON DELETE CASCADE
);
-- ON DELETE CASCADE : 부모테이블 값이 삭제되면 연쇄적으로 자식테이블 값 역시 삭제

INSERT INTO MAP 
VALUES('a@a', '33.460001', '126.570770','어딘가','TEST','TEST');

SELECT * FROM MAP;

ALTER TABLE map DROP CONSTRAINT fk_map;
DROP TABLE map;