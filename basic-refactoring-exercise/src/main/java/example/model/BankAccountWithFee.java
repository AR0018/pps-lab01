package example.model;

public class BankAccountWithFee implements BankAccount {

    public static int WITHDRAW_FEE = 1;

    private final BankAccount account;

    public BankAccountWithFee(final AccountHolder holder, final double balance) {
        this.account = new SimpleBankAccount(holder, balance);
    }

    @Override
    public double getBalance() {
        return account.getBalance();
    }

    @Override
    public void deposit(int userID, double amount) {
        account.deposit(userID, amount);
    }

    @Override
    public void withdraw(int userID, double amount) {
        account.withdraw(userID, amount + WITHDRAW_FEE);
    }
}
