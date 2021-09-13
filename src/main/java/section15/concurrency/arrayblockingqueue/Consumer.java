package section15.concurrency.arrayblockingqueue;

import java.util.concurrent.ArrayBlockingQueue;


import static section15.concurrency.arrayblockingqueue.Main.EOF;

public class Consumer implements Runnable {
    private ArrayBlockingQueue<String> buffer;
    private String colour;

    public Consumer(ArrayBlockingQueue<String> buffer, String colour) {
        this.buffer = buffer;
        this.colour = colour;
    }

    @Override
    public void run() {
        int counter = 0;
        while (true) {
            synchronized (buffer) {
                try {
                    if (buffer.isEmpty()) {
                        continue;
                    } else if (buffer.peek().equals(EOF)) {
                        System.out.println(colour + "Exiting");
                        break;
                    } else {
                        System.out.println(colour + "Removed " + buffer.take());
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
