# 로지스틱 회귀와 신경망의 차이

로지스틱 회귀는 히든레이어가 없는 신경망의 특수한 경우라고 볼 수 있다. <br>

<br>


로지스틱 회귀는 선형 모델에 주로 사용하는데, 선형모델이 아닌 복잡한 비선형 관계에서는 사용하기 어려울 정도로 퍼포먼스가 나지않는다. 그에 반해 신경망은 히든레이어를 구축하고 점점 더 복잡한 관계를 포착할 수 있기 때문에 퍼포먼스가 더 좋다. <br>

![](https://i.stack.imgur.com/UWMVt.png) <br>
*비선형관계에서 Logistic Regression의 decision boundaries. 죽 그어진 선 형태라 위와같이 비선형분포에서 정확도가 떨어질 수 밖에 없다.*

<br>

![](https://i.stack.imgur.com/nEtI6.png) <br>
*비선형관계에서 은닉층을 4개 둔 신경망의 decision boundaries.*

* * * 
**참조** <br>
[Quora: What is the difference between neural network and logistic regression?](https://www.quora.com/What-is-the-difference-between-neural-network-and-logistic-regression)

[StackExchange : what is the difference between logistic regression and neural networks](https://stats.stackexchange.com/questions/43538/what-is-the-difference-between-logistic-regression-and-neural-networks)