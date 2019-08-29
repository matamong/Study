# 이벤트를 추가하자



<br>
오늘은 이벤트를 추가하는 법을 알아보았다. 자, 버튼을 만들고 이벤트를 추가해보자.<br>

```html
<html>
  <head>
    <title>Event And Remove</title>
  </head>
  <body>
    <!-- 이곳에 js로 버튼을 넣어보자 -->
  </body>
</html>
```

```js
//버튼노드를 생성한다.
var btn = document.createElement('button');
    
//text를 생성한다.
var btnText = document.createTextNode('Click Me!'); 

//버튼에 text를 넣는다.
btn.appendChild(btnText);

//body의 하위노드로 버튼을 붙인다.
document.body.appendChild(btn);

//버튼에 id를 부여한다.
btn.id = 'btn';
    
```

일단 간단한 `html`을 만들고 `JavaScript`로  `btn`을 생성해주었다.<br>
- 선수학습<br>
[CreateElement, Append](https://github.com/matamong/Study/blob/master/TIL/Web/JavaScript/DOM/FindCreateAppend.md) <br>
<br><br>

# 어떻게 이벤트를 추가할까?

## EventTarget.addEventListener() <br>
`EventTarget`에 지정된 타입의 이벤트가 발생했을 때,<br> 
알림(Event 인터페이스를 구현하는 객체)을 받는 객체이다. 버튼에 적용해보자.

```js
var b = document.getElementById('btn');
b.addEventListener('click', function(event){
  alert('Hello world! '+event.target.value);
});


```
[test 해보러 가기!] (추가준비중)



