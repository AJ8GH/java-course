package section14.inputoutput.fileio.adventure.nio;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import section12.collections.maps.adventure.Location;
import section14.inputoutput.fileio.adventure.Locations;

import java.io.BufferedOutputStream;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;

public class NioAdventureSerialiser {
    private static final String NIO_LOC_CSV = "io/nio_locations.csv";
    private static final String NIO_DIR_CSV = "io/nio_directions.csv";
    private static final String OBJECTS_DAT = "io/nio_objects.dat";
    private static final String DELIMITER = ",";
    private static final String LINE_BREAK = "\n";

    private static final Logger LOGGER = LoggerFactory.getLogger(NioAdventureSerialiser.class);
    private static final Locations LOCATIONS = new Locations();

    private static final Path LOC_PATH = FileSystems.getDefault().getPath(NIO_LOC_CSV);
    private static final Path OBJECT_PATH = FileSystems.getDefault().getPath(OBJECTS_DAT);
    private static final Path DIR_PATH = FileSystems.getDefault().getPath(NIO_DIR_CSV);

    private static final NioAdventureDeserialiser DESERIALISER = new NioAdventureDeserialiser();
    private static final NioAdventureSerialiser SERIALISER = new NioAdventureSerialiser();

    public static void main(String[] args) {
        DESERIALISER.deserialiseNioObject(LOCATIONS);
        SERIALISER.serialiseToObjectNio(LOCATIONS);
    }

    public void serialiseWithNio(Locations locations) {
        try (BufferedWriter locWriter = Files.newBufferedWriter(LOC_PATH);
             BufferedWriter dirWriter = Files.newBufferedWriter(DIR_PATH)) {
            for (Location location : locations.values()) {
                LOGGER.info("Writing loc {}: {}", location.getLocationId(), location.getDescription());
                locWriter.write(location.getLocationId() + DELIMITER +
                        location.getDescription() + LINE_BREAK);
                for (String direction : location.getExits().keySet()) {
                    if (!direction.equalsIgnoreCase("Q")) {
                        LOGGER.info("Writing dir {}: {}", direction, location.getExits().get(direction));
                        dirWriter.write(location.getLocationId() + DELIMITER +
                                direction + DELIMITER + location.getExits()
                                .get(direction) + LINE_BREAK);
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void serialiseToObjectNio(Locations locations) {
        try (ObjectOutputStream stream = new ObjectOutputStream(
                new BufferedOutputStream(Files.newOutputStream(OBJECT_PATH)))) {
            for (Location location : locations.values()) {
                LOGGER.info("Writing loc {}", location.getLocationId());
                stream.writeObject(location);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
