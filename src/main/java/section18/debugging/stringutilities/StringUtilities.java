package section18.debugging.stringutilities;

public class StringUtilities {
    private StringBuilder sBuilder = new StringBuilder();
    private int charsAdded = 0;

    public void addChar(StringBuilder sBuilder, char c) {
        sBuilder.append(c);
        charsAdded++;
    }

    public String upperAndPrefix(String string) {
        String upper = string.toUpperCase();
        return "Prefix_" + upper;
    }

    public String addSuffix(String string) {
        return string + "_Suffix";
    }
}
