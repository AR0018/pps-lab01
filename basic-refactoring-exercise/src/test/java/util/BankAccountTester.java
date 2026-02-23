package util;

import example.model.AccountHolder;
import example.model.BankAccount;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class BankAccountTester {
    public static final int DEPOSIT_AMOUNT = 100;
    public static final int WITHDRAW_AMOUNT = 70;
    public static final int INITIAL_AMOUNT = 0;
    public static final int WRONG_DEPOSIT_AMOUNT = 50;
    public static final int CORRECT_ID = 1;
    public static final int WRONG_ID = 2;

    private final BankAccount bankAccount;
    private final AccountHolder accountHolder;

    public BankAccountTester(final BankAccount account, final AccountHolder accountHolder) {
        this.bankAccount = account;
        this.accountHolder = accountHolder;
    }

    private void assertCorrectBalance(int correctBalance) {
        assertEquals(correctBalance, this.bankAccount.getBalance());
    }

    public void testInitialBalance(final int expectedBalance) {
        assertCorrectBalance(expectedBalance);
    }

    private void depositTestAmount(final int amount) {
        bankAccount.deposit(accountHolder.id(), amount);
    }

    public void testDeposit(final int expectedBalance) {
        depositTestAmount(DEPOSIT_AMOUNT);
        assertCorrectBalance(expectedBalance);
    }

    public void testDepositWrongID(final int expectedBalance) {
        depositTestAmount(DEPOSIT_AMOUNT);
        bankAccount.deposit(WRONG_ID, WRONG_DEPOSIT_AMOUNT);
        assertCorrectBalance(expectedBalance);
    }

    public void testWithdraw(final int expectedBalance) {
        depositTestAmount(DEPOSIT_AMOUNT);
        bankAccount.withdraw(accountHolder.id(), WITHDRAW_AMOUNT);
        assertCorrectBalance(expectedBalance);
    }

    public void testWithdrawWrongID(final int expectedBalance) {
        depositTestAmount(DEPOSIT_AMOUNT);
        bankAccount.withdraw(WRONG_ID, WITHDRAW_AMOUNT);
        assertCorrectBalance(expectedBalance);
    }

    public void testWithdrawWithEmptyBalance(final int expectedBalance) {
        bankAccount.withdraw(accountHolder.id(), WITHDRAW_AMOUNT);
        assertCorrectBalance(expectedBalance);
    }

    public void testWithdrawWithNotEnoughBalance(final int expectedBalance) {
        bankAccount.deposit(accountHolder.id(), DEPOSIT_AMOUNT);
        bankAccount.withdraw(accountHolder.id(), DEPOSIT_AMOUNT + 1);
        assertCorrectBalance(expectedBalance);
    }

    public void testDepositWithNegativeAmount(final int expectedBalance) {
        bankAccount.deposit(accountHolder.id(), -DEPOSIT_AMOUNT);
        assertCorrectBalance(expectedBalance);
    }

    public void testWithdrawWithNegativeAmount(final int expectedBalance) {
        bankAccount.deposit(accountHolder.id(), DEPOSIT_AMOUNT);
        bankAccount.withdraw(accountHolder.id(), -WITHDRAW_AMOUNT);
        assertCorrectBalance(expectedBalance);
    }
}
