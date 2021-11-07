package section15.concurrency.bankchallenge;


public class Main {
    public static void main(String[] args) {
        NewBankAccount account1 = new NewBankAccount(500.0, "12345-678");
        NewBankAccount account2 = new NewBankAccount(1000.0, "98765-432");

        new Thread(new Transfer(account1, account2, 10.00), "Transfer1").start();
        new Thread(new Transfer(account1, account2, 55.88), "Transfer2").start();
    }
}
