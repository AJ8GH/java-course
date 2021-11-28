package section5.controlflow;

public class NumberToWords {
    private static final String[] numWords = {
            "Zero",
            "One",
            "Two",
            "Three",
            "Four",
            "Five",
            "Six",
            "Seven",
            "Eight",
            "Nine"
    };

    public static void main(String[] args) {
        numberToWords(123);
        numberToWords(1010);
        numberToWords(1000);
        numberToWords(-12);

        System.out.println(getDigitCount(0));
        System.out.println(getDigitCount(123));
        System.out.println(getDigitCount(-12));
        System.out.println(getDigitCount(5200));

        System.out.println(reverse(-121));
        System.out.println(reverse(1212));
        System.out.println(reverse(1234));
        System.out.println(reverse(100));
    }

    public static void numberToWords(int number) {
        if (number < 0) {
            System.out.println("Invalid Value");
        } else {
            int digitCount = getDigitCount(number);
            number = reverse(number);
            int newDigitCount = getDigitCount(number);
            int diff = digitCount - newDigitCount;
            while (number > 0) {
                int lastDigit = number % 10;
                System.out.println(numWords[lastDigit]);
                number /= 10;
            }
            if (diff > 0) {
                for (int i = 0; i < diff; i++) {
                    System.out.println("Zero");
                }
            }
        }
    }

    public static int reverse(int number) {
        boolean negative = false;
        if (number < 0) {
            number = Math.abs(number);
            negative = true;
        }

        StringBuilder reversedNumber = new StringBuilder();
        while (number > 0) {
            reversedNumber.append(number % 10);
            number /= 10;
        }
        int output = Integer.parseInt(reversedNumber.toString());
        return negative ? -output : output;
    }

    public static int getDigitCount(int number) {
        if (number < 0) return -1;
        if (number == 0) return 1;
        int count = 0;
        while (number > 0) {
            count++;
            number /= 10;
        }
        return count;
    }
}
