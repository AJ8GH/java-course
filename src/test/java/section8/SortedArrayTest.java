package section8;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

public class SortedArrayTest {

    @Test
    void itSortsATwoElementArray() {
        int[] sortedArray = SortedArray.sortIntegers(new int[] { 1, 2 });
        int[] expected = new int[] { 2, 1 };
        assertArrayEquals(expected, sortedArray);
    }
}
