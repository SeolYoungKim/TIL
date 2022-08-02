# 중요3 : 스프링 시큐리티 & OAuth 2.0

[구글 클라우드 서비스 등록](%E1%84%8C%E1%85%AE%E1%86%BC%E1%84%8B%E1%85%AD3%20%E1%84%89%E1%85%B3%E1%84%91%E1%85%B3%E1%84%85%E1%85%B5%E1%86%BC%20%E1%84%89%E1%85%B5%E1%84%8F%E1%85%B2%E1%84%85%E1%85%B5%E1%84%90%E1%85%B5%20&%20OAuth%202%200%20ba39e2d17c884f4b8aec99a2b3533245/%E1%84%80%E1%85%AE%E1%84%80%E1%85%B3%E1%86%AF%20%E1%84%8F%E1%85%B3%E1%86%AF%E1%84%85%E1%85%A1%E1%84%8B%E1%85%AE%E1%84%83%E1%85%B3%20%E1%84%89%E1%85%A5%E1%84%87%E1%85%B5%E1%84%89%E1%85%B3%20%E1%84%83%E1%85%B3%E1%86%BC%E1%84%85%E1%85%A9%E1%86%A8%20c2a6893441924743961836cebd874d40.md)

[네이버 API 등록](%E1%84%8C%E1%85%AE%E1%86%BC%E1%84%8B%E1%85%AD3%20%E1%84%89%E1%85%B3%E1%84%91%E1%85%B3%E1%84%85%E1%85%B5%E1%86%BC%20%E1%84%89%E1%85%B5%E1%84%8F%E1%85%B2%E1%84%85%E1%85%B5%E1%84%90%E1%85%B5%20&%20OAuth%202%200%20ba39e2d17c884f4b8aec99a2b3533245/%E1%84%82%E1%85%A6%E1%84%8B%E1%85%B5%E1%84%87%E1%85%A5%20API%20%E1%84%83%E1%85%B3%E1%86%BC%E1%84%85%E1%85%A9%E1%86%A8%200bb94edc5d9d48808dcb69f287a5617c.md)

[Java의 직렬화(Serialize)](%E1%84%8C%E1%85%AE%E1%86%BC%E1%84%8B%E1%85%AD3%20%E1%84%89%E1%85%B3%E1%84%91%E1%85%B3%E1%84%85%E1%85%B5%E1%86%BC%20%E1%84%89%E1%85%B5%E1%84%8F%E1%85%B2%E1%84%85%E1%85%B5%E1%84%90%E1%85%B5%20&%20OAuth%202%200%20ba39e2d17c884f4b8aec99a2b3533245/Java%E1%84%8B%E1%85%B4%20%E1%84%8C%E1%85%B5%E1%86%A8%E1%84%85%E1%85%A7%E1%86%AF%E1%84%92%E1%85%AA(Serialize)%20d3dde5fb2420478aaae2aed8eae1bfb4.md)

[참고 자료](%E1%84%8C%E1%85%AE%E1%86%BC%E1%84%8B%E1%85%AD3%20%E1%84%89%E1%85%B3%E1%84%91%E1%85%B3%E1%84%85%E1%85%B5%E1%86%BC%20%E1%84%89%E1%85%B5%E1%84%8F%E1%85%B2%E1%84%85%E1%85%B5%E1%84%90%E1%85%B5%20&%20OAuth%202%200%20ba39e2d17c884f4b8aec99a2b3533245/%E1%84%8E%E1%85%A1%E1%86%B7%E1%84%80%E1%85%A9%20%E1%84%8C%E1%85%A1%E1%84%85%E1%85%AD%2068cc1fdf5190447daf1fbbdaa1476529.md)

## 스프링 시큐리티

---

스프링 기반 애플리케이션의 보안을 위한 표준.

인터셉터, 필터 기반의 보안 → **스프링 시큐리티를 통한 보안 권장**

### 기능

- 인증(Authentication)
- 인가(Authorization) 혹은 권한 부여

