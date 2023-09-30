package 북스터디.five_lines_of_code._2주차;

public enum Input {
    LEFT(){
        @Override
        void handle() {
            moveHorizontal(-1);
        }
    },
    RIGHT(){
        @Override
        void handle() {
            moveHorizontal(1);
        }
    },
    UP(){
        @Override
        void handle() {
            moveVertical(-1);
        }
    },
    DOWN(){
        @Override
        void handle() {
            moveVertical(1);
        }
    },;

    abstract void handle();
}
