원티드 프리온보딩 챌린지 백엔드 코스라는게 있어 참여를 해보기로 했습니다.

여기서는 5개의 사전 과제를 주었는데, 그 중 하나가 "본인이 주력으로 사용하는 언어에서 자료구조와 관련 된 클래스가 내부적으로 어떻게 동작하는지 한 가지 사례를 정하여 작성"하는 것이었습니다.

저는 제가 최근에 주로 많이 사용하는 ArrayList를 분석해보고자 했고, 아래는 분석 내용입니다.

### ArrayList
- 선정 이유 : ArrayList는 제가 가장 많이 사용했던 Collection입니다. 이를 더 자세히 알아보고 사용하는 게 좋다고 판단하여, 이번 기회에 알아보기로 결정하였습니다.

### 사례
ArrayList에 백엔드 관련 프레임워크들을 넣고자 합니다. 기술 스택은 `String` 타입이고, 총 5개가 있습니다.
해당 자료들을 ArrayList에 담아두고, 추후 웹 페이지에서 table로 보여 줄 예정입니다.
```
"SpringBoot", "Node.js", "Django", "Ruby on Rails", "Laravel"
```

#### 1. 생성
해당 자료들을 넣기 위해, ArrayList를 새로 만들겠습니다.
```java
ArrayList<String> arrList = new ArrayList<>();
```
- new 생성자를 통해 ArrayList를 생성했습니다.
- String 타입의 자료형을 넣을 예정이므로, 지네릭스는 String으로 설정했습니다.
- 생성자에 파라미터를 아무것도 넘겨주지 않았기 때문에, default 생성자 기준으로 ArrayList가 생성됩니다.

```java
public ArrayList() {  
    this.elementData = DEFAULTCAPACITY_EMPTY_ELEMENTDATA;  
}
```
- 기본 생성자입니다. `elementData`필드에 `DEFAULTCAPACITY_EMPTY_ELEMENTDATA`를 할당해줍니다.

```java
transient Object[] elementData;

private static final Object[] DEFAULTCAPACITY_EMPTY_ELEMENTDATA = {};
```
- `elementData` 는 ArrayList의 요소가 저장되는 배열 버퍼입니다. transient 타입으로 설정되어 있는데, 이는 직렬화 대상에서 제외된다는 뜻입니다. 하지만, 직렬화가 불가능하다는 것은 아니며, ArrayList는 이에 따라 다른 직렬화 로직을 따르게 됩니다. (공부해야 할 내용이 많은 부분이기 때문에 여기까지만 살펴보았습니다.)
- `DEFAULTCAPACITY_EMPTY_ELEMENTDATA`는 위와 같이 비어있는 Object 배열입니다.
- 이 필드가 ArrayList의 역할을 할 필드이며, 우리가 자료를 추가하고 삭제하면 위 배열에 추가되고 삭제됩니다.

>여기서는 기본 생성자만 다룰 예정이지만, 배열의 초기 크기 값을 생성자에 넘겨줄 수도 있고, `Collection`을 넘겨주어 이를 ArrayList로 변환 할 수도 있습니다. 


#### 2. 자료 추가
새로 만든 ArrayList에 자료들을 차례로 넣겠습니다.
```java
arrList.add("SpringBoot");
arrList.add("Node.js");
arrList.add("Django");
arrList.add("Ruby on Rails");
arrList.add("Laravel");
```

넣은 데이터들은 ArrayList의 안쪽 어딘가 저장이 될텐데, 어디에 어떻게 저장되는지 확인하기 위해, 
add Method를 먼저 확인해보겠습니다.
```java
public boolean add(E e) {  // E : String, e = "SpringBoot"
    modCount++;  
    add(e, elementData, size);  // add("SpringBoot", elementData, 0)
    return true;
}
```
- `modCount`를 증가시키고,
- `add(e, elementData, size)`함수를 실행하고,
- 마지막으로 true를 리턴합니다.

원리를 전체적으로 깨우치기 전에, 요소 별로 세세히 알아보기로 했습니다.
1. `size` 
	- size는 ArrayList의 size를 의미합니다. "포함하는 요소의 수"이며, 현재는 아무것도 없기 때문에 0입니다.