### 로그인 직접 구현 vs 소셜 네트워크 로그인

- OAuth를 통한 로그인 구현 시 고려해야 할 요소
    - 로그인 시 보안
    - 회원가입 시 이메일 or 전화번호 인증
    - 비밀번호 찾기
    - 비밀번호 변경
    - 회원정보 변경

### Spring Security Oauth2 Client Library

- 스프링부트 2 방식의 인터넷 자료를 찾을 경우, 다음 두 가지를 확인하자.
    - spring-security-oauth2-autoconfigure 라이브러리를 사용했는가
    - application.properties 혹은 application.yaml 정보가 다음과 같은 차이가 있는가
        
        ![Untitled](%E1%84%8C%E1%85%AE%E1%86%BC%E1%84%8B%E1%85%AD3%20%E1%84%89%E1%85%B3%E1%84%91%E1%85%B3%E1%84%85%E1%85%B5%E1%86%BC%20%E1%84%89%E1%85%B5%E1%84%8F%E1%85%B2%E1%84%85%E1%85%B5%E1%84%90%E1%85%B5%20&%20OAuth%202%200%20ba39e2d17c884f4b8aec99a2b3533245/Untitled.png)
        

### CommonOAuth2Provider (Enum)

![Untitled](%E1%84%8C%E1%85%AE%E1%86%BC%E1%84%8B%E1%85%AD3%20%E1%84%89%E1%85%B3%E1%84%91%E1%85%B3%E1%84%85%E1%85%B5%E1%86%BC%20%E1%84%89%E1%85%B5%E1%84%8F%E1%85%B2%E1%84%85%E1%85%B5%E1%84%90%E1%85%B5%20&%20OAuth%202%200%20ba39e2d17c884f4b8aec99a2b3533245/Untitled%201.png)

- 구글, 깃허브, 페이스북, 옥타의 기본 설정값을 모두 제공
- 다른 소셜 로그인(네이버, 카카오 등)을 추가하면 직접 다 추가해야 함.

### scope=profile, email

- scope의 default 는 openid, profile, email이다.
- 강제로 profile, email을 등록한 이유는 openid라는 scope가 존재하면, Open Id Provider로 인식하기 때문이다.
- 이렇게 되면 OpenId Provider인 서비스(구글)과 그렇지 않은 서비스(네이버/카카오 등)로 나눠서 각각 OAuth2Service를 만들어야 함
- 하나의 OAuth2Service로 사용하기 위해 일부러 openid scope를 빼고 등록함

### application-oauth.properties

- .gitignore에 꼭 등록할것
- application-xxx.properties
    - xxx라는 이름의 profile이 생성됨
    - profile=xxx 라는 식으로 호출하면, 해당 properties의 설정들을 가져올 수 있음.
    - 아래와 같은 방식으로 `application.yaml`에 oauth 설정 정보들을 가져왔다.
        
        ![Untitled](%E1%84%8C%E1%85%AE%E1%86%BC%E1%84%8B%E1%85%AD3%20%E1%84%89%E1%85%B3%E1%84%91%E1%85%B3%E1%84%85%E1%85%B5%E1%86%BC%20%E1%84%89%E1%85%B5%E1%84%8F%E1%85%B2%E1%84%85%E1%85%B5%E1%84%90%E1%85%B5%20&%20OAuth%202%200%20ba39e2d17c884f4b8aec99a2b3533245/Untitled%202.png)
        

### 스프링 시큐리티 - 권한 코드 설정

```java
package ToyProject.RestApiPractice.domain.user;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

//TODO : Enum 좀 더 공부해보기!
@Getter
@RequiredArgsConstructor
public enum Role {

    // Spring security에서는 항상 "ROLE_" 이 앞에있어야만 한다.
    // 그래서 코드 별 Key 값을 ROLE_GUEST, ROLE_USER 등으로 지정한다.

    GUEST("ROLE_GUEST", "손님"),
    USER("ROLE_USER", "일반 사용자"),
    ;

    private final String key;
    private final String title;
}
```

