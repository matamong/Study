





## 기본 DBCP 셋팅

- DBCP 라이브러리 추가
    - common-dbcp 
    - common-pool
    - mysql-connector

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