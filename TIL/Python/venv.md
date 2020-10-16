# `venv`
python 3.5 이후부터 지원하는 가상환경 모듈
>가상 환경은 파이썬 인터프리터, 라이브러리 및 스크립트가 다른 가상 환경에 설치된 것과 (기본적으로) 《시스템》 파이썬(즉, 여러분의 운영 체제 일부로 설치되어있는 것)에 설치된 모든 라이브러리와 격리되어있는 파이썬 환경입니다.<br>
<docs.python.org>

<br><br>

## 왜 가상환경이 필요한가?
python은 버전에 따라 기능차이가 있고, 모듈도 버전에 따라 기능차이가 있다.(의존성이 있다.) <br>
이러한 기능차이가 존재하기 때문에 하나의 환경에서는 여러 프로젝트를 여러 버전으로 다루기가 까다로워진다. <br> 이 때, 가상환경을 만들어서 같은 버전들만 관리해주어 python의 의존성을 해결한다. <br>
격리되어있는 환경이기에 모듈, 인터프리터, pip 등등을 각각 버전에 맞게 쓸 수 있다.

<br><br>

## `venv` 가상환경 Create (Python 3.7)

<br>

### Mac/Linux
```linux
python3 -m venv (경로)
```

### Windows
- `venv` 명령을 호출
```cmd
c:\>c:\Python35\python -m venv c:(경로)
```
- 혹은, 파이썬이 PATH경로에 변수로 지정되어있을 경우
```cmd
c:\>python -m venv c:(경로)
```

운영체제에 맞춰 위 명령어를 이용하면 지정한 경로에 `venv`라는 폴더가 생긴다. <br>
`Mac/Linux`는 `bin폴더`가 들어가있고 `Window`의 경우에는 `Script폴더`가 생성되어있다.

## `venv` 가상환경 Activate/Deactivate
### Mac/Linux
```command
source ./venv/bin/activate
source ./venv/bin/deactivate
```

### Windows
```cmd
경로/Script> activate
경로/Script> deactivate.bat
```

<br><br>
* * * 
https://docs.python.org/ko/3.7/library/venv.html