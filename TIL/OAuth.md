# OAuth(OAuth2.0)란 무엇일까? 소셜로그인이 작동하는 법

사이트에 회원가입을 하지않아도 이미 가지고 있는 아이디를 통해 로그인할 수 있게 만드는 소셜로그인. 많은 사람들이 들어봤거나 사용해봤을 것이다. 이렇게 간편한 로그인은 OAuth를 통해서 구현할 수 있다.<br>
이 OAuth는 무엇이고 어떻게 작동하는 것인지 궁금해져서 이번 기회에 간단하게 정리해보려고한다.<br>


<br><br>


# **What is OAuth?**
![https://upload.wikimedia.org/wikipedia/commons/thumb/d/d2/Oauth_logo.svg/270px-Oauth_logo.svg.png](https://upload.wikimedia.org/wikipedia/commons/thumb/d/d2/Oauth_logo.svg/270px-Oauth_logo.svg.png)

OAuth는 `Open Authorization`의 줄임말로 보안 위임 엑세스(secure delegated access) 개방형 표준(open standard)이다. <br> 
현재 OAuth1.0과 OAuth2.0 두 버전이 존재한다.


<br><br>

## **Open Standard**
많은 사람들이 OAuth를 API라고 생각하는데 **OAuth는 `개방형 표준(open standard)` 이다.** <br>
개방형 표준이라하면 기술 표준이 문서가 공개되어 있으며 사용이 자유로운 것을 뜻하는데 OAuth는 이러한 표준인 것이다. 그래서 누구나 만들 수 있고 활용할 수 있으며 그 위에 HTTPS, API, 서버, 토큰 등등의 개념이 올라가있는 것이다. 그렇다면 OAuth는 어떤 것에 대한 표준일까? <br>
바로 앱이 클라이언트 애플리케이션에 **`보안 위임 엑세스(secure delegated access)` 를 제공하는데 사용하는 표준이다.** <br> 
풀어말하면 A서비스를 이용하는 사람들이 별다른 비밀번호나 자격증명없이 로그인할 수 있도록 B서비스에서의 접근 권한을 부여할 수 있게하는 표준인 것이다. 구글로그인같은 것을 생각하면 이해가 쉽다. <br>
[OAuth2.0 프로토콜이라고도 불리고 프레임워크라고도 불린다.](https://stackoverflow.com/questions/35070594/oauth-2-is-a-protocol-or-a-framework) 거의 동의어로 불리며 여러 언어로 만들 때는 프레임워크라고 하는 듯.

<br><br>

## **OAuth1.0 VS OAuth2.0**
OAuth는 두 가지 버전이 존재한다. OAuth1.0이 먼저 개발됐지만 암호화나 운용에 있어 구현이 힘든 면이 있었기때문에 이보다 **더 쉽고 간단하게 구현할 수 있으며 웹에만 한정되어있지않은 OAuth2.0을 많이 사용한다.** <br>
그렇기 때문에 앞으로 OAuth2.0을 정리해볼 것이다.

<br><br>

## **Not an authentication protocol!**
**OAuth2.0은** `인증(authentication) 프로토콜`이 아니라 **`인가(authorization) 프로토콜` 이다.** <br> 
*"인증과 인가가 뭐가 다른데!"* 라는 소리가 나오겠지만 둘은 전혀 다르다. <br>
- `인증(authentication)` : 
    - 누가 누구인지 확인하는 프로세스
    - Ex 
        - 비행기를 타기 전 탑승자의 신원을 확인하는 것
        - 아이디와 비밀번호를 통해 유저를 확인하는 것
- `인가(authorization)`:
    - 사용자가 접근할 수 있는 무엇인가를 확인하는 프로세스. 즉, 권한을 부여하는 것이다.
    - Ex
        - 비행기에 탄 탑승자에게 해당 좌석을 부여하는 것
        - 유저의 등급에 따라 해당 데이터를 보여주는 것

즉, OAuth2.0은 "네가 누군데?"를 말하는게 아니라 **"네가 누군진 중요하지않으니 상관없고, 지금 너한텐 어떤 권리가 있으니 여기까지만 들어갈 수 있게 해줄게."** 를 말하는 것이다. <br>
OAuth는 그저 권한을 부여하는 것이다. 실제로 소셜로그인을 하면 권한을 부여하시겠습니까?라고 묻는데 이것은 바로 OAuth2.0, 즉, 인가 프로토콜을 이용했기 때문이다. *(권한 부여를 위해 해당 소셜의 아이디,비밀번호를 입력하는 것은 별개의 것이다!)* <br>


<br><br>

# **Why OAuth?**
왜 이렇게 많은 기업들과 개발자들이 OAuth2.0을 사용하는 것일까? <br>
<br>

### **Back in the day...**
OAuth가 프로토콜로 나오기 전에는 보안 위임 엑세스 표준이란게 없었다. 그래서 모든 기업들이 각자 그에 맞는 기술을 개발했고 때로는 위험하기도 했다. 사용자가 어떤 앱에서 다른 서비스의 정보를 불러오고싶을 때, 앱은 대놓고 사용자의 아이디와 비밀번호를 요구해서 직접 그 서비스에 로그인한 뒤, 정보를 가져왔을 수 있었던 것이다.

<br><br>

## **Nodays...**
OAuth는 앱이 사용자의 아이디와 비밀번호를 요구하는 대신, 다른 서비스에서 정보를 얻을 수 있는 권한을 사용자에게 요구하도록 한다. 이것은 여러방면에서 보안에 좋고 편리해서 많은 기업과 개발자들이 사용한다. <br>

<br><br>

# **How Does it work?**
그렇다면 OAuth2.0은 어떻게 권한을 부여할까? <br>


<br>

## **Roles**
권한부여의 과정을 이해하려면 OAuth2.0의 필수구성요소들의 역할들을 잠깐 알아보고 가야한다.
- **`Resource Owner`** : 보호된 리소스를 소유하고 이에 대한 접근 권한을 부여할 수 있는 사용자 또는 시스템
    - ex) 일반 사용자
- **`Client`** : 보호된 리소스에 대한 접근이 필요한 시스템. 리소스에 접근하려면 이 클라이언트는 적절한 `액세스 토큰`을 가지고 있어야한다.
    - ex) 구글, 카카오등에게서 정보를 가져오려는 제 3의 서비스
