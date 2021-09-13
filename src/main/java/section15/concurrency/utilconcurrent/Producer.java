package section15.concurrency.utilconcurrent;

import java.util.List;
import java.util.Random;
import java.util.concurrent.locks.ReentrantLock;

import static section15.concurrency.utilconcurrent.Main.EOF;

public class Producer implements Runnable {
    private List<String> buffer;
    private String colour;
    private ReentrantLock bufferLock;

    public Producer(List<String> buffer,
                    String colour,
                    ReentrantLock bufferLock) {
        this.buffer = buffer;
        this.colour = colour;
        this.bufferLock = bufferLock;
    }

    @Override
    public void run() {
        Random random = new Random();
        String[] numbers = { "1", "2", "3", "4", "5" };

        for (String number : numbers) {
            try {
                System.out.println(colour + "Adding..." + number);
                bufferLock.lock();
                try {
                    buffer.add(number);
                } finally {
                    bufferLock.unlock();
                }

                Thread.sleep(random.nextInt(1000));
            } catch (InterruptedException e) {
                System.out.println("Producer was interrupted");
            }
        }

        System.out.println(colour + "Adding EOF and exiting...");
        bufferLock.lock();
        try {
            buffer.add(EOF);
        } finally {
            bufferLock.unlock();
        }
    }
}
