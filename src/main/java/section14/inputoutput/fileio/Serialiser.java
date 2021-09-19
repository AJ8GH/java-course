package section14.inputoutput.fileio;

import com.fasterxml.jackson.databind.ObjectMapper;
import section12.collections.maps.adventure.Location;

import java.io.FileWriter;
import java.io.IOException;
import java.util.Map;

public class Serialiser {
    private static final Map<Integer, Location> LOCATIONS = new Locations();
    private static final ObjectMapper MAPPER = new ObjectMapper();
    private static FileWriter locFile = null;

    public static void main(String[] args) {
        toJSON();
    }

    private static void toCSV() {
        try {
            locFile = new FileWriter("locations.txt");
            for (Location location : LOCATIONS.values()) {
                locFile.write(location.getLocationId() + "," + location.getDescription() + "\n");
            }
        } catch (IOException e) {
            System.out.println("in catch block ...");
            e.printStackTrace();
        } finally {
            try {
                System.out.println("in finally block");
                if (locFile != null) locFile.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private static void toJSON() {
        try {
            locFile = new FileWriter("locations.json");
            String json = MAPPER.writerWithDefaultPrettyPrinter()
                    .writeValueAsString(LOCATIONS);
            locFile.write(json);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                locFile.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
