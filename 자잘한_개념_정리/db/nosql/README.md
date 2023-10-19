# NoSQL
## 목적에 따라...
### 중앙화된 캐시
- Redis

### 검색
- ElasticSearch
- Solr

### 시계열 차트
- InfluxDB
- OpenTSDB
- Druid
- ElasticSearch, Redis...

## Key-Value DB
- Redis, Memcached
- 하나의 거대한 Map으로 구성되어 있다고 생각하면 됨 
- 메모리에 올라와 있어서 Disk I/O 없이 빠르게 데이터를 가져올 수 있음 

### Redis
- 싱글 스레드
  - 병렬 처리로부터 자유로워지면서, 상당한 고성능을 자랑함
- 샤딩, 클러스터 모드
  - 확장 전략 
- Keys 사용 금지

## Wide column DB
- Cassandra, HBase
- 대량의 데이터를 저장하는 데 특화됨 
  - 대량의 데이터를 동적인 column을 갖는 테이블에 Read/write 수행 
  - 데이터 압축 및 분산 처리에 특화 

### Cassandra
- Consistent hashing
  - 데이터를 분산 저장하기 위한 알고리즘
  - Hash 함수로 나온 값을 노드 별로 부담해서 분산 저장 
  - 노드 또한 가상의 v노드로 분할 -> v노드 기준으로 근처에 있는 hash 데이터를 관리 
- Partition key가 중요
  - 해시를 만드는 데 사용 
- 뛰어난 Write 성능 


## Document DB
- MongoDB, CouchDB, ElasticSearch
- JSON 형태로 데이터를 저장

### ElasticSearch
- 검색에 사용할 수 있는 DB
- 전문 검색 성능이 좋음 
- 인덱스라는 데이터 스키마를 사용 
- 인덱스가 많아지면 안됨 
- 엄밀히는 Update 동작이 없음
  - 데이터를 삭제하고 새로 만들어버림 
- 통계 내기에 유용함 


### MongoDB
- 확장성이 매우 높음 
- 트랜잭션 지원 
- 전문 검색 기능 제공 


## Graph DB
- Neo4j
- 사회적 관계망을 표현하는 데 있어서 사용하기 좋은 DB
- 팔로우와 같은 기능을 구현할 때 주로 사용 

## Message System
- DB라고 하기엔 약간 부족한...
- Kafka, RabbitMQ, ActiveMQ
- 거대한 Queue!

### RabbitMQ
- 자체 관리 페이지 GUI
- Topic exchange
- Fanout exchange

### Kafka
- Topic, Partition, Consumer Group, Consumer
- 파티션:컨슈머 = 1:1
  - 하나의 파티션에는 하나의 컨슈머만 붙을 수 있음 (같은 그룹 내에서는 중복해서 붙을 수 없음)
  - 다른 컨슈머 그룹의 컨슈머는 하나 이상 붙을 수 있음 (다른 그룹에 속한 컨슈머는 하나의 파티션을 공유할 수 있음)
- 파티션 < 컨슈머 는 의미없음
- 컨슈머 그룹
- 파티션 개수는 확장만 되고 축소가 안됨
  - 그러니 파티션 숫자는 처음부터 너무 큰 숫자를 잡지 말자 
- 파티션 내부의 순서는 보장됨
- 메시지 순서는 보장 못함 

## Etc...
- Lucene
  - 전문 검색 엔진 라이브러리
- ELK
  - ElasticSearch, Logstash, Kibana
- Hadoop
  - 빅 데이터 저장을 위한 오픈소스
  - HDFS + MapReduce 
- Zookeeper
  - 분산 환경의 코디네이션을 위한 트리 형테의 데이터 저장소 
  - Hadoop, Hbase, Hive ... 

