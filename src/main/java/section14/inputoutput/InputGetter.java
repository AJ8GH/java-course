package section14.inputoutput;

import java.util.InputMismatchException;
import java.util.NoSuchElementException;
import java.util.Scanner;
import java.util.Set;

public class InputGetter {
    private static final Scanner SCANNER = new Scanner(System.in);
    private static final Set<Character> DIGITS =
            Set.of('0', '1', '2', '3', '4', '5', '6', '7', '8', '9');

    public static void main(String[] args) {
        try {
            int x = divide();
            System.out.println(x);
        } catch (ArithmeticException | NoSuchElementException e) {
            System.out.println(e);
            System.out.println("Unable to perform division, autopilot shutting down");
        }
    }

    private static int getInt() {
        System.out.println("Please enter an integer");
        while (true) {
            try {
                return SCANNER.nextInt();
            } catch (InputMismatchException e) {
                SCANNER.nextLine();
                System.out.println("Please enter an integer");
            }
        }
    }

    private static int getIntLBYL() {
        String input = SCANNER.nextLine();
        for (int i = 0; i < input.length(); i++) {
            if (!DIGITS.contains(input.charAt(i))) {
                System.out.println("Non-numeric input detected");
                return 0;
            }
        }
        return Integer.parseInt(input);
    }

    private static int getIntEAFP() {
        try {
            return SCANNER.nextInt();
        } catch (InputMismatchException e) {
            System.out.println("Non-int detected");
            return 0;
        }
    }

    private static int divide() {
        int x;
        int y;
//        try {
        x = getInt();
        y = getInt();
        System.out.println("x is " + x + ", y is " + y);
        return x / y;
//      try catch here no longer necessary when exceptions handled in main:
//        } catch (NoSuchElementException e) {
//            throw (new NoSuchElementException("Unsuitable input"));
//        } catch (ArithmeticException e) {
//            throw new ArithmeticException("Cannot divide by 0");
//        }
    }
}
