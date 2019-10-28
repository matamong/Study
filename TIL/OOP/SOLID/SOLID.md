(이 문서는 계속 업데이트 될 수 있습니다.) <br><br>

# 객체 지향 설계 5원칙 - SOLID

## SOLID란? <br>

SOLID란 용어는 로버트 C.마틴(aka Uncle Bob)가 객체 지향 프로그래밍 및 설계의 다섯 가지 기본 원칙으로 제시한 원칙을 파이클 페더스가 앞글자만 따서 만들어진 용어이며. 다섯가지 원칙은 다음과 같다.
- SRP(Single Reponsibility Principle) : 단일 책임 원칙
- OCP(Open Closed Principle) : 개방 폐쇄 원칙
- LSP(Liskov Substitution Principle) : 리스코프 치환 원칙
- ISP(Interface Segregation Principle) : 인터페이스 분리 원칙
- DIP(Dependency Inversion Principle) : 의존 역전 원칙

SOLID는 기존의 높은 응집도, 낮은 결합도를 위한 프로그래밍 원칙을 객체 지향의 관점에서 재정립한 것이다. 객체지향의 기본이라 할 수 있는 이 원칙은 어떤 것인지 알 수는 있어도 직접 체화하기에는 어마어마한 시간이 걸리는 원칙이며 독립적인 원칙이 아니라 다른 개념들과 이어져있는 원칙이다. 그렇기에 하나하나 곱씹어보기위해 정리해보려고 한다.

<br><br>

# SRP - 단일 책임 원칙

## **용어의 뜻**
객체 지향 설계의 5원칙인 SOLID 중 S를 맡고 있는 것이 **SRP**(Single Responsiblity Principle)이다. 한글로 번역하면 **단일 책임 원칙** 이라고 한다. 이 원칙은 로버트 C.마틴(aka 엉클 밥)이 그의 저서 `Principles of Object Oriented Design`에서 처음 사용하였다. 그는 `Tom Demarco’s`의 아이디어인 **응집력**으로부터 이 개념을 떠올렸다고 한다.<br><br>

## **SRP란?**

> "어떤 클래스를 변경해야 하는 이유는 오직 하나뿐이어야 한다." - 로버트 C. 마틴

SRP를 대표하는 로버트 C.마틴의 말이다.이 말을 좀 더 풀어서 생각해보면 **클래스의 역할(책임)을 분리하고 하나의 클래스에 하나의 책임만 가지게 하여야 한다**라고 생각 할 수도 있을 것 같다.  <br><br>

코드로 예를 들어보자

```java
public class Service{

    private DataSource dataSource;

    public void setDataSource(dataSource){
        this.dataSource = dataSource;
    }


    //연결한 데이터베이스의 내용을 가져와 어떤 작업을 수행하는 메소드
    public void doService{
        TransactionSynchronizationManager.initSynchronizer();
        Connection c = DataSourceUtiols.getConnection(dataSource);
        c.setAutoComit(false);
        //트랜잭션을 사용하기 위한 세팅

        try{
            //수행 해야 할 비즈니스 로직
        }catch{
            //예외
        }
        
        //대충 트랜잭션 정리 코드
    }
}
```
여기 이 Service라는 클래스의 doService메소드를 한 번 보자. <br>
DB의 내용을 가지고 비즈니스로직을 처리하는 역할을 하고있다.(dataSource는 미리 주입받았다고 가정하자)<br><br>

이 클래스는 얼핏보면 DB에 연결하고 연결한 데이터베이스의 내용을 가지고 작업을 하기 때문에 단일 책임 원칙을 잘 지킨 것 같이 보인다. <br> 
하지만 JDBC의 API인 현재 트랜잭션을 글로벌 트랜잭션인 JTX로 바꿔달라는 요청이 있을 경우에는 아래와 같이 코드를 바꿔야 한다.

