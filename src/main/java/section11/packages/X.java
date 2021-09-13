package section11.packages;

import java.util.Scanner;

public class X {
    public static void main(String[] args) {
        x x = new x();
        x.x();
    }

    public static class x {
        Scanner x = new Scanner(System.in);
        private void x() {
            System.out.println("Enter a number:");
            int x = this.x.nextInt();
            for (int i = 1; i <= 12; i++) {
                System.out.println(i * x);
            }
        }
    }
}