1. `modCount`
	- modCount란, ArrayList 자료구조에 얼마나 수정이 되었는지에 대한 횟수를 체크하는 필드입니다. 이는 ArrayList가 상속한 `AbstractList`라는 클래스에 있는 필드입니다.
	- ArrayList 등의 자료 구조는, for each문을 도는 동안 변형이 오면 안됩니다.
	- 예기치 않은 변형, 예를 들어 순서가 존재하는 ArrayList 등에서 첫 번째 index부터 삭제할 경우, 해당 필드에 의해 `ConcurrentModificationException` 예외가 발생합니다.
		-> 이는 `ListItr` 클래스에 존재하는 `expectedModCount`라는 필드와 비교되며, `modCount`와 `expectedModCount`가 서로 값이 다를 경우 발생되는 예외입니다.
	- `modCount`와 `expectedModCount`값은, iterator가 돌 때, 즉 ArrayList를 for each문을 돌릴 때 사용됩니다. 대략적인 구동 방식은 다음과 같습니다.
		- iterator가 새로 생성이 될 때, `expectedModCount` 는 `modCount`값으로 초기화 됩니다.
		- iterator가 `next()`를 할 때마다, `modCount`와 `expectedModCount`를 비교하고, 중간에 데이터에 변화가 있었는지 체크합니다.
		- 만약 같지 않다면 데이터에 변화가 있었다고 생각하고 `ConcurrentModificationException`을 던집니다.
	- 즉, 해당 필드는 ArrayList 자료구조에 변경이 발생했는지 여부를 체크하는 필드입니다. 일종의 안전장치를 해둔 것 같습니다.

추후 변경 사항을 체크할 modCount를 증가시킨 후에는 `add(e, elementData, size);` 를 통해 또 다른 add 메서드를 실행합니다.
```java
private void add(E e, Object[] elementData, int s) {  // e = "SpringBoot", s = 10
    if (s == elementData.length)  
        elementData = grow();  
    elementData[s] = e;  
    size = s + 1;  
}
```
- 먼저, s와 `elementData.length`가 같은지 확인합니다.
	- `s = size = 0` 이기 때문에, `elementData.length`와 동일하므로, 아래 구문이 수행됩니다.
	- `grow()`가 수행되어, 결과적으로 `elementData`의 size를 늘립니다. 결과적으로 elementData의 size는 `DEFAULT_CAPACITY`인 10이 됩니다.
	- 구동 방식은 아래와 같습니다.
```java
// grow() 구동 방식

private Object[] grow() {  
    return grow(size + 1); // 파라미터에 size + 1을 넘겨 오버로딩 된 메서드를 수행합니다. 
}

/**
* Arrays.copyOf(T[], newLength)를 수행하여 현재 elementData의 값을 복사하고,
* newCapacity(minCapacity)값을 넘겨 새로운 길이를 가지는 배열을 생성합니다.
* 새로 생성한 배열을 기존의 elementData에 덮어씌우고 반환합니다.
*/
private Object[] grow(int minCapacity) {  // minCapacity = 1
    return elementData = Arrays.copyOf(elementData,  
                                       newCapacity(minCapacity));  
}

/**
* 새로운 capacity 부여
* 주어진 최소 capacity만큼 큰 capacity를 반환함
* 충분할 경우 현재 용량에 50% 증가하여 반환
* 주어진 최소 용량이 MAX_ARRAY_SIZE보다 크지 않을 경우, 
* MAX_ARRAY_SIZE보다 큰 capacity를 반환하지 않는다.
*/
private int newCapacity(int minCapacity) {  // minCapacity = 1
    // overflow-conscious code  
    int oldCapacity = elementData.length;  // 0
    
    // >> 는 1bit를 오른쪽으로 밀어낸다는 뜻이다.
    // 이진수로 1bit는 2이므로, 한 칸을 밀어낼 경우 해당 값의 1/2가 된다.
    // 예외적으로 1을 1칸 오른쪽으로 밀면 0이다.
    // 즉, 현재 capacity의 50%만큼 증가한 값이다.
    int newCapacity = oldCapacity + (oldCapacity >> 1); 
    
    if (newCapacity - minCapacity <= 0) { 
        if (elementData == DEFAULTCAPACITY_EMPTY_ELEMENTDATA)  
            return Math.max(DEFAULT_CAPACITY, minCapacity);  // 첫번째 수행: 해당 값 반환 
        if (minCapacity < 0) // overflow  
            throw new OutOfMemoryError();  
        return minCapacity;  
    }  
    
    return (newCapacity - MAX_ARRAY_SIZE <= 0)  
        ? newCapacity  
        : hugeCapacity(minCapacity);  
        // hugeCapacity(): newCapacity의 값을 최대 Integer.MAX_VALUE로 보정한다.
}

```
- 그리고 elementData 배열의 s index에 우리가 넘겨준 값을 저장합니다.
- 마지막으로 ArrayList의 size를 1 늘리고 메서드를 종료합니다.

