package section15.concurrency.counter;

import static section15.concurrency.threads.ThreadColour.*;

public class Countdown {
    private int i;

    public void doCountdown() {
        String colour;
        switch (Thread.currentThread().getName()) {
            case "Thread1":
                colour = ANSI_CYAN;
                break;
            case "Thread2":
                colour = ANSI_PURPLE;
                break;
            default:
                colour = ANSI_RED;
        }

        synchronized (this) {
            for (i = 10; i > 0; i--) {
                System.out.println(colour + Thread.currentThread().getName() + ": i = " + i);
            }
        }

    }
}
