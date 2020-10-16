# list []
- Mutable 
	- 안의 요소들을 자유롭게 변경가능
- 동적배열(Dynamic Array)
	- 자유롭게 확장가능

## append()
```python
>>> word_list = ['a', 'b', 'c']
>>> word_list.append('d')
>>> word_list
['a', 'b', 'c', 'd']
```

## sort()
```python
>>> name_list = ['나', '다', '바', '가']
>>> name_list.sort()
>>> name_list
['가', '나', '다', '바']
```

## count()
```python
>>> name_list
['가', '나', '다', '바']
>>> name_list.count('나')
1
>>> 
```

## len()
```python
>>> index_list = [0, 1, 2, 3]
>>> len(index_list)
4
```

# tuple ()
Immutable <br>
() 안 써도 자동으로 tuple 이 됨

## Packing & Unpacking
### Packing
```python
>>> my_tuple = (1, 2, 3)
```
### Unpacking
```python
>>> my_tuple = (1, 2, 3)
>>> num1, num2, num3 = my_tuple
>>> num1
1
>>> num2
2
>>> num3
3
>>> 

# num2와 num1의 값을 Unpacking 형태로 바꿈
>>> num1, num2 = num2, num1
>>> num1
2
>>> num2
1
```
# dictionary {:}
key & Value
```python
>>> my_dict
{}
>>> my_dict[0] = 'a'
>>> my_dict
{0: 'a'}
>>> my_dict['a'] = 'abcd'
>>> my_dict
{0: 'a', 'a': 'abcd'}
>>> 
```
## 모듈
`keys()` , `values()`, `items()(key,와 value 모두)`

# set {}
mutable <br>
중복이 없는 요소들로만 구성된 집합 컬렉션. <br>
순서대로 저장이 안되기 때문에 순서관련 기능들은 쓸 수 없음
```python
#set 정의
>>> my_set = {3, 3, 4, 5, 6}
>>> print(my_set)
{3, 4, 5, 6}

# 리스트를 set으로 변환
>>> my_list = [3, 3, 4, 5, 6]
>>> list_to_set = set(my_list)
>>> print(list_to_set)
{3, 4, 5, 6}

```

## add()
set에 새로운 요소 `하나` 추가
## update()
set에 `여러개`의 요소들을 한꺼번에 추가
```python
>>> my_set = {1}
>>> my_set.update({2, 3, 4})
```
## remove()
set에 `하나의` 요소 삭제

## clear()
set 전체의 요소를 삭제

# String (Immutable)
## ''' Or """
여러줄(\n) 까지 들어갈 수 있음 <br>
자체적으로 쓰면 주석이 될 수 있음
## Formating
- %s
```python
# C Type
>>> my_str = 'Hello %s' % 'World!'
>>> 'Hello World!'

# Formating method Type
>>> my_str = 'Hello {}'.format('World!')
>>> 'Hello World!'
```
- %d
```python
# C Type
>>> my_int = '%d %d' % (1, 2)
>>> '1 2'

# Formating method Type
>>> my_int = '{} {}'.format(1, 2)
>>> '1 2'
```
## Indexing
음수 인덱싱 가능
```python
# 마지막부터 -1, -2, -3...
>>> my_str = 'Hello World!'
>>> my_str[-1]
>>> '!'
```
## slice()
```python
>>> my_str = 'Hello World!'
>>> my_str[1:2]
>>> 'e'

# 앞 혹은 뒤 값을 생략 가능
>>> my_str = 'Hello World!'
>>> my_str[:2]
>>> 'He'
```

## split()
```python
>>> words = 'a s d f d'
>>> word = words.split()
>>> word
['a', 's', 'd', 'f', 'd']
>>> 
```

## Print End
```python
# 마지막에 엔터를 없애거나 다른 것을 추가할 수 있음
>>> print('엔터를 없애줘', end='')
엔터를 없애줘
```
## 연산가능
`+`, `*` 연산 가능

# For
파이썬은 반복문을 `코드블럭`을 `:`,`띄어쓰기` 로 구분한다. <br>
`띄어쓰기`는 4칸 띄어쓰기가 권장됨(탭 ㄴㄴ)
```python
>>> for 변수 in 컨테이너:
    print(변수)
```

# range()
```python
>>> for n in range(0, 3):
	print(n)

0
1
2
```

# If

## elif
파이썬의 `else if` 

