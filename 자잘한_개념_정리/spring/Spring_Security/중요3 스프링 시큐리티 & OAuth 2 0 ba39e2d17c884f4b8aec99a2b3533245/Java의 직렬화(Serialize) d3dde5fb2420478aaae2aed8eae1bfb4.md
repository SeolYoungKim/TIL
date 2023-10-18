# Java의 직렬화(Serialize)

### Java의 직렬화(Serialize)

- 직렬화(Serialize)
    - 자바 시스템 내부에서 사용되는 Object 또는 Data를 외부의 자바 시스템에서도 사용할 수 있도록 byte 형태로 데이터를 변환하는 기술
    - JVM(Java Virtual Machine)의 메모리에 상주(힙 또는 스택)되어 있는 객체 데이터를 바이트 형태로 변환하는 기술
- 역직렬화(Deserialize)
    - byte로 변환된 Data를 원래대로 Object나 Data로 변환하는 기술
    - 직렬화된 바이트 형태의 데이터를 객체로 변환해서 JVM으로 상주시키는 형태
- 직렬화 조건
    - `Serializable` 상속
- 사용하는 이유
    - 복잡한 데이터 구조의 클래스 객체라도, 직렬화 기본 조건만 지키면 큰 작업 없이 바로 직렬화/역직렬화가 가능함
    - 데이터 타입이 자동으로 맞춰지므로, 관련 부분을 크게 신경쓰지 않아도 됨.
- 사용처
    - 서블릿 세션(Servlet session)
        - 세션을 서블릿 메모리 위에서 운용한다면 직렬화를 필요로 하지 않지만, 파일로 저장하거나 세션 클러스터링, DB를 저장하는 옵션 등을 선택하게 될 경우 세션 자체가 직렬화 된 후 저장되어 전달됨.
    - 캐시(Cache)
        - Ehcache, Redis, Memcached 라이브러리 시스템을 많이 사용함.
    - 자바 RMI(Remote Method Invocation)
        - 원격 시스템 간의 메시지 교환을 위해서 사용하는, 자바에서 지원하는 기술
- 주의
    - 외부 저장소로 저장되는 데이터는 짧은 만료 시간의 데이터를 제외하고, 자바 직렬화 사용을 지양함
    - 역직렬화 시, 반드시 예외가 생긴다는 것을 생각하고 개발할 것
    - 자주 변경되는 비즈니스적인 데이터에는 자바 직렬화를 사용하지 말것
    - 긴 만료 시간을 가지는 데이터는 JSON 등 다른 포맷을 사용하여 저장할 것

[Java의 직렬화(Serialize)란?](https://go-coding.tistory.com/101)

[[Java] 직렬화(Serialization)란 무엇일까?](https://devlog-wjdrbs96.tistory.com/268)