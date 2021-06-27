package session3.PrimitiveTypes;

public class Main {
    public static void main(String[] args) {
        //        int - max range of 32 bits
        int minimumIntValue = Integer.MIN_VALUE;
        int maximumIntValue = Integer.MAX_VALUE;

        System.out.println("Integer Maximum Value = " + maximumIntValue);
        System.out.println("Integer Minimum Value = " + minimumIntValue);

        System.out.println("Overflow Integer Maximum Value = " + (maximumIntValue + 1));
        System.out.println("Overflow Integer Minimum Value = " + (minimumIntValue - 1));

        //        short - max range of 16 bits
        short maximumShortValue = Short.MAX_VALUE;
        short minimumShortValue = Short.MIN_VALUE;

        System.out.println("Overflow Short Maximum Value = " + (maximumShortValue));
        System.out.println("Overflow Short Minimum Value = " + (minimumShortValue));

        //        byte - max range of 8 bits
        byte maximumByteValue = Byte.MAX_VALUE;
        byte minimumByteValue = Byte.MIN_VALUE;

        System.out.println("Overflow Byte Maximum Value = " + (maximumByteValue));
        System.out.println("Overflow Byte Minimum Value = " + (minimumByteValue));

        //        long - max range of 64
        long maximumLongValue = Long.MAX_VALUE;
        long minimumLongValue = Long.MIN_VALUE;

        System.out.println("Overflow Long Maximum Value = " + (maximumLongValue));
        System.out.println("Overflow Long Minimum Value = " + (minimumLongValue));

        //        letter L indeciates number is a long, not an int (case insensitive, but uppercase preferred for clarity)
        long myLongValue = 100L;

        //        casting values to different types:
        byte myByteValue = (byte) (maximumByteValue / 2);
        short myShortValue = (short) (maximumShortValue / 2);

        float maximumFloatValue = Float.MAX_VALUE;
        float minimumFloatValue = Float.MIN_VALUE;

        System.out.println("Overflow Float Maximum Value = " + (maximumFloatValue));
        System.out.println("Overflow Float Minimum Value = " + (minimumFloatValue));

        double maximumDoubleValue = Double.MAX_VALUE;
        double minimumDoubleValue = Double.MIN_VALUE;

        System.out.println("Overflow Double Maximum Value = " + (maximumDoubleValue));
        System.out.println("Overflow Double Minimum Value = " + (minimumDoubleValue));

        // f is required when declaring float, or casting (float). d is optional when declaring double as double is the default decimal type.
        float myFloat = 5.25f;
        double myDouble = 5.25d;

        char myChar = 'D';
        char myUnicodeChar = '\u0044';

        System.out.println(myChar);
        System.out.println(myUnicodeChar);

        boolean myTrueBooleanValue = true;
        boolean myFalseBooleanValue = false;
    }
}

// ## Primitive Types
// boolean, byte, char, short, int, long, float, double

// every primitive has a wrapper class
// Primitive wrap

// float is single precision number - 32 bits / 4 bytes
// double is double precision number - 64 bits / 8 bytes

// char is different to string. similar but only stores single characters
