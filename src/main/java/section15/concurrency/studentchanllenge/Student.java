package section15.concurrency.studentchanllenge;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

@Slf4j
public class Student {
    private final Tutor tutor;
    private final Lock lock = new ReentrantLock();

    public Student(Tutor tutor) {
        this.tutor = tutor;
    }

    public void startStudy() {
        log.info("Student is studying");
    }

    public void handInAssignment() {
        synchronized (tutor) {
            tutor.getProgressReport();
            synchronized (this) {
                log.info("Student handed in assignment");
                tutor.notifyAll();
            }
        }
    }

    public Lock getLock() {
        return lock;
    }
}