- **`Authorization Server`** :  **`Client`** 로부터 `액세스 토큰`에 대한 요청을 받고 **`Resource Owner`** 의 인증 및 동의에 따라 `액세스 토큰`을 발행해준다.
    - 이 서버는 두 가지 `endpoint`를 노출시키고 있다.
        - `Authorization endpoint` :  사용자 인증 및 동의를 처리하기 위해 사용자와 상호작용하는 엔드포인트
        - `Token endpoint` :  기계와 기계가 상호작용하는 엔드포인트
    - ex) 구글, 카카오처럼 로그인을 통해 인증 후 권한을 부여하는 서버.
- **`Resource Server`** : 사용자의 자원을 보호하고 **`Client`** 로부터 접근 요청을 받는 서버. **`Client`** 의 `액세스 토큰`을 받아 확인하고 적절한 리소스를 **`Client`** 에게 반환해준다.
    - ex) 구글, 카카오처럼 일반 사용자의 정보를 제공하는 서버

<br><br>

## **Process**
OAuth2.0의 인가 과정은 다음과 같다.
- **`Client`** 가 **`Authorization Server`** 에게 인가를 요청하면서 다음과 같은 데이터를 준다.
    - `client id` - 식별용
    - `secret` - 식별용
    - `scope` - 인가 요청 범위
    - `endpoint URI` - 리디렉션 URI
- **`Authorization Server`** 는 **`Client`** 를 인증하고 요청된 `scope`가 허용되는지 확인한다.
- **`Resource owner`** 가 인가를 위해서 **`Authorization Server`** 와 상호작용한다.
- **`Authorization Server`** 는 인가 유형에 따라 `액세스 토큰` 혹은 인가 코드를 발행해서 **`Client`** 로 다시 리디렉션한다. 이 때, `리프레시 토큰`을 같이 줄 수도 있다.
    - `액세스 토큰` : 접근을 위한 토큰으로 수명이 짧다.
    - `리프레시 토큰`: 새로운 `액세스 토큰`을 얻기 위한 토큰으로 비교적 오래 지속된다.
- **`Client`** 는 `액세스 토큰`을 사용하여 **`Resource Server`** 에게 리소스 접근을 요청한다.

<br><br>

## **Grant Type**
과정을 보면 `액세스 토큰`이 중요해보이지않는가? OAuth2.0은 이 `액세스 토큰`을 부여하는 몇가지 방법을 정의했다. 
- **`Authorization Code Grant`** (권한 부여 승인 코드 방식)  
    - `인가 코드`를 이용해 `액세스 토큰`을 부여하는 방식이다. **`Authorization Server`** 가 일회용 `인가 코드` 를 **`Client`** 에게 전해주면 그것을 `액세스 토큰` 으로 교환할 수 있다. 서버사이드에서 교환이 안전하게 이루어질 수 있는 기존 웹 어플리케이션에 적합한 방식이다. 다만, 이 방식으로는 서버사이드에서 **`Client`** 의 `secret` 을 안전하게 저장할 수 없기 때문에 `client id`만 사용하는 경우도 있다.
- **`Implicit Grant`** (암묵적 승인)
    - **`Client`** 에게 `액세스 토큰` 이 직접 전해지는 간단한 방법이다. **`Authorization Server`** 는 `액세스 토큰` 을 URI에 담아서 리턴해준다. 사용자의 브라우저에서 실행되는 SPA(Single-Page Applications)에서 사용된다.
- **`Resource Owner Password Grant`** (자원 소유자 자격증명 승인 방식)
    - **`Client`** 가 먼저 **`Resource Owner`** 의 아이디나 비밀번호등의 자격증명을 획득해야 하며, 이 아이디와 비밀번호가 **`Authorization Server`** 로 전달된다. 즉, 유저의 아이디와 비밀번호로 `액세스 토큰`을 얻는 것이다. **`Authorization Server`** 로의 리다이렉트가 없는 장점이 있지만 자격증명이 노출되기 때문에 신뢰도가 높은 어플리케이션에서만 사용해야한다. 잘 권장되진 않는다.
- **`Client Credentials Grant`** (클라이언트 자격증명 승인 방식)
    - `client id` 와 `secret` 을 이용하여 `액세스 토큰`을 받는 것으로 유저가 아닌 기계와 기계가 상호작용할 때, 즉, 주로 자동화된 프로세스 등과 같은 어플리케이션에 사용된다.

<br><br><br><br>

* * *
참조 <br>
*https://developer.okta.com/blog/2017/06/21/what-the-heck-is-oauth* <br>
*https://auth0.com/intro-to-iam/what-is-oauth-2/* <br>
*https://stackoverflow.com/questions/35070594/oauth-2-is-a-protocol-or-a-framework* <br>
*https://d2.naver.com/helloworld/24942* <br>
*https://blog.naver.com/mds_datasecurity/222182943542* <br>
*https://meetup.toast.com/posts/105* <br>