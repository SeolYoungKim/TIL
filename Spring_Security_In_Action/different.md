
☝ 책과 다른 내용이 많이 있다.

- WebSecurityConfigurerAdapter가 deprecated 됨으로써, Provider와 같은 애들을 그냥 빈으로 등록해주면 Manager가 알아서 사용한다는데, 그 기작을 잘 모른다. 이부분을 공부해봐야겠다.
- hmacShaKeyFor 는 짧은 글자를 key로써 사용할 수 없다. 적어도 256 bits (32 bytes)여야 한다.
- restTemplate가 deprecated 예정 객체라서, WebClient를 사용했다.
- SecurityConfig 적용 방식이 정말 많이 달라진 것 같다.
