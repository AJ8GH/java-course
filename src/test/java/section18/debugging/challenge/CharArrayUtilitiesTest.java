package section18.debugging.challenge;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CharArrayUtilitiesTest {
    private CharArrayUtilities victim;

    @BeforeEach
    void setUp() {
        victim = new CharArrayUtilities();
    }

    @Test
    void testEveryNthChar_One() {
        char[] input = new char[] { 'a' };

        assertArrayEquals(input, victim.everyNthChar(input, 1));
    }

    @Test
    void testEveryNthChar_Two() {
        char[] input = new char[] { 'a', 'b' };
        char[] expected = new char[] { 'b' };

        assertArrayEquals(expected, victim.everyNthChar(input, 2));
    }

    @Test
    void testEveryNthChar_LengthLessThanN() {
        char[] input = new char[] { 'a', 'b' };

        assertArrayEquals(input, victim.everyNthChar(input, 3));
    }

    @Test
    void testEveryNthChar_TwoWithLengthFour() {
        char[] input = new char[] { 'a', 'b', 'c', 'd' };
        char[] expected = new char[] { 'b', 'd' };

        assertArrayEquals(expected, victim.everyNthChar(input, 2));
    }

    @Test
    void testEveryNthChar_ThreeWithLengthEight() {
        char[] input = new char[] { 'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h' };
        char[] expected = new char[] { 'c', 'f' };

        assertArrayEquals(expected, victim.everyNthChar(input, 3));
    }

    @Test
    void testEveryNthChar_fourWithLengthSeventeen() {
        char[] input = new char[] { 'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q' };
        char[] expected = new char[] { 'd', 'h', 'l', 'p' };

        assertArrayEquals(expected, victim.everyNthChar(input, 4));
    }
}
