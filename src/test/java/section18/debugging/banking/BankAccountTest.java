package section18.debugging.banking;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BankAccountTest {
    private BankAccount account;

    @BeforeEach
    void setUp() {
        account = new BankAccount(
                "Dobby",
                "The House Elf",
                20,
                BankAccount.AccountType.CHECKING
        );
    }

    @Test
    void deposit_BranchIsTrue() {
        account.deposit(20, true);
        assertEquals(40, account.getBalance());

        account.deposit(20, true);
        assertEquals(65, account.getBalance(), 5);
    }

    @Test
    void withdraw_Branch() {
        account.withdraw(600, true);
        assertEquals(-580, account.getBalance());
    }

    @Test()
    void withdraw_NotBranch() {
        assertThrows(IllegalArgumentException.class, () -> {
            account.withdraw(600, false);
        });
    }

    @Test
    void checking_True() {
        assertTrue(account.isChecking(), "Account type is not CHECKING");
    }
}
