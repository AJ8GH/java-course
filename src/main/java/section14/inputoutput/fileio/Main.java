package section14.inputoutput.fileio;

import section12.collections.maps.adventure.Location;

import java.io.FileReader;
import java.io.IOException;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

public class Main {
    private static final Locations LOCATIONS = new Locations();
    private static final Scanner SCANNER = new Scanner(System.in);
    private static final Deserialiser deserialiser = new Deserialiser();
    private static final Serialiser serialiser = new Serialiser();
    private static final RandomFileReader fileReader = new RandomFileReader();

    private static final int START_LOCATION = 1;

    public static void main(String[] args) throws IOException {
        fileReader.deserialiseRandomAccessFile();
        playGameFromRandomAccessFile();
    }

    private static void playGameFromRandomAccessFile() throws IOException {
        Location currentLocation = fileReader.getLocation(START_LOCATION);

        while (true) {
            System.out.println(currentLocation.getDescription());
            if (currentLocation.getLocationId() == 0) break;

            Map<String, Integer> exits = currentLocation.getExits();

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
                currentLocation = fileReader.getLocation(currentLocation
                        .getExits()
                        .get(direction));
            } else {
                System.out.println("You cannot go in that direction");
            }
            fileReader.close();
        }
    }

    private static void playGameInMemory() {
        deserialiser.deserialiseBytes(LOCATIONS);

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
