package section18.debugging.challenge;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class StringCharUtilitiesTest {
    private StringCharUtilities victim;

    @BeforeEach
    void setUp() {
        victim = new StringCharUtilities();
    }

    @Test
    void removePairs_NoPairs() {
        String input = "abc";
        assertEquals(input, victim.removePairs(input));
    }

    @Test
    void removePairs_OnePair() {
        String input = "aabc";
        String expected = "abc";
        assertEquals(expected, victim.removePairs(input));
    }

    @Test
    void removePairs_ThreePairs() {
        String input = "aabbcc";
        String expected = "abc";
        assertEquals(expected, victim.removePairs(input));
    }

    @Test
    void removePairs_ThreePairs_WithNonConsecutivePairs() {
        String input = "abaabcbcacc";
        String expected = "ababcbcac";
        assertEquals(expected, victim.removePairs(input));
    }

    @Test
    void removePairs_ThreePairs_WithManyDuplicates() {
        String input = "abbbbaaaabcccccbcacc";
        String expected = "ababcbcac";
        assertEquals(expected, victim.removePairs(input));
    }
}
