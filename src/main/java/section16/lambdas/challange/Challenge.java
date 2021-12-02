package section16.lambdas.challange;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Collectors;

public class Challenge {
    private static Function<String, String> everySecondChar;
    private static final List<String> TOP_NAMES_2015 = Arrays.asList(
            "Amelia",
            "Olivia",
            "emily",
            "Isla",
            "Ava",
            "Oliver",
            "Jack",
            "Charlie",
            "harry",
            "Jacob"
    );

    public static void main(String[] args) {
        challenge1();
        challenge2();
        challenge3();
        challenge4();
        challenge6();
        challenge8();
        challenge9();
        challenge10();
        challenge11();
        challenge12();
        challenge13();
    }

    private static void challenge1() {
        Runnable runnable = () -> {
            String myString = "Let's split this up into an array";
            String[] parts = myString.split(" ");
            for (String part : parts) System.out.println(part);
        };

        new Thread(runnable).start();
    }

    private static void challenge2() {
        everySecondChar = s -> {
            StringBuilder builder = new StringBuilder();
            for (int i = 0; i < s.length(); i++) {
                if (i % 2 != 0) {
                    builder.append(s.charAt(i));
                }
            }
            return builder.toString();
        };
    }

    private static void challenge3() {
        System.out.println(everySecondChar.apply("1234567890"));
    }

    private static void challenge4() {
        System.out.println(everySecondCharacter(everySecondChar, "1234567890"));
    }

    private static void challenge6() {
        Supplier<String> supplier = () -> "I love Java!";

        System.out.println(supplier.get());
    }

    private static void challenge8() {
        Callable<String> callable = () -> {
            String message = "Coming from the callable!";
            System.out.println(message);
            return message;
        };

        ExecutorService executor = Executors.newFixedThreadPool(1);
        executor.submit(callable);
    }

    private static void challenge9() {
        List<String> capitalised = TOP_NAMES_2015
                .stream()
                .map(name -> name.substring(0, 1).toUpperCase() + name.substring(1))
                .collect(Collectors.toList());
        capitalised.sort((n1, n2) -> n1.compareTo(n2));
        capitalised.forEach(n -> System.out.println(n));
    }

    private static void challenge10() {
        List<String> capitalised = TOP_NAMES_2015
                .stream()
                .map(name -> name.substring(0, 1).toUpperCase() + name.substring(1))
                .collect(Collectors.toList());
        capitalised.sort(String::compareTo);
        capitalised.forEach(System.out::println);
    }

    private static void challenge11() {
        TOP_NAMES_2015
                .stream()
                .map(name -> name.substring(0, 1).toUpperCase() + name.substring(1))
                .sorted()
                .forEach(System.out::println);
    }

    private static void challenge12() {
        long count = TOP_NAMES_2015
                .stream()
                .map(name -> name.substring(0, 1).toUpperCase() + name.substring(1))
                .filter(n -> n.startsWith("A"))
                .count();
        System.out.println(count);
    }

    private static void challenge13() {
        announceChallenge(13);
        List<String> newList = TOP_NAMES_2015
                .stream()
                .map(name -> name.substring(0, 1).toUpperCase() + name.substring(1))
                .peek(System.out::println)
                .sorted()
                .collect(Collectors.toList());
     }

    private static String everySecondCharacter(Function<String, String> function,
                                               String s) {
        return function.apply(s);
    }

    private static void announceChallenge(int number) {
        System.out.printf("=== CHALLENGE %s ===\n", number);
    }
}
