# React

**`React`** 란 `node.js`를 이용한 프론트엔드 라이브러리의 하나로 페이스북이 제공해주고 있다. <br>
리액트는 복잡한 구조를 **`컴포넌트`** 형식으로 관리하여 유지보수와 재사용성이 높다.

# React 설치법
## Window
- node.js를 설치한다.
    - [node.js홈페이지](https://nodejs.org/ko/)
- create-react-app 을 설치한다.
    - [create-react-app](https://github.com/facebook/create-react-app)
    -   ```cmd
        > npm install -g create-react-app
        
        (해당경로에서) 
        > create-react-app . 
        ```
    - 참고로 공식적으로는 npx 방식으로 설치하는 것을 권장한다.
# React 기초
- 기본적으로는 id가 **`root`** 인 곳 태그 안에 react-app 컴포넌트가 들어간다.
- 각 run 명령어
    - `개발 환경` - npm run start
    - `빌드 환경` - npm run build / npx serve -s build
        - build 폴더가 생기며 이 폴더에 있는 내용들이 실 서버환경에 배포된다.
        - 경량화되어있음

    - 취소는 `ctrl + c`

- [리액트 자습서](https://ko.reactjs.org/tutorial/tutorial.html)
- [리액트 디벨로퍼 툴즈(크롬)](https://chrome.google.com/webstore/detail/react-developer-tools/fmkadmapgofadopljbjfkapdkoienihi/related?hl=ko)
