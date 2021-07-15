package section8;

import java.util.Scanner;

public class MinimumElement {
    private static Scanner scanner  = new Scanner(System.in);

    public static int readInteger() {
        return scanner.nextInt();
    }

    public static int[] readElements(int numberOfInts) {
        int[] array = new int[numberOfInts];
        for (int i = 0; i < array.length; i++) {
            array[i] = scanner.nextInt();
        }
        return array;
    }

    public static int findMin(int[] array) {
        int min = array[0];
        for (int number : array) {
            if (number < min) min = number;
        }
        return min;
    }
}
