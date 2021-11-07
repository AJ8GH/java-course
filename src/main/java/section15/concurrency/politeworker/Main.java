package section15.concurrency.politeworker;

public class Main {
    public static void main(String[] args) {
        final Worker worker1 = new Worker("worker 1", true);
        final Worker worker2 = new Worker("worker 2", true);

        final SharedResource sharedResource = new SharedResource(worker1);

        new Thread(() -> worker1.work(sharedResource, worker2)).start();
        new Thread(() -> worker2.work(sharedResource, worker1)).start();
    }
}
