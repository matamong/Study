# 선택정렬(Selection Sort)
선택하여 정렬하는 알고리즘

# 무엇을 선택하는가
## 최소값 선택 정렬 (오름차순)
1. 정렬되지 않은 숫자 중에서 **가장 작은 숫자를 선택한다**.
2. 선택한 숫자를 정렬되지 않은 숫자들 중 **첫 번째 숫자**와 자리를 바꾸면 선택된 숫자는 정렬된 것이다.
3. 모든 숫자를 옮길 때까지 1-2번 과정을 반복한다.


## 최대값 선택 정렬 (내림차순)
1. 정렬되지 않은 숫자 중에서 **가장 큰 숫자를 선택한다**.
2. 선택한 숫자를 정렬되지 않은 숫자들 중 **첫 번째 숫자**와 자리를 바꾸면 선택된 숫자는 정렬된 것이다.
3. 모든 숫자를 옮길 때까지 1-2번 과정을 반복한다. 

# 시간 복잡도
## `O(n²)` 

### 설명

- 크고작음을 비교하는 함수의 횟수를 구해보자.(비교를 몇 번 하느냐를 보자.)
    - n개의 수가 있을 때는 (n-1)까지 비교한다. 
    - (n-1)개의 수가 있을 때는 (n-2)까지 비교한다.
    - (n-2)개의 수가 있을 때는 (n-3)까지 비교한다.
    - ... 
    - 즉, n부터 (n-1)의 합까지 비교한다.
    - `n(n-1)/2` = `(n²-1)/2` = 사실상 `n²`의 존재감이 제일 크다.


# 코드
## JAVA
```java
public class Selection {
    
    public void sort(int[] data){
        int size = data.length;
        int min; 
        int temp;
        
        for(int i=0; i<size-1; i++){ 
            min = i;
            for(int j=i+1; j<size; j++){
                if(data[min] > data[j]){
                    min = j;
                }
            }
            temp = data[min];
            data[min] = data[i];
            data[i] = temp;
        }
    }

    public static void main(String[] args) {
            
        Selection selection = new Selection();
        
        int data[] = {66, 10, 1, 99, 5};
 
        selection.sort(data);
 
        for(int i=0; i<data.length; i++){
            System.out.println("data["+i+"] : " + data[i]);
        }
    }
}
```
<br><br><br>

# 특징
최선 최악의 경우가 없으며 O(n²)으로 비효율적인 알고리즘이다.


<br><br><br>

* * *
### 참고
[T아카데미 컴퓨터 알고리즘 기초](https://youtu.be/x2nOeHlIbNc) <br>
[동빈나 실전 알고리즘 강좌](https://youtu.be/8ZiSzteFRYc)