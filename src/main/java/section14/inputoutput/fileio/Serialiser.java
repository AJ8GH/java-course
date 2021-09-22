package section14.inputoutput.fileio;

import com.fasterxml.jackson.databind.ObjectMapper;
import section12.collections.maps.adventure.Location;

import java.io.FileWriter;
import java.io.IOException;

public class Serialiser {
    private static final ObjectMapper MAPPER = new ObjectMapper();
    private static FileWriter locFile = null;

    public static void main(String[] args) throws IOException {
        tryWithResources(new Locations());
    }

    private static void toCSV(Locations locations) {
        try {
            locFile = new FileWriter("locations.txt");
            for (Location location : locations.values()) {
                locFile.write(location.getLocationId() + "," + location.getDescription() + "\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (locFile != null) locFile.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private static void toJSON(Locations locations) throws IOException {
        try {
            locFile = new FileWriter("locations.json");
            String json = MAPPER.writerWithDefaultPrettyPrinter()
                    .writeValueAsString(locations);
            locFile.write(json);
        } finally {
            locFile.close();
        }
    }

//    try with resources closes file stream automatically or throws IOException if it can't
    private static void tryWithResources(Locations locations) throws IOException {
        try(FileWriter locWriter = new FileWriter("locations.csv");
            FileWriter dirWriter = new FileWriter("directions.csv")) {
            for (Location location : locations.values()) {
                locWriter.write(location.getLocationId() + ","
                        + location.getDescription() + "\n");
                for (String direction : location.getExits().keySet()) {
                    dirWriter.write(location.getLocationId() + "," + direction +
                            "," + location.getExits().get(direction) + "\n");
                }
            }
        }
    }
}
