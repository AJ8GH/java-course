package section15.concurrency.politeworker;

public class Worker {
    private String name;
    private boolean active;

    public Worker(String name, boolean active) {
        this.name = name;
        this.active = active;
    }

    public String getName() {
        return name;
    }

    public boolean isActive() {
        return active;
    }

    public synchronized void work(SharedResource sharedResource, Worker otherWorker) {
        while (active) {
            if (sharedResource.getWorker() != this) {
                try {
                    wait(10);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                continue;
            }

            if (otherWorker.isActive()) {
                System.out.println(this.name + " : give the resource to the worker " + otherWorker.getName());
                sharedResource.setWorker(otherWorker);
                continue;
            }

            System.out.println(this.name + " working on the common resource");
            this.active = false;
        }
    }
}
