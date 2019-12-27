# Gradle

## Gradle이란?
- 그루비(Groovy) 기반의 DSL(Domain Specific Language) 오픈 소스
  - Java와 문법이 유사함
  -  특정 도메인에 특화된 언어를 DSL이라고 하는데 빌드 도구인 gradle도 여기에 해당
  - 빌드 스크립트를 xml이 아닌 그루비(Grooby)로 작성한다.
  - Gradle wrapper를 이용하여 gradle이 설치되어있지 않아도 사용 가능
  - 확장성이 뛰어나다.

## Gradle vs Maven
-  Gradle의 Groovy 언어를 이용한 스크립트 작성 vs Maven의 xml 작성
- Gradle의 주입 방식 vs Maven의 상속 방식
- Maven보다 Gradle의 

## Gradle 설치(Window10)
### 1. Gradle 다운로드
- [Gradle 홈페이지](http://www.gradle.org/) 의 다운로드 페이지에서 gradle을 다운로드한다.
   - binary 파일(Gradle 파일만)이나 complete 파일(문서 등이 통합 된 파일) 아무거나 받는다.
### 2. 환경설정
- C:\Gradle 디렉토리를 만든다. (권장)
- C:\Gradle 디렉토리에 압축을 푼 gradle 폴더를 넣는다.
- 시스템 환경변수 새로만들기 
   - 변수이름 : GRADE_HOME 
   - 변수 값 : C:\Gradle\gradle-c
- 시스템 환경변수의 PATH 편집
   - 추가 : C:\Gradle\gradle-5.6.2\bin 
- CMD 에서 `gradle -v`를 입력하고 gradle 정보가 나오면 성공!