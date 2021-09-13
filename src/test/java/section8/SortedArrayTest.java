package section8;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import section8.arrays.SortedArray;

public class SortedArrayTest {

    @Test
    void itSortsATwoElementArray() {
        int[] sortedArray = SortedArray.sortIntegers(new int[] { 1, 2 });
        int[] expected = new int[] { 2, 1 };
        assertArrayEquals(expected, sortedArray);
    }

    @Test
    void itSortsAThreeElementArray() {
        int[] sortedArray = SortedArray.sortIntegers(new int[] { 1, 3, 2 });
        int[] expected = new int[] { 3, 2, 1 };
        assertArrayEquals(expected, sortedArray);
    }

    @Test
    void itSortsAFiveElementArray() {
        int[] sortedArray = SortedArray.sortIntegers(new int[] { 4, 1, 5, 3, 2 });
        int[] expected = new int[] { 5, 4, 3, 2, 1 };
        assertArrayEquals(expected, sortedArray);
    }
}
