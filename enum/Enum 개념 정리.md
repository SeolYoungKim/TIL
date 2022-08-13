# Enum 개념 정리

## 1. Enum이란?


- 서로 연관된 상수를 편리하게 선언하기 위한 것
- 여러 상수를 정의할 때 사용하면 유용함
- Enum이 갖는 값 뿐만 아니라 타입도 관리함 → 논리적인 오류 감소

```java
class Card {
	enum Kind { CLOVER, HEART }  // 열거형 Kind 정의
	enum Value { TWO, THREE }    // 열거형 Value 정의

	final Kind kind;
	final Value value;
}
```

<aside>
☝ **타입에 안전한 Enum**

- 실제 값이 같아도 타입이 다르면 컴파일 에러 발생
- 값 뿐만 아니라 타입까지 체크하기 때문에 타입에 안전하다고 하는 것

**중요!**

- 상수
    - 값이 바뀌면 해당 상수를 참조하는 모든 소스를 다시 컴파일 해야 함
- Enum
    - 기존의 소스를 다시 컴파일 하지 않아도 됨
</aside>

## 2. Enum의 정의와 사용



- 정의하는 방법
    
    ```java
    enum 열거형이름 { 상수명1, 상수명2, ... }
    
    // 예시
    enum Direction { EAST, WEST }
    ```
    
- 사용하는 방법
    
    ```java
    Direction dir1 = Direction.EAST;
    Direction dir1 = Direction.WEST;
    ```
    
- Enum 상수 간 비교
    - `==` 사용 가능 → 빠른 성능 제공
    - `<` , `>` 사용 불가능
    - `compareTo()` 사용 가능
        - 두 비교 대상이 같으면 0
        - 왼쪽이 크면 양수
        - 오른쪽이 크면 음수
    - if문 → 제약조건 없음
    - case문 → `Direction.EAST`라고 쓰면 안됨(제약조건) 무조건 `EAST`와 같이 상수의 이름만 사용해야 함

## 3. java.lang.Enum



```java
enum Direction { EAST, WEST }
```

- `values()` : Direction에 정의된 모든 상수를 배열로 반환
    
    ```java
    Direction[] dArr = Drection.values();
    ```
    
- `ordinal()` : Enum 상수가 정의된 순서(0부터 시작)를 정수로 반환
- `getDeclaringClass()` : Enum의 클래스 객체 반환
- `name()` : Enum의 상수 이름을 문자열로 반환
- `valueOf(Class<T> enumType, String name)` : 지정된 Enum에서 name과 일치하는 Enum 상수를 반환
    
    ```java
    Direction.valueOf("WEST") == Direction.WEST
    ```
    

## 4. Enum에 멤버 추가



- Enum 클래스에 정의된 ordinal() → Enum 상수가 정의된 순서를 반환
    - 하지만, 해당 값을 Enum 의 상수 값으로 사용하지 않는 것이 좋음
    - 해당 값은 내부적인 용도로만 사용하기 위한 것임
- Enum 상수 값이 불연속적인 경우
    - Enum 상수의 이름 옆에 원하는 값을 `()`와 함께 적어준다
        
        ```java
        enum Direction { EAST(1), WEST(-1) }
        ```
        
    - **지정된 값을 저장할 수 있는 인스턴스 변수와 생성자를 새로 추가해준다**
        
        <aside>
        ☝ **규칙**
        
        - 먼저 Enum 상수를 모두 정의한 다음 다른 멤버들을 추가해야 함
        - Enum 상수의 마지막에 `;`도 잊으면 안됨
        </aside>
        
    - 아래와 같이 정의한다
        
        ```java
        enum Direction {
        		EAST(1), WEST(-1);  // 가장 먼저 Enum 상수를 모두 정의한다.
        		
        		private final int value;  // Enum상수의 괄호에 있는 정수를 저장할 필드
        
        		Direction(int value) {  // 생성자 추가
        				this.value = value;
        		}
        
        		public int getValue() {
        				return value;
        		}
        }
        ```
        
        - Enum의 생성자는 외부에서 호출이 불가능하기 때문에, `getValue()`같은 외부 호출 메서드를 만들어주면 편하다.
            - Enum의 생성자는 제어자가 `private`이다.
    - 하나의 Enum 상수에 값을 여러개 지정할 수 있다.
        
        ```java
        enum Direction {
        		EAST(1, "동"), WEST(-1, "서");  
        		
        		private final int value;  
        		private final String str;
        
        		Direction(int value, String str) {  // 생성자 추가
        				this.value = value;
        				this.str = str;
        		}
        
        		public int getValue() {
        				return value;
        		}
        
        		public String getStr() {
        				return str;
        		}
        }
        ```
        

