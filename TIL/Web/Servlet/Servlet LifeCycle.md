# Servlet의 생명주기

**서블릿**의 생명주기를 알아보려고 한다.<br>
먼저, 서블릿이 동작을 할 때 까지의 과정을 알아보자.<br>
<br>

## **Servlet**이 동작하려면...<br>
* * *
![ppt1](https://github.com/matamong/Study/blob/master/TIL/Web/Servlet/img/Servlet/%EC%8A%AC%EB%9D%BC%EC%9D%B4%EB%93%9C1.JPG?raw=true)

- 먼저 `클라이언트(Web Browser)`가 요청을 보낸다.
- 클라이언트의 요청을 `WAS`가 받아 `HttpServletRequest`객체와 `HttpServletResponse`객체가 존재하는지 확인 한 후,<br>
  존재하지 않으면 두 객체를 생성해서 웹 어플리케이션의 `서블릿 컨테이너`로 전달해준다.
- 이제 비로서 서블릿이 동작하며 서블릿 라이프사이클이 돌아가게된다.<br>
<br>
## **Servlet**의 생명주기
* * *
![ppt2](https://github.com/matamong/Study/blob/master/TIL/Web/Servlet/img/Servlet/%EC%8A%AC%EB%9D%BC%EC%9D%B4%EB%93%9C2.JPG?raw=true)

**Servlet**은 
- **`init()`**
- **`service()`**
- **`destroy()`**
이 세가지의 메서드를 돌며 위의 그림과 같이 돌며 살아간다.<br>
<br>

### **`init()`**<br>
* * *
![init()ppt](https://github.com/matamong/Study/blob/master/TIL/Web/Servlet/img/Servlet/%EC%8A%AC%EB%9D%BC%EC%9D%B4%EB%93%9C3.JPG?raw=true)

`init()` 메서드는 최초로 딱 한번 호출된다.<br>
그 때가 언제냐면 바로 서블릿 컨테이너가 전달받는 객체가 생성 될 때이다.
WAS에서 서블릿 컨테이너에게 전달해주는 객체를 생성할 때  `init()`메서드를 호출한다.<br>
(사실 이 전 단계에 객체 생성 단계가 있어야 한다)<br>
<br>

### **`service()`**<br>
* * *
![service()ppt](https://github.com/matamong/Study/blob/master/TIL/Web/Servlet/img/Servlet/%EC%8A%AC%EB%9D%BC%EC%9D%B4%EB%93%9C4.JPG?raw=true)

`service()`메서드는 실제로 제일 열일하는 메서드이다.<br>
`HttpServletRequest`,`HttpServletResponse` 이 두 객체를 가진 컨테이너가 `service()`메서드를 불러낸다.<br>
호출된 `service()`메서드 안에 있는 서브 메서드들이 요청에 맞게 호출된다.<br>
그리고 `HttpServletResponse`객체에 응답을 담아 보낸다.<br>
<br>

### **`destroy()`**<br>
* * *
![service()ppt](https://github.com/matamong/Study/blob/master/TIL/Web/Servlet/img/Servlet/%EC%8A%AC%EB%9D%BC%EC%9D%B4%EB%93%9C5.JPG?raw=true)

기존의 서블릿이 더 이상 필요없을경우 `destroy()`메서드가 호출된다.<br>
이는 `service()`메서드 내의 변경사항이 있을 경우에도 해당이 되기 때문에<br>
변경사항이 있을 경우 `destroy()`가 호출되고 다시 `service()`가 실행되는 모습을 볼 수 있다.<br>




 
