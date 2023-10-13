package 북스터디.five_lines_of_code._3주차.strategy;

public class SumProcessor implements ElementProcessor {
    private int accumulator;

    public SumProcessor(int accumulator) {
        this.accumulator = accumulator;
    }

    public void processElement(Integer number) {
        accumulator += number;
    }

    public int getAccumulator() {
        return accumulator;
    }
}
