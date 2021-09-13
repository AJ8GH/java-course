package section12.collections;

import java.util.*;

public class BinarySearching {
    public static void main(String[] args) {

        List<Integer> intList = new ArrayList<>();

        for (int i = 0; i < 10_000_000; i++) {
            intList.add(i);
        }

    int result = Collections.binarySearch(intList, 70_100_562);

        System.out.println(result);
    }
}
