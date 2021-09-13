package section15.concurrency;
/*
* process - unit of execution with own memory space
* each application running is a process
* each has its own memory space (heap)
* thread - unit of execution within a process
* each process can have multiple threads
* in Java every process has at least one thread - main thread
* almost all also have multiple system threads which handle tasks like memory management and I/O
* our code runs on main thread, unless we explicitly create others
* every thread created by a process shares that processes memory and files
* this can cause big problems
* in addition to the process' memory (heap), each thread has a thread stack
* thread stack is memory that only the thread can access.
* thread can be used to execute slow tasks without having the application wait for it to complete
* threads are at the mercy of the JVM and OS, therefore results can be unpredictable
* threads are started with the start() method
* don't call the run method directly, implement it but call start() instead, otherwise it runs it on the current thread
* preferable to implement Runnable interface rather than extending the Thread class
* threads can be set to sleep for specific amount of time
* */
public class Concurrency {

}
