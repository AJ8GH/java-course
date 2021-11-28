package section16.lambdas.streams;

import section16.lambdas.employees.Employee;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Bingo {
    private static final List<String> BINGO_NUMBERS = Arrays.asList(
            "N40", "N36",
            "B12", "B6",
            "G53", "G49", "G60", "G50", "g64",
            "I26", "I17", "I29",
            "O71"
    );

    public static void main(String[] args) {
        BINGO_NUMBERS.stream()
                .map(String::toUpperCase)
                .filter(s -> s.startsWith("G"))
                .sorted()
                .forEach(System.out::println);

        List<String> gList = new ArrayList<>();
        BINGO_NUMBERS.forEach(number -> {
            number = number.toUpperCase();
            if (number.startsWith("G")) {
                gList.add(number);
                gList.sort(String::compareTo);
            }
        });

        gList.forEach(System.out::println);

        Stream<String> iNumberStream = Stream.of("I26", "I17", "I29");
        Stream<String> nNumberStream = Stream.of("N40", "N36", "I26", "I17", "I29");
        Stream<String> concatStream = Stream.concat(iNumberStream, nNumberStream);
        System.out.println(concatStream
                .distinct()
                .peek(System.out::println)
                .count());

        collectToList();
        collectToArrayList();
    }

    private static void collectToList() {
        List<String> collected = BINGO_NUMBERS.stream()
                .map(String::toUpperCase)
                .filter(s -> s.startsWith("G"))
                .sorted()
                .collect(Collectors.toList());
        System.out.println(collected);
    }

    private static void collectToArrayList() {
        List<String> collected = BINGO_NUMBERS.stream()
                .map(String::toUpperCase)
                .filter(s -> s.startsWith("G"))
                .sorted()
                .collect(ArrayList::new, ArrayList::add, ArrayList::addAll);
        System.out.println(collected);
    }

    private static void printAll() {
        BINGO_NUMBERS.forEach(System.out::println);
    }
}
