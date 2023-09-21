package 북스터디.five_lines_of_code._1주차;

import java.util.Arrays;

class Chapter3Ex {
    int findMinNum(int[][] numbers) {
        int result = Integer.MAX_VALUE;
        for (int x = 0; x < numbers.length; x++) {
            for (int y = 0; y < numbers[x].length; y++) {
                result = getMinNum(result, numbers[x][y]);
            }
        }

        return result;
    }

    private int getMinNum(int result, int targetNumber) {
        if (result > targetNumber) {
            result = targetNumber;
        }

        return result;
    }

    double calculateAverage(int[] numbers) {
        return (double) sum(numbers) / size(numbers);
    }

    private int sum(int[] numbers) {
        return Arrays.stream(numbers).sum();
    }

    private int size(int[] numbers) {
        return numbers.length;
    }

    void reportPrimes(int number) {
        for (int i = 0; i < number; i++) {  // 반복을 수행한다.
            reportIfPrime(number);
        }
    }

    private void reportIfPrime(int number) {
        if (isPrime(number)) {  // 소수인지 체크한다.
            System.out.printf("%d is prime.\n", number);
        }
    }

    private boolean isPrime(int number) {
        System.out.println("대충 소수를 판별하는 로직 (귀찮아서 구현 안한거 맞음)");
        return true;
    }
}