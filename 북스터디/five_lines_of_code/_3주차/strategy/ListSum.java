package 북스터디.five_lines_of_code._3주차.strategy;

import java.util.List;

public class ListSum {
    private final SumProcessor sumProcessor = new SumProcessor();
    private int accumulator;

    public ListSum(int accumulator) {
        this.accumulator = accumulator;
    }

    int process(List<Integer> numbers) {
        for (Integer number : numbers) {
            sumProcessor.processElement(number);
        }

        return accumulator;
    }
}