```java
public class Service{

    private DataSource dataSource;

    public void setDataSource(dataSource){
        this.dataSource = dataSource;
    }


    //연결한 데이터베이스의 내용을 가져와 어떤 작업을 수행하는 메소드
    public void doService{
        InitalContext ctx = new InitialContext();
        UserTransaction tx = (UserTransaction)ctx.lookup(USER_TX_JNDI_NAME);
        tx.begin();
        Connection conn = dataSource.getConnection();
        //트랜잭션을 사용하기 위한 세팅

        try{
            //수행 해야 할 비즈니스 로직
        }catch{
            //예외
        }
        
        //대충 트랜잭션 정리 코드
    }
}
```
간단하다. 다른 코드들은 다 내버려두고 트랜잭션을 사용하기 위한 세팅만 바꾸면 된다. <br>
그런데 나중에는 하이버네이트를 이용해서 트랜잭션 관리를 해달라는 요청이 들어온다. 그럼 그 때는 Connection이 아닌 Session을 이용하여 독자적인 API를 사용해야 할 것이다. <br><br> 이 과정에서 다시 한 번 상기해보자. <br><br>
**이 클래스는 비즈니스 로직을 수행해야 하는 클래스이다.** 
<br><br>
그런데 **정작 트랜잭션 관리라는 다른 이유 때문에 원래의 코드는 전혀 변경하지 않은 채로 계속 클래스를 수정해야만 하는 것이다!!**  <br>
자신의 책임이 아닌 다른 책임 때문에 클래스를 수정하는 이러한 경우는 SRP원칙에 위배되어버린다. <br>
이럴 때는 추상화와 DI방식을 이용해서 트랜잭션 관리라는 책임을 외부에서 조종할 수 있도록 하여 한 클래스에서는 하나의 책임만 지도록 함으로써 SRP 원칙을 지킬 수 있을 것이다. <br><br>

이렇게 SRP를 지켰을 때의 장점은
- **어떤 변경이 필요할 때 수정 대상이 명확해진다.**
- **특정 기술에 종속되지 않을 수 있다.**

이며 이를 통해 작업량이 적어지게 되고 실수도 적어지면 유지보수가 훨씬 잘 될 것이다. 아주 기본적인 것 같으면서 대단한 원칙인 것 같다.





# OCP
# LSP
# ISP

# DIP - 의존 역전 원칙

> 고차원 모듈은 저차원 모듈에 의존하면 안 된다. 이 두 모듈 모두 다른 추상화된 것에 의존해야 한다.

> 추상화된 것은 구체적인 것에 의존하면 안 된다. 구체적인 것이 추상화된 것에 의존해야 한다.

`로버트 C. 마틴`은 **DIP**에 대해 이렇게 말했다. 이것만 보면 이 말이 **의존 역전 원칙**이라는 용어와 잘 맞물리지 않는다. 고차원 모듈은 뭐고 저차원 모듈은 뭐지? 먼저 이것부터 알아보자.<br>

- **고차원 모듈**(High Level Module) : Interface/abstraction 같이 저차원 모듈을 조종하는 모듈
- **저차원 모듈**(Low Level Module) : 고차원 모듈이 일을 할 수 있게 도와주는 작은 모듈들

예를 들어 고차원 모듈은 인터페이스클래스, 저차원 모듈은 인터페이스에 의해 일을 하는 클래스라고 생각하면 될 것 같다. 고차원 모듈은 자신이 다루는 모든 저차원 모듈의 공통성을 가져야 하기 때문에 당연히 어느 하나에 구체적이기보단 두루뭉실하게 추상적이어야한다. 저차원 모듈은 고차원 모듈의 추상적인 어떤 것들을 구체적으로 구상해서 풀어나가야하기 때문에 구체적이어야 한다.(저차원모듈이 꼭 고차원 모듈을 필요로 하는 것은 아니다.) <br>
 일단 `고차원 모듈 = 추상적` / `저차원 모듈 = 구체적` 이라고 간단히만 이해하고 바로 예시를 보자.


```java
class MyPhone{
    ChargerUSBC usbC = new ChargerUSBC();

    st.charge(); 
    //콘솔 : "충전을 시작합니다."
}
```

