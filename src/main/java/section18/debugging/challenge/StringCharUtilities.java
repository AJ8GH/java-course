package section18.debugging.challenge;

public class StringCharUtilities {
    public String removePairs(String source) {
        if (source == null || source.length() < 2) return source;
        StringBuilder output =  new StringBuilder();

        for (int i = 0; i < source.length(); i++) {
            char currentChar = source.charAt(i);
            if (i > 0 && output.charAt(output.length() - 1) == currentChar) {
                continue;
            }
            output.append(currentChar);
        }
        return output.toString();
    }
}
