package 북스터디.five_lines_of_code._1주차;

class Chapter3Ex {
    int findMinNum(int[][] numbers) {
        int result = Integer.MAX_VALUE;
        for (int x = 0; x < numbers.length; x++) {
            for (int y = 0; y < numbers[x].length; y++) {

                if (result > numbers[x][y]) {
                    result = numbers[x][y];
                }
            }
        }

        return result;
    }
}