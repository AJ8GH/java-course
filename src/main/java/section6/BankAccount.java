package section6;

public class BankAccount {
    private int accountNumber, balance, phoneNumber;
    private String name, email;

    public BankAccount(int phoneNumber, String email, String name) {
        this.accountNumber = 99999;
        this.balance = 0;
        this.name = name;
        this.email = email;
        this.phoneNumber = phoneNumber;
    }

    public void withdraw(int amount) {
        if (amount >= balance) {
            this.balance -= amount;
            System.out.println("You withdrew " + amount);
        } else {
            System.out.println("Insufficient funds");
        }
    }

    public void deposit(int amount) {
        System.out.println("You deposited " + amount);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(int accountNumber) {
        this.accountNumber = accountNumber;
    }

    public int getBalance() {
        return balance;
    }

    public void setBalance(int balance) {
        this.balance = balance;
    }

    public int getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(int phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
