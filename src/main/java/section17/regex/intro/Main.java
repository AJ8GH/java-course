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

        // match n consecutive occurrences of specified number of the char with the quantifier,
        // e.g. 3 e's -> "e{3}" is equivalent to "eee"
        System.out.println(alphanumeric.replaceAll("^abcDe{3}", "X"));

        // match one or more of the char with the plus,
        // e.g. "e+" means match occurrences of one or more consecutive e's
        System.out.println(alphanumeric.replaceAll("^abcDe+", "X"));

        // match zero or more of the char with the asterisk,
        // e.g. "e*" means match occurrences of zero or more consecutive e's
        System.out.println(alphanumeric.replaceAll("^abcDe*", "X"));

        // match min to max consecutive occurrences of the char with the quantifiers,
        // e.g. "e{2,5}" means match occurrences of between 2 and 5 (inclusive) consecutive e's
        System.out.println(alphanumeric.replaceAll("^abcDe{2,5}", "X"));

        // specific example - match: one or more "h" followed by zero or more "i"s followed by one "j"
        System.out.println(alphanumeric.replaceAll("^abcDe{2,5}", "X"));
    }
}