나머지 4개의 값은 사이즈를 늘리는 로직 없이 add 로직만 수행됩니다. 만약, 현재 할당된 size 값인 10과 ArrayList 안 요소의 개수가 같아진다면, 다시 `grow()` 로직을 통해 배열의 사이즈를 늘립니다.


#### 3. 자료 조회
자료가 제대로 잘 추가가 되었는지 확인하고자 합니다. 주로 사용되는 `get(index num)`을 살펴보겠습니다.
```java
arrList.get(0);  // 결과적으로 "SpringBoot"가 조회됩니다.
```

구동 방식입니다.
```java
public E get(int index) {  
    Objects.checkIndex(index, size);  
    return elementData(index);  
}

// Objects.checkIndex()
public static int checkIndex(int index, int length) {  
    return Preconditions.checkIndex(index, length, null);  
}

// Preconditions.checkIndex()
public static <X extends RuntimeException>  
int checkIndex(int index, int length,  
               BiFunction<String, List<Integer>, X> oobef) {  
    if (index < 0 || index >= length)  
        throw outOfBoundsCheckIndex(oobef, index, length);  
    return index;  
}
```
- 위의 get 메서드가 수행됩니다.
- `Objects.checkIndex(index, size)`를 통해 index가 올바른지 체크합니다.
	- `Objects.checkIndex(index, size)`는 `Preconditions.checkIndex(index, length, null)`로 업무를 위임합니다.
	-  `Preconditions.checkIndex(index, length, null)`은 index가 length보다 큰지, 혹은 0보다 작은지를 체크하고, length보다 크거나 0보다 작다면 `outOfBoundsCheckIndex()`예외를 던집니다.
	- 문제가 없다면 index를 반환해줍니다.
	- 반환된 index를 이용하여 `elementData`에서 이 index에 해당하는 값을 찾아 반환해줍니다.

참고로 `indexOf(Object o)`를 이용하면 특정 자료의 index 번호를 조회할 수 있습니다.
```java
public int indexOf(Object o) {  
    return indexOfRange(o, 0, size);  // 모든 자료를 탐색합니다.
}  

// 찾고자 하는 Object가 null이라면 equals() 메서드를 사용하지 못합니다.(NullPointerException)
// equals()를 사용하는 이유는, 참조값이 아닌 "값 자체"를 비교하기 위함입니다.
int indexOfRange(Object o, int start, int end) {  
    Object[] es = elementData;  
    if (o == null) {  // object가 null일 경우 
        for (int i = start; i < end; i++) {  
            if (es[i] == null) {  
                return i;  
            }  
        }  
    } else {  // object가 null이 아닐 경우
        for (int i = start; i < end; i++) {  
            if (o.equals(es[i])) {  
                return i;  
            }  
        }  
    }  
    return -1;  // object가 없을 경우
}
```


#### 4. 자료 삭제
"SpringBoot"를 기술 스택 목록에서 제거하고자 합니다.
ArrayList의 자료를 삭제하기 위해선 두 가지 방법이 있습니다.
```java
// Object로 삭제
arrList.remove("SpringBoot");

// index 번호로 삭제
arrList.remove(0);
```

첫번째로, Object로 삭제하는 방법부터 살펴보겠습니다.
```java
// 1. Object로 삭제
public boolean remove(Object o) {  
    final Object[] es = elementData;  
    final int size = this.size;  
    int i = 0;  
    found: {  
        if (o == null) {  // Object가 null일 경우
            for (; i < size; i++)  
                if (es[i] == null)  
                    break found;  
                    
        } else {  // Object가 null이 아닐 경우
            for (; i < size; i++)  
                if (o.equals(es[i]))  
                    break found;  
        }  
        return false;  // 없을 경우
    }  
    fastRemove(es, i);  // Object에 해당하는 index를 찾은 후 fastRemove 메서드 수행
    return true;
}

private void fastRemove(Object[] es, int i) {  // 배열과 index 번호를 넘김
    modCount++;  // 자료 구조가 변경되었으므로, modCount를 증가시킵니다.
    final int newSize;  
    if ((newSize = size - 1) > i)  // 배열의 마지막 값은 이 작업을 안해도 된다.
        System.arraycopy(es, i + 1, es, i, newSize - i); 
        // 원본 배열의 i+1의 값을 복사할 배열의 i의 값에 복사
    es[size = newSize] = null;  // 그리고 배열의 마지막 값을 null로 바꾼다.
}

/**
* Params:
* src - 원본 배열
* srcPos - 원본 배열의 복사 시작 위치. 여기서부터 전부 복사합니다.
* dest - 복사할 배열
* destPost - 복사할 배열의 복사 시작 위치. 여기서부터 전부 덮어씁니다.
* length - 복사할 요소의 개수
*/
@HotSpotIntrinsicCandidate  
public static native void arraycopy(Object src,  int  srcPos,  
                                    Object dest, int destPos,  
                                    int length);

```
- 간단한 메커니즘은 다음과 같습니다.
	- Object에 해당하는 index를 찾습니다.
	- 찾은 index에 해당하는 자료를 삭제하고, 나머지를 앞으로 땡겨옵니다.
	- 배열의 마지막 값을 null로 변경합니다.
	
