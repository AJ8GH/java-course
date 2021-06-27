package session3.PrimitiveTypes;

public class Assignment {
    public static void main(String[] args) {
        byte myByte = 16;
        short myShort = 1000;
        int myInt = 10;

        long myLong = 50_000L + 10L * (myByte + myInt + myShort);
        System.out.println(myLong);
    }
}
