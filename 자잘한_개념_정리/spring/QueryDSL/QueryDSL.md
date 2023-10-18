# 9. Query DSL
- Query DSL은 JPQL 빌더다! 

```java
public List<Article> getList(int page) {
    return jpaQueryFactory.selectFrom(article)
            .limit(10)
            .offset((long) (page - 1) * 10)
            .fetch();
}
```

- `limit` : 페이지 사이즈 (몇 개를 보여줄건지)
- `offset` : 시작 지점 (0부터 시작함. 0, 1, 2, 3…)
    - 그래서, page =1 이 들어올 경우 offset=0 이 된다. (시작 지점이 0이므로)
        
        ⇒ 그러면, 0번째 부터 9번째 까지 10개를 보여준다.
        
    - page=2 → offset=10
        
        ⇒ 10번째 부터 19번째 까지 10개를 보여준다.
        

### fetch

1. `fetch()` : 리스트로 결과를 반환한다. 데이터가 없을 경우, 빈 리스트를 반환한다.
2. `fetchOne()` : 단건 조회 시 사용한다. 결과가 없을 때는 null을 반환하며, 2개 이상일 때는 NonUniqueResultException을 던진다.
3. `fetchFirst()` : 처음의 한 건을 가져온다. `.limit(1).fetchOne()`과 같다.
4. `fetchResults()` : 페이징을 위해 사용될 수 있다. 페이징을 위한 total contents를 가져온다. 즉, 조회한 리스트 + 전체 개수를 포함한 QueryResults를 반환한다. count 쿼리가 추가로 실행된다.
5. `fetchCounts()` : count 쿼리를 날린다. 개수 조회 및 long 타입 반환.

### sort

1. 내림차순 정렬 (`desc()`) : `.orderBy(article.id.desc())`
2. 오름차순 정렬 (`asc()`) : `.orderBy(article.id.asc())`
3. 여러 조건으로 정렬 : `.orderBy(article.id.desc(), article.title.asc())`
4. null 처리
    1. null이 있을 때, 가장 뒤에 나오도록 하려면 `nullsLast()`
        
        : `.orderBy(article.id.desc(), article.title.asc().nullsLast())`
        
    2. null이 있을 때, 가장 앞에 나오도록 하려면 `nullsFirst()`
        
        : : `.orderBy(article.id.desc(), article.title.asc().nullsFirst())`
        

### paging

```java
QueryResults<Member> result = queryFactory
		.selectFrom(member)
		.orderBy(member.userName.desc())
		.offset(1)
		.limit(2)
		.fetchResults();

long total = result.getTotal(); //전체 컨텐츠 갯수
long limit = result.getLimit(); // 현재 제한한 갯수
long offset = result.getOffset(); // 조회 시작 위치
List<Member> results = result.getResults(); // 조회된 컨텐츠 리스트
```

```java
public Page<Membmer> search(Pageable pageable) {
	List<Membmer> content = queryFactory
                .select(member)
                .from(member)
                .leftJoin(member.team, team)
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();

        JPAQuery<Member> countQuery = queryFactory
                .select(member)
                .from(member)
                .leftJoin(member.team, team);

        return PageableExecutionUtils.getPage(content, pageable, countQuery::fetchCount);
}
```

### aggregation

- QueryDSL에서는 SQL 집계 기능을 사용할 수 있다.
- `groupBy()`, `having()`

```java
List<Tuple> result = queryFactory
		.select(
				team.name,
				member.count(),
				member.age.avg(),
				member.age.sum(),
				member.age.min(),
				member.age.max()
		)
		.from(member)
		.join(member.team, team)
		.groupBy(team.name)
		.having(team.name.eq("a"))
		.fetch();

Tuple teamA = result.get(0);

String teamAName = teamA.get(team.name);
Long teamACount = teamA.get(member.count());
Double teamAAvgAge = teamA.get(member.age.avg());
Integer teamASum = teamA.get(member.age.sum());
Integer teamAMin = teamA.get(member.age.min());
Integer teamAMax = teamA.get(member.age.max());
```

### 참고자료

---

[JPA N+1 문제 및 해결방안](https://jojoldu.tistory.com/165)

[[querydsl] queryDsl 기본 문법 정리 - fetch, sort, paging, aggregation](https://devkingdom.tistory.com/243)

[[JPA] QueryDSL Fetch & FetchJoin](https://velog.io/@moonyoung/JPA-QueryDSL-Fetch-FetchJoin)