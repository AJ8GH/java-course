package section15.concurrency.arrayblockingqueue;

import org.junit.platform.commons.function.Try;

import java.util.List;
import java.util.Random;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.locks.ReentrantLock;

import static section15.concurrency.utilconcurrent.Main.EOF;

public class Producer implements Runnable {
    private ArrayBlockingQueue<String> buffer;
    private String colour;

    public Producer(ArrayBlockingQueue<String> buffer, String colour) {
        this.buffer = buffer;
        this.colour = colour;
    }

    @Override
    public void run() {
        Random random = new Random();
        String[] numbers = { "1", "2", "3", "4", "5" };

        for (String number : numbers) {
            try {
                System.out.println(colour + "Adding..." + number);
                buffer.put(number);
                Thread.sleep(random.nextInt(1000));
            } catch (InterruptedException e) {
                System.out.println("Producer interrupted");
                e.printStackTrace();
            }
        }

        try {
            System.out.println(colour + "Adding EOF and exiting...");
            buffer.put(EOF);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
