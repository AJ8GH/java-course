package section14.inputoutput.fileio.javanio;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.charset.StandardCharsets;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.List;

public class NioSerialiser {
    private static final Logger LOGGER = LoggerFactory.getLogger(NioSerialiser.class);
    private static final NioDeserialiser DESERIALISER = new NioDeserialiser();

    private static final String DATA_TXT = "io/data.txt";
    private static final String BINARY_DAT = "io/binary.dat";

    private static byte[] bytes;
    private static ByteBuffer buffer;
    private static ByteBuffer intBuffer;

    public static void main(String[] args) {
        writeBinaryFile();
        DESERIALISER.readBinaryFile(bytes.length);
        DESERIALISER.readBinaryWithChannel(buffer, intBuffer, bytes);
    }

    private static void WriteFileChannel() {
        try (FileInputStream file = new FileInputStream(DATA_TXT);
                FileChannel channel = file.getChannel()) {
            Path path = FileSystems.getDefault().getPath(DATA_TXT);
            Files.write(path, "line 5".getBytes(StandardCharsets.UTF_8), StandardOpenOption.APPEND);
            List<String> lines = Files.readAllLines(path);
            for (String line : lines) {
                System.out.println(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void writeBinaryFile() {
        try (FileOutputStream stream = new FileOutputStream(BINARY_DAT);
            FileChannel channel = stream.getChannel()) {

            bytes = "Hello, World!".getBytes();
            buffer = ByteBuffer.wrap(bytes);
            int numberOfBytes = channel.write(buffer);
            logBytesWritten(numberOfBytes);

            intBuffer = ByteBuffer.allocate(Integer.BYTES);
            intBuffer.putInt(245);
            intBuffer.flip();
            numberOfBytes = channel.write(intBuffer);
            logBytesWritten(numberOfBytes);

            intBuffer.flip();
            intBuffer.putInt(-98765);
            intBuffer.flip();
            numberOfBytes = channel.write(intBuffer);
            logBytesWritten(numberOfBytes);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void logBytesWritten(int numberOfBytes) {
        LOGGER.info("Number of bytes written: {}", numberOfBytes);
    }
}
