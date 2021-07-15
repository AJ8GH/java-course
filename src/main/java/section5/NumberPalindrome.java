package section5;

public class NumberPalindrome {
    public static boolean isPalindrome(int number) {
        String numberString = String.valueOf(Math.abs(number));
        return numberString.equals(new StringBuilder(numberString)
                .reverse().toString());
    }
}
