package section14.inputoutput.fileio.adventure.nio;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import section12.collections.maps.adventure.Location;
import section14.inputoutput.fileio.adventure.Locations;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.EOFException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Scanner;

public class NioAdventureDeserialiser {
    private static final String NIO_LOC_CSV = "io/nio_locations.csv";
    private static final String NIO_DIR_CSV = "io/nio_directions.csv";
    private static final String OBJECTS_DAT = "io/nio_objects.dat";
    private static final String DELIMITER = ",";
    private static final String LINE_BREAK = "\n";

    private static final Logger LOGGER = LoggerFactory.getLogger(NioAdventureDeserialiser.class);
    private static final Locations LOCATIONS = new Locations();
    private static final NioAdventureDeserialiser DESERIALISER = new NioAdventureDeserialiser();
    private static final NioAdventureSerialiser SERIALISER = new NioAdventureSerialiser();

    private static final Path LOC_PATH = FileSystems.getDefault().getPath(NIO_LOC_CSV);
    private static final Path DIR_PATH = FileSystems.getDefault().getPath(NIO_DIR_CSV);
    private static final Path OBJECT_PATH = FileSystems.getDefault().getPath(OBJECTS_DAT);

    public static void main(String[] args) {
        DESERIALISER.deserialiseNioObject(LOCATIONS);
        SERIALISER.serialiseToObjectNio(LOCATIONS);
    }

    public void deserialiseWithNio(Locations locations) {
        try (Scanner scanner = new Scanner(Files.newBufferedReader(LOC_PATH))) {
            scanner.useDelimiter(DELIMITER);
            while (scanner.hasNextLine()) {
                int locId = scanner.nextInt();
                scanner.skip(scanner.delimiter());
                String description = scanner.nextLine();
                LOGGER.info("Imported loc {}: {}", locId, description);
                locations.put(locId, new Location(locId, description, null));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        try (BufferedReader reader = Files.newBufferedReader(DIR_PATH)) {
            String input;
            while ((input = reader.readLine()) != null) {
                String[] data = input.split(DELIMITER);
                int locId = Integer.parseInt(data[0]);
                String direction = data[1];
                int destination = Integer.parseInt(data[2]);
                LOGGER.info("{}: {}: {}", locId, direction, destination);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void deserialiseNioObject(Locations locations) {
        try (ObjectInputStream stream = new ObjectInputStream(new BufferedInputStream(Files.newInputStream(OBJECT_PATH)))) {
           boolean eof = false;
           while (!eof) {
               try {
                   Location location = (Location) stream.readObject();
                   locations.put(location.getLocationId(), location);
               } catch (EOFException e) {
                   eof = true;
                   LOGGER.info("End of file reached");
               }
           }
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }

    }
}
