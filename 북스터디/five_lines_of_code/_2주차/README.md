# 4. 타입 코드 처리하기

## Introduction

### 이번 장에서 다룰 내용

- `if`문에서 `else`를 사용하지 말 것
    - `switch`를 사용하지 말 것
- `if`문 제거
    - 클래스로 타입 코드 대체
    - 클래스로의 코드 이관
- 메서드 전문화
- 인터페이스에서만 상속받을 것
- 메서드 인라인화 & 삭제 후 컴파일하기 > 불필요한 메서드 제거

## 4.1 간단한 if문 리팩터링

### 4.1.1 규칙: if문에서 else를 사용하지 말것

> 프로그램에서 이해하지 못하는 타입인지를 검사하지 않는 한, if문에서 else를 사용하지 마십시오.

- `if-else`는 코드에서 **결정이 내려지는 지점을 고정**함 -> **하드코딩 된 결정**
    - `if-else` 구문이 쓰여진 위치 이후에서는 다른 변형을 도입할 수 없음 -> 유연성 저하


- 사용자 입력이나 데이터베이스에서 값을 가져오는 등의 외부 데이터 타입인 경우
    - `if-else`를 피할 수 없는 경우가 있을 것
    - 이럴 경우, 외부 데이터 타입을 **제어 가능한 내부의 데이터 타입으로 매핑**해야 함


- 독립된 `if`문은 **검사**로 간주


- `if-else`문은 **의사 결정**으로 간주
    - 그러므로 해당 규칙에서는 `else`를 찾으면 됨
    - `else`를 제거하는 방향으로 리팩토링

```java
class ThisIsClass {
    // 리팩토링 전 
    double averageBeforeRefactoring(List<Double> numbers) {
        if (size(numbers) == 0) {
            throw new IllegalArgumentException("Empty list not allowed");
        } else {
            return sum(numbers) / size(numbers);
        }
    }

    // 리팩토링 후 
    double averageAfterRefactoring(List<Double> numbers) {
        validateNotEmpty(numbers);
        return sum(numbers) / size(numbers);
    }

    void validateNotEmpty(List<Double> numbers) {
        if (size(numbers) == 0) {
            throw new IllegalArgumentException("Empty list not allowed");
        }
    }
}
```

- 스멜 | 이른 바인딩 vs 늦은 바인딩
    - 이른 바인딩: `if-else`와 같은 의사결정 동작은 컴파일 시 처리 -> 애플리케이션에 고정 -> 재컴파일 없이는 수정 불가
        - 추가에 의한 변경 방해
    - 늦은 바인딩: 런타임에 동작이 결정 됨
        - 추가를 통한 변경 가능


- 규칙의 의도
    - `if`는 흐름을 제어한다 -> 다음에 실행할 코드를 결정한다
    - 하지만, 객체지향 프로그래밍에서는 **객체라는 더 강력한 제어 흐름 연산자**가 존재함
    - 인스턴스화 하는 클래스에 따라 실행할 코드를 결정

### 4.1.2 규칙 적용

- 클래스로 타입 코드 대체
    - `input == Input.LEFT` -> `input.isLeft()`
- 클래스로의 코드 이관
    - `if(input.isLeft())`와 같은 `if`문들을 클래스 내부로 이동
    - `if(true)...else if(false)...`로 변경
    - `if`문과 `else if`삭제
- 메서드의 인라인화
    - 메서드가 한 줄만 있는 경우 메서드 인라인화 수행
    - 주의: 동일한 추상화는 유지할 것

```java
// 인라인화를 수행하면 안 되는 메서드
class ThisIsClass {
    /**
     * 낮은 수준의 연산에 의존하는 경우, 인라인화 수행 금지
     * - 작업은 동일한 추상화 수준을 유지해야 함 
     * - 메서드는 의도를 확실히 전달해야 함 (낮은 수준의 연산은 의도가 제대로 전달되지 않을 가능성이 큼)
     */
    double absolute(double number) {
        return /*대충 엄청 복잡한 계산 로직*/;
    }
}
```

## 4.2 긴 if문의 리팩터링

- 4.1의 규칙을 차례로 적용하여 리팩터링 수행

