package section15.concurrency.utilconcurrent;

import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

import static section15.concurrency.utilconcurrent.Main.EOF;

public class Consumer implements Runnable {
    private List<String> buffer;
    private String colour;
    private ReentrantLock bufferLock;

    public Consumer(List<String> buffer,
                    String colour,
                    ReentrantLock bufferLock) {
        this.buffer = buffer;
        this.colour = colour;
        this.bufferLock = bufferLock;
    }

    @Override
    public void run() {
        int counter = 0;
        while (true) {
            try {
                if (bufferLock.tryLock(500, TimeUnit.MILLISECONDS)) {
                    try {
                        if (buffer.isEmpty()) {
                            continue;
                        } else if (buffer.get(0).equals(EOF)) {
                            System.out.println(colour + "Exiting");
                            break;
                        } else {
                            System.out.println(colour + "Removed " + buffer.remove(0));
                        }
                    } finally {
                        bufferLock.unlock();
                    }
                    System.out.println("Counter = " + counter);
                } else {
                    counter += 1;
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
