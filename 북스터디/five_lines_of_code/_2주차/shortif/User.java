package 북스터디.five_lines_of_code._2주차.shortif;

public class User {
    private int horizontalLocation;
    private int verticalLocation;

    /**
     * 만약, handleInput 메서드를 사용하는 곳이 있다면, 메서드 인라인화를 통해 한줄로 만들 수 있다.
     */
    void handleInput(Input input) {
        /*
         * 이건 매우 좋지 않은 코드인 것 같다. 지금은 책을 따라가며 예제를 구현하기에 이렇게 구현했으나,
         * 사실 User 객체의 상태값을 변경할 수 있는 것은 user여야 한다.
         * 이부분도 고민할만한 부분인 것 같은데, 일단은 책의 내용에 집중하여 따라가 보도록 하자.
         * - Input에 moveHorizontal 등을 구현하는 것이 별로인 선택인 것 같아서 이와 같이 구성해 두었다.
         */
        input.handle(this);
    }

    void moveHorizontal(int value) {
        horizontalLocation += value;  // 이건 사실 동시성 이슈가 발생할 수 있는 코드임을 인지... (그냥 예시라서 고려안함)
    }

    void moveVertical(int value) {
        verticalLocation += value;
    }
}
