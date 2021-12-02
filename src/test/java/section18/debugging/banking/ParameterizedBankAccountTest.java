package section18.debugging.banking;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class ParameterizedBankAccountTest {
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

    @ParameterizedTest
    @ValueSource(ints = {1, 20, 200, 500, 600})
    void withdraw(int amount) {
        double initialBalance = account.getBalance();
        account.withdraw(amount, true);
        assertEquals(initialBalance - amount, account.getBalance());
    }

    @ParameterizedTest
    @MethodSource("withdrawIsBranchArgs")
    void withdraw_Branch(Double amount, Boolean isBranch) {
        double initialBalance = account.getBalance();
        account.withdraw(amount, isBranch);
        assertEquals(initialBalance - amount, account.getBalance());
    }

    private static Stream<Arguments> withdrawIsBranchArgs() {
        return Stream.of(
            Arguments.of(100.0, true),
            Arguments.of(200.0, true),
            Arguments.of(1000.0, true),
            Arguments.of(1200.0, true)
        );
    }

    @ParameterizedTest
    @MethodSource("withdrawNotBranchArgs")
    void withdraw_NotBranch(Double amount, Boolean isBranch) {
        double initialBalance = account.getBalance();
        account.withdraw(amount, isBranch);
        assertEquals(initialBalance - amount, account.getBalance());
    }

    private static Object[][] withdrawNotBranchArgs() {
        return new Object[][] {
                { 100.0, false },
                { 200.0, false },
                { 300.0, false },
                { 400.0, false }
        };
    }

    @ParameterizedTest
    @MethodSource("withdrawNotBranchOver500Args")
    void withdraw_NotBranch_Over500(Double amount, Boolean isBranch) {
        double initialBalance = account.getBalance();

        assertThrows(IllegalArgumentException.class, () -> {
            account.withdraw(amount, isBranch);
        });
        assertEquals(initialBalance, account.getBalance());
    }

    private static Stream<Arguments> withdrawNotBranchOver500Args() {
        return Stream.of(
                Arguments.of(501.0, false),
                Arguments.of(700.0, false),
                Arguments.of(900.0, false),
                Arguments.of(1200.0, false)
        );
    }
}
