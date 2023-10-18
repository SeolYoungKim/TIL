# 개발 용어 정리
## REST API
- 로이 필딩의 논문에 아래와 같이 소개되어 있음 
  - Uniformb (일관성)
  - Stateless (무상태성)
  - Cacheable (캐시 가능)
  - Self-descriptiveness (인터페이스만 봐도 이해 가능)
  - Client-Server
  - 계층형 구조 

### 형태
```text
GET /users
POST /users
GET /users/{id}
PUT /users/{id}
DELETE /users/{id}
```
- Http Method로 행위를 표현
  - GET, POST, PUT, DELETE..
- URI로 자원을 표현
  - /users, /users/{id}..
  - 복수형으로 사용하자 
  - 끝에 / 를 붙이지 말자 
  - PUT, PATCH는 구분해서 사용하자 
    - PUT은 전체 리소스를 업데이트할 때 사용 (멱등성 보장)
    - PATCH는 부분 리소스를 업데이트할 때 사용 (멱등성 보장 X)
  - 검색은 GET + 쿼리 스트링으로 하는게 좋으나...
    - POST도 사용하기는 한다... 
  - path에 동사를 사용하지말자. 행위는 반드시 HTTP Method로 표현한다.
- 응답 코드를 신경쓰자 


## OAuth
- Open Authorization
- 인터넷 사용자들이 비밀번호를 제공하지 않고 다른 웹사이트 상의 자신들의 정보에 대해 웹사이트나 애플리케이션의 접근 권한을 부여할 수 있는 공통적인 수단
  - 접근 위임을 위한 개방형 표준 
  - ex: Google login, Naver login...
- 왜 쓸까?
  - 사용자 입장에서, 신뢰할 수 있는 사이트에만 가입하고 싶을 수 있음
  - 가입이 매우 편함 

## 인증 vs 인가
- 인증
  - 사용자가 누구인지 확인하는 과정
  - 없으면 401 Unauthorized (토큰이 유효하지 않을 때)
- 인가
  - 사용자가 특정 자원에 접근할 수 있는 권한이 있는지 확인하는 과정
  - 없으면 403 Forbidden (토큰이 접근 권한이 없을 때)


## SSO
- Single Sign On
- 로그인 한번으로 여러 서비스를 이용할 수 있으면 SSO를 지원하는 것 
- 네이버 페이 로그인 -> 네이버 로그인을 사용하고 있는 다른 서비스에서도 자연스럽게 로그인 처리가 되어있으면 SSO를 지원하는 것 


## 불변성


