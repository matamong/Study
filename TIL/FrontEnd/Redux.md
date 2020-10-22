# Redux

Redux란 상태관리를 해주는 저장소이다.
<br><br>

## 왜 필요한데?
React에서는 데이터가 수직으로만 내려가기때문에 필요한 데이터를 얻기 위해서는 상위로 계속 올라가야한다. <br>
이런 경우, 앱이 확장될수록 어느 상위에 어떤 데이터가 있는지 알지 못하는 상황이 오게되고<br>
이걸 피하려다 결국 구조의 최상위이인 `App`에 데이터를 몰아넣어버려 지저분해지고 관리하기 어려워진다. <br>
이걸 해결하기 위하여 데이터를 별도의 공간에 넣어버린 것이 `Redux`이다.

<br>

## Redux의 구성

### Store
전역화된 스토어

### Action
이름으로 보면 뭔가를 하는 것처럼 보이지만 그런건 아니고 **무언가를 할거라는 것을 리덕스에게 알려주는 것.** <br>
모든 action은 그저 오브젝트를 리턴하는 function이며 실제로 실행하지는 않는다.

### Reducer
`action`을 토대로 `store`에 변화를 주는 것

### Dispatch
`action`을 실제로 실행시켜주는 것 <br>

<br><br>

## Redux가 돌아가는 형식
Dispatch -> Action -> Reducer -> Store

<br><br>

## 설치법
- 리액트 앱을 설치한다.
```npm
npx react-create app (프로젝트명)
```
- `리덕스`와 리액트와 리덕스를 이어주는 `리액트-리덕스`를 설치한다.
```npm
npm install redux react-redux
```

<br><br>

## Redux 사용 예 1 (Redux를 이용하여 간단한 숫자 Increment/Decrement 기능 만들기)
<br>


```js
// ./src/index.js
import { createStore } from 'redux';
```
- 전역 `store`를 만든다.

<br><br>


```js
// ./src/index.js
const increment = () => {
    return {
        type: 'INCREMENT'
    }
}
const decrement = () => {
    return {
        type: 'DECREMENT'
    }
}
```
- `action`을 정해준다
    - `action`은 앞에서 말했듯, 어떤 행동을 할 것이라는 것을 그저 설명한 단순한 함수이다.

<br><br>

```js
// ./src/index.js
const counder = (state = 0;) => {
    switch(action.type){
        case "INCREMENT":
            return state + 1 
        case "DECREMENT":
            return state - 1
    }

}
```
- `reducer`를 만든다.
    - 인자로 `state`를 정해준다.
    - `reducer`는 `action`을 토대로 무엇을 할 것인가를 정해준다.
    - `action`

<br><br>


```js
// ./src/index.js
let store = createStore(counter);
```
- `store`를 생성해준다.
    - 생성해줄 때, 카운터를 인자로 넣어준다.

<br><br>

```js
// ./src/index.js
store.dispatch(increment());
```
- 마지막으로 모든 과정을 실행시켜 줄 `dispatch`를 만든다.
    - 인자로 원하는 `action`을 넣어준다.
    - 지금은 **increment** `action`을 넣어주자.

<br><br>


```js
// ./src/index.js
store.subscribe(() => console.log(store.getState()));
```
- 결과를 확인할 수 있게 store의 상황을 console.log로 찍어보자.

<br><br>


- 실행시키면 `dispatch`가 과정을 실행시켜, `action`을 토대로 `reducer`가 실행되고 `store`가 변한다.
    - 즉, 콘솔창에 1이 찍혀있을 것이다.

<br><br>

## Redux를 React에서 어떻게 사용하는가 (흩어져있는 Action/Reducer 사용)
<br>
Counter기능과 로그인유무확인 기능을 예시로 확장된 리액트 구조 속에서 Redux를 어떻게 사용하는지 알아보자. <br><br>
<br>

```js
./src/actions
./src/reducers
```
- `actions`/`reducers` 폴더를 생성한다.

<br><br>

```js
./src/reducers/index.js
```
- reducer에 index.js를 생성한다.

<br><br>
```js
./src/reducers/counter.js
./src/reducers/isLogged.js
```

```js
// ./src/reducers/counter.js
const counterReducer = (state=0, action) => {
    switch(action.type){
        case 'INCREMENT':
            return state + 1
        case 'DECREMENT:
            return state - 1
        default:
            return state;
    }
}

export default counterReducer;
```
```js
// ./src/reducers/isLogged.js
const loggedReducer = (state = false , action) => {
    switch(action.type){
        case 'SIGN_IN':
            return !state
        default:
            return false
    }
}

export default loggedReducer;
```
- 만들고 싶은 `reducer`파일을 생성하여 `reducer`를 작성해준다.
    - `state`와 `action`을 인자로 해야한다.

<br><br>

**자, 여기서 만든 Reducer들을 어떻게 Store에 연결시킬 수 있을까?**
<br><br>


```js
// ./src/index.js
import { createStore } from 'redux';
```
- store를 만들어준다.

<br><br>

```js
// ./src/index.js
const store = createStore(counterReducer, loggedReducer)
```
- store에 reducer를 넣어주면....

<br>

