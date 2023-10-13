package 북스터디.five_lines_of_code._3주차.strategy;

import java.util.List;

public class ListMinimum {
    private final MinimumProcessor minimumProcessor;

    public ListMinimum(int accumulator) {
        this.minimumProcessor = new MinimumProcessor(accumulator);
    }

    int process(List<Integer> numbers) {
        for (Integer number : numbers) {
            minimumProcessor.processElement(number);
        }

        return minimumProcessor.getAccumulator();
    }
}
