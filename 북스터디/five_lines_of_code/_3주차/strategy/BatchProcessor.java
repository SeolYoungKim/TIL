package 북스터디.five_lines_of_code._3주차.strategy;

import java.util.List;

public class BatchProcessor {
    private final ElementProcessor elementProcessor;

    public BatchProcessor(ElementProcessor elementProcessor) {
        this.elementProcessor = elementProcessor;
    }

    int process(List<Integer> numbers) {
        for (Integer number : numbers) {
            elementProcessor.processElement(number);
        }

        return elementProcessor.getAccumulator();
    }
}
