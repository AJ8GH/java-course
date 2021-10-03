package section14.inputoutput.fileio.adventureio;

import section12.collections.maps.adventure.DirectionGetter;
import section12.collections.maps.adventure.Location;

import java.io.IOException;
import java.util.Map;
import java.util.Scanner;

public class Main {
    private static final Locations LOCATIONS = new Locations();
    private static final Scanner SCANNER = new Scanner(System.in);
    private static final Deserialiser DESERIALISER = new Deserialiser();
    private static final Serialiser SERIALISER = new Serialiser();
    private static final RandomFileReader FILE_READER = new RandomFileReader();
    private static final DirectionGetter DIRECTION_GETTER = new DirectionGetter();

    private static final int START_LOCATION = 1;

    public static void main(String[] args) throws IOException {
        FILE_READER.deserialiseRandomAccessFile();
        playGameFromRandomAccessFile();
    }

    private static void playGameFromRandomAccessFile() throws IOException {
        Location currentLocation = FILE_READER.getLocation(START_LOCATION);

        while (true) {
            System.out.println(currentLocation.getDescription());
            if (currentLocation.getLocationId() == 0) break;
            Map<String, Integer> exits = currentLocation.getExits();
            String direction = DIRECTION_GETTER.getDirectionFromPlayer(exits);
            if (exits.containsKey(direction)) {
                currentLocation = FILE_READER.getLocation(currentLocation
                        .getExits()
                        .get(direction));
            } else {
                System.out.println("You cannot go in that direction");
            }
        }
        FILE_READER.close();
    }

    private static void playGameInMemory() {
        DESERIALISER.deserialiseBytes(LOCATIONS);
        int loc = 1;
        while (true) {
            Location location = LOCATIONS.get(loc);
            System.out.println(LOCATIONS.get(loc).getDescription());
            if (loc == 0) break;
            Map<String, Integer> exits = LOCATIONS.get(loc).getExits();
            String direction = DIRECTION_GETTER.getDirectionFromPlayer(exits);
            if (exits.containsKey(direction)) {
                loc = exits.get(direction);
            } else {
                System.out.println("You cannot go in that direction");
            }
        }
    }
}
