package section5.controlflow;

import java.util.HashSet;
import java.util.Set;

public class SharedDigit {

    public static void main(String[] args) {
        System.out.println(hasSharedDigit(90, 12));
    }

    public static boolean hasSharedDigit(int int1, int int2) {
        if (int1 < 10 || int1 > 99 || int2 < 10 || int2 > 99) return false;
        Set<Integer> digits1 = new HashSet<>();
        Set<Integer> digits2 = new HashSet<>();

        while (int1 > 0) {
            int number = int1 % 10;
            digits1.add(number);
            int1 /= 10;
        }

        while (int2 > 0) {
            int number = int2 % 10;
            digits2.add(number);
            int2 /= 10;
        }

        for (Integer digit : digits1) {
            if (!digits2.add(digit)) return true;
        }
        return false;
    }
}
