package section14.inputoutput.fileio;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import section12.collections.maps.adventure.Location;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.EOFException;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.RandomAccessFile;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

public class Deserialiser {
    private static final String LOC_DAT = "io/loc.dat";
    private static final String LOC_CSV = "io/locations.csv";
    private static final String DIR_CSV = "io/directions.csv";

    private static final Logger LOGGER = LoggerFactory.getLogger(Deserialiser.class);

    public static void main(String[] args) {
        Deserialiser deserialiser = new Deserialiser();
        deserialiser.deserialiseObject(new Locations());
    }

    public void fromCSV(Locations locations) {
        loadLocations(locations);
        loadDirections(locations);
    }

    public void deserialiseObject(Locations locations) {
        boolean eof = false;
        while (!eof) {
            try (ObjectInputStream inputStream = new ObjectInputStream(new BufferedInputStream(new FileInputStream(LOC_DAT)))) {
                Location location = (Location) inputStream.readObject();
                LOGGER.info("Read location {}: {}", location.getLocationId(), location.getDescription());
                LOGGER.info("Found {} exits", location.getExits().size());
                locations.put(location.getLocationId(), location);
            } catch (EOFException e) {
                eof = true;
                LOGGER.info("End of file reached");
            } catch (ClassNotFoundException | IOException e) {
                e.printStackTrace();
            }
        }
    }

    private void loadLocations(Locations locations) {
        try (BufferedReader reader = new BufferedReader(new FileReader(LOC_CSV))) {
            String input;
            while ((input = reader.readLine()) != null) {
                String[] data = input.split(",");
                int loc = Integer.parseInt(data[0]);
                String description = data[1];
                LOGGER.info("Imported loc: {}: {}", loc, description);
                Map<String, Integer> exits = new HashMap<>();
                locations.put(loc, new Location(loc, description, exits));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void loadDirections(Locations locations) {
        try (BufferedReader reader = new BufferedReader(
                new FileReader(DIR_CSV))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] data = line.split(",");
                int loc = Integer.parseInt(data[0]);
                String direction = data[1];
                int destination = Integer.parseInt(data[2]);
                LOGGER.info("Imported direction: {}: {}: {}:", loc, direction, destination);
                locations.get(loc).addExit(direction, destination);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void deserialiseBytes(Locations locations) {
        try (DataInputStream inputStream = new DataInputStream(
                new BufferedInputStream(new FileInputStream(LOC_DAT)))) {
            boolean eof = false;
            while (!eof) {
                try {
                    Map<String, Integer> exits = new LinkedHashMap<>();
                    int locId = inputStream.readInt();
                    String description = inputStream.readUTF();
                    int numExits = inputStream.readInt();
                    LOGGER.info("Read location {} : {}", locId, description);
                    LOGGER.info("Found {} exits", numExits);
                    for (int i = 0; i < numExits; i++) {
                        String direction = inputStream.readUTF();
                        int destination = inputStream.readInt();
                        exits.put(direction, destination);
                        LOGGER.info("\t\t" + direction + "," + destination);
                    }
                    locations.put(locId, new Location(locId, description, exits));
                } catch (EOFException e) {
                    eof = true;
                    LOGGER.info("End of file reached");
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
