package section14.inputoutput.fileio.javanio;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

public class NioDeserialiser {
    private static final Logger LOGGER = LoggerFactory.getLogger(NioDeserialiser.class);
    private static final String DATA_TXT = "io/data.txt";
    private static final String BINARY_DAT = "io/binary.dat";
    private static final String RWD = "rwd";

//    io deserialisation - to show that it is agnostic to which package you use for ser/deser
    public void readBinaryFile(int bytesToRead) {
        try (RandomAccessFile file = new RandomAccessFile(BINARY_DAT, RWD)) {
            byte[] bytes = new byte[bytesToRead];
            file.read(bytes);
            LOGGER.info(new String(bytes));

            long int1 = file.readInt();
            long int2 = file.readInt();

            LOGGER.info(String.valueOf(int1));
            LOGGER.info(String.valueOf(int2));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void readBinaryWithChannel(ByteBuffer buffer, ByteBuffer intBuffer,
                                      byte[] bytes) {
        try (RandomAccessFile file = new RandomAccessFile(BINARY_DAT, RWD);
             FileChannel channel = file.getChannel()) {
            bytes[0] = 'a';
            bytes[1] = 'b';
            buffer.flip();
            long numberOfBytesRead = channel.read(buffer);
            if (buffer.hasArray()) {
                LOGGER.info("Byte buffer = {}", new String(buffer.array()));
            }
//            Relative read:
//            intBuffer.flip();
//            numberOfBytesRead = channel.read(intBuffer);
//            intBuffer.flip();
//            LOGGER.info(String.valueOf(intBuffer.getInt()));
//            LOGGER.info("bytes read: {}", numberOfBytesRead);
//
//            intBuffer.flip();
//            numberOfBytesRead = channel.read(intBuffer);
//            intBuffer.flip();
//            LOGGER.info(String.valueOf(intBuffer.getInt()));
//            LOGGER.info("bytes read: {}", numberOfBytesRead);

//            Absolute read (less flips required):
            intBuffer.flip();
            channel.read(intBuffer);
            LOGGER.info(String.valueOf(intBuffer.getInt(0)));
            intBuffer.flip();
            channel.read(intBuffer);
            LOGGER.info(String.valueOf(intBuffer.getInt(0)));

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
