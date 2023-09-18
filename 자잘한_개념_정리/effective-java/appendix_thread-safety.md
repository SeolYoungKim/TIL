- 멀티스레드 환경 → 동시다발적으로 여러 스레드가 해당 코드를 실행할 수 있는 환경
    - 쓰레드에 안전할 경우, 멀티 스레드 환경에서도 우리가 예측 한대로 동작한다.
- 쓰레드 안전하게 만드는 법
    1. `synchronized` 키워드로 감싼다.
        
        ```java
        private int hashCode; // 자동으로 0으로 초기화된다.
        
        @Override 
        public int synchronized hashCode() {
        		int result = hashCode;
            if (result == 0) {
                result = Short.hashCode(areaCode);
                result = 31 * result + Short.hashCode(prefix);
                result = 31 * result + Short.hashCode(lineNum);
                this.hashCode = result;
            }
            return result;
        }
        ```
        
        - 메서드 단위로 이 키워드를 걸면, 성능상의 문제를 유발할 수 있다.
        - 일종의 락을 사용하는 개념이다.
    2. `double checked locking`
        
        ```java
        private volatile int hashCode; // 자동으로 0으로 초기화된다.
        
        @Override 
        public int hashCode() {
            if (this.hashCode != 0) {
                return hashCode;
            }
        
            synchronized (this) {  // 여기서 블록의 형태로 처리
                int result = hashCode;
                if (result == 0) {
                    result = Short.hashCode(areaCode);
                    result = 31 * result + Short.hashCode(prefix);
                    result = 31 * result + Short.hashCode(lineNum);
                    this.hashCode = result;
                }
                return result;
            }
        }
        ```
        
        - 특정 블록 만큼만 동기화가 걸린다.
        - if문으로 한번, synchronized 블록으로 한번 체크해서 double checked locking이라고 불린다.
        - volatile : 쓰레드 안전과 관련 있는 키워드
            - CPU 캐시
                - 여기에 저장된 데이터를 읽어올 때, 업데이트 했지만 예전에 캐시했던 데이터(유효하지 않은 데이터)를 가져올 수 있음.
            - 메인 메모리
                - Volatile 키워드를 지정하면, 해당 데이터는 메인 메모리에 저장된다.
                - 가장 최근에 업데이트된 데이터를 참조할 수 있게 해준다.
                    - 그래야 여러 쓰레드에서 업데이트 해도, 최근 데이터를 참조할 수 있다.
    3. 쓰레드 로컬 사용
        - `@Transactional` 사용할 때 사용함.
    4. 불변 객체로 만들기
    5. 태생적으로 synchronized가 도배된 클래스 사용
        - HashMap(쓰레드에 안전하지 않음) vs HashTable(쓰레드 안전)
    6. Concurrent 객체 사용
        - 동시 접근(멀티 쓰레드)이 가능하게 만든 Collections
        - 동시성 보장
