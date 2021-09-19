package section15.concurrency.deadlocks;

public class Main {
    public static final Object LOCK_1 = new Object();
    public static final Object LOCK_2 = new Object();

    public static void main(String[] args) {
        new Thread1().start();
        new Thread2().start();
    }
}
