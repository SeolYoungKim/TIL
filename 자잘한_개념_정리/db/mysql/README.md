# MySQL 
## 실행 계획
- SQL 쿼리를 옵티마이저가 어떻게 처리할지를 확인하는 방법 
- 인덱스를 제대로 타는지를 확인하자!
  - `EXPLAIN SELECT * FROM users WHERE id = 1;`
- MySQL의 실행 계획 컬럼
  - `select_type`: 쿼리의 종류 (일반 쿼리/서브 쿼리/Union 쿼리)
    - 쿼리를 다시 구성해야 하는 경우: `DEPENDENT`, `DERIVED` 
      - 서브 쿼리나 Union 쿼리일 확률이 높음
      - 단적으로 서브 쿼리를 피하라는 말 
  - `type`: 어떤 타입의 인덱스를 참조했는지
    - 실제로 우리 쿼리가 인덱스를 탔는지 안탔는지 알려주는 지표 
    - 쿼리를 다시 구성해야 하는 경우: `index`, `fulltext`, `all`
      - 인덱스 풀 스캔, 전문 검색 기능, 데이터 풀 스캔을 이용하게 됨 
        - `index` 타입은 인덱스를 풀 스캔하겠다는 의미임 
        - 일반적으로 인덱스를 탄다 = B-Tree를 위에서부터 아래로 가면서 원하는 인덱스를 사용한다는 뜻 
        - 인덱스 풀스캔 = 갖고있는 인덱스를 처음부터 끝까지 다 탐색했음 
      - 레코드가 몇개 없는 경우에는 풀스캔이 빨라, 인덱스가 있어도 `all`이 나오기도 함 
  - `key`: 어떤 인덱스를 사용했는지
    - 인덱스를 탔다면 어떤걸 사용했는가? 의도한 인덱스를 탔는가?
  - `extra`: 쿼리를 어떤 방식으로 풀었는지 
    - 쿼리를 어떤 방식으로 해결했는가? 
    - 쿼리를 다시 구성해야 하는 경우: `Full scan`, `Impossible`, `No matching`, `Using filesort`, `Using temporary`, `Using join buffer`
      - filesort -> 추가 정렬 
      - temporary -> 임시 temp 파일을 생성
      - join buffer -> Join이 인덱스를 사용할 수 없어 buffer를 사용하였음 

    
## 실무에서..
- 외래키는 공짜가 아니다 
    - 외래키를 걸면 쓰기 성능이 크게 저하됨
      - insert, update, delete 작업 시 락 경합을 유도 -> 성능 저하  
    - 외래키 없어도 조인을 걸 수 있음


- 한방 쿼리 지양 
  - 쿼리에 논리 로직을 넣으려하지 마세요 
    - case, if then, 서브쿼리 등 
  - 쿼리를 여러개로 쪼개고, 애플리케이션에서 조합하는게 나음 
    - DB가 병목인 케이스가 많다. 이는 DB에게 어려운 일을 주었기 때문.
    - 차라리 쉽고 빠른 일을 여러번 주는게 좋다.


- 서브 쿼리 지양
  - 스칼라 서브쿼리 (`select (select)`)
  - 인라인뷰 서브쿼리 (`from (select)`)
  - 중첩 서브쿼리 (`where (select)`)


- Fulltext index 사용 금지 
  - 전문 검색용 인덱스 
  - 전반적으로 한계가 많은 기능 
  - 인덱싱 데이터가 임계점을 넘어가면 재앙이 시작됨 
    - 문제가 발생하면 튜닝으로는 해결도 안됨 
    - 걍쓰지마셈


- In 쿼리
  - In 쿼리에 갯수가 많아지면 **풀 스캔을 수행**함 (개수는 DB마다 다름)
    - 쿼리를 메모리에 올리는 것 또한 제한이 걸리기 때문.
  - **In 안에 100개 이상이 넘어갈 것 같은 경우에는 쿼리를 쪼갤 것** 


- `%`는 뒤에 있을 때만
  - `like %`는 뒤에 있을 때만 인덱스를 태울 수 있음
    - `select * from users where name like '%a'`는 인덱스를 타지 못함
    - `select * from users where name like 'a%'`는 인덱스를 탐


- with Redis
    ```mermaid
    flowchart LR
        A[Client] -->|캐시 서버에 저장| B[Redis]
        B -->|배치로 sync| C[(DB)]
    ```
  - RDB만으로 모든 read/write를 감당하기 버거울 때 사용 
  - 중간에 cache를 두고, 스케줄링을 도는 배치 서버를 둬서 데이터를 주기적으로 맞추는 sync 전략을 사용할 수 있음
    - 이 전략을 `write-back`이라고 부름 


- Partition, Shard
  - 수평 파티셔닝  == Sharding
    - row를 파티셔닝 하는 것 
  - 수직 파티셔닝
    - column을 파티셔닝 하는 것