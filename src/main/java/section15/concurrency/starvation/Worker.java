package section15.concurrency.starvation;

import static section15.concurrency.starvation.Main.LOCK;

public class Worker implements Runnable {
    private int runCount = 1;
    private String threadColour;

    public Worker(String threadColour) {
        this.threadColour = threadColour;
    }

    @Override public void run() {
        for (int i = 0; i < 100; i++) {
            LOCK.lock();
            try {
                System.out.format(threadColour + "%s: priority %s: runCount = %d\n",
                        Thread.currentThread().getName(),
                        Thread.currentThread().getPriority(),
                        runCount++);
            } finally {
                LOCK.unlock();
            }
        }
    }
}
