package section5;
import java.util.Scanner;

public class UserInput {

    public static void main(String[] args) {
        printSum();

        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter your name");
        String name = scanner.nextLine();
        System.out.println("Enter your year of birth");
        boolean hasNextInt = scanner.hasNextInt();
        if (hasNextInt) {
            int year = scanner.nextInt();
            System.out.println("Hello " + name + " you were born in " + year);
        } else {
            System.out.println("Year not number");
        }
        scanner.close();
    }

    private static void printSum() {
        Scanner scanner = new Scanner(System.in);
        int sum = 0;
        int count = 0;

        while (count < 10) {
            System.out.println("Enter a number");
            boolean isInt = scanner.hasNextInt();
            if (isInt) {
                sum += scanner.nextInt();
                count++;
            } else {
                System.out.println("Not a number!");
            }
            scanner.nextLine();
        }
        System.out.println(sum);
        scanner.close();
    }
}
