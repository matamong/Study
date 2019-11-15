# 커넥션 풀 Connection Pool

# 커넥션 풀이란?
 - 데이터베이스가 **연결되고 사용**하는 과정의 자원 소모를 효율적으로 하기 위하여 **연결 부분(Connect)**과 **사용(Pool)** 부분을 분리 한 뒤 커넥션을 미리 만들어 Pool 속에 저장해 두었다가 가져다 쓴 뒤 다시 Pool에 저장하는 것.

 ## 장점
     - DB 접속에 드는 큰 자원을 효율적으로 관리할 수 있다.
	 - 커넥션 수를 제어하여 접속자들을 효율적으로 관리할 수 있다.




## 기본 DBCP 셋팅(설정파일로 셋팅하는 법)

- DBCP 라이브러리 추가
    - common-dbcp 
    - common-pool
    - mysql-connector 등등 DBMS 자체의 dbcp 풀 라이브러리
- javax.sql 패키지의 DataSource 상속

- Dao.java
```java
    public class MyDAO{
        private DataSource ds;
        private static final MyDAO INSTANCE = new MyDAO();

	    private MyDAO {
		    try {
	    		Context init = new InitialContext(); 
    			ds = (DataSource)init.lookup("java:comp/env/jdbc/mysqlDB");
		    } catch (NamingException e) {			
			    e.printStackTrace();
		    }
	    }

        public static MyDAO getInstance() {
		return INSTANCE;
	    }

        public Connection connect() {
		Connection con = null;
		try {
			con = ds.getConnection();
		} catch (SQLException e) {			
			e.printStackTrace();
		}		
		return con;
	}
    }
```


- web.xml(project)

```xml
  <resource-ref>
  	<description>Connection</description>
  	<res-ref-name>jdbc/mySqlDB</res-ref-name>
  	<res-type>javax.sql.DataSource</res-type>
  	<res-auth>Container</res-auth>
  </resource-ref>
```
- context.xml(server)
```xml
<Resource
    	name="jdbc/mysqlDB"
    	auth="Container"
    	driverClassName="com.mysql.jdbc.Driver"
    	type="javax.sql.DataSource"
    	url="jdbc:mysql://localhost:3306/test?serverTimezone=UTC"
    	username="root"
    	password="1111"
    	initialSize="10"
    	maxActive="10"
    	maxIdle="10"
    	minIdle="10"
    	maxWait="3000"
    	/>
```