### 4.2.2 리팩터링 패턴: 메서드 전문화

- 메서드를 일반화 하면..
    - 책임이 흐려짐
    - 다양한 위치에서 코드 호출 -> 문제 발생 여지가 있음
- 더 전문화된 메서드
    - 더 적은 위치에서 호출 -> 필요성이 없어질 경우 빠르게 제거 가능

```java
class ThisIsClass {
    // 일반적인 메서드 
    boolean canMove(Tile start, Tile end, int dx, int dy) {
        return dx * Math.abs(start.x - end.x) == dy * Math.abs(start.y - end.y)
                || dy * Math.abs(start.x - end.x) == dx * Math.abs(start.y - end.y);
    }

    // 전문 메서드 
    boolean rookCanMove(Tile start, Tile end) {
        return Math.abs(start.x - end.x) == 0
                || Math.abs(start.y - end.y) == 0;
    }
}
```

### 4.2.4 규칙: switch를 사용하지 말 것

- 정의
    - `default` 케이스가 없고, 모든 `case`에 반환 값이 있는 경우가 아니라면 `switch`를 사용하지 마십시오
- 설명
    - `switch`는 버그로 이어지는 두 가지 편의성을 허용한다 -> 이 부분이 문제
    - `default`
        - 이를 지정할 경우, 새로운 값이 추가된다 하더라도, 모든 `case`들에 대해 검증을 수행했는지 알기 어려워진다.
        - 새로 추가된 값에 대해 `case`를 지정하여 처리하려고 했던 것인지, `default`로 처리하려 했던 것인지에 대해 컴파일러는 알 수 없다.
    - `fall-through`방식
        - `break`키워드를 만나기 전까지 케이스를 연속 실행하는 방식
        - `break`를 누락하면 알기 어려움
    - 그러니 일반적으로 `switch`는 멀리하자
        - 만약 `switch`를 사용해야 한다면 `default`를 사용해선 안된다
        - `default`생략을 허용하지 않는 언어에서는 사용하지 말아야 한다
- 스멜
    - `switch`는 스멜의 이름
    - `switch`: 값 X를 처리하는 방법에 초점을 맞춤 (컨텍스트) -> 데이터에서 불변속성을 더 멀리 위치시켜 불변속성을 전역화한다
    - `class`: 값(객체)이 상황 X를 처리하는 방법에 초점을 맞춤

```java
class ThisIsClass {
    void switchMethod(Alphabet value) {
        switch (value) {
            A:
            // 값을 
            System.out.println("This is A");  // 처리하는 방법에 초점. Alphabet에 들어가면 좋을 메서드가 외부로 나옴 -> 불변 속성 전역화 
            break;
            B:
            System.out.println("This is B");
            break;
        }
    }

    void classMethod(Alphabet value) {
        value.print();  // 값이 상황을 처리하는 방법에 초점 
    }
}
```

## 4.3 코드 중복 처리

### 4.3.1 인터페이스 대신 추상 클래스를 사용할 수는 없을까?

- 인터페이스
    - 구현해야 할 속성을 강제화 하여 잊지 않게 해줌
    - 해서는 안되는 오버라이드(재정의) 방지
        - Q: 요즘 java에는 default 메서드가 추가되어 오버라이드가 되지 않나..? 보통 인터페이스의 default 메서드를 오버라이드하는 경우가 없어서 그런가
- 추상클래스
    - 구현해야 할 속성을 강제화 할수는 있음
    - Q: 추상클래스를 정말로 사용하지 않는가?

### 4.3.2 규칙: 인터페이스에서만 상속받을 것

- 정의
    - 상속은 오직 인터페이스를 통해서만 받는다
- 설명
    - 추상클래스를 사용하는 이유
        - 공통 필드 제공
        - 일부 메서드 기본 구현 제공 및 다른 메서드는 추상화
    - 단점
        - 코드 공유는 강한 결합을 유발
- 스멜
    - 상속보다는 컴포지션
    - 코드 공유가 필요할 경우에는 컴포지션을 이용하자

## 4.5 필요 없는 코드 제거

- 관련 없는 코드를 더 빨리 제거할수록, 비용과 노력 측면에서 프로세스에 들어가는 비용이 낮아짐 