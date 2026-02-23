import example.model.AccountHolder;
import example.model.BankAccount;
import example.model.BankAccountWithFee;
import example.model.SimpleBankAccount;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import util.BankAccountTester;

import static org.junit.jupiter.api.Assertions.assertEquals;
// TODO: WRITE THE TESTS (LIMITING REPETITION)
public class BankAccountWithFeeTest {

    private BankAccountTester tester;
    private final AccountHolder accountHolder =
            new AccountHolder("Mario", "Rossi", BankAccountTester.CORRECT_ID);

    @BeforeEach
    void beforeEach(){
        tester = new BankAccountTester(
                new BankAccountWithFee(
                        accountHolder,
                        BankAccountTester.INITIAL_AMOUNT
                ), accountHolder);
    }

    @Test
    void testInitialBalance() {
        tester.testInitialBalance(BankAccountTester.INITIAL_AMOUNT);
    }

    @Test
    void testInitialBalanceLowerThanMinimum() {
        tester = new BankAccountTester(
                new SimpleBankAccount(
                        accountHolder,
                        BankAccountTester.INITIAL_AMOUNT - 1
                ), accountHolder);
        tester.testInitialBalance(BankAccountTester.INITIAL_AMOUNT);
    }

    @Test
    void testDeposit() {
        tester.testDeposit(BankAccountTester.DEPOSIT_AMOUNT);
    }

    @Test
    void testDepositWrongID() {
        tester.testDepositWrongID(BankAccountTester.DEPOSIT_AMOUNT);
    }

    @Test
    void testWithdraw() {
        tester.testWithdraw(BankAccountTester.DEPOSIT_AMOUNT - BankAccountTester.WITHDRAW_AMOUNT);
    }

    @Test
    void testWithdrawWrongID() {
        tester.testWithdrawWrongID(BankAccountTester.DEPOSIT_AMOUNT);
    }

    @Test
    void testWithdrawWithEmptyBalance() {
        tester.testWithdrawWithEmptyBalance(BankAccountTester.INITIAL_AMOUNT);
    }

    @Test
    void testWithdrawWithNotEnoughBalance() {
        tester.testWithdrawWithNotEnoughBalance(BankAccountTester.DEPOSIT_AMOUNT);
    }

    @Test
    void testDepositWithNegativeAmount() {
        tester.testDepositWithNegativeAmount(BankAccountTester.INITIAL_AMOUNT);
    }

    @Test
    void testWithdrawWithNegativeAmount() {
        tester.testWithdrawWithNegativeAmount(BankAccountTester.DEPOSIT_AMOUNT);
    }
}
