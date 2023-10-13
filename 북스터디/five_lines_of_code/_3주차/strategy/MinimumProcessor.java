package 북스터디.five_lines_of_code._3주차.strategy;

public class MinimumProcessor {
    private int accumulator;

    public MinimumProcessor(int accumulator) {
        this.accumulator = accumulator;
    }

    public void processElement(Integer number) {
        if (accumulator > number) {
            accumulator = number;
        }
    }

    public int getAccumulator() {
        return accumulator;
    }
}
