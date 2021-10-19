package section15.concurrency.politeworker;

public class SharedResource {
    private Worker worker;

    public SharedResource(Worker worker) {
        this.worker = worker;
    }

    public Worker getWorker() {
        return worker;
    }

    public synchronized void setWorker(Worker worker) {
        this.worker = worker;
    }
}
