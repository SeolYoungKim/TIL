# 4. JPA

### Entity

- 실제 DB 테이블과 매핑되는 핵심 클래스
- 데이터베이스의 테이블에 존재하는 컬럼들을 필드로 가지는 객체
    - DB  테이블과 1:1로 매핑되기 때문에, 테이블이 가지지 않는 컬럼을 필드로 가져서는 안됨.
- 데이터베이스 영속성(persistent)의 목적으로 사용되는 객체
    - 요청이나 응답 값을 전달하는 클래스로 사용하는 것은 좋지 않음!
    - Setter 메서드의 사용을 지양해야 한다.
        - 객체(데이터)의 일관성, 안정성을 보장하기 힘들어지기 때문이다.
    - Setter 대신, Constructor 또는 Builder를 사용한다.
        - 디자인 패턴에서, Builder를 사용할 것을 권장하고 있다.
            - 생성자에 반드시 값을 순서대로 넣어주지 않아도 되기 때문이다. 세팅할 필드 값을 명시하여 적용할 수 있다.
            - 멤버 변수가 많아지더라도, 어떤 값을 어떤 필드에 넣는지 코드를 통해 확인할 수 있고, 필요한 값만 넣는 것이 가능하다.
            
            ```java
            @Builder
            @Getter
            @Entity
            @NoArgsConstructor
            public class Membmer {
                @Id
                @GeneratedValue(strategy = GenerationType.IDENTITY)
                private Long idx;
                private String name;
                private String email;
            
                public Member(Long idx, String name, String email) {
                    this.idx = idx;
                    this.name = name;
                    this.email = email;
                }
            }
            
            // 사용 방법
            Member member = Member.builder()
                    .name("Jan")
                    .email("Jan@Jan.com")
                    .build();
            ```
            

### DTO

- 계층(Layer) 간 데이터 교환이 이루어질 수 있도록 하는 객체
- JSON serialization과 같은 직렬화에도 사용되는 객체
- DAO(Data Access Object) 패턴에서 유래된 단어 → DAO에서 DB 처리 로직을 숨기고 DTO라는 결괏값을 내보내는 용도로 활용했었음.
- Controller 같은 클라이언트 단과 직접 마주하는 계층에서는 Entity 대신 DTO를 사용해서 데이터를 교환함.
- 주로 View와 Controller 사이에서 데이터를 주고받을 때 활용성이 높음.
- **Getter, Setter 메서드를 포함한다. Entity의 필드만을 가지며, 다른 비즈니스 로직은 포함하지 않는다.**
    - 뷰 템플릿과 컨트롤러가 서로 데이터를 주고받을 땐 Setter의 활성화가 필수이다.(Setter를 설정 하지 않으면 thymeleaf 뷰 템플릿에서 객체를 null로 넘겨준다.)

### 프로퍼티(property)

- 자바의 객체는 “고유한 속성(특징)”을 가진다. → 속성을 칭하는 단어를 프로퍼티라고 한다.
- 프로퍼티는 필드(데이터 멤버)와 메서드의 중간에 있는 클래스 멤버의 특수한 유형이다.
- 프로퍼티의 읽기, 쓰기
    - getter, setter 메소드 호출로 변환된다.
    - 이 속성의 실제 값을 담는 곳을 필드(field, 멤버 변수)라고 한다.
- 자바 빈 프로퍼티 규약

### VO(Value Object)

- 값 자체를 표현하는 객체
- 객체들의 주소 값이 달라도, 데이터 값이 같으면 동일한 것으로 여김.
- Getter 메서드와 비즈니스 로직은 포함할 수 있지만, Setter 메서드는 가지지 않음.
- 필요한 경우 값 비교를 위해 equals()와 hashCode()메서드를 오버라이딩 해줘야 함.
- 내용물 == 값 자체 ⇒ “Read Only” 특징

### Entity와 DTO를 분리하는 이유

- DB와 View 사이의 역할 분리를 하기 위해서이다.
- DTO
    - 각 계층끼리 주고받는 우편물이나 상자의 개념.
    - 목적 자체가 “데이터의 전달”
        
        → 읽고, 쓰는 것이 모두 가능하고, 일회성으로 사용되는 성격이 강함
        
- Entity
    - JPA 에서, Entity 객체는 실제 데이터베이스와 관련된 중요한 역할을 한다.
    - 내부적으로 EM(EntityManager)에 의해 관리된다.
    - DTO와는 다르게, Entity의 생명 주기(Life Cycle)도 전혀 다르다.
- 테이블에 매핑되는 정보와 실제 view에서 요청되는 정보가 다를 경우, 테이플에 필요한 정보에 맞게 데이터를 변환하는 로직이 필요할 수 있다.
    - 이 로직이 Entity에 그대로 들어가는 것은…깔끔하지 못하다.
