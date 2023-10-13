package 북스터디.five_lines_of_code._3주차.strategy;

import java.util.List;

public class ListSum {
    private final SumProcessor sumProcessor;

    public ListSum(int accumulator) {
        this.sumProcessor = new SumProcessor(accumulator);
    }

    int process(List<Integer> numbers) {
        for (Integer number : numbers) {
            sumProcessor.processElement(number);
        }

        return sumProcessor.getAccumulator();
    }
}
