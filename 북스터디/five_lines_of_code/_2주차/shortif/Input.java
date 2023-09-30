package 북스터디.five_lines_of_code._2주차.shortif;

public enum Input {
    LEFT(){
        @Override
        void handle(User user) {
            user.moveHorizontal(-1);
        }
    },
    RIGHT(){
        @Override
        void handle(User user) {
            user.moveHorizontal(1);
        }
    },
    UP(){
        @Override
        void handle(User user) {
            user.moveVertical(-1);
        }
    },
    DOWN(){
        @Override
        void handle(User user) {
            user.moveVertical(1);
        }
    },;

    abstract void handle(User user);
}