내가 사용하고 있는 스마트폰이라는 뜻의 `MyPhone`이라는 클래스를 만들었다.<br> 
`MyPhone` 클래스에서 사용할 충전기객체를 호출해보자. 충전기는 **USB-C타입**으로 charge()메소드를 실행하면 콘솔에 "충전을 시작합니다."라고 해보았다.
이렇게 보면 아무 문제 없이 동작할 것 같다.<br>
하지만 스마트폰은 좀 오래 썼다 하면 3년이듯이 교체주기가 짧다. `MyPhone`은 새로운 충전기가 필요해질 것이다. `MyPhone`은 이제 **8pin 타입**의 충전기가 필요하다.

```java
class MyPhone{
    //ChargerUSBC usbC = new ChargerUSBC();
    ChargerEightPin Eightpin = new ChargerEightpin();

}
```

부랴부랴 `usbC 객체`를 지우고 `8pin의 충전기 객체`를 호출한다.

```java
class MyPhone{
    // ChargerUSBC usbC = new ChargerUSBC();
    ChargerEightPin Eightpin = new ChargerEightpin();

    st.EightPincharge(); 
    //콘솔 : "8핀만의 충전을 시작합니다."

}
```
 8핀만의 메서드도 호출해보았다. 이렇게 충전기가 바뀔 때마다 그때그때 충전기 객체를 바꿔주면 충전은 잘 되것이다. 하지만 이 방식으로는 충전기가 바뀔 때 마다 계속 `MyPhone` 클래스를 건드려야 하거니와 바뀐 객체의 메서드를 지저분하게 늘어놓을 수 밖에 없을 것이다.<br> **충전기를 위해 `MyPhone`을 계속 건드려야 하는 것**(Myphone 내에서 충전기 객체를 생성하는 일).이것이 MyPhone이 **충전기에 의존하게 되는 것**이다. 이렇게 되버리면 효율도 좋지않고 위험하다.<br>
<br>

**여기서 이 불합리한 의존성을 역전해버리는 것이 DIP다.** <br><br>


DIP 원칙을 이용해 코드를 다시 짜보겠다.<br>
먼저, 인터페이스에다 충전기의 기능을 애매모호하게 다 몰아넣어 줄 것이다.<br>
```java
interface Charger {
    void charge();
}
```
<br><br>
그리고 Charger 인터페이스를 구현하는 각각의 충전기 클래스를 만들어준다. <br>

```java
class USBC implements Charger{
    void charge(){
        system.out.println("충전을 시작합니다.");
    }
}
```

```java
class EigtPin implements Charger{
    void charge(){
        system.out.println("8민만의 충전을 시작합니다.");
    }
}
```
<br><br>
그리고 MyPhone클래스는 아래와 같이 작성해주겠다. <br>

```java
class MyPhone {
    Charger charger = new EightPin();
    
    charger.charge();
}
```
<br>

## 변화한 것을 살펴보자.
- 새로운 **인터페이스**인 `Charger`가 생겼다.
- `MyPhone`은 각각의 충전기에 직접 의존하지 않고 **하나의 인터페이스인 Charger에만 의존**한다.
- 어느 곳에도 의존하지않던 각각의 `충전기`는 이제 **Charger에 의존**하게 된다. 

간단하게 표현하면 다음과 같다.

![DIP 전과 후](https://github.com/matamong/Study/blob/master/TIL/OOP/img/DIP_BeforeAfter.png)

이렇게 의존성이 역전되면 충전기가 변할 때 마다 `MyPhone 클래스`를 건드릴 필요없이 인터페이스만 이용하여 각 충전기를 교체할 수 있다. <br>
<br>

정리하자면 <br>
**자신보다 변하기 쉬운 것에 의존하지 말고 변하지 않을 가능성이 높은 상위 클래스에 의존하라는 것이 DIP일 것이다.** <br>
`MyPhone`은 주변의 변화에 폐쇄적이게 되어 편하고 충전기들은 한없이 늘어날 수 있어 개방적인 면에서는 OCP(개방 폐쇄 원칙)와도 닮아있다. 프록시 패턴, 템플릿 메서드 패턴, 전략 패턴, Spring의 IoC/DI 등을 알기 위해 꼭 필요한 원칙이다.




* * *
[ 참고도서 ] <br> 
**`<<스프링 입문을 위한 자바 객체 지향의 원리와 이해>>`** - 지은이 김종민 <br>
**`<<토비의 스프링 3.1>>`** - 지은이 이일민
