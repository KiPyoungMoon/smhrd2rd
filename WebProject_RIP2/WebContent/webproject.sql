create table member(
id varchar2(50) primary key,
email varchar2(50),
pw varchar2(50) not null,
name varchar2(50) not null,
tel varchar2(50) not null,
birth varchar2(50) not null,
gender varchar2(10) not null,
hint_index number,
answer varchar2(100) not null
);

insert into member values('thefates82','thefates82@gmail.com','11111111','문기평','010-9292-2482','200011','male',1,'1');

delete from member where id='thefates82';

drop table member

select * from hint;
select * from member;

alter table member modify hint_index number

create table hint(
hint_index number primary key,
hint varchar2(100) not null
)

insert into hint values(1,'아버지의 성함은?');
insert into hint values(2,'어머니의 성함은?');
insert into hint values(3,'졸업한 초등학교 이름은?');
insert into hint values(4,'졸업한 중학교 이름은?');
insert into hint values(5,'졸업한 고등학교 이름은?');
insert into hint values(6,'나의 보물 제 1호는?');
insert into hint values(7,'가장 친한 친구의 이름은?');
insert into hint values(8,'나의 좌명은?');
insert into hint values(9,'내가 좋아하는 음식은?');
insert into hint values(10,'가장기억에 남는 영화이름은?');


alter table member add foreign key(hint_index) references hint(hint_index) //외래키

create table guestbook(
	id varchar2(50),
	writer varchar2(50),
	context varchar2(500),
	visittime varchar2(50)
);

insert into guestbook values('thefates', 'abcd', 'fkdlsjfklsdjfkldsjflkdsfjlksdfjklsdfjklsdfjkldsfjkl;', to_char(sysdate,'yyyy-mm-dd'));
delete from GUESTBOOK;
select * from GUESTBOOK;

create table memorial(
	id varchar2(50) primary key,
	name varchar2(50) not null,
	birth varchar2(50) not null,
	photo varchar2(50),
	grave varchar2(50),
	qr varchar2(50),
	music varchar2(50)
);


drop table guestbook;
alter table guestbook add foreign key(id) references memorial(id);
alter table guestbook add foreign key(writer) references member(id);
CREATE INDEX IDX_guestbook ON guestbook( id );





