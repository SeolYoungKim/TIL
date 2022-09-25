## Authentication?

- 인증 프로세스의 필수 인터페이스
- 인증 요청 이벤트를 나타내고, 애플리케이션에 접근을 요청한 엔티티의 세부 정보를 담는다.
- 인증 요청 이벤트와 관련된 정보는 아래의 경우 이용이 가능하다.
    - 인증 프로세스 도중
    - 인증 프로세스 이후
- **Principal**
    - 애플리케이션에 접근을 요청하는 사용자
- 스프링 시큐리티의 Authentication은 Principal을 상속한다.(extends)
- 다음과 같은 정보를 담고 있다.
    - name | 이름
    - Cridential | 암호
    - Authorities | 권한 (Collection<? extends GrantedAuthority>)
    - isAuthenticated | 인증 객체가 인증됐거나, 인증 프로세스가 진행중임
        - 인증 끝 → true || 인증 중 → false
    

## AuthenticationProvider
![image](https://user-images.githubusercontent.com/100072078/192133281-10b72374-44ad-4400-b2cc-460b3f205d98.png)

- 사용자를 찾는 책임 → `UserDetailsService`에 위임
- 암호를 관리 → `PasswordEncoder`로 관리
- 인증 로직을 갖고 있음 `authenticate()` → 아래 세 개중 하나를 뱉도록 해야됨
    - 인증 실패 시 `AuthenticationException` 투척
    - 현재 AuthenticationProvider가 지원되지 않는 인증 객체를 받을 경우 `null`을 반환
    - 완전히 인증된 `Authentication` 인스턴스 반환
        - 완전히 인증된 `Authentication` 인스턴스의 `isAuthenticated()`는 `true`를 반환함
