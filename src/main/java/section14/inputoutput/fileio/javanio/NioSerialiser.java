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

    private static final byte[] OUTPUT_BYTES = "Hello, World!".getBytes();
    private static final byte[] OUTPUT_BYTES_2 = "Nice to meet you".getBytes();
    private static ByteBuffer buffer;
    private static ByteBuffer intBuffer;

    private static long position1;
    private static long position2;
    private static long position3;

    public static void main(String[] args) {
//        sequentialWrite();
        DESERIALISER.readSequentially(position1, position2, position3);

    }

    public static void sequentialWrite() {
        try (FileOutputStream file = new FileOutputStream(BINARY_DAT);
             FileChannel channel = file.getChannel()) {

            buffer = ByteBuffer.allocate(100);
            buffer.put(OUTPUT_BYTES);
            position1 = OUTPUT_BYTES.length;
            buffer.putInt(245);
            position2 = position1 + Integer.BYTES;
            buffer.putInt(-98765);
            buffer.put(OUTPUT_BYTES_2);
            position3 = position2 + Integer.BYTES + OUTPUT_BYTES_2.length;
            buffer.putInt(1000);
            buffer.flip();
            channel.write(buffer);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void allInOneWriteWithChannel() {
        try (FileOutputStream file = new FileOutputStream(BINARY_DAT);
             FileChannel channel = file.getChannel()) {

            ByteBuffer writeBuffer = ByteBuffer.allocate(100);
            writeBuffer.put(OUTPUT_BYTES).putInt(245).putInt(-98765)
                    .put(OUTPUT_BYTES_2).putInt(1000)
                    .flip();

            channel.write(writeBuffer);
        } catch (IOException e) {
            e.printStackTrace();
        }
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

            buffer = ByteBuffer.wrap(OUTPUT_BYTES);
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

    private static void experimentalWriteBinaryFile() {
        try (FileOutputStream stream = new FileOutputStream(BINARY_DAT);
            FileChannel channel = stream.getChannel()) {

            buffer = ByteBuffer.allocate(OUTPUT_BYTES.length);
            buffer.put(OUTPUT_BYTES);
            buffer.flip();

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
