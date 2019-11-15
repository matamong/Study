# ServerTimeZone error


## 원인
MySql의 서버 시간대는 UTC에 맞춰져있다.
만약 버전이 꼬여 다른 곳에서 UTC를 찾지 못하면 serverTimezone에러가 생기게 되는 것이다.

## 해결
드라이버 클래스와 url을 아래와 같이 바꿔주자.

``` java
DriverClassName : "com.mysql.cj.jdbc.Driver"

Url : "jdbc:mysql://localhost:3306/test?serverTimezone=UTC"
```
드라이버 클래스 이름 중간에 cj를 넣어준다.<br>
url 뒤에 `?serverTimezone=UTC`를 넣어준다.