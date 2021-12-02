package section18.debugging.challenge;

public class CharArrayUtilities {
    public char[] everyNthChar(char[] sourceArray, int n) {
        if (sourceArray == null || sourceArray.length < n) return sourceArray;
        char[] outputArray = new char[sourceArray.length / n];
        int index = 0;

        for (int i = n - 1; i < sourceArray.length; i += n) {
            outputArray[index++] = sourceArray[i];
        }
        return outputArray;
    }
}
