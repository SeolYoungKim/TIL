class Refactoring1 {
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
    boolean isTrue(boolean bool) {
        if (bool) {
            return true;
        }

        return false;
    }

    // 단순화
    boolean isTrue(boolean bool) {
        return bool;
    }
}

/**
 * 상속
 */
interface Bird {
    boolean hasBeak();

    boolean canFly();
}

class CommonBird implements Bird {
    boolean hasBeak() {
        return true;
    }

    boolean canFly() {  // 범위가 제한되지 않은 불변 속성
        return true;
    }
}

class Penguin extends CommonBird {
    @Override
    public boolean canFly() {  // 범위가 제한되지 않아 상속을 통해 얼마든지 변경될 수 있다.
        return false;
    }
}