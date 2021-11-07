package section15.concurrency.bankchallenge;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class Transfer implements Runnable {
    private final NewBankAccount sourceAccount;
    private final NewBankAccount destinationAccount;
    private final double amount;

    public Transfer(NewBankAccount sourceAccount, NewBankAccount destinationAccount, double amount) {
        this.sourceAccount = sourceAccount;
        this.destinationAccount = destinationAccount;
        this.amount = amount;
    }

    @Override
    public void run() {
        String threadName = Thread.currentThread().getName();
        while (!sourceAccount.transfer(destinationAccount, amount)) {
            log.info("{} Livelocked", threadName);
        }
        log.info("{} completed", threadName);
    }
}
