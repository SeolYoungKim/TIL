package 북스터디.five_lines_of_code._2주차;

public class User {
    private int horizontalLocation;
    private int verticalLocation;

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

    private void moveHorizontal(int value) {
        horizontalLocation += value;  // 이건 사실 동시성 이슈가 발생할 수 있는 코드임을 인지... (그냥 예시라서 고려안함)
    }

    private void moveVertical(int value) {
        verticalLocation += value;
    }
}
