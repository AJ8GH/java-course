package section12.collections.maps.adventure;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

public class Main {
    private static final Map<Integer, Location> LOCATIONS = new HashMap<>();
    private static final Scanner SCANNER = new Scanner(System.in);

    public static void main(String[] args) {
        setUp();

        int loc = 1;
        while (true) {
            System.out.println(LOCATIONS.get(loc).getDescription());
            if (loc == 0) break;

            Map<String, Integer> exits = LOCATIONS.get(loc).getExits();

            System.out.println("Available exits are ");
            for (String exit : exits.keySet()) {
                System.out.println(exit + ", ");
            }
            System.out.println();

            String direction = "";
            String[] input = SCANNER.nextLine().toUpperCase().split(" ");
            if (Set.of(input).contains("WEST") || Set.of(input).contains("W")) {
                direction = "W";
            } else if (Set.of(input).contains("NORTH") || Set.of(input).contains("N")) {
                direction = "N";
            } else if (Set.of(input).contains("SOUTH") || Set.of(input).contains("S")) {
                direction = "S";
            } else if (Set.of(input).contains("EAST") || Set.of(input).contains("E")) {
                direction = "E";
            } else if (Set.of(input).contains("QUIT") || Set.of(input).contains("Q")) {
                direction = "Q";
            }

            if (exits.containsKey(direction)) {
                loc = exits.get(direction);
            } else {
                System.out.println("You cannot go in that direction");
            }
        }
    }

    private static void setUp() {
        Map<String, Integer> exits = new HashMap<>();
        LOCATIONS.put(0, new Location(0, "You are sitting in front of a computer learning Java", exits));

        exits = new HashMap<>();
        exits.put("W", 2);
        exits.put("E", 3);
        exits.put("S", 4);
        exits.put("N", 5);
        LOCATIONS.put(1, new Location(1, "You are standing at the end of a road before a small bridge", exits));

        exits = new HashMap<>();
        exits.put("N", 5);
        LOCATIONS.put(2, new Location(2, "You are at the top of a hill", exits));

        exits = new HashMap<>();
        exits.put("W", 1);
        LOCATIONS.put(3, new Location(3, "You are inside a building, a well house for a small spring", exits));

        exits = new HashMap<>();
        exits.put("N", 1);
        exits.put("W", 2);
        LOCATIONS.put(4, new Location(4, "You are in a valley beside a stream", exits));

        exits = new HashMap<>();
        exits.put("S", 1);
        exits.put("W", 2);
        LOCATIONS.put(5, new Location(5, "You are in the forest", exits));
    }
}
