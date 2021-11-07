package section15.concurrency.bank;

public class Main {
    public static void main(String[] args) {

        BankAccount account = new BankAccount(1000.00, "12345-678");
        Thread thread1 = new Thread(() -> {
            account.tryLockDeposit(1000.00);
            account.tryLockWithdraw(50);
        });

        Thread thread2 = new Thread(() -> {
            account.tryLockDeposit(203.75);
            account.tryLockWithdraw(100.00);
        });

        thread1.start();
        thread2.start();
    }
}
