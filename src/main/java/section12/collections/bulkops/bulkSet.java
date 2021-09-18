package section12.collections.bulkops;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class bulkSet {
    private static Set<Integer> squares = new HashSet<>();
    private static Set<Integer> cubes = new HashSet<>();

    public static void main(String[] args) {
        for (int i = 1; i <= 100; i++) {
            squares.add(i * i);
            cubes.add(i * i * i);
        }

        System.out.println("There are " + squares.size() + " squares.");
        System.out.println("There are " + cubes.size() + " cubes.");

//        addAll() - union
        Set<Integer> union = new HashSet<>(squares);
        union.addAll(cubes);
        System.out.println("Union has " + union.size() + " elements.");

//        retaiAll() - intersection
        Set<Integer> intersection = new HashSet<>(squares);
        intersection.retainAll(cubes);
        System.out.println("Intersection has " + intersection.size() + " elements");

//        array literals to get around lack of set and list literals
        String sentence = "one day in the year of the fox";
        String[] wordArray = sentence.split(" ");
        Set<String> words = new HashSet<>(Arrays.asList(wordArray));

        for (String word : words) System.out.println(word);

        Set<String> nature = new HashSet<>();
        Set<String> divine = new HashSet<>();

        String[] natureWords = { "all", "nature", "is", "but", "art", "unknown", "to", "thee" };
        nature.addAll(Arrays.asList(natureWords));

        String[] divineWords = { "to", "err", "is", "human", "to", "forgive", "is", "divine" };
        divine.addAll(Arrays.asList(divineWords));

//        removeAll()
        System.out.println("nature - divine");
        Set<String> diff1 = new HashSet<>(nature);
        diff1.removeAll(divine);
        printSet(diff1);

        System.out.println("divine - nature");
        Set<String> diff2 = new HashSet<>(divine);
        diff2.removeAll(nature);
        printSet(diff2);

        Set<String> unionTest = new HashSet<>(nature);
        unionTest.addAll(divine);
        Set<String> intersectionTest = new HashSet<>(nature);
        intersectionTest.retainAll(divine);

        System.out.println("Symmetric Difference");
        unionTest.removeAll(intersectionTest);
        printSet(unionTest);

//        containsAll()
        if (nature.containsAll(divine)) {
            System.out.println("Divine is a subset of nature");
        }

        if (nature.containsAll(intersectionTest)) {
            System.out.println("IntersectionTest is a subset of nature");
        }

        if (divine.containsAll(intersectionTest)) {
            System.out.println("IntersectionTest is a subset of divine");
        }
    }

    private static void printSet(Set<String> set) {
        System.out.print("\t");
        for (String s : set) System.out.print(s + " ");
        System.out.println();
    }
}
