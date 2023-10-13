package 북스터디.five_lines_of_code._3주차.strategy;

import java.util.List;

public class ListMinimum {
    private int accumulator;

    public ListMinimum(int accumulator) {
        this.accumulator = accumulator;
    }

    int process(List<Integer> numbers) {
        for (Integer number : numbers) {
            if (accumulator > number) {
                accumulator = number;
            }
        }

        return accumulator;
    }
}
