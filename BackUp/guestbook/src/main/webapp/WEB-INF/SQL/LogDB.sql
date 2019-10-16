conn sys as sysdba

CREATE USER me IDENTIFIED BY tiger
 default tablespace users
 temporary tablespace temp;

grant connect, resource, plustrace, alter session to me;


CREATE TABLE log (
    id  number(20) NOT NULL primary key,
    ip varchar2(255) NOT NULL,
    method varchar2(10) NOT NULL,
    regdate date
    );
    
CREATE SEQUENCE log_seq START WITH 1 INCREMENT BY 1 MAXVALUE 99999999 CYCLE NOCACHE;

create trigger increse_num
      before insert on log
      for each row
    begin
    select log_seq.nextval
        into :new.id
        from dual;
    end;
  /