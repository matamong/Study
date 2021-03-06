# 버블정렬(Bubble Sort)
선택하여 정렬하는 알고리즘

# 버블정렬의 원리
버블정렬은
 - 당장 옆에 있는 값과 비교
 - 더 작은 값을 앞으로 보냄
위의 두 가지를 반복하는 정렬이다.

# 설명
```java
(1 10) 5 8 7 6 4 3 2 9 

// 첫번째 비교: 1과 10을 비교하여 작은 값을 앞으로 (작은 값: 1)

1 (10 5) 8 7 6 4 3 2 9 

// 두번째 비교: 10과 5를 비교하여 작은 값을 앞으로 (작은 값: 5)


1 5 (10 8) 7 6 4 3 2 9

// 세번째 비교: 10과 8을 비교하여 작은 값을 앞으로 (작은 값: 8)

// . . .

// 결과적으로 가장 큰 값이 맨 뒤로 이동한다.

1 5 8 7 6 4 3 2 9 (10)

// 이것을 반복한다. 이 때, 비교되어 옮겨진 큰 값은 제외(맨 마지막)

```
```java

public static void main(String[] args){
    int[] list = [1, 10, 5, 8, 7, 6, 4, 3, 2, 9]
    int temp;
    for(int i=0; i < list.length; i++) {
        for(int j=0; j < list.length-1-i; j++) {
            if(list[j] > list[j+1]) {
                temp = list[j];
                list[j] = list[j+1];
                list[j+1] = temp;
            }
        }
    }
}

```

# 시간복잡도
`O(n²)` <br>
반복문이 두번이나 돌고 안에서 연산을 많이 하기 때문에 <br>
선택정렬보다 느리므로 정렬 알고리즘 중에 가장 느리다.

# 특징
구현이 쉽고 직관적이지만 정렬 알고리즘 중에서 가장 비효율적이다.