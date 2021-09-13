package section5.controlflow;

public class FirstAndLastDigitSum {
    public static int sumFirstAndLastDigit(int number) {
        if (number < 0) return -1;
        if (number < 10) return number * 2;

        int last = number % 10;

        while (number >= 10) {
            number /= 10;
        }
        return number + last;
    }
}
