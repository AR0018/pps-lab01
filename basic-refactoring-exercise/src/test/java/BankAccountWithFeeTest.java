import example.model.AccountHolder;
import example.model.BankAccountWithFee;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import util.BankAccountTester;

public class BankAccountWithFeeTest {

    private static final int WITHDRAW_FEE = 1;

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
                new BankAccountWithFee(
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
        tester.testWithdraw(
                BankAccountTester.DEPOSIT_AMOUNT - BankAccountTester.WITHDRAW_AMOUNT - WITHDRAW_FEE);
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
