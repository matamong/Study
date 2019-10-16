# **Missing artifacts com.oracle:ojdbc.jar**

Spring 프로젝트에 Oracle을 연동하려고 Maven에 설정을 했는데 <br>
`Missing artifacts com.oracle:ojdbc6:jar:11.2.0.3` 이라고 떠버렸다. <br>

## 원인
Maven 중앙저장소에서는 ojdbc를 직접 제공해주지 않는다고한다. <br>
그래서 `<dependencies>`에 아무리 넣어도 불러와주질 않았다.
<br><br>

## 해결
- mvnrepository.com 으로 가서 ojdbc 검색
    - https://mvnrepository.com/artifact/com.oracle/ojdbc6/11.2.0.3 (현재 최신버전) 
- `spring plugin` 선택
- Note 부분의 repository 주소를 긁어서 `<repository>`안에 `<url>`에 넣는다. (`<repository>`는 `<properties>` 위에 위치해야 함)

<br><br><br>

`<repository>`
```html
<repositories>
	<repository>
		<id>oracle</id>
		<url>https://repo.spring.io/plugins-release/</url>
	</repository>
</repositories>

```
<br><br>

`<dependency>`
```html
<dependencies>
	<dependency>
		<groupId>com.oracle</groupId>
		<artifactId>ojdbc6</artifactId>
		<version>11.2.0.3</version>
	</dependency>
</dependencies>
```