# List Comprehension
`for` 과 `if`를 한번에 실행 <br>
[표현식 for 요소 in 컬렉션[if 조건식]]
```python
>>> numbers = [1,2,3,4,5,6,7,8,9,10]
>>> odd_numbers = []
>>> for number in numbers:
	if number % 2 == 1:
		odd_numbers.append(number)

		
>>> print(odd_numbers)
[1, 3, 5, 7, 9]
```
이것을
``` python
>>> odd_numbers = [number for number in numbers if number % 2 ==1]
>>> print(odd_numbers)
[1, 3, 5, 7, 9]
```

# Membership
```python
>>> my_list = [1, 2, 3, 4, 5]

>>> 6 in my_list
False
>>> 6 not in my_list
True
```

# Function
- `def`로 정의 <br>
- `return`은 있어도 되고 없어도 된다.
- `return`의 값은 여러개일 수 있다. 단, 여러개의 값은 `tuple`이 된다. (동시에 패킹, 언패킹 가능)
```python
# return은 여러개일 수 있다.
>>> def add_mul(num1, num2):
	return num1 + num2, num1 * num2

>>> add_mul(1,2)
(3, 2)

# 동시에 패킹, 언패킹 가능
>>> my_add, my_mul = add_mul(1, 2)
>>> my_add
3
>>> my_mul
2
```

# Class
대문자로 시작
```python
class Snake:
```

<br><br>

## 생성자(Initializer)
**__init__**
```python
class Snake:
  def __init__(self, color, name):
    self.color = color
	self.name = name

```
<br><br>


## 클래스 변수
```python
class Snake:

  species = snake # 클래스 변수

  def __init__(self, color, name):
    self.color = color
	self.name = name
```
<br><br>


## 메서드
- 메서드의 첫 번재 인자는 항상 `self`
	- `self`: 클래스의 **인턴스 변수 그 자체의 의미**. 파이썬은 자바와 달리 클래스 내부 변수를 미리 선언하지 않고 사용하기 때문에 self로 커버.
	- Python은 자동으로 메서드에 `self`를 전달한다. 그러므로 self없으면 에러.

<br><br>

## 접근제한
- 파이썬은 접근제한자가 없음(모두 Public)
	- 내부적으로만 사용하는 변수 혹은 메서드는 이름 앞에 **`_`**
	- private하게 만들고 싶으면 **`__`**

<br><br>

## 상속
- 생성자를 따로 정의할 필요 없음
- 생성자를 호출 한뒤 추가 가능
- 다중상속 허용
```python
class Animal:
  def __init__(self, color, name):
    self.color = color
	self.name = name

  def speak(self):
    pass

class Snake(Animal, 다른 클래스, 또 다른 클래스):
  def speak(self):
    print("shhhhhh")

    
```
<br><br>

## 예외

### 예제
```python

# 예외처리
try:
  문장1
  문장2
except Exception as err:
  print(str(err))
finally:
  마지막에 항상 수행

# 예외처리를 똑같이 다룰 때
try:
  문장1
  문장2
except (IndexError, ValueError):
  print("Error")
finally:
  마지막에 항상 수행
```

<br>

### Pass & Raise
- `pass`: 에러를 무시
- `raise`: 개발자가 정의한 에러를 발생시킴
```python
# pass
try:
   check()
except FileExistsError:
    pass
 
# raise
if 1 < 0:
    raise Exception('Error')
```
<br>

### with
- `with` 블럭이 끝날 때 자동으로 리소스를 해제
- `with` 블럭 내에서는 어떤 예외가 일어나도 반드시 리소스를 해제해준다.
```python
# 전형적인 파일 입출력 에러처리
try:
   fp = open("test.txt", "r")
   try:
      lines = fp.readlines()
      print(lines)
   finally:
      fp.close()
except IOError:
   print('파일에러')

# with을 이용한 파일 입출력 에러처리
with open('test.txt', 'r') as fp:
    lines = fp.readlines()
    print(lines)
```
<br><br>

## `if __name__ == "__main__":`
- python에는 main함수가 없다. 그렇다면 어떻게 실행기능을 사용할 수 있을까?
- python은 직접 실행한 파일은 `__name__` 이라는 내장변수에 `__main__` 이라는 값을 넣어준다. (나머지는 모듈 이름이 들어감)
- 이를 이용하여 파일이 실행다면 `__main__값이 __name__에 들어간 것`이기 때문에 그것을 확인하여 main 관련 기능을 실행시켜준다.


