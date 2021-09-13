package section5.controlflow;

public class GreatestCommonDivisor {
    public static int getGreatestCommonDivisor(int first, int second) {
        if (first < 10 || second < 10) return -1;
        int i = Math.max(first, second);
        while (true) {
            if ((first % i == 0) && (second % i == 0)) return i;
            i--;
        }
    }
}