## 5. Enum에 추상 메서드 추가



```java
enum Transportation {
		BUS(100), SUBWAY(150);

		private final int BASIC_FARE;

		pribate Transportation(int basicFare) {
				BASIC_FARE = basicFare;
		}

		int fare() {
				return BASIC_FARE;
		}
}
```

- 운송 수단 종류 별 상수 정의
- 각 운송 수단에 기본 요금이 책정되어 있음

### 거리에 따라 요금을 계산하는 방식이 각 운송 수단마다 다를 경우

- 이런 경우 Enum에 추상 메서드 `fare(int distance)`를 선언하여 사용한다.
    - 이 추상 메서드는 Enum 상수가 반드시 구현해야 한다
        
        ```java
        enum Transportation {
        		BUS(100) {
        				int fare(int distance) { return distance * BASIC_FARE; }
        		}
        
        		SUBWAY(150) {
        				int fare(int distance) { return distance * BASIC_FARE; }
        		}
        			
        		abstract int fare(int distance);  // 거리에 따른 요금 계산
        	
        		protected final int BASIC_FARE;  // protected로 해야 Enum이 접근 가능
        
        		private Transportation(int basicFare) {
        				BASIC_FARE = basicFare;
        		}
        
        		int getBasicFare() {
        				return BASIC_FARE;
        		}
        }
        ```
        
    - Enum에 정의 된 추상 메서드를 각 상수가 어떻게 구현해야 하는지에 대한 예시임
    - 위처럼 정의된 Enum은 아래와 같이 사용한다
        
        ```java
        // 클래스의 필드에 접근하듯이 사용
        Transportation.BUS.fare(100);  // 100 = distance
        Transportation.SUBWAY.fare(100);
        
        -> BASIC_FARE가 각각 다르기 때문에, 각각 다른 요금이 나온다.
        ```
        

## 6. Enum의 이해



- Enum은 내부적으로 어떻게 구현되어 있는가?
    
    ```java
    enum Direction { EAST, WEST }
    ```
    
    - **Enum 상수 하나 하나가 Direction 객체**
        
        → Enum이 추상 메서드를 상수마다 구현해줘야 하는 이유이다.
        
- MyEnum이라는 예시로 Enum 원리 파헤치기
    
    ```java
    abstract class MyEnum<T extends MyEnum<T>> implements Comparable<T> {
    		static int id = 0; // 객체에 붙일 일련번호(0부터 시작함)
    		
    		int ordinal;
    		String name;
    
    		public int ordinal() {
    				return ordinal;
    		}
    
    		MyEnum(String name) {
    				this.name = name;
    				ordinal = id++;  // 객체를 생성할 때 마다 id 값 증가
    		}
    
    		public int compareTo(T t) {
    				return ordinal - t.ordinal();
    		}
    }
    ```
    
    - 객체가 생성될 때 마다 번호를 붙여서 인스턴스 변수인 `ordinal`에 저장
    - Comparable 인터페이스 구현 → Enum 상수 간 비교 가능
        - 두 Enum 간의 ordinal 값을 서로 빼준다
    - `MyEnum<T extends MyEnum<T>>`과 같이 적지 않으면, 타입 T에 `ordinal()`이 있는지 알 수 없기 때문에 이와 같이 타입 제약 조건을 걸어줘야 한다.
        - 이렇게 구성하면 형변환이 없어도 에러가 나지 않음
    

### 참고



- 자바의 정석 책

[Java Enum 활용기 | 우아한형제들 기술블로그](https://techblog.woowahan.com/2527/)
