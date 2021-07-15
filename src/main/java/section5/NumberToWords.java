package section5;

public class NumberToWords {

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

    public static void numberToWords(int number) {
        if (number < 0) {
            System.out.println("Invalid Value");
        } else {
            int reversedNumber = reverse(number);
            int digitDiff = getDigitCount(number) - getDigitCount(reversedNumber);
            while (reversedNumber > 0) {
                System.out.println((numWords[reversedNumber % 10]));
                reversedNumber /= 10;
            }
            for (int i = 0; i < digitDiff; i++) {
                System.out.println((numWords[0]));
            }
        }
    }

    public static int reverse(int number) {
        int absNumber = Math.abs(number);
        StringBuilder reversedNumber = new StringBuilder();
        while (absNumber > 0) {
            reversedNumber.append(absNumber % 10);
            absNumber /= 10;
        }
        int output = Integer.parseInt(reversedNumber.toString());
        return (number < 0) ? -output : output;
    }

    public static int getDigitCount(int number) {
        if (number < 0) return -1;
        int count = 0;
        while (number > 0) {
            count++;
            number /= 10;
        }
        return count;
    }
}
