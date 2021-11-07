package section15.concurrency.bankchallenge;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

@Slf4j
public class NewBankAccount {
    private final String accountNumber;
    private final Lock lock = new ReentrantLock();
    private double balance;

    public NewBankAccount(double balance, String accountNumber) {
        this.balance = balance;
        this.accountNumber = accountNumber;
    }

    public boolean transfer(NewBankAccount destinationAccount,
                                         double amount) {
        if (withdraw(amount)) {
            if (destinationAccount.deposit(amount)) {
                return true;
            } else {
                // The deposit failed. Refunding the money back into the account.
                log.info("{}: Destination account busy. Refunding {}",
                        Thread.currentThread().getName(), amount);
                deposit(amount);
            }
        }
        return false;
    }

    public boolean withdraw(double amount) {
        String threadName = Thread.currentThread().getName();
        if (lock.tryLock()) {
            try {
                // Simulate database access
                Thread.sleep(100);
                balance -= amount;
                log.info("{}: Withdrew {}", threadName, amount);
                return true;
            } catch (InterruptedException e) {
                log.info("{} interrupted: {}", threadName, e.getMessage());
            } finally {
                lock.unlock();
            }
        }
        return false;
    }

    public boolean deposit(double amount) {
        String threadName = Thread.currentThread().getName();
        if (lock.tryLock()) {
            try {
                // Simulate database access
                Thread.sleep(100);
                balance += amount;
                log.info("{}: deposited {}", threadName, amount);
                return true;
            } catch (InterruptedException e) {
                log.info("{} interrupted: {}", threadName, e.getMessage());
            } finally {
                lock.unlock();
            }
        }
        return false;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public double getBalance() {
        return balance;
    }
}
