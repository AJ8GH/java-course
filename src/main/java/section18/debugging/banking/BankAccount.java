package section18.debugging.banking;

public class BankAccount {
    public enum AccountType {
        CHECKING,
        SAVINGS
    }

    private final String firstName;
    private final String lastName;

    private double balance;
    private final AccountType accountType;

    public BankAccount(String firstName,
                       String lastName,
                       double balance,
                       AccountType accountType) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.balance = balance;
        this.accountType = accountType;
    }

    public double deposit(double amount, boolean branch) {
        balance += amount;
        return balance;
    }

    public double withdraw(double amount, boolean branch) {
        if (amount > 500.0 && !branch) {
            throw new IllegalArgumentException();
        }
        balance -= amount;
        return balance;
    }

    public double getBalance() {
        return balance;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public boolean isChecking() {
        return accountType == AccountType.CHECKING;
    }

    public AccountType getAccountType() {
        return accountType;
    }
}
