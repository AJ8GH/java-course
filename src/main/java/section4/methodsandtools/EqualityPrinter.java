package section4.methodsandtools;

public class EqualityPrinter {
    public static final String INVALID_INPUT_MESSAGE = "Invalid Value";
    public static final String ALL_EQUAL_MESSAGE = "All numbers are equal";
    public static final String ALL_DIFFERENT_MESSAGE = "All numbers are different";
    public static final String NEITHER_EQUAL_OR_DIFFERENT  = "Neither all are equal or different";


    public static void printEqual(int int1, int int2, int int3) {
        if (int1 < 0 || int2 < 0 || int3 < 0) {
            System.out.println(INVALID_INPUT_MESSAGE);
        } else if (int1 == int2 && int1 == int3) {
            System.out.println(ALL_EQUAL_MESSAGE);
        } else if (int1 != int2 && int3 != int1 && int3 != int2) {
            System.out.println(ALL_DIFFERENT_MESSAGE);
        } else {
            System.out.println(NEITHER_EQUAL_OR_DIFFERENT);
        }
    }
}
