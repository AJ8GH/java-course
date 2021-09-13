package section15.concurrency.arrayblockingqueue;

import java.util.concurrent.*;

import static section15.concurrency.threads.ThreadColour.*;

public class Main {
    public static final String EOF = "EOF";

    public static void main(String[] args) {
        ArrayBlockingQueue<String> buffer = new ArrayBlockingQueue<>(6);
        ExecutorService executor = Executors.newFixedThreadPool(3);

        Producer producer = new Producer(buffer, ANSI_YELLOW);
        Consumer consumer1 = new Consumer(buffer, ANSI_PURPLE);
            Consumer consumer2 = new Consumer(buffer, ANSI_CYAN);

        executor.execute(producer);
        executor.execute(consumer1);
        executor.execute(consumer2);

        Future<String> future = executor.submit(new Callable<String>() {
            @Override
            public String call() throws Exception {
                System.out.println(ANSI_WHITE + "I'm being printed by the callable class");
                return "This is the callable result";
            }
        });

        try {
            System.out.println(future.get());
        } catch (ExecutionException e) {
            System.out.println("Something went wrong");
        } catch (InterruptedException e) {
            System.out.println("Thread running the task was interrupted");
        }
        executor.shutdown();
    }
}
