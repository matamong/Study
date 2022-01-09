# **Nginx란?**

서버를 배포할 때 Nginx 서버를 두고 ssl을 적용했었다. 오늘은 이 Nginx를 자세하게 공부해볼 것이다. <br>
Nginx를 검색해보면 주로 다음과 같은 기능을 한다고 한다.
- **Web Server**
- **Reverse Proxy**
    - **HTTP Cache**
- **Load Balancer**


이것이 무엇을 뜻하는지 하나씩 도장을 깨며 알아보자.

<br>

## **Nginx as Web Server**
웹 서버는 클라이언트에게 HTTP형식의 요청을 받고 처리를 해준다. <br> 유저가 "사진 나와라 `Get!`" 하는 요청을 하면 유저의 브라우저에 사진을 보여주고, "글 나와라 `Get!`" 하면 글을 보여주는 것이다. <br>
Nginx는 **이 클라이언트의 요청을 최소한의 하드웨어 리소스로 여러 클라이언트에게 동시에 처리하도록 설계** 되어 나온 서버이다. <br>
가장 많이 쓰이는 아파치서버가 싱글 스레드당 하나의 커넥션을 연결하는 것과는 다르게 **Nginx의 싱글 스레드는 여러 커넥션을 연결할 수 있는 것이다.**  이것으로 메모리를 상당히 아낄 수 있다고한다. <br> 그렇다. Nginx는 응애하는 순간 트래픽 가성비를 외치며 나온 것이다. <br>
**Nginx는 대신 동적인 컨텐츠를 자체적으로 처리하지 못한다.**

<br><br><br><br>

## **Nginx as Reverse Proxy**

Proxy란 대리인이라는 뜻이고 Proxy server는 대리인처럼 클라이언트와 임의의 서버 사이에 끼어서 본인을 거쳐갈 수 있게 중계하는 서버이다.

![https://ko.wikipedia.org/wiki/%ED%94%84%EB%A1%9D%EC%8B%9C_%EC%84%9C%EB%B2%84](https://upload.wikimedia.org/wikipedia/commons/thumb/2/27/Open_proxy_h2g2bob.svg/525px-Open_proxy_h2g2bob.svg.png)

<br>

### **Forward Proxy**
![](https://s3.shunyafoundation.com/s3/2b37c116cedf3378ed4d66c4c28c7b6de4057de6/proxy-server.png)

<br>

이 Proxy 서버가 앞에 위치하게 되면 Forward Proxy라고 한다. 가장 일반적으로 쓰이는 Proxy server이다. <br>
본인을 거쳐갈 때, 보안을 적용한다던지 캐시를 유지해서 빠르게 데이터를 제공할 수 있게하여 이점을 얻을 수 있어 사용한다. 회사 내부망(클라이언트)에서 유해사이트(임의의 서버)에 접속시키지 못하게 할 수 있는 것은 바로 이 Proxy 서버를 이용해서 그런 것이다. <br>
**이상한 인터넷으로 못 들어가게 지켜주는(?) 클라이언트의 듬직한 대리인이라는 느낌이다.**

<br><br>

### **Reverse Proxy**
![https://s3.shunyafoundation.com/s3/6d9892fda50c65bd5176d8a8e54b0195f3a8349c/reverse-proxy-server.png](https://s3.shunyafoundation.com/s3/6d9892fda50c65bd5176d8a8e54b0195f3a8349c/reverse-proxy-server.png) 

<br><br>

이름 그대로 Forward Proxy를 역으로 하는 것이 Reverse Proxy 이다. <br>
사설 네트워크의 뒤에 위치하여 클라이언트의 요청을 적절한 백엔드 서버로 보내는 것이다. <br>
이로인해 Reverse Proxy Server 뒤에 있는 백엔드 서버는 클라이언트에게 노출되지 않으므로 다음과 같은 장점을 얻는다.
- 클라이언트는 백엔드 서버와 직접 소통하지않고 Reverse Proxy Server의 IP주소와 소통하기 때문에 보안에 좋다. 
- 백엔드의 소통구조를 자유롭고 융통성있게 만질 수 있다. 
- 캐시를 통해 속도를 높일 수 있다.
    - (캐시와 보안을 이용해 특정 클라이언트의 DDOS공격을 예방할 수 도 있음)

<br>

**이상한 클라이언트의 요청으로부터 지켜주는(?) 서버의 듬직한 대리인이라는 느낌이다.** <br>

<br><br>

### **Nginx as Reverse Proxy**
Nginx는 Web Server로도 사용하지만 Reverse Proxy server로도 사용할 수 있게 설계되었다. *(최근엔 Forward Proxy도 가능함)* <br> 그리고 많은 사람들이 Nginx를 **웹서버로 사용하기보다 Reverse Proxy 서버로써 사용**한다고 한다. <br>
왜 이렇게 많은 사람들이 Reverse Proxy로 사용할까? 그 답은 앞서 **Nginx는 동적인 컨텐츠를 자체적으로 처리하지 못 한다**고 한 것에 있다. <br>
Nginx가 동적인 컨텐츠를 자체적으로 처리하지 못 하는 대신에 정적인 컨텐츠는 잘 처리하며 **Reverse Proxy도 가능한 점을 이용하여 정적인 컨텐츠의 처리는 Nginx에게 맡기고 동적인 컨텐츠의 처리는 아파치(백엔드 서버)에게 맡기고 있는 것**이다. Nginx를 Reverse Proxy로 하여 요청을 받아 정적인 컨텐츠들은 캐싱하여 효율적으로 처리해주고 동적인 요청들은 백엔드 서버에 요청하는 것이다.


<br><br><br><br>


## **Nginx as Load Balancer**
클라이언트에서 너무 많은 요청이 들어오면 단일 서버로 버티기 힘들기 때문에 서버를 여러개로 늘릴 것이다. **이 때 트래픽을 효율적으로 서버들에게 분산하는 역할을 해주는 것을 Load Balancer라고 한다.** 여러개의 서버 중에 어떤 단일 서버가 다운되면 Load Balancer는 살아있는 서버로 리디렉션하고, 어떤 단일 서버가 생성되면 자동으로 요청을 보내는 등 마치 교통경찰처럼 요청을 밸런스있게 분산해준다. <br>
이 역할을 Nginx가 해줄 수 있는 것이다.


<br><br><br><br>

***

*참조*

*https://www.javatpoint.com/difference-between-apache-and-nginx* <br>
*https://www.sumologic.com/blog/nginx-vs-apache/* <br>
*https://medium.com/sjk5766/nginx-reverse-proxy-%EC%82%AC%EC%9A%A9%ED%95%98%EA%B8%B0-e11e18fcf843* <br>
*https://www.nginx.com/resources/glossary/reverse-proxy-server* <br>
*https://www.nginx.com/resources/glossary/reverse-proxy-vs-load-balancer/* <br>
*https://www.quora.com/Why-is-NGINX-the-most-used-reverse-proxy-in-the-industry* <br>
*https://www.nginx.com/resources/glossary/load-balancing/*

<br><br><br><br>

