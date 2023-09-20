package 북스터디.five_lines_of_code._1주차;

class Chapter2Ex {
    static class Refactoring1 {
        // before
        boolean checkValue(boolean str) {
            // 값 체크
            if (str != false) {
                // 반환
                return true;
            } else {  // 그렇지 않으면
                return str;
            }
        }

        // after refactoring
        boolean isTrue1(boolean bool) {
            if (bool) {
                return true;
            }

            return false;
        }

        // 단순화
        boolean isTrue2(boolean bool) {
            return bool;
        }
    }

    /**
     * 상속보다는 컴포지션
     */
    interface Bird {
        boolean hasBeak();

        boolean canFly();
    }

    /**
     * 상속을 이용하는 방법
     */
    static class CommonBird implements Bird {
        public boolean hasBeak() {
            return true;
        }

        public boolean canFly() {  // 범위가 제한되지 않은 불변 속성
            return true;
        }
    }

    static class Penguin1 extends CommonBird {  // Bird 인터페이스에 새로운 메서드가 추가될 경우 CommonBird만 컴파일 에러가 발생하며, Penguin에는 컴파일 에러가 발생하지 않음 -> 수정해야 함을 놓칠 수 있음 -> 심각한 에러 유발
        public boolean canFly() {  // 범위가 제한되지 않아 상속을 통해 얼마든지 변경될 수 있다.
            return false;
        }
    }

    /**
     * 컴포지션을 이용하는 방법
     */
    static class Penguin2 implements Bird {  // Bird 인터페이스에 새로운 메서드가 추가될 경우 컴파일 에러 발생 -> Penguin도 수정해야 함을 알 수 있음
        Bird bird = new CommonBird();

        public boolean hasBeak() {
            return bird.hasBeak();  // 컴포지션을 통해 commonBird의 hasBeak()메서드 이용
        }

        public boolean canFly() {
            return false;  // -> 결과적으로 CommonBird의 불변 속성이 깨지지 않았다.
        }
    }
}