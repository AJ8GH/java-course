package section8;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

public class ReverseArrayTest {

    @Test
    void itReversesATwoElementArray() {
        int[] array = new int[] { 1, 2 };
        ReverseArray.reverse(array);
        assertArrayEquals(new int[] { 2, 1 }, array);
    }

    @Test
    void itReversesAThreeElementArray() {
        int[] array = new int[] { 3, 1, 2 };
        ReverseArray.reverse(array);
        assertArrayEquals(new int[] { 2, 1, 3 }, array);
    }

    @Test
    void itReversesAFourElementArray() {
        int[] array = new int[] { 4, 3, 2, 1 };
        ReverseArray.reverse(array);
        assertArrayEquals((new int[] { 1, 2, 3, 4 }), array);
    }

    @Disabled
    @Test
    void itReversesAFiveElementArray() {
        int[] array = new int[] { 3, 5, 1, 4, 2 };
        ReverseArray.reverse(array);
        assertArrayEquals(new int[] { 2, 4, 1, 5, 3 }, array);
    }

    @Disabled
    @Test
    void itReversesATenElementArray() {
        int[] array = new int[] { 1, 2, 3, 4, 5, 6, 7, 8, 9, 10 };
        ReverseArray.reverse(array);
        assertArrayEquals(new int[] { 10, 9, 8, 7, 6, 5, 4, 3, 2, 1 }, array);
    }
}
