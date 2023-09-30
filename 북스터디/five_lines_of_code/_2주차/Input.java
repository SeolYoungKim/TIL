package 북스터디.five_lines_of_code._2주차;

public enum Input {
    LEFT(){
        @Override
        boolean isLeft() {
            return true;
        }

        @Override
        boolean isRight() {
            return false;
        }

        @Override
        boolean isUp() {
            return false;
        }

        @Override
        boolean isDown() {
            return false;
        }
    },
    RIGHT(){
        @Override
        boolean isLeft() {
            return false;
        }

        @Override
        boolean isRight() {
            return true;
        }

        @Override
        boolean isUp() {
            return false;
        }

        @Override
        boolean isDown() {
            return false;
        }
    },
    UP(){
        @Override
        boolean isLeft() {
            return false;
        }

        @Override
        boolean isRight() {
            return false;
        }

        @Override
        boolean isUp() {
            return true;
        }

        @Override
        boolean isDown() {
            return false;
        }
    },
    DOWN(){
        @Override
        boolean isLeft() {
            return false;
        }

        @Override
        boolean isRight() {
            return false;
        }

        @Override
        boolean isUp() {
            return false;
        }

        @Override
        boolean isDown() {
            return true;
        }
    },;

    abstract boolean isLeft();
    abstract boolean isRight();
    abstract boolean isUp();
    abstract boolean isDown();
}
