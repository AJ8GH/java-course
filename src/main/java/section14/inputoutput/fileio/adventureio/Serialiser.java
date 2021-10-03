package section14.inputoutput.fileio.adventureio;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import section12.collections.maps.adventure.Location;

import java.io.BufferedOutputStream;
import java.io.BufferedWriter;
import java.io.DataOutputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.RandomAccessFile;
import java.util.LinkedHashMap;
import java.util.Map;

public class Serialiser {
    private static final String LOC_DAT = "io/loc.dat";
    private static final String LOC_CSV = "io/loc.csv";
    private static final String DIR_CSV = "io/dir.csv";
    private static final String LOC_JSON = "io/loc.json";
    private static final String DIR_JSON = "io/dir.json";
    private static final String LOC_RAND = "io/locations_rand.dat";
    private static final String RWD = "rwd";
    private static final String DELIMITER = ",";
    private static final String END_OF_LINE = "\n";

    private static final Map<Integer, IndexRecord> INDEX = new LinkedHashMap<>();
    private static final ObjectMapper MAPPER = new ObjectMapper();
    private static final Logger LOGGER = LoggerFactory.getLogger(Deserialiser.class);

    private static FileWriter writer = null;

    public static void main(String[] args) throws IOException {
        Locations locations = new Locations();
        Deserialiser deserialiser = new Deserialiser();
        deserialiser.fromCSV(locations);
        serialiseToRandomAccessFile(locations);
    }

//    Random access files
//    1. The first 4 bytes will contain the number of locations (bytes 0 - 3)
//    2. The next 4 bytes will contain the start of the offset of the locations section (bytes 4 - 7)
//    3. The next section of the file will contain the index. The index is 1692 bytes long (bytes 8 - 1699)
//    4. The final section will contain the location records - the data (it will start at byte 1700)

    public static void serialiseToRandomAccessFile(Locations locations) {
        try (RandomAccessFile file = new RandomAccessFile(LOC_RAND, RWD)) {
            file.writeInt(locations.size());
            int indexSize = locations.size() * 3  * Integer.BYTES;
            int locationStart = (int) (indexSize + file.getFilePointer() + Integer.BYTES);
            file.writeInt(locationStart);

            long indexStart = file.getFilePointer();
            int startPointer = locationStart;
            file.seek(startPointer);

            for (Location location : locations.values()) {
                file.writeInt(location.getLocationId());
                file.writeUTF(location.getDescription());
                StringBuilder stringBuilder = new StringBuilder();
                for (String direction : location.getExits().keySet()) {
                    if (!direction.equalsIgnoreCase("Q")) {
                        stringBuilder.append(direction)
                                .append(",")
                                .append(location.getExits().get(direction))
                                .append(",");
                    }
                }
                file.writeUTF(stringBuilder.toString());
                IndexRecord record = new IndexRecord(startPointer, (int) (file.getFilePointer() - startPointer));
                INDEX.put(location.getLocationId(), record);
                startPointer = (int) file.getFilePointer();
            }
            file.seek(indexStart);
            for (Integer locationId : INDEX.keySet()) {
                file.writeInt(locationId);
                file.writeInt(INDEX.get(locationId).getStartByte());
                file.writeInt(INDEX.get(locationId).getLength());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //    Object streams
    private static void writeObject(Locations locations) {
        try (ObjectOutputStream outputStream = new ObjectOutputStream(new BufferedOutputStream(new FileOutputStream(LOC_DAT)))) {
            for (Location location : locations.values()) {
                LOGGER.info("Writing loc {} from object: {}", location.getLocationId(), location.getDescription());
                outputStream.writeObject(location);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //    Buffered Streams / byte streams
    private static void writeAsStream(Locations locations) {
        try (DataOutputStream outputStream = new DataOutputStream(new BufferedOutputStream(new FileOutputStream(LOC_DAT)))) {

            for (Location location : locations.values()) {
                outputStream.writeInt(location.getLocationId());
                outputStream.writeUTF(location.getDescription());
                LOGGER.debug("Writing Location: {}: {}",
                        location.getLocationId(),
                        location.getDescription());

                LOGGER.debug("writing {} exits", location.getExits().size() - 1);
                outputStream.writeInt(location.getExits().size() - 1);

                for (String direction : location.getExits().keySet()) {
                    if (!direction.equalsIgnoreCase("Q")) {
                        outputStream.writeUTF(direction);
                        outputStream.writeInt(location.getExits().get(direction));
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //    try with resources closes file stream automatically or throws IOException if it can't
    private static void tryWithResources(Locations locations) throws IOException {
        try (BufferedWriter locWriter = new BufferedWriter(new FileWriter(LOC_CSV));
            BufferedWriter dirWriter = new BufferedWriter(new FileWriter(DIR_CSV))) {

            for (Location location : locations.values()) {
                locWriter.write(location.getLocationId() + DELIMITER
                        + location.getDescription() + END_OF_LINE);

                for (String direction : location.getExits().keySet()) {
                    if (!direction.equalsIgnoreCase("Q")) {
                        dirWriter.write(location.getLocationId() + DELIMITER + direction +
                                DELIMITER + location.getExits().get(direction) + END_OF_LINE);
                    }
                }

            }
        }
    }

//    WITHOUT TRY WITH RESOURCES
    private static void toCSV(Locations locations) {
        try {
            writer = new FileWriter(LOC_CSV);
            for (Location location : locations.values()) {
                writer.write(location.getLocationId() + "," + location.getDescription() + "\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (writer != null) writer.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private static void toJSON(Locations locations) throws IOException {
        try {
            writer = new FileWriter(DIR_JSON);
            String json = MAPPER.writerWithDefaultPrettyPrinter()
                    .writeValueAsString(locations);
            writer.write(json);
        } finally {
            writer.close();
        }
    }
}