# Unit Test
기본적으로 ` unittest 모듈 (Unit testing framework)`을 사용할 수 있음
## 예제
```python
# myCalc.py
def add(a, b):
    return a + b
 
def substract(a, b):
    return a - b
```
```python
# tests.py
import unittest
import myCalc
 
class MyCalcTest(unittest.TestCase):
 
    def test_add(self):
        c = myCalc.add(20, 10)
        self.assertEqual(c, 30)
 
    def test_substract(self):
        c = myCalc.substract(20, 10)
        self.assertEqual(c, 10)
 
if __name__ == '__main__':
    unittest.main()
```

## Fixture
테스트 사전작업준비
### 예제
아래의 setUp, tearDown은 각각의 테스트 메서드가 실행될 때 전,후로 작업을 도와준다.
```python
import unittest
import os
import myUtil
 
class MyUtilTest(unittest.TestCase):
    testfile = 'test.txt'
 
    # Fixture
    def setUp(self):
        f = open(MyUtilTest.testfile, 'w')
        f.write('1234567890')
        f.close()
 
    def tearDown(self):
        try:
            os.remove(MyUtilTest.testfile)
        except:
            pass
 
    def test_filelen(self):
        leng = myUtil.filelen(MyUtilTest.testfile)
        self.assertEqual(leng, 10)
 
    def test_count_in_file(self):
        cnt = myUtil.count_in_file(MyUtilTest.testfile, '0')
        self.assertEqual(cnt, 1)
 
if __name__ == '__main__':
    unittest.main()
```
<br><br>

# Iterator

### Iterable
- 반복적으로 데이터를 하나씩 처리할 수 있으면 `Iterable`이라 부른다.
  - `ex : ` list, dict, set, tuple, str ...

### Iterator 객체 생성
- iterable한 객체를 내장함수나 메소드를 이용하여 생성
  - 내장함수 : `iter()`을 사용하여 iterator객체를 사용할 수 있다.
  
```python
# 리스트를 파이썬 내장함수 iter()을 이용하여 iterator 객체로 만듦
>>> my_list = [1, 2, 3]
>>> my_list_iter = iter(my_list)
>>> type(my_list_iter)
<class 'list_iterator'>
```
  - 메소드: iterable객체의 매직메소드인 `__iter__`을 이용하여 사용할 수 있다.

```python
# 리스트를 iterable객체의 매직메소드인 __iter__ 메소드를 이용하여 iterator 객체로 만듦
>>> my_list = {1, 2, 3}
>>> my_list_iter = my_list.__iter__()
>>> type(my_list_iter)
<class 'set_iterator'>
```
<br>

### Iterator의 Next
Iterator 객체의 다음 값을 간편하게 알려면 내장함수나 메직메소드를 사용할 수 있다.
- 내장함수
```python
>>> next(a_iter)
1
>>> next(a_iter)
2
>>> next(a_iter)
3
>>> next(a_iter)
```
- 매직 메소드
```python
>>> b_iter.__next__()
1
>>> b_iter.__next__()
2
>>> b_iter.__next__()
3
>>> 
```


# Generator
Iterator를 생성해주는 함수
- 함수 안에 `yield` 키워드를 사용하여 데이터를 하나씩 리턴한다.
- 무한한 순서가 있는 객체를 모델링할 수 있다.

# 예제
## generator 생성
```python
>>> def test_generator():
...     yield 1
...     yield 2
...     yield 3
... 
>>> gen = test_generator()
>>> type(gen)
<class 'generator'>
>>> next(gen)
1
>>> next(gen)
2
>>> next(gen)
3
>>> next(gen)
```

## 무한한 generator
```python
>>> def infinite_generator():
...     count = 0
...     while True:
...             count+=1
...             yield count
... 
>>> gen = infinite_generator()
>>> next(gen)
1
>>> next(gen)
2
>>> next(gen)
3
>>> next(gen)
4
>>> next(gen)
5
>>> next(gen)
6
>>> next(gen)
7
>>> next(gen)
8
>>> next(gen)
9
>>> next(gen)
10
>>> next(gen)
11
>>> next(gen)
12
... 계속
```

## yield from iterable
- `yield`는 한번씩만 값을 내보내서 여러번 전달하려면 for문을 사용해야했다.
- 이것을 대신해주는 것이 `yield from iterable`

```python
>>> def three_generator():
...     a = [1, 2, 3]
...     yield from a
... 
>>> gen = three_generator()
>>> list(gen)
[1, 2, 3]
```
