package section5;

import java.util.*;

public class LastDigitChecker {
    public static void main(String[] args) {
        System.out.println(hasSameLastDigit(13, 12, 231));
    }

    public static boolean hasSameLastDigit(int int1, int int2, int int3) {
        List<Integer> nums = Arrays.asList(int1, int2, int3);
        Set<Integer> rightDigits = new HashSet();
        for (int num : nums) {
            if (!isValid(num)) return false;
            if (!rightDigits.add(num % 10)) return true;
        }
        return false;
    }

    public static boolean isValid(int number) {
        return number <= 1000 && number >= 10;
    }
}
