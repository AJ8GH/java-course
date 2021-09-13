package section5.controlflow;

import java.util.Scanner;

public class InputCalculator {
    public static void main(String[] args) {
        inputThenPrintSumAndAverage();
    }

    public static void inputThenPrintSumAndAverage() {
        Scanner scanner = new Scanner(System.in);
        int count = 0;
        int sum = 0;

        while (true) {
            if (scanner.hasNextInt()) {
                count += 1;
                sum += scanner.nextInt();
            } else {
                break;
            }
        }

        long avg = Math.round(((double) sum / (double) count));
        System.out.println("SUM = " + sum + " AVG = " + avg);
    }
}
