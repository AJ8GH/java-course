package section15.concurrency.bank;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

@Slf4j
public class BankAccount {

    private final Lock lock;
    private final String accountNumber;
    private double balance;

    public BankAccount(double initialBalance, String accountNumber) {
        this.balance = initialBalance;
        this.accountNumber = accountNumber;
        this.lock = new ReentrantLock();
    }

    public synchronized void synchronisedDeposit(double amount) {
        balance += amount;
        log.info("Deposited: {}, new balance: {}", amount, balance);
    }

    public synchronized void synchronisedWithdraw(double amount) {
        balance -= amount;
        log.info("Withdrew: {}, new balance: {}", amount, balance);
    }

    public void reentrantLockDeposit(double amount) {
        lock.lock();
        try {
            balance += amount;
            log.info("Deposited: {}, new balance: {}", amount, balance);
        } finally {
            lock.unlock();
        }
    }

    public void reentrantLockWithdraw(double amount) {
        lock.lock();
        try {
            balance -= amount;
            log.info("Deposited: {}, new balance: {}", amount, balance);
        } finally {
            lock.unlock();
        }
    }

    public void tryLockDeposit(double amount) {
        boolean status = false;
        try {
            if (lock.tryLock(1000, TimeUnit.MILLISECONDS)) {
                log.info("Thread: {} has claimed the deposit lock", Thread.currentThread().getName());
                try {
                    balance += amount;
                    status = true;
                    log.info("Deposited: {}, new balance: {}", amount, balance);
                } finally {
                    lock.unlock();
                    log.info("Thread: {} has released the deposit lock", Thread.currentThread().getName());
                }
            } else {
                log.info("Could not get the lock");
            }
        } catch (InterruptedException e) {
            log.error("Error: {}", e.getMessage());
        }
        log.info("Transaction status = {}", status);
    }

    public void tryLockWithdraw(double amount) {
        boolean status = false;
        try {
            if (lock.tryLock(1000, TimeUnit.MILLISECONDS)) {
                log.info("Thread: {} has claimed the withdraw lock", Thread.currentThread().getName());
                try {
                    balance -= amount;
                    status = true;
                    log.info("Deposited: {}, new balance: {}", amount, balance);
                } finally {
                    lock.unlock();
                    log.info("Thread: {} has released the withdraw lock", Thread.currentThread().getName());
                }
            } else {
                log.info("Could not get the lock");
            }
        } catch (InterruptedException e) {
            log.error("Error: {}", e.getMessage());
        }
        log.info("Transaction status = {}", status);

    }

    public double getBalance() {
        return balance;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public void printAccountNumber() {
        System.out.println("Account number = " + accountNumber);
    }
}