- 스프링 시큐리티에서는 권한 코드에 **반드시 “ROLE_”이 앞에 있어야** 함.
- 코드 별 키 값을 `ROLE_GUEST`, `ROLE_USER` 등으로 지정함.

### 소셜 로그인 시 고려 사항

- Email 주소
    - 리포지토리에 `findByEmail`로직 구현
    - 소셜 로그인으로 반환되는 값 중, email을 통해 이미 생성된 사용자인지, 처음 가입하는 사용자인지 판단하기 위한 메소드

### build.gradle

```python
implementation 'org.springframework.boot:spring-boot-starter-security'

# Social login 등 클라이언트 입장에서 소셜 기능 구현 시 필요한 의존성.
implementation 'org.springframework.boot:spring-boot-starter-oauth2-client'
```

### SecurityConfig

![Untitled](%E1%84%8C%E1%85%AE%E1%86%BC%E1%84%8B%E1%85%AD3%20%E1%84%89%E1%85%B3%E1%84%91%E1%85%B3%E1%84%85%E1%85%B5%E1%86%BC%20%E1%84%89%E1%85%B5%E1%84%8F%E1%85%B2%E1%84%85%E1%85%B5%E1%84%90%E1%85%B5%20&%20OAuth%202%200%20ba39e2d17c884f4b8aec99a2b3533245/Untitled%203.png)

- `@EnableWebSecurity`
    - Spring security 설정 활성화
- `.csrf().disable().headers().frameOptions().disable()`
    - h2-console 화면을 사용하기 위해 해당 옵션들을 disable
- `authorizeRequests()`
    - URL 별 권한 관리를 설정하는 옵션의 시작점
    - authorizeRequests가 선언 되어야만 antMatchers 옵션 사용 가능
