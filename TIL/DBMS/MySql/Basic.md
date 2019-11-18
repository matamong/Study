# 기본 SQL문


- ### database 선택
```sql
SHOW DATABASES;
USE [데이터베이스명];
```

<br><br>

- ### 테이블 생성
```sql
CREATE TABLE [테이블명](
	...	
);

```
```sql
CREATE TABLE [테이블명](
    ex_id int auto_increment PRIMARY KEY,
    ex_age INT not null,
    ex_name VARCHAR(30),
    ex_email VARCHAR(50),
    ex_birth DATE,
    ex_desc TEXT(1000)
);

```
<br><br>

- ### INSERT

```SQL
INSERT INTO [테이블명] 
VALUES(NULL,20,'ma','ma@maa',2019-11-12,'dsafsdfas');


INSERT INTO [테이블명] (age) VALUES (23);

```
<BR><BR>

- ### SELECT
```SQL
SELECT * FROM [테이블명];


```
<br><br>

- # 응용하기
- 예제테이블 생성
```sql
CREATE TABLE my_contacts (
  last_name varchar(30) ,
  first_name varchar(20) ,
  email varchar(50) ,
  gender char(1),
  birthday date ,
  profession varchar(50),
  location varchar(50),
  status varchar(20),
  interests varchar(100),
  seeking varchar(100)
)
```

- 데이터 넣기
```sql
INSERT INTO my_contacts (last_name,first_name,email,gender,birthday,profession,location,status,interests,seeking) VALUES ('Anderson','Jillian','jill_anderson@ \nbreakneckpizza.com','F','1980-09-05','Technical Writer','Palo Alto, CA','single','kayaking, reptiles','relationship, friends');

INSERT INTO my_contacts (last_name,first_name,email,gender,birthday,profession,location,status,interests,seeking) VALUES ('Kenton','Leo','lkenton@starbuzzcoffee.com','M','1974-01-10','Manager','San Francisco, CA','divorced','women','women to date');

INSERT INTO my_contacts (last_name,first_name,email,gender,birthday,profession,location,status,interests,seeking) VALUES ('McGavin','Darrin',' captainlove@headfirsttheater.com','M','1966-01-23','Cruise Ship Captain','San Diego, CA','single','sailing, fishing, yachting','women for casual relationships');

INSERT INTO my_contacts (last_name,first_name,email,gender,birthday,profession,location,status,interests,seeking) VALUES ('Franklin','Joe','joe_franklin@leapinlimos.com','M','1977-04-28','Software Sales','Dallas, TX','married','fishing, drinking','new job');

INSERT INTO my_contacts (last_name,first_name,email,gender,birthday,profession,location,status,interests,seeking) VALUES ('Hamilton','Jamie','dontbother@starbuzzcoffee.com','F','1964-09-10','System Administrator','Princeton, NJ','married','RPG','nothing');

INSERT INTO my_contacts (last_name,first_name,email,gender,birthday,profession,location,status,interests,seeking) VALUES ('Chevrolet','Maurice','bookman4u@objectville.net','M','1962-07-01','Bookshop Owner','Mountain View, CA','married','collecting books, scuba diving','friends');

INSERT INTO my_contacts (last_name,first_name,email,gender,birthday,profession,location,status,interests,seeking) VALUES ('Kroger','Renee','poorrenee@mightygumball.net','F','1976-12-03','Unemployed','San Francisco, CA','divorced','cooking','employment');

INSERT INTO my_contacts (last_name,first_name,email,gender,birthday,profession,location,status,interests,seeking) VALUES ('Mendoza','Angelina','angelina@starbuzzcoffee.com','F','1979-08-19','UNIX Sysadmin','San Francisco, CA','married','acting, dancing','new job');

INSERT INTO my_contacts (last_name,first_name,email,gender,birthday,profession,location,status,interests,seeking) VALUES ('Murphy','Donald','padraic@tikibeanlounge.com','M','1967-01-23','Computer Programmer','New York City, NY','committed relationsh','RPG, anime','friends');

INSERT INTO my_contacts (last_name,first_name,email,gender,birthday,profession,location,status,interests,seeking) VALUES ('Spatner','John','jpoet@objectville.net','M','1963-04-18','Salesman','Woodstock, NY','married','poetry, screenwriting','nothing');

INSERT INTO my_contacts (last_name,first_name,email,gender,birthday, profession,location) VALUES ('Toth','Anne','Anne_Toth@leapinlimos.com','F','1969-11-18', 'Artist','San Fran, CA');

INSERT INTO my_contacts (last_name,first_name,email,gender,birthday, profession,location) VALUES ('Manson','Anne','am86@objectville.net','F','1977-08-09', 'Baker','Seattle, WA');

INSERT INTO my_contacts (last_name,first_name,email,gender,birthday, profession,location) VALUES ('Hardy','Anne','anneh@b0tt0msup.com','F','1963-04-18', 'Teacher','San Fran, CA');

INSERT INTO my_contacts (last_name,first_name,email,gender,birthday, profession,location) VALUES ('Parker','Anne','annep@starbuzzcoffee.com','F','1983-01-10', 'Student','San Fran, CA');

INSERT INTO my_contacts (last_name,first_name,email,gender,birthday, profession,location) VALUES ('Blunt','Anne','anneblunt@breakneckpizza.com','F','1959-10-09', 'Web Designer','San Fran, CA');

INSERT INTO my_contacts (last_name,first_name,email,gender,birthday, profession,location) VALUES ('Jacobs','Anne','anne99@objectville.net','F','1968-02-05', 'Computer Programmer','San Jose, CA');
```

- select
```sql
SELECT * FROM my_contacts 
WHERE gender='f' AND first_name='Anne' AND location LIKE '%San F%';

SELECT * FROM my_contacts 
WHERE location LIKE '%CA' AND STATUS = 'married';

SELECT * FROM my_contacts 
WHERE seeking = 'new job';

SELECT * FROM my_contacts 
WHERE gender='m' and status = 'divorced';

SELECT * FROM my_contacts 
WHERE gender='m' ORDER BY birthday DESC;

SELECT * FROM my_contacts 
WHERE gender='f' ORDER BY birthday ASC;

SELECT * FROM my_contacts 
WHERE interests not LIKE '%RPG%' AND gender = 'f';

SELECT * FROM my_contacts 
WHERE location LIKE '%NY%' ORDER BY birthday;

SELECT * FROM my_contacts 
WHERE email NOT LIKE '%com';

SELECT * FROM my_contacts 
WHERE STATUS='married' AND seeking='friends';

SELECT * FROM my_contacts 
WHERE birthday LIKE '%-04-%' ORDER BY gender, birthday;

SELECT * FROM my_contacts 
WHERE interests LIKE '%ing' AND STATUS = 'married';

SELECT * FROM my_contacts 
WHERE last_name LIKE '%m%' AND location = '%CA%' AND gender = 'f';
```