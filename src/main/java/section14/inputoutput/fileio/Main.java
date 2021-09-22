package section14.inputoutput.fileio;

import section12.collections.maps.adventure.Location;

import java.util.Map;
import java.util.Scanner;
import java.util.Set;

public class Main {
    private static final Locations LOCATIONS = new Locations();
    private static final Scanner SCANNER = new Scanner(System.in);
    private static final Deserialiser deserialiser = new Deserialiser();
    private static final Serialiser serialiser = new Serialiser();

    public static void main(String[] args) {
        deserialiser.fromCSV(LOCATIONS);

        System.out.println(LOCATIONS);
        int loc = 1;
        while (true) {
            Location location = LOCATIONS.get(loc);
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
}