- 또한, DB로부터 조회된 Entity를 그대로 view로 넘기게 되면 불필요한 정보나 노출되면 안 되는 정보까지 노출될 수 있어 보안상 위험하다.

## 연관관계 매핑

### 매핑 방식

<aside>
💡 N쪽에 foreign key가 위치하도록 한다.

</aside>

- 다대일 [N:1] : `@ManyToOne`
- 일대다 [1:N] : `@OneToMany`
- 일대일 [1:1] : `@OneToOne`
- 다대다 [N:M] : `@ManyToMany`

### 연관관계의 주인

- 테이블은 외래 키 하나로 두 테이블이 연관관계를 맺음
- 객체 양방향 관계는 A->B, B-> A처럼 참조가 2군데
- 객체 양방향 관계는 참조가 2군데 있다. 둘중 테이블의 **외래 키를 관리하는 곳을 지정**해야함A를 바꿀때 B도 같이 바꿀지 / B를 바꿀때 A도 같이 바꿀지
- 연관관계의 주인 : 외래 키를 관리하는 참조
- 주인의 반대편 : 외래 키에 영향을 주지 않음

### 애노테이션 관련

- `@JoinColumn`
    - Foreign key를 관리하는 쪽에 작성해준다.
    - `@JoinColumn(name = "Foreign key 이름")`
- `@OneToMany(mappedBy = "외래 키 소유 측의 연관관계 매핑 속성 이름")`
    - ex
    
    ```java
    // Foreign key owner
    @ManyToOne 
    @JoinColumn(name = "series_id")
    private SeriesEntity seriesEntity;
    
    // Reference
    @OneToMany(mappedBy = "seriesEntity")
    private List<ArticleEntity> articles = new ArrayList<>();
    ```
    
    [](https://www.baeldung.com/jpa-joincolumn-vs-mappedby)
    

### Test Code 작성 실패 및 삽질 경험

1. series Entity 만들어놓고 리포지토리 안만듬
2. series Entity에 `private List<ArticleEntity> articles = new ArrayList<>();` 라고 작성해서 nullporintexception이 다른 이유로 뜬줄알았음

```java
SeriesEntity series = SeriesEntity.builder()
        .series("시리즈1")
        .articles(new ArrayList<>())
        .build();
```

1. 근데 builder에 빈객체 넘겨주니까 괜찮아짐…ㅎㅎ;;;

### Cascade, FetchType, Transactional

위 세 단어가 두고두고 날 괴롭히고 있어서, 구글링을 했다.

- Cascade
    - 영속성 전이 라는 뜻
    - 특정 엔티티에 대해 특정 작업을 수행하면, 관련 엔티티에도 동일 작업을 수행함
    - 연관 엔티티 간에 의존성을 설정함
    - enum 형태의 CascadeType 제공
        - `CascadeType.ALL` : 부모 엔티티에서 자식 엔티티로 모든 작업을 전이
        - `CascadeType.PERSIST` : 엔티티 영속화에 대한 영속성 전이 (부모 엔티티 영속화(저장) → 자식 엔티티 영속화(저장))
        - `CascadeType.MERGE` : 엔티티 상태 병합 시, 연관된 엔티티도 모두 병합
        - `CascadeType.REMOVE` : 엔티티를 제거할 때, 연관된 엔티티도 제거
        - `CascadeType.DETACH` : 부모 엔티티 detach() → 연관 엔티티 detach() ⇒ 변경 사항을 반영하지 않음.
        - `CascadeType.REFRESH` : 부모 엔티티를 새로고침하면, 연관된 엔티티도 새로고침 됨.
- FetchType
    - Lazy
    - Eager
    - 참고
        
        [https://ict-nroo.tistory.com/132](https://ict-nroo.tistory.com/132) 
        
    

## 영속성 컨텍스트

**엔티티를 영구 저장하는 환경을 의미**

![Untitled](4%20JPA%20164f1a6f73b143748e4792a4339b84e7/Untitled.png)

- EntityManagerFactory
    - 요청 별로 EntityManager를 만들어낸다.
- EntityManager
    - 객체의 영속화를 관리한다.
    - 커넥션 풀을 사용한다.
    - Commit을 해야 비로소 DB에 저장된다.
    - 1차 캐싱 기능이 있다.
- Transaction
    - 작업은 한 트랜잭션 내에서 이뤄진다.
    - JPA의 모든 데이터 변경은 트랜잭션 안에서 이뤄진다.

![Untitled](4%20JPA%20164f1a6f73b143748e4792a4339b84e7/Untitled%201.png)

**엔티티 생명주기**

- 비영속
    - 영속성 컨텍스트와 전혀 관계가 없는 새로운 상태
- 영속
    - 영속성 컨텍스트에 의해 관리되는 상태
- 준영속
    - 영속성 컨텍스트에 저장되었다가 분리된 상태
- 삭제
    - 삭제된 상태