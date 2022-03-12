# [인터넷의 재료 2탄] **IP위에서 돌아가는 TCP/UDP 프로토콜**



**[인터넷의 재료 1탄: 네트워크, IP란?](https://matamong.tistory.com/entry/%EC%9D%B8%ED%84%B0%EB%84%B7%EC%9D%98-%EC%9E%AC%EB%A3%8C-1%ED%83%84-%EB%84%A4%ED%8A%B8%EC%9B%8C%ED%81%AC-IP%EB%9E%80)**

<br>
저번 시간에서는 인터넷의 정의와 네트워크, 컴퓨터와 컴퓨터가 데이터 통신을 하기 위해 만들어둔 프로토콜에 대하여 살펴보았었다.
<br>
가장 중요했던 것은 장치를 고유하게 식별할 수 있게 주소를 사용하며, 보내려는 메시지를 패킷으로 잘게 나누어 목적지에 잘 도착하도록 하는 인터넷 프로토콜에 관련된 것들이었다.
그리고 끝에서 이런 얘기를 했었다. 이러한 IP가 패킷의 모든 결과를 처리해주지 않는다고 말이다. <br>
보내려는 패킷이 오염되고, 손실되고, 순서없이 도착했을 경우 IP가 해줄 수 있는 일은 없기때문에 IP 위에서 더 높은 수준의 프로토콜을 사용해아한다. 그 프로토콜들을 이제부터 알아보자. 

<br><br><br>

# **UDP (User Datagram Protocol)**
UDP는 IP 위에서 사용할 수 있는 경량화된 데이터 이동 프로토콜이며 **패킷들 중 오염된 데이터를 감지하는 기능을 제공한다.** 하지만 아쉽게도 오염된 데이터를 감지하는 기능 이외의 패킷문제들은 무시해버리는 저돌적인 프로토콜이다. 심지어 상대 디바이스가 데이터를 잘 받았는지 확인조차 하지않는다. 그래서 UDP는 *Unreliable Data Protocol(믿을 수 없는 데이터 프로토콜)* 이라고 불리기도 한다. <br>
그럼에도 UDP를 쓰는 이유는 **단순하지만 빠르기 때문이다.** 이런 이유로 UDP는 정확성보다는 속도가 더 중요한 실시간 비디오 스트리밍이나 온라인 게임, DNS 같은 곳에서 잘 쓰이고 있다. <br>

<br>

## **Packet format**

![https://cdn.kastatic.org/ka-perseus-images/9d185d3d44c7ef1e2cd61655e47befb4d383e907.svg](https://cdn.kastatic.org/ka-perseus-images/9d185d3d44c7ef1e2cd61655e47befb4d383e907.svg)

- IP위에서 UDP를 사용하여 패킷을 전송할 경우, 각 IP 패킷의 데이터 부분형식은 UDP 세그먼트로 지정된다. <br>
- 각각의 UDP 세그먼트는 8-byte 헤더와 다양한 길이의 데이터로 이루어져있다.

<br>

이 UDP 세그먼트의 헤더는 다음과 같이 이루어져있다.

- ### **Port numbers**
    - UDP 세그먼트 헤더의 첫 4byte는 소스와 목적지를 위해 포트 넘버를 저장해둔다. 
    - 이것으로 네트워크 장치는 다른 가상 포트들에서 메시지들을 수신할 수 있다. 서로 다른 포트는 서로 다른 유형의 네트워크 트래픽을 구별하는 데 도움을 준다.
- ### **Segment Length**
    - 포트 넘버 다음의 2byte는 세그먼트의 길이를 저장해둔다. (이 때 헤더의 길이도 포함)
- ### **Checksum**
    - 마지막 2byte는 checksum으로 이것은 데이터 손상을 확인하기 위해 발신자와 수신자가 사용하는 필드이다.
    - 발신자가 보내는 데이터의 byte를 이용하여 checksum을 계산한 뒤 데이터와 함께 보내면 수신자는 발신자가 보낸 checksum과 받은 데이터를 통해 직접 계산한 checksum을 비교한다. 여기서 데이터의 오염을 감지할 수 있는 것이다.


<br><br><br>

# **TCP (Transmission Control Protocol, 전송제어 프로토콜)**
TCP는 **패킷의 안정적인 전송을 보장**하기 위해 IP 위에서 사용되는 전송 프로토콜이다. <br>
안정성을 위해 TCP는 패킷손실이나 중복패킷, 오염패킷등등의 문제를 해결하기위한 매커니즘을 가지고있다. IP위에서 사용되는 프로토콜 중 가장 대중적이기 때문에 TCP/IP라고도 불린다.

<br>

## **Packet format**

![https://cdn.kastatic.org/ka-perseus-images/e5fdf560fdb40a1c0b3c3ce96f570e5f00fff161.svg](https://cdn.kastatic.org/ka-perseus-images/e5fdf560fdb40a1c0b3c3ce96f570e5f00fff161.svg)

- TCP/IP를 사용하여 패킷들을 전송할 때, 각 IP 패킷의 데이터 부분형식은 UDP 세그먼트로 지정된다. <br>
- 각각의 TCP 세그먼트들은 헤더와 데이터를 가지고 있다.
- TCP헤더는 UDP헤더보다 더 많은 필드를 가지고 있으며 헤더의 사이즈를 20~60byte까지 조절할 수 있다.
- TCP 헤더는 앞서 UDP에서 봤던 소스포트넘버, 도착포트넘버, checksum같은 필드들을 사용하고있다.

<br>

## **Process**
TCP/IP로 패킷을 전송하는 과정을 차근차근 알아보자. <br>

- ### **Step 1: 연결 설정**
    - 두 컴퓨터가 TCP를 이용하여 데이터를 각각 서로 보내려고 할 때, **먼저 3방향 핸드쉐이크(three-way handshake)를 사용하여 연결을 설정**해야한다.
        - 첫번째 컴퓨터가 TCP 헤더에 있는 **SYN bit**이 1로 설정된 패킷을 보낸다. (SYN은 synchronize를 뜻한다.)
        - 두번째 컴퓨터는 SYN bitd 1로 설정된 패킷과 **ACK bit**이 1로 설정된 패킷을 다시 돌려보낸다. (ACK는 acknowledge를 뜻한다.)
        - 첫번째 컴퓨터는 다시 ACK를 설정해서 돌려보낸다.
        - 이것이 3방향 핸드쉐이크(three-way handshake) 이다.
        - 패킷에 데이터는 포함하지않고 오로지 헤더로 상호작용한다.
    - 3방향 핸드쉐이크가 끝나면 비로서 두 컴퓨터는 패킷들을 받을 수 있다.
- ### **Step 2: 데이터의 패킷들을 전송**
    - 첫번째 컴퓨터가 **시퀀스넘버와 데이터가 포함된 패킷을 전송**한다.
    - 두번째 컴퓨터는 **헤더를 통해 ACK bit를 세팅하고 수신된 데이터의 길이만큼 승인 번호(acknowledgement number)를 증가시킴**으로써 이를 승인한다.
        - TCP를 통해 데이터 패킷을 전송할 때 받는 사람은 항상 받은 내용을 확인해야하기때문이다.
        - ACK bit과 승인번호를 통해 데이터가 성공적으로 수신되었는지, 어떤 데이터가 손실되었는지 등등을 추적할 수 있게해준다.
- ### **Step 3: 연결 종료**
    - 연결을 종료하고싶은 컴퓨터가 **FIN bit**를 1로 설정한 패킷을 전송한다. (FIN은 finish를 뜻한다.)
    - 상대 컴퓨터는 **ACK와 FIN**으로 답해준다.
    - 연결을 종료하고싶은 컴퓨터가 ACK를 보내면 연결이 종료된다.

정말 영국신사같은 상호작용이 아닐 수 없다. 신중하며 안전하게 대화를 이끌어나간다. 이렇게 계속해서 연결을 열고, 유지하고, 종료하는 과정이 있기 때문에 TCP를 **연결지향적** 프로토콜이라고 한다.

<br><br>

## **Handling Packets**
IP위에서 TCP는 패킷에 문제가 생겼을 때 그것을 해결할 수 있다. 어떤 문제를 어떻게 해결하는지 한 번 알아보자.

<br>

### **Lost packets**
![https://cdn.kastatic.org/ka-perseus-images/b1017461d232cd46fa5b445f80e75568bf31c57c.svg](https://cdn.kastatic.org/ka-perseus-images/b1017461d232cd46fa5b445f80e75568bf31c57c.svg)

TCP연결은 **`timeout`을 이용하여 손실패킷들을 찾아낼 수 있다.** 어떻게 가능한지 timeout을 이용한 과정을 살펴보자.<br>
- 패킷을 전송한 뒤 발신자는 **타이머를 켜고 패킷을 재전송 대기열(retransmission queue)에 넣는다.**
- 만약에 타이머가 다되었음에도 발신자가 수신자로부터 **ACK를 받지 못했다면 패킷을 다시 보낸다.**

이렇게 timeout이 된다면 재전송 대기열에 있던 패킷을 다시 전송함으로써 패킷 손실을 막는 것이다. 물론 timeout되었다고해서 패킷이 꼭 손실된 것만은 아닐 것이다. 도착하는 시간이나 승인하는 시간이 너무 오래 걸렸지 받을 수 있는 환경일 수 있기때문이다. <br>
그러나 손실보다는 더 있는게 낫기 때문에 이 방식을 사용한다. *(내 경우에도 어딘가에서 뭘 주문했을 때 상품이 없는 것 보다는 상품 하나를 더 주는게 훨씬 나았다. 껄껄)*

<br>

### **Out of order packets**
![https://cdn.kastatic.org/ka-perseus-images/27f4fa1915c98689623e0ee224416c5290afc65a.svg](https://cdn.kastatic.org/ka-perseus-images/27f4fa1915c98689623e0ee224416c5290afc65a.svg)

TCP연결은 **`시퀀스`와 `승인번호`를 이용하여 순서가 잘못된 패킷을 찾아낼 수 있다.** 수신자가 지금까지 확인한 것 보다 더 높은 시퀀스 번호를 보면 그 사이에 적어도 하나의 패킷이 누락되었음을 할 수 있기 때문이다. <br>
해당 패킷이 느리게 수신되었던지 누락되어 다시 보내졌던지간에 일단 순서가 잘못된 패킷에 대해서는 수신자가 처리해야한다. 다행히도 수신자는 시퀀스 번호를 사용해서 순서를 재조립할 수 있다.

<br>

### **Corrupted packets**
UDP에서 봤던 checksum계산을 통하여 데이터 오염을 확인할 수 있다.


<br><br>

* * *
참조 <br>
https://www.khanacademy.org/computing/computers-and-internet/xcae6f4a7ff015e7d:the-internet <br>
https://ko.wikipedia.org/wiki/%EC%82%AC%EC%9A%A9%EC%9E%90_%EB%8D%B0%EC%9D%B4%ED%84%B0%EA%B7%B8%EB%9E%A8_%ED%94%84%EB%A1%9C%ED%86%A0%EC%BD%9C <br>
https://www.geeksforgeeks.org/differences-between-tcp-and-udp/ <br>
https://www.guru99.com/tcp-vs-udp-understanding-the-difference.html <br>