자세한 메커니즘을 살펴보겠습니다.
- Object에 해당하는 index를 찾는 로직은 쉽습니다. 배열을 돌면서 값에 해당하는 index를 리턴합니다.
- `fastRemove`라는 별도의 메서드를 마련하여 제거 로직을 수행합니다. 
	- 이 때, 삭제하고자 하는 값이 배열의 마지막 값이라면, 추가적인 로직을 수행하지 않으며, 해당 값을 null로 변경하기만 합니다.
	- 배열의 마지막 값이 아닐 경우에는 `System.arraycopy()`메서드를 수행합니다.![](https://s3.us-west-2.amazonaws.com/secure.notion-static.com/448b8419-57bb-431c-96e2-1814e3bb3e1f/Untitled.png?X-Amz-Algorithm=AWS4-HMAC-SHA256&X-Amz-Content-Sha256=UNSIGNED-PAYLOAD&X-Amz-Credential=AKIAT73L2G45EIPT3X45%2F20220927%2Fus-west-2%2Fs3%2Faws4_request&X-Amz-Date=20220927T052500Z&X-Amz-Expires=86400&X-Amz-Signature=ec2922403a8214e768071e28ddfb331ce151c1959bd7114756f7c943b850c06c&X-Amz-SignedHeaders=host&response-content-disposition=filename%20%3D%22Untitled.png%22&x-id=GetObject)
		- 위와 같은 메커니즘으로 값이 복사됩니다. 결과적으로 기존의 Array에 있는 값들은 우측과 같이 변경됩니다.
	- 그 후, `es[size = newSize] = null;` 을 통해 배열의 마지막 값을 null로 변경합니다. 
- `remove()`메서드를 이용한 값 삭제는, 사실 삭제가 아닌 복사를 활용한 변경에 가깝다고 볼 수 있겠습니다.

두 번째로, index 번호로 삭제하는 방법입니다.
```java
// 2. index 번호로 삭제
public E remove(int index) {  
    Objects.checkIndex(index, size);  
    final Object[] es = elementData;  

	// 해당 애노테이션은 unchecked 경고 메세지를 억제해줍니다.
	// 타입 안정성이 확실할 때만 사용해야 합니다.
    @SuppressWarnings("unchecked") E oldValue = (E) es[index];  
    fastRemove(es, index);  
  
    return oldValue;  
}
```
- Object로 삭제하는 방법과 큰 차이는 "index값을 탐색하지 않아도 된다."는 점입니다. 때문에, index를 탐색하는 로직이 필요없습니다. 
- 다만, index 값이 유효한지 판단해야 하기 때문에, `Objects.checkIndex()`메서드를 이용합니다.
- Object를 이용한 삭제 방법과 똑같이 `fastRemove()`메서드를 이용하며, 메커니즘은 동일합니다.


여기까지 `ArrayList`의 추가, 조회, 삭제에 대한 기본적인 동작 방식들을 살펴보았습니다.

추가적으로, `ArrayList`에는 

- Collection을 통째로 추가하거나, 제거
- 특정 Object가 포함되어있는지 여부를 `boolean`으로 반환
- `sort()`를 통한 배열 정렬

등의 다양한 기능이 많이 있습니다. 추가, 제거 로직은 위에 설명드린 로직과 크게 다르지 않기 때문에 따로 다루지 않았습니다. 정렬 또한 다뤄보고자 했으나, 글이 너무 길어질 것 같아 따로 분리하여 추후 정리해보고자 합니다.
