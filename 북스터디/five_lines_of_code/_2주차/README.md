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
