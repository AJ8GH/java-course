package section15.concurrency.deadlocks;

import static section15.concurrency.deadlocks.Main.LOCK_1;
import static section15.concurrency.deadlocks.Main.LOCK_2;

public class Thread1 extends Thread {
    @Override
    public void run() {
        synchronized (LOCK_1) {
            System.out.println("Thread 1: has LOCK_1");
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("Thread 1: waiting for LOCK_2");
            synchronized (LOCK_2) {
                System.out.println("Thread 1: has LOCK_1 and LOCK_2");
            }
            System.out.println("Thread 1: released LOCK_2");
        }
        System.out.println("Thread 1: released LOCK_1. Exiting...");
    }
}
