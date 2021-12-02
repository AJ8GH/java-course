package section18.debugging.stringutilities;

public class Main {
    public static void main(String[] args) {
        StringUtilities util = new StringUtilities();
        StringBuilder sb = new StringBuilder();
        while(sb.length() < 10) {
            util.addChar(sb, 'a');
        }
        System.out.println(sb);

        String string = "abcdefg";
        String result = util.upperAndPrefix(util.addSuffix(string));
    }
}
