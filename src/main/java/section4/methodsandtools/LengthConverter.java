package section4.methodsandtools;

public class LengthConverter {
    public static void main(String[] args) {
        System.out.println("1 foot and 6 inches is " + calcFeetAndInchesToCentimeters(1, 6) + " cm");
        System.out.println("10 inches is " + calcFeetAndInchesToCentimeters(10) + " cm");
        System.out.println("74 inches is " + calcFeetAndInchesToCentimeters(74) + " cm");
    }

    public static double calcFeetAndInchesToCentimeters(double feet, double inches) {
        if (feet < 0 || inches < 0 || inches > 12) return -1;
        return (feet * 12 + inches) * 2.54;
    }

    public static double calcFeetAndInchesToCentimeters(double inches) {
        if (inches < 0) return -1;
        double feet = (int) inches / 12;
        double remainingInches = (int) inches % 12;
        return calcFeetAndInchesToCentimeters(feet, remainingInches);
    }
}
