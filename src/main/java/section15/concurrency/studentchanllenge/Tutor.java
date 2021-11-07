package section15.concurrency.studentchanllenge;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

@Slf4j
public class Tutor {
    private final Lock lock = new ReentrantLock();
    private Student student;

    public void setStudent(Student student) {
        this.student  = student;
    }

    public void studyTime() {
        synchronized (this) {
            log.info("Tutor has arrived");
//            synchronized (student) {
                try {
                    // Wait for student to arrive and hand in assignment
                    this.wait();
                } catch (InterruptedException e) {
                    log.error("Error sleeping thread: {}", e.getMessage());
                }
                student.startStudy();
                log.info("Tutor is studying with student");
//            }
        }
    }

    public void getProgressReport() {
        log.info("Tutor gave progress report");
    }
}
