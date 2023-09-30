package 북스터디.five_lines_of_code._2주차;

import java.util.List;

public class RemoveElseExample {
    double averageBeforeRefactoring(List<Double> numbers) {
        if (size(numbers) == 0) {
            throw new IllegalArgumentException("Empty list not allowed");
        } else {
            return sum(numbers) / size(numbers);
        }
    }

    private double sum(List<Double> numbers) {
        return numbers.stream()
                .mapToDouble(num -> num)
                .sum();
    }

    private int size(List<Double> numbers) {
        return numbers.size();
    }

    double averageAfterRefactoring(List<Double> numbers) {
        validateNotEmpty(numbers);
        return sum(numbers) / size(numbers);
    }

    void validateNotEmpty(List<Double> numbers) {
        if (size(numbers) == 0) {
            throw new IllegalArgumentException("Empty list not allowed");
        }
    }
}
