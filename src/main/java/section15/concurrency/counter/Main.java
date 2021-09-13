package section15.concurrency.counter;

public class Main {
    public static void main(String[] args) {
        Countdown countdown = new Countdown();

        CountdownThread t1 = new CountdownThread(countdown);
        t1.setName("Thread1");

        CountdownThread t2 = new CountdownThread(countdown);
        t2.setName("Thread2");

        t1.start();
        t2.start();
    }
}
