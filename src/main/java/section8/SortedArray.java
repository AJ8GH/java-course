package section8;

import java.util.Scanner;

public class SortedArray {

    public static int[] getIntegers(int size) {
        Scanner scanner = new Scanner(System.in);
        int[] array = new int[size];
        for (int i = 0; i < array.length; i++) array[i] = scanner.nextInt();
        return array;
    }

    public static void printArray(int[] array) {
        for (int i = 0; i < array.length; i++) {
            System.out.println("Element " + i + " contents " + array[i]);
        }
    }

    public static int[] sortIntegers(int[] array) {
        int[] sortedArray = new int[array.length];
        for (int i = 0; i < array.length; i++) {
            if (i < 1) {
                sortedArray[i] = array[i];
            } else {
                if (sortedArray[i] < array[i]) {
                    sortedArray[i + 1] = sortedArray[i];
                    sortedArray[i] = array[i];
                }
            }
        }
        return sortedArray;
    }
}
