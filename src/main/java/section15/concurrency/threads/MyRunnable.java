package section15.concurrency.threads;

import static section15.concurrency.threads.ThreadColour.ANSI_RED;

public class MyRunnable implements Runnable {

    @Override
    public void run() {
        System.out.println(ANSI_RED + "Hello from my runnable.");
    }
}
