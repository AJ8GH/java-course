package section14.inputoutput.fileio.adventure.io;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import section12.collections.maps.adventure.Location;

import java.io.EOFException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.LinkedHashMap;
import java.util.Map;

public class RandomFileReader {
    private static RandomAccessFile file;
    private static final Map<Integer, IndexRecord> INDEX = new LinkedHashMap<>();
    private static final Logger LOGGER = LoggerFactory.getLogger(RandomFileReader.class);

    private static final String LOC_RAND = "io/locations_rand.dat";
    private static final String RWD = "rwd";

    public void deserialiseRandomAccessFile() throws IOException {
        try {
            file = new RandomAccessFile(LOC_RAND, RWD);
            int numLocations = file.readInt();
            long locationStartPoint = file.readInt();

            while (file.getFilePointer() < locationStartPoint) {
                int locationId = file.readInt();
                int locationStart = file.readInt();
                int locationLength = file.readInt();

                IndexRecord record = new IndexRecord(locationStart, locationLength);
                INDEX.put(locationId, record);
            }
        } catch (EOFException e) {
            LOGGER.info("End of file reached");
        }
    }

    public void close() throws IOException {
        file.close();
    }

    public Location getLocation(int locationId) throws IOException {
        IndexRecord record = INDEX.get(locationId);
        file.seek(record.getStartByte());
        int id = file.readInt();
        String description = file.readUTF();
        String exits = file.readUTF();
        String[] exitArray = exits.split(",");

        Location location = new Location(id, description, null);
        if (locationId != 0) {
            for (int i = 0; i < exitArray.length; i++) {
                LOGGER.info("exit part {} = {}", i, exitArray[i]);
                LOGGER.info("exit part {} = {}", i + 1, exitArray[i + 1]);

                String direction = exitArray[i];
                int destination = Integer.parseInt(exitArray[++i]);
                location.addExit(direction, destination);
            }
        }
        return location;
    }
}
