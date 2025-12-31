DROP TABLE t_Board CASCADE CONSTRAINTS;

--게시판 테이블 생성
create table t_Board(
    articleNO number(10) primary key,
    parentNO number(10) default 0,
    title varchar2(500) not null,
    content varchar2(4000),
    imageFileName varchar(100),
    writeDate date default sysdate not null,
    id varchar2(10),
    CONSTRAINT FK_ID FOREIGN KEY(id)REFERENCES t_member(id)
    );
    
    select * from t_member order by joinDate desc;
    
INSERT INTO t_board( articleno, parentno, title, content, imagefilename, writedate, id)
 values(1, 0, '테스트글입니다.', '테스트글입니다.', NULL, sysdate, 'test');

INSERT INTO t_board( articleno, parentno, title, content, imagefilename, writedate, id)
 values(2, 0, '안녕하세요.', '상품 후기입니다.', NULL, sysdate, 'hong');
 
 INSERT INTO t_board( articleno, parentno, title, content, imagefilename, writedate, id)
 values(3, 2, '답변입니다.', '상품 후기에 대한 답변입니다.', NULL, sysdate, 'hong');
 
 INSERT INTO t_board( articleno, parentno, title, content, imagefilename, writedate, id)
 values(5, 3, '답변입니다.', '상품 좋습니다.', NULL, sysdate, 'lee');
 
 INSERT INTO t_board( articleno, parentno, title, content, imagefilename, writedate, id)
 values(4, 0, '김유신입니다.', '김유신 테스트글입니다.', NULL, sysdate, 'kim');
 
 INSERT INTO t_board( articleno, parentno, title, content, imagefilename, writedate, id)
 values(6, 2, '상품 후기입니다.', '이순신씨의 상품 사용 후기를 올립니다!!.', NULL, sysdate, 'lee');
 
 select * from t_board order by articleno;
 
 commit;