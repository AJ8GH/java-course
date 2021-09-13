package section4.methodsandtools;

public class TeenNumberChecker {
    public static boolean hasTeen(int int1, int int2, int int3) {
        return isTeen(int1) || isTeen(int2) || isTeen(int3);
    }

    public static boolean isTeen(int input) {
        return input >= 13 && input <= 19;
    }
}