**잠깐!** <br>
Store에는 `하나 이상의 Reducer를 넣을 수 없다!`<br>
reducer를 하나밖에 사용 할 수 없다니...많은 양의 데이터를 처리하고 싶어서 Redux를 쓰는건데 이렇게 되면 Redux의 활용가치가 너무 없어지는 것이 아닌가...라고 생각할 수 있다. 그러나 걱정도 잠시.<br>
**Redux는 Reducer들을 다 합쳐버린다!** <br>
계속 진행해보자.

<br><br>

```js
// ./src/reducers/index.js
import counterReducer from './counter';
import loggedReducer from './isLogged';

import { combineReducer } from 'redux';
```
- reducers파일의 index.js에 reducer들을 전부 불러버린다.
- `redux`의 `combineReducer`를 불러온다.

<br><br>

```js
// ./src/reducers/index.js

const allReducers = combineReducers({
    counter: counterReducer,
    isLogged: loggedReducer
})

export default allReducers;
```
- combineReducers에 넣고싶은 reducer들을 이름과 함께 정의하여 뭉쳐준다.
    - 참고: 이름 넣을 필요없이 reducer 하나만 넣으면 이름은 알아서 그 reducer이름을 따라감


<br><br>

```js
// ./src/index.js
import allReducers from './reducers';
```
- 뭉쳐놓은 reducer(./src/reducers/index.js)를 불러와보자.
    - 참고: 굳이 index를 주소 끝에 안 붙여도 react는 자동으로 index를 불러와줌

<br><br>
```js
// ./src/index.js

const store = createStore(allReducer);
```
- 두 개 이상의 인자를 넣을 수 없어 망설였던 그 곳에 뭉쳐놨던 reducer들을 인자로 넣어준다.

<br><br>


```js
// ./src/index.js

import { Provider } from 'react-redux';
```
- 이제 뭉쳐놓은 allReducer들을 연결해주기 위하여 `react-redux`에서 `Provider`를 불러온다. 
- `Provider`은 `전역 state`를 모든 `App`에 적용시키는 녀석이다.

<br><br>


```js
// ./src/index.js
ReactDOM.render(
    <Provider store={store}>
        <App />
    </Provider>,
    document.getElementById('root'));
```
- `Provider`에 적용할 store를 지정해주고 `App`을 감싸준다. 이렇게 하면 store에 저장되어 있는 reducer를 모든 곳에서 사용할 수 있다.

<br><br>

```js
// ./src/App.js
import {useSelector} from 'react-redux';

function App(){
    const counter = useSelector(state => state.counter)
    const isLogged = useSelector(state => state.isLogged)
    return (
        <div className="App">
            <h1>Counter is {counter}</h1>
            
            {isLogged ? <h3>You are Logged!</h3>: <h3>You're not Logged....</h3>}
        </div>
    )
}
```
- 자, 모든 곳에서 사용가능하니 사용해보자
- `react-redux`의 `useSelector`를 불러와 state에서 원하는 reducer를 골라와서 사용하면 된다.

<br><br>

**state에 접근해서 활용하는 것은 가능한데...Reducer로 State의 값을 어떻게 변경할 수 있을까?**
<br>

**`reducer`는 `action`을 참고하여 작동하고 `action`은 `dispatch`가 물고와준다고 한 것을 기억해보자.**

<br><br>

```js
// ./src/actions/index.js
export const increment = () => {
    return {
        type: 'INCREMENT'
    };
};
export const decrement = () => {
    return {
        type: 'DECREMENT'
    };
};
```
- actions파일에 index.js를 만들고 action을 만들어준다.


<br><br>


```js
// ./src/App.js
import { useSelector, useDispatch } from 'react-redux'; //added
import { increment } from './actions'; // added

function App(){
    const counter = useSelector(state => state.counter)
    const isLogged = useSelector(state => state.isLogged)
    const dispatch = useDispatch(); // added
    return (
        <div className="App">
            <h1>Counter is {counter}</h1>
            <button onClick={() => dispatch(increment())}>+</button> // added
            <button onClick={() => dispatch(decrement())}>-</button> // added
            
            {isLogged ? <h3>You are Logged!</h3>: <h3>You're not Logged....</h3>}
        </div>
    )
}
```
- 만들어놓은 `action`을 불러와준다.
- `action`을 실행시켜줄 `dispatch`도 불러와준다.
- `dispatch`에 어떤 `action`을 행할 것인지 이야기해준다.
- `action`을 토대로 `reducer`가 실행되고 `state`의 값이 변경된다.

<br><br>

**추가로,** <br>
**`action`에는 인자가 들어갈 수 있으며 이것을 `reducer`에서 사용할 수 있다.**


<br><br>

```js
// ./src/actions/index.js
export const increment = nr => {
    return {
        type: 'INCREMENT',
        payload: nr
    };
};
export const decrement = nr => {
    return {
        type: 'DECREMENT',
        payload: nr
    };
};
```
- 먼저 `action`에 인자를 넣어주고 payload라고 지정해준다.

<br><br>

```js
// ./src/reducers/counter.js
const counterReducer = (state=0, action) => {
    switch(action.type){
        case 'INCREMENT':
            return state + action.payload;
        case 'DECREMENT:
            return state - 1
        default:
            return state;
    }
}

export default counterReducer;
```
- `reducer`에서 action.payload로 인자를 불러와 사용할 수 있다.


<br><br>




* * *
[Redux For Beginners | React Redux Tutorial](https://youtu.be/CVpUuw9XSjY)