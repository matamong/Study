# 서블릿 초기화 파라미터(ServletConfig & ServletContext & ServletContextListener)

자주 변하는 데이터를 서블릿에 넣어야 한다고 생각해보자. 서블릿 하나하나에 하드코딩을 하게 될 것이다. 이것은 유지보수를 생각하면 옳지 않은 방법이다. 그리하여 선대(?) 개발자들은 DD(배포 서술자)에 변화하기 쉬운 데이터를 넣고 서블릿에서는 그것을 파라미터로 받게 만들었다. 이제 데이터가 변경될 때는 DD만 수정하면 되는 것이다! 이 방법에 대하여 공부해보자.


# ServletConfig 초기화 파라미터

컨테이너는 서블릿을 하나 생성하려고 할 때 하나의 ServletConfig 객체를 만든다. 이 ServletConfig 객체를 이용하여 서블릿을 초기화 시켜줄 수 있다. <br><br>
## 과정
- 먼저 서블릿 컨테이너가 DD(web.xml)에 적혀진 파라미터를 읽는다.
- 컨테이너는 ServletConfig 객체를 만든 뒤, 읽었던 파라미터 정보를 ServletConfig에 넘겨준다.
-  컨테이너는 정보를 가지고 있는 ServletConfig 를 해당 Serlvet의 init()메서드의 파라미터로 넣어준다.👍

<br><br>
## 어떻게 사용할까?
<br>

web.xml에 파라미터 등록
```xml
<web-app ...>
    <servlet>
        <servlet-name>서블릿 이름 1</servlet-name>
        <servlet-class>패키지 경로.서블릿1 클래스이름</servlet-class>
        <init-param>
            <param-name>파라미터 이름</param-name>
            <param-value>파라미터 값</param-value>
        </init-param>
    </servlet>
    <servlet-mapping>
        <servlet-name>서블릿 이름 1</servlet-name>
        <url-pattern>/매핑주소<url-pattern>
    <servlet-mapping>
</web-app>
```

<br><br>

서블릿에서는 아래와 같이 파라미터를 꺼낸다.
```java
getServletConfig().getInitParameterName("Param-name");
```
<br><br>

특정 서블릿에서만 값을 꺼낼 수 있기 JSP에서는 파라미터 값을 사용 할 수 없다. JSP에서 파라미터를 사용하고자 할 때에는 속성을 이용하여 JSP에게 값을 넘겨줘야한다.(forwarding)

<br><br>


## init()의 파라미터인 죄...
하지만 이 방식엔 아쉬운 점이 있다. 바로 init()의 파라미터이기 때문에 초기화 될 때 파라미터를 **오직 한 번만** 읽을 수 있다는 것이다. 그래서 실행 중인 서블릿은 다시 배포되지 않는 이상 DD를 아무리 수정한다 한들 바뀌는게 없다!! 만약 수정했다면 수정된 서블릿을 다시 초기화하기 위해서 재배포해야한다. 이 말은 즉, 데이터 하나 바꾼다고 서버를 내렸다가 올려야한다는 말이다! 하드코딩은 피했지만 그래도 아쉽기만 하다.
<br><br>

# ServletContext 파라미터



