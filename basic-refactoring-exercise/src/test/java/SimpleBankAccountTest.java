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

    private void assertCorrectBalance(int correctBalance) {
        assertEquals(correctBalance, bankAccount.getBalance());
    }

    @Test
    void testInitialBalance() {
        assertCorrectBalance(INITIAL_AMOUNT);
    }

    @Test
    void testInitialBalanceLowerThanMinimum() {
        bankAccount = new SimpleBankAccount(accountHolder, INITIAL_AMOUNT - 1);
        assertCorrectBalance(INITIAL_AMOUNT);
    }

    private void depositTestAmount(final int amount) {
        bankAccount.deposit(accountHolder.id(), amount);
    }

    @Test
    void testDeposit() {
        depositTestAmount(DEPOSIT_AMOUNT);
        assertCorrectBalance(DEPOSIT_AMOUNT);
    }

    @Test
    void testWrongDeposit() {
        depositTestAmount(DEPOSIT_AMOUNT);
        bankAccount.deposit(WRONG_ID, WRONG_DEPOSIT_AMOUNT);
        assertCorrectBalance(DEPOSIT_AMOUNT);
    }

    @Test
    void testWithdraw() {
        depositTestAmount(DEPOSIT_AMOUNT);
        bankAccount.withdraw(accountHolder.id(), WITHDRAW_AMOUNT);
        assertCorrectBalance(DEPOSIT_AMOUNT - WITHDRAW_AMOUNT);
    }

    @Test
    void testWrongWithdraw() {
        depositTestAmount(DEPOSIT_AMOUNT);
        bankAccount.withdraw(WRONG_ID, WITHDRAW_AMOUNT);
        assertCorrectBalance(DEPOSIT_AMOUNT);
    }
}
