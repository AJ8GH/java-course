package section17.regex.intro;

public class Main {
    public static void main(String[] args) {
        String string = "I am a string. Yes, I am.";
        System.out.println(string);

        String yourString = string.replaceAll("I", "You");
        System.out.println(yourString);

        // -----------------

        String alphanumeric = "abcDeeeF12Ghhiiiijkl99z";
        System.out.println(alphanumeric.replaceAll(".", "Y"));

        System.out.println(alphanumeric.replaceAll("^abcDee", "YYY"));

        alphanumeric = "abcDeeeF12Ghhiiiijkl99zabcDee";
        System.out.println(alphanumeric.replaceAll("^abcDee", "YYY"));

        // match pattern at beginning of string
        System.out.println(alphanumeric.matches("^hello"));
        System.out.println("hello".matches("hello"));

        alphanumeric = "abcDeeeF12Ghhiiiijkl99z";
        // match pattern string at end of string
        System.out.println(alphanumeric.replaceAll("jkl99z$", "0000"));

        // match any occurrence of these chars
        System.out.println(alphanumeric.replaceAll("[aei]", "X"));

        // match any of occurrence a char from the first group,
        // as long as it is directly followed by any char from the second group,
        // second char is included in the match
        System.out.println(alphanumeric.replaceAll("[aei][Fj]", "X"));
        System.out.println("AbcAdeABkjABCdek");
        System.out.println("AbcAdeABkjABCdek".replaceAll("[CBA][dekj]", "X"));

        System.out.println("Hello".replaceAll("[Hh]ello", "ByeBye"));
        System.out.println("hello".replaceAll("[Hh]ello", "ByeBye"));

        // match any character not in the brackets after the carat
        System.out.println(alphanumeric.replaceAll("[^ej]", "X"));

        // match all chars in the ranges specified
        System.out.println(alphanumeric.replaceAll("[a-fA-F1-8]", "X"));

        // make the regex case-insensitive for ascii strings
        System.out.println(alphanumeric.replaceAll("(?i)[a-f1-8]", "X"));
        System.out.println(alphanumeric.replaceAll("(?i)HHII", "X"));

        // make the regex case-insensitive for unicode strings
        System.out.println(alphanumeric.replaceAll("(?iu)[a-f1-8]", "X"));
        System.out.println(alphanumeric.replaceAll("(?iu)HHII", "X"));

        // match any digit
        System.out.println(alphanumeric.replaceAll("\\d", "X"));

        // match any non-digit
        System.out.println(alphanumeric.replaceAll("\\D", "X"));

        String stringWithSpace = "string with   with\tspaces\nin \n\n\t";

        // match any whitespace char
        System.out.println(stringWithSpace.replaceAll("\\s", "X"));

        // match any non-whitespace char
        System.out.println(stringWithSpace.replaceAll("\\S", "X"));

        String alphanumericWithSpecialChars = "k{'jL90 3\t&£$*\tz)(0@a2\t345lks&#jdf";

        // match any alphanumeric character
        System.out.println(alphanumericWithSpecialChars.replaceAll("\\w", "X"));

        // match any non-alphanumeric character
        System.out.println(alphanumericWithSpecialChars.replaceAll("\\W", "X"));

        // match any word (sequence of alphanumeric characters)
        System.out.println(alphanumericWithSpecialChars.replaceAll("\\b", "X"));
        System.out.println(stringWithSpace.replaceAll("\\b", "X"));

        System.out.println(alphanumericWithSpecialChars.replaceAll("\\B", "X"));
        System.out.println(stringWithSpace.replaceAll("\\B", "X"));

    }
}
