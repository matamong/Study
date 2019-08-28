# 노드 ID/Class를 찾아보자!!
## Document.getElementById() / Document.getElementsByClassName()

**input** : 문자열 (id속성/class속성) <br>
**return** : 객체/유사객체 (id Element/class속성) <br>
<br>

- 찾고싶은 id/class 요소를 매개변수로 넣는다. 
- 그와 일치하는 DOM요소를 나타내는 객체/유사객체를 반환해준다.<br>
- id,class에 접근 할 때 꼭 써야하는 메서드
<br>
<br>
<br>

# 노드를 생성해보자!!
## Document.createElement()

**input** : 문자열 (tagName요소) <br>
**return** : tagName 요소 <br>
<br>

- `JavaScript`에서 직접 요소를 만들 때 사용되는 메서드 <br>
<br>

# 생성한 노드를 붙여보자!!
## Node.appendChild()

**input** : 붙일노드 <br>

<br>

- 한 노드를 부모 노드의 자식 노드 중 가장 마지막 자식으로 붙인다.
> cf) `Node.appendChild()` 메서드 외에 노드 붙이는 메서드
>- `Node.prepend()` : 부모 노드의 자식 노드 중 가장 앞에 붙는다.
>- `Node.before()` : 특정 노드 앞에 노드를 붙인다.
>- `Node.after()` : 특정 노드 뒤에 노드를 붙인다
<br>
<br>




# 노드를 찾고 만들고 붙여보자 <br>
ex) 동물 추가해보기 <br><br>



```html
<html>
<head>
  <title> Three Fruit & Three Animal</title>
</head>
<body>
  <div id="Fruit"> 
    <div> Apple </div>
    <div> Peach </div>
    <div> Orange </div>
  </div>
  <div id="Animal">
    <div> Dog </div>
    <div> Cat </div>
  </div>
</body>
</html>
```
위의 HTML 코드는 div에 id를 각각 과일과 동물로 부여하고 <br>
id의 이름과 어울리는 자식 div를 세개씩 만들어 보려고 한다. <br>
하지만 무슨 이유에서인지 id동물의 자식 div는 두개밖에 없다.<br>
`JavaScript`로 후다닥 추가해보자.<br>
- 어떻게 Animal에 접근할 수 있을까?
- 어떻게 div를 만들 수 있을까?
- 만든 div를 어떻게 붙일 수 있을까?
<br><br>

```js
function addHamster() {
    var animal = document.getElementById("Animal"); // Animal 요소를 넣으면 요소를 담은 객체를 반환해준다.

    var hamster = document.createElement("div"); //div를 만들어 hamster변수에 넣어준다.

    hamster.innerHTML = "Hamster"; //hamster(div)에 text를 입력한다.

    animal.appendChild(hamster);  //hamster(div)를 가져왔던 animal객체에 붙여준다.

}
```
위 형식대로 짠 코드를 실행시켜보자!콘솔창도 잊지말고 챙겨보기!<br>

[적용해보러가기] (https://matamong.github.io/matamongWorld/)






