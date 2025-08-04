create table t_member(
    id varchar2(10) primary key,
    pwd varchar2(10),
    name varchar2(50),
    email varchar2(50),
    joinDate date default sysdate);
    
insert into t_member
VALUES( 'hong','1212','홍길동','hong@gamil.com', sysdate);

insert into t_member
values( 'lee', '1212', '이순신', 'lee@test.com', sysdate);

INSERT INTO t_member
VALUES( 'kim', '1212', '김유신', 'kim@jweb.com', SYSDATE);

SELECT * FROM t_member;

COMMIT;