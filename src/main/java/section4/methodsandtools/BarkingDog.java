package section4.methodsandtools;

public class BarkingDog {
    public static boolean shouldWakeUp(boolean barking, int hourOfDay) {
        if (hourOfDay < 0 || hourOfDay > 23) return false;
        return barking && (hourOfDay > 22 || hourOfDay < 8);
    }
}
