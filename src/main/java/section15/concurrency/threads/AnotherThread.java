package section15.concurrency.threads;

import static section15.concurrency.threads.ThreadColour.ANSI_BLUE;
import static section15.concurrency.threads.ThreadColour.ANSI_CYAN;

public class AnotherThread extends Thread {
    @Override
    public void run() {
        System.out.println(ANSI_CYAN + "Hello from " + currentThread().getName());
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            System.out.println(ANSI_BLUE + "Another thread woke me up");
            // e.printStackTrace();
            return;
        }

        System.out.println(ANSI_BLUE + "Three seconds have passed and I'm awake");
    }
}