- `antMatchers()`
    - 권한 관리 대상을 지정하는 옵션
    - URL, HTTP 메소드 별로 관리 가능
    - “/” 등 지정된 URL들은 permitAll() 옵션을 통해 전체 열람 권한을 줌.
    - “api/v1/**” 주소를 가진 API는 USER 권한을 가진 사람만 가능하도록 함.
- `anyRequest()`
    - 설정된 값들 외의 나머지 URL들을 나타냄
    - `anyRequest().authenticated()` : 나머지 URL들은 모두 인증된 사용자들에게만 허용하게 함. (인증된 사용자 == 로그인한 사용자)
- `logout().logoutSuccessUrl()`
    - 로그아웃 기능에 대한 여러 설정의 진입점
    - 로그아웃 성공시 “/” 주소로 이동함
- `oauth2Login()`
    - OAuth 2 로그인 기능에 대한 여러 설정의 진입점
- `userInfoEndpoint()`
    - OAuth 2 로그인 성공 이후, 사용자 정보를 가져올 때의 설정들을 담당함.
- `userService`
    - 소셜 로그인 성공 시, 후속 조치를 진행할 UserService 인터페이스의 구현체를 등록 함
    - 리소스 서버(소셜 서비스들)에서 사용자 정보를 가져온 상태에서 추가로 진행하고자 하는 기능을 명시할 수 있음.

## 인증 관련 코드 정리

---

### CustomOAuth2UserService

```java
@RequiredArgsConstructor
@Service
public class CustomOAuth2UserService implements OAuth2UserService<OAuth2UserRequest, OAuth2User> {

    private final UserRepository userRepository;
    private final HttpSession httpSession;

    @Override
    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {

        OAuth2UserService<OAuth2UserRequest, OAuth2User> delegate = new DefaultOAuth2UserService();
        OAuth2User oAuth2User = delegate.loadUser(userRequest);

        // 현재 로그인 진행 중인 서비스를 구분하는 코드
        String registrationId = userRequest
                .getClientRegistration()
                .getRegistrationId();

        // OAuth2 로그인 진행 시, 키가 되는 필드값. == primary key
        // google 기본 코드 = "sub" (네이버, 카카오는 기본 코드를 지원하지 않음.)
        String userNameAttributeName = userRequest
                .getClientRegistration()
                .getProviderDetails()
                .getUserInfoEndpoint()
                .getUserNameAttributeName();

        // OAuth2UserService를 통해 가져온 OAuth2User attribute를 담을 클래스 (이후 다른 소셜 로그인도 해당 클래스 사용)
        OAuthAttributes attributes = OAuthAttributes
                .of(registrationId, userNameAttributeName, oAuth2User.getAttributes());

        User user = saveOrUpdate(attributes);  // 구글 사용자 정보가 업데이트 되었을 때를 대비한 update 기능.

        // SessionUser : 세션에 사용자 정보를 저장하기 위한 Dto 클래스.
        httpSession.setAttribute("user", new SessionUser(user));

        return new DefaultOAuth2User(
                Collections.singleton(new SimpleGrantedAuthority(user.getRoleKey())),
                attributes.getAttributes(),
                attributes.getNameAttributeKey());
    }

    private User saveOrUpdate(OAuthAttributes attributes) {
        User user = userRepository.findByEmail(attributes.getEmail())
                .map(entity -> entity.update(attributes.getName(), attributes.getPicture()))
                .orElse(attributes.toEntity());  // email로 유저 찾아봤는데 없으면 게스트

        return userRepository.save(user);
    }

}
```

- `registraitonId`
    - 현재 로그인 진행 중인 서비스를 구분하는 코드
    - 구글만 사용할 때에는 불필요하나, 이후 네이버 로그인 연동 시 네이버 로그인인지, 구글 로그인인지 구분하기 위해 사용.
- `userNameAttributeName`
    - OAuth2 로그인 진행 시 키가 되는 필드값을 의미함.
        - Primary Key와 같은 의미
    - 구글의 경우 기본적으로 코드를 지원하나, 네이버 카카오 등은 기본 지원 하지않음.
        - 구글의 기본 코드 = “sub”
    - 이후, 네이버 로그인과 구글 로그인을 동시 지원할 때 사용
- `OAuthAttributes`
    - `OAuth2UserService`를 통해 가져온 `OAuth2User`의 `attribute`를 담을 클래스
    - 이후 네이버 등 다른 소셜 로그인도 이 클래스를 사용
- `SessionUser`
    - 세션에 사용자 정보를 저장하기 위한 Dto 클래스

### OAuthAttributes

```java
@Getter
public class OAuthAttributes {

    private final Map<String, Object> attributes;
    private final String nameAttributeKey;
    private final String name;
    private final String email;
    private final String picture;

    @Builder
    public OAuthAttributes(Map<String, Object> attributes, String nameAttributeKey, String name, String email, String picture) {
        this.attributes = attributes;
        this.nameAttributeKey = nameAttributeKey;
        this.name = name;
        this.email = email;
        this.picture = picture;
    }

    // OAuth2User에서 반환하는 사용자 정보는 Map이기 때문에, 값 하나 하나를 변환해야만 한다..
    public static OAuthAttributes of(String registrationId, String userNameAttributeName, Map<String, Object> attributes) {
        return ofGoogle(userNameAttributeName, attributes);
    }

    private static OAuthAttributes ofGoogle(String userNameAttributeName, Map<String, Object> attributes) {
        return OAuthAttributes.builder()
                .name((String) attributes.get("name"))
                .email((String) attributes.get("email"))
                .picture((String) attributes.get("picture"))
                .attributes(attributes)
                .nameAttributeKey(userNameAttributeName)
                .build();
    }

    public User toEntity() {
        return User.builder()
                .name(name)
                .email(email)
                .picture(picture)
                .role(Role.GUEST)
                .build();
    }
}
```

- `of()`
    - OAuth2User에서 반환하는 사용자 정보는 Map이기 때문에 값 하나 하나를 변환해야 한다.
- `toEntity()`
    - User 엔티티를 생성함
    - OAuthAttributes에서 엔티티를 생성하는 시점 = 처음 가입할 때
    - 가입 할 때 기본 권한을 GUEST로 주기 위해서 role의 빌더 값에는 Role.GUEST를 사용.
    - OAuthAttributes 클래스 생성이 끝났으면 같은 패키지에 SessionUser 클래스를 생성

### SessionUser

```java
@Getter
public class SessionUser implements Serializable {

    private final String name;
    private final String email;
    private final String picture;

    public SessionUser(User user) {
        this.name = user.getName();
        this.email = user.getEmail();
        this.picture = user.getPicture();
    }
}
```

- 인증된 사용자 정보만 필요함.
    - 그 외의 정보는 필요 없기 때문에, name, email, picture만 필드로 선언
- `Serializable` 을 상속받음 ⇒ 직렬화가 가능한 객체가 됨.
- 왜 세션에 User 클래스를 사용하면 안될까??
    
    ![Untitled](%E1%84%8C%E1%85%AE%E1%86%BC%E1%84%8B%E1%85%AD3%20%E1%84%89%E1%85%B3%E1%84%91%E1%85%B3%E1%84%85%E1%85%B5%E1%86%BC%20%E1%84%89%E1%85%B5%E1%84%8F%E1%85%B2%E1%84%85%E1%85%B5%E1%84%90%E1%85%B5%20&%20OAuth%202%200%20ba39e2d17c884f4b8aec99a2b3533245/Untitled%204.png)
    
    - User 클래스를 세션에 저장하고자 할 때,
        
        **User 클래스에 직렬화를 구현하지 않았다**는 의미의 에러가 발생한다.
        
    - **User 클래스는 “엔티티"이다.**
        - 언제 다른 엔티티와 관계가 형성될 지 모른다.
        - `@OneToMany`, `@ManyToOne` 등 자식 엔티티를 갖고 있을 경우 직렬화 대상이 자식들까지 포함된다.
            - 이는 **성능 이슈와 부수 효과를 발생**시킬 확률이 높다.
    - **직렬화 기능을 가진 세션 Dto** 를 하나 추가로 만드는 것이 이후 운영 및 유지보수 때 많은 도움이 된다.

## 테스트 관련

---

```python
//테스트용 가짜 인증사용자 추가
testImplementation 'org.springframework.security:spring-security-test'
```

![Untitled](%E1%84%8C%E1%85%AE%E1%86%BC%E1%84%8B%E1%85%AD3%20%E1%84%89%E1%85%B3%E1%84%91%E1%85%B3%E1%84%85%E1%85%B5%E1%86%BC%20%E1%84%89%E1%85%B5%E1%84%8F%E1%85%B2%E1%84%85%E1%85%B5%E1%84%90%E1%85%B5%20&%20OAuth%202%200%20ba39e2d17c884f4b8aec99a2b3533245/Untitled%205.png)

- `@WithMockUser(roles = “USER”)`
    - 인증된 모의 사용자를 만들어서 사용
    - role에 권한을 추가할 수 있음
    - ROLE_USER 권한을 가진 사용자가 API를 요청하는 것과 같은 효과가 나타남

### @WebMvcTest

**스캔 대상**

- `WebSecurityConfigurerAdapter`
- `WebMvcConfigurer`
- `@ControllerAdvice`
- `@Controller`

**스캔 대상이 아님**

- `CustomOAuth2UserService`
- `@Repository`
- `@Service`
- `@Component`

<aside>
☝ 그래서 DI가 필요할 때 `@WebMvcTest`보다는,  `@AutoConfigureMockMvc`와 `@SpringBootTest`를 혼용해서 사용한다.

</aside>

### @EnableJpaAuditing

- 해당 애노테이션을 사용하려면 최소 하나의 엔티티가 필요하다.
- `@WebMvcTest`의 경우, 당연히 엔티티가 없다.
- `@EnableJpaAuditing`을 `@SpringBootApplication`과 함께 사용하면 `@WebMvcTest`에서도 이를 스캔하게 되어있음.
- 왠만하면 `@EnableJpaAuditing`은 따로 `@Configuration` 클래스를 마련해서 그쪽에 적용하자.