package 북스터디.five_lines_of_code._2주차;

public enum Input {
    LEFT(){
        void handleInput(Input input) {
            if (input.isLeft()) {
                moveHorizontal(-1);
            } else if (input.isRight()) {
                moveHorizontal(1);
            } else if (input.isUp()) {
                moveVertical(-1);
            } else if (input.isDown()) {
                moveVertical(1);
            }
        }

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
        void handleInput(Input input) {
            if (input.isLeft()) {
                moveHorizontal(-1);
            } else if (input.isRight()) {
                moveHorizontal(1);
            } else if (input.isUp()) {
                moveVertical(-1);
            } else if (input.isDown()) {
                moveVertical(1);
            }
        }

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
        void handleInput(Input input) {
            if (input.isLeft()) {
                moveHorizontal(-1);
            } else if (input.isRight()) {
                moveHorizontal(1);
            } else if (input.isUp()) {
                moveVertical(-1);
            } else if (input.isDown()) {
                moveVertical(1);
            }
        }

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
        void handleInput(Input input) {
            if (input.isLeft()) {
                moveHorizontal(-1);
            } else if (input.isRight()) {
                moveHorizontal(1);
            } else if (input.isUp()) {
                moveVertical(-1);
            } else if (input.isDown()) {
                moveVertical(1);
            }
        }

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
