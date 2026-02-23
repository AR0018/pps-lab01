import example.model.AccountHolder;
import example.model.BankAccount;
import example.model.SimpleBankAccount;

import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;

/**
 * The test suite for testing the SimpleBankAccount implementation
 */
class SimpleBankAccountTest {

    public static final int DEPOSIT_AMOUNT = 100;
    public static final int WITHDRAW_AMOUNT = 70;
    public static final int INITIAL_AMOUNT = 0;
    public static final int WRONG_DEPOSIT_AMOUNT = 50;
    public static final int CORRECT_ID = 1;
    public static final int WRONG_ID = 2;

    private AccountHolder accountHolder;
    private BankAccount bankAccount;

    @BeforeEach
    void beforeEach(){
        accountHolder = new AccountHolder("Mario", "Rossi", CORRECT_ID);
        bankAccount = new SimpleBankAccount(accountHolder, INITIAL_AMOUNT);
    }

    @Test
    void testInitialBalance() {
        assertEquals(INITIAL_AMOUNT, bankAccount.getBalance());
    }

    private void depositTestAmount() {
        bankAccount.deposit(accountHolder.id(), DEPOSIT_AMOUNT);
    }

    @Test
    void testDeposit() {
        depositTestAmount();
        assertEquals(DEPOSIT_AMOUNT, bankAccount.getBalance());
    }

    @Test
    void testWrongDeposit() {
        depositTestAmount();
        bankAccount.deposit(WRONG_ID, WRONG_DEPOSIT_AMOUNT);
        assertEquals(DEPOSIT_AMOUNT, bankAccount.getBalance());
    }

    @Test
    void testWithdraw() {
        depositTestAmount();
        bankAccount.withdraw(accountHolder.id(), WITHDRAW_AMOUNT);
        assertEquals(DEPOSIT_AMOUNT - WITHDRAW_AMOUNT, bankAccount.getBalance());
    }

    @Test
    void testWrongWithdraw() {
        depositTestAmount();
        bankAccount.withdraw(WRONG_ID, WITHDRAW_AMOUNT);
        assertEquals(DEPOSIT_AMOUNT, bankAccount.getBalance());
    }
}
