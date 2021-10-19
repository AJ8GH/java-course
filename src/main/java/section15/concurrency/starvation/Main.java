package section15.concurrency.starvation;

import section15.concurrency.threads.ThreadColour;

import java.util.concurrent.locks.ReentrantLock;

public class Main {
    public static final ReentrantLock LOCK = new ReentrantLock(true);

    public static void main(String[] args) {
        Thread t1 = new Thread(new Worker(ThreadColour.ANSI_RED), "Thread 1");
        Thread t2 = new Thread(new Worker(ThreadColour.ANSI_BLUE), "Thread 2");
        Thread t3 = new Thread(new Worker(ThreadColour.ANSI_GREEN), "Thread 3");
        Thread t4 = new Thread(new Worker(ThreadColour.ANSI_CYAN), "Thread 4");
        Thread t5 = new Thread(new Worker(ThreadColour.ANSI_PURPLE), "Thread 5");

        t1.setPriority(10);
        t2.setPriority(8);
        t3.setPriority(6);
        t4.setPriority(4);
        t5.setPriority(2);

        t1.start();
        t2.start();
        t3.start();
        t4.start();
        t5.start();
    }
}
