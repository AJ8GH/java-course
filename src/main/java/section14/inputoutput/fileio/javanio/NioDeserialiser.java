package section14.inputoutput.fileio.javanio;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.charset.StandardCharsets;

public class NioDeserialiser {
    private static final Logger LOGGER = LoggerFactory.getLogger(NioDeserialiser.class);
    private static final String DATA_TXT = "io/data.txt";
    private static final String BINARY_DAT = "io/binary.dat";
    private static final String COPY_FILE = "io/copy.dat";
    private static final String COPY_FILE_2 = "io/copy_2.dat";
    private static final String RWD = "rwd";

    public void readSequentially(long position1,
                                 long position2,
                                 long position3) {
        try (RandomAccessFile file = new RandomAccessFile(BINARY_DAT, RWD);
             FileChannel channel = file.getChannel()) {

            ByteBuffer readBuffer = ByteBuffer.allocate(Integer.BYTES);
            channel.position(position3);
            channel.read(readBuffer);
            readBuffer.flip();
            System.out.println("int3 = " + readBuffer.getInt());

            readBuffer.flip();
            channel.position(position2);
            channel.read(readBuffer);
            readBuffer.flip();
            System.out.println("int2 = " + readBuffer.getInt());

            readBuffer.flip();
            channel.position(position1);
            channel.read(readBuffer);
            readBuffer.flip();
            System.out.println("int1 = " + readBuffer.getInt());

            copyFileFrom(channel);
            copyFileTo(channel);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void allInOneReadWithChannel(int bytes, int bytes2) {
        try (RandomAccessFile file = new RandomAccessFile(BINARY_DAT, RWD);
                FileChannel channel = file.getChannel()) {

            ByteBuffer readBuffer = ByteBuffer.allocate(100);
            channel.read(readBuffer);
            readBuffer.flip();

            byte[] inputString = new byte[bytes];
            readBuffer.get(inputString);
            System.out.println("inputString = " + new String(inputString));
            System.out.println("int1 = " + readBuffer.getInt());
            System.out.println("int2 = " + readBuffer.getInt());

            byte[] inputString2 = new byte[bytes2];
            readBuffer.get(inputString2);
            System.out.println("inputString = " + new String(inputString2));
            System.out.println("int3 = " + readBuffer.getInt());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

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
                LOGGER.info("Byte buffer = {}", new String(bytes));
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
            System.out.println(intBuffer.getInt(0));
            intBuffer.flip();
            channel.read(intBuffer);
            intBuffer.flip();
            System.out.println(intBuffer.getInt());
            System.out.println(intBuffer.getInt(0));

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void copyFileFrom(FileChannel originalChannel) {
        try (RandomAccessFile copyFile = new RandomAccessFile(COPY_FILE_2, RWD);
             FileChannel copyChannel = copyFile.getChannel()) {
            originalChannel.position(0);
            long bytesTransferred = copyChannel.transferFrom(originalChannel, 0, originalChannel.size());
            LOGGER.info("Bytes transferred = {}", bytesTransferred);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void copyFileTo(FileChannel originalChannel) {
        try (RandomAccessFile copyFile = new RandomAccessFile(COPY_FILE, RWD);
                FileChannel copyChannel = copyFile.getChannel()) {
            originalChannel.position(0);
            long bytesTransferred = originalChannel.transferTo(0, copyChannel.size(), copyChannel);
            LOGGER.info("Bytes transferred = {}", bytesTransferred);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
