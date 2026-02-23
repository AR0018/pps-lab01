import example.model.AccountHolder;
import example.model.BankAccount;
import example.model.SimpleBankAccount;

import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;

/**
 * The test suite for testing the SimpleBankAccount implementation
 */
class SimpleBankAccountTest {

    public static final int DEPOSIT_TEST_AMOUNT = 100;
    public static final int WITHDRAW_TEST_AMOUNT = 70;
    private AccountHolder accountHolder;
    private BankAccount bankAccount;

    @BeforeEach
    void beforeEach(){
        accountHolder = new AccountHolder("Mario", "Rossi", 1);
        bankAccount = new SimpleBankAccount(accountHolder, 0);
    }

    @Test
    void testInitialBalance() {
        assertEquals(0, bankAccount.getBalance());
    }

    @Test
    void testDeposit() {
        bankAccount.deposit(accountHolder.id(), DEPOSIT_TEST_AMOUNT);
        assertEquals(DEPOSIT_TEST_AMOUNT, bankAccount.getBalance());
    }

    @Test
    void testWrongDeposit() {
        bankAccount.deposit(accountHolder.id(), DEPOSIT_TEST_AMOUNT);
        bankAccount.deposit(2, 50);
        assertEquals(DEPOSIT_TEST_AMOUNT, bankAccount.getBalance());
    }

    @Test
    void testWithdraw() {
        bankAccount.deposit(accountHolder.id(), DEPOSIT_TEST_AMOUNT);
        bankAccount.withdraw(accountHolder.id(), WITHDRAW_TEST_AMOUNT);
        assertEquals(DEPOSIT_TEST_AMOUNT - WITHDRAW_TEST_AMOUNT, bankAccount.getBalance());
    }

    @Test
    void testWrongWithdraw() {
        bankAccount.deposit(accountHolder.id(), DEPOSIT_TEST_AMOUNT);
        bankAccount.withdraw(2, WITHDRAW_TEST_AMOUNT);
        assertEquals(DEPOSIT_TEST_AMOUNT, bankAccount.getBalance());
    }
}
