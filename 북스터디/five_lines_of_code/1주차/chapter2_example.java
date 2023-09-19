class Main {
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
}