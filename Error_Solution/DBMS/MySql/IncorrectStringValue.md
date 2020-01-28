# **Incorrect String Value error**

## **원인**
인코딩이 어딘가 맞지않아 생기는 전형적인 문제이다.

## **해결**

<br>

- **`my.ini`** 파일을 찾아서 아래와 같이 넣어주기

<br>


```ini
[mysql]
default-character-set=utf8mb4

[mysqld]
character-set-server = utf8mb4
collation-server = utf8mb4_unicode_ci
```

<br>

이모지까지 들어갈 수 있는 `utf8mb4` 로 인코딩해보았다.
<br>
기본적인 `utf-8` 인코딩은 아래와 같이 입력한다.


```ini
character-set-server = utf8

collation-server = utf8_general_ci


```

- SQL 프로그램을 재시작 한다.
- 테이블 인코딩 바꿔주기
```sql
ALTER TABLE 테이블 이름 convert to charset utf8(or utf8mb4);

```


