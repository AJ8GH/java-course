package section8;

public class ReverseArray {
    public static void reverse(int[] array) {
        int j = array.length - 1;

        for (int i = 0; i < array.length / 2; i++) {
            int el = array[j];
            array[j] = array[i];
            array[i] = el;
            j--;
        }
    }
}
