package section4.methodsandtools;

public class MegaBytesConverter {
    private static final int KB_IN_MB = 1024;

    public static void printMegaBytesAndKiloBytes(int kiloBytes) {
        int mb = kiloBytes / KB_IN_MB;
        int kb = kiloBytes - (mb * KB_IN_MB);

        String output = kiloBytes < 0 ? "Invalid Value" :
                kiloBytes + " KB = " + mb + " MB and " + kb + " KB";

        System.out.println(output);
    }
}
