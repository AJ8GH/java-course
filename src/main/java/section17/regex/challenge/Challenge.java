package section17.regex.challenge;


import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Challenge {
    private static final String CHALLENGE_1 = "I want a bike.";
    private static final String CHALLENGE_2 = "I want a ball.";
    private static final String CHALLENGE_4 = "Replace all blanks with underscores";
    private static final String CHALLENGE_5 = "aaabccccccccdddefffg";
    private static final String CHALLENGE_7 = "abcd.135";
    private static final String CHALLENGE_8 = "abcd.135uvqz.7tzik.999";
    private static final String CHALLENGE_9 = "abcd\t\t.\t135\t uvqz\t\n.\t7\ttzik\t.\t999\n";
    private static final String CHALLENGE_11 = "{0, 2}, {0, 5}, {1, 3}, {2, 4}";
    private static final String CHALLENGE_12 = "11111";
    private static final String CHALLENGE_13 = "11111-1111";

    public static void main(String[]args){
        challenge1();
        challenge2();
        challenge3();
        challenge4();
        challenge5();
        challenge6();
        challenge7();
        challenge8();
        challenge9();
        challenge10();
        challenge11();
        challenge12();
        challenge13();
        challenge14();
    }

    public static void challenge1() {
        System.out.println("=== Challenge 1 ===");
        String regex = "I want a bike.";
        System.out.println(CHALLENGE_1.matches(regex));
    }

    public static void challenge2() {
        System.out.println("=== Challenge 2 ===");

        String regex = "I want a \\w+.";
        System.out.println(CHALLENGE_1.matches(regex));
        System.out.println(CHALLENGE_2.matches(regex));

        String regex2 = "I want a (bike|ball).";
        System.out.println(CHALLENGE_1.matches(regex2));
        System.out.println(CHALLENGE_2.matches(regex2));
    }

    public static void challenge3() {
        System.out.println("=== Challenge 3 ===");

        String regex = "I want a \\w+.";
        Pattern pattern = Pattern.compile(regex);

        Matcher matcher = pattern.matcher(CHALLENGE_1);
        System.out.println(matcher.matches());

        matcher = pattern.matcher(CHALLENGE_2);
        System.out.println(matcher.matches());
    }

    public static void challenge4() {
        System.out.println("=== Challenge 4 ===");

        String regex = "\\s";
        System.out.println(CHALLENGE_4.replaceAll(regex, "_"));
    }

    public static void challenge5() {
        System.out.println("=== Challenge 5 ===");

        String regex = "[a-g]+";
        System.out.println(CHALLENGE_5.matches(regex));
    }

    public static void challenge6() {
        System.out.println("=== Challenge 6 ===");

        String regex = "^a{3}bc{8}d{3}ef{3}g$";
        System.out.println(CHALLENGE_5.matches(regex));
    }

    public static void challenge7() {
        System.out.println("=== Challenge 7 ===");

        String regex = "^[a-zA-Z]+\\.[0-9]+$";
        System.out.println(CHALLENGE_7.matches(regex));

        System.out.println("kjisl.22".matches(regex));
        System.out.println("f5.12a".matches(regex));
    }

    public static void challenge8() {
        System.out.println("=== Challenge 8 ===");

        String regex = "([a-zA-Z]+)(\\.)([0-9]+)";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(CHALLENGE_8);

        while (matcher.find()) {
            System.out.println(matcher.group(3));
        }
    }

    public static void challenge9() {
        System.out.println("=== Challenge 9 ===");

        String regex = "[a-zA-Z]\\s*+\\.\\s*(\\d+)";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(CHALLENGE_9);

        while (matcher.find()) {
            System.out.println(matcher.group(1));
        }
    }

    public static void challenge10() {
        System.out.println("=== Challenge 10 ===");

        String regex = "[a-zA-Z]\\s*+\\.\\s*(\\d+)";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(CHALLENGE_9);

        while (matcher.find()) {
            System.out.println(matcher.start(1));
            System.out.println(matcher.end(1) - 1);
        }
    }

    public static void challenge11() {
        System.out.println("=== Challenge 11 ===");

        String regex = "\\{(\\d+,\\s*\\d+)}";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(CHALLENGE_11);

        while (matcher.find()) {
            System.out.println(matcher.group(1));
        }
    }

    public static void challenge12() {
        System.out.println("=== Challenge 12 ===");

        String regex = "^\\d{5}$";
        System.out.println(CHALLENGE_12.matches(regex));
    }

    public static void challenge13() {
        System.out.println("=== Challenge 13 ===");

        String regex = "^\\d{5}-\\d{4}$";
        System.out.println(CHALLENGE_13.matches(regex));
    }

    public static void challenge14() {
        System.out.println("=== Challenge 14 ===");

        String regex = "^\\d{5}(-\\d{4}$)?$";
        System.out.println(CHALLENGE_12.matches(regex));
        System.out.println(CHALLENGE_13.matches(regex));
    }
}
