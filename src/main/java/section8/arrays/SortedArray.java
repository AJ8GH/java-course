package section8.arrays;

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
        for (int i = 0; i < array.length; i++) {
            int element = array[i];
            int j = i - 1;

            while (j >= 0 && array[j] < element) {
                array[j + 1] = array[j];
                j--;
            }
            array[j + 1] = element;
        }
        return array;
    }
}
