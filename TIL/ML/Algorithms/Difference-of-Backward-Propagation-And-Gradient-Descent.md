# 역전파와 경사하강법의 차이점
## 역전파와 경사하강법은 같은 것인가?

가중치와 편향을 업데이트하기 위해서 사용한다는 것이 비슷해서 헷갈리는 역전파와 경사하강법. <br>
과연 같은 것인지 정리해보자.

**경사하강법** : 비용함수가 최소가 되도록 가중치와 편향을 찾는 최적화 알고리즘. <br>

**역전파** : 신경망의 기울기를 계산하는 알고리즘.

**경사하강법은** 비용함수가 최소가 되도록 하는 가중치와 편향을 찾기위해 **역전파**(신경망 기울기 계산) **를 사용할 수 있다.**

<br> <br>

![Imgur](https://i.imgur.com/FtaIb7k.jpg)

**`결론` : 똑같지 않으며 경사하강법이 역전파를 이용하는 관계다.**

* * * 

**참고** <br>

[What is the difference between gradient descent and back propagation in deep learning? Are they not the same thing?](https://www.quora.com/What-is-the-difference-between-gradient-descent-and-back-propagation-in-deep-learning-Are-they-not-the-same-thing)