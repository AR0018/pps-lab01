package example.model;

public class BankAccountWithFee implements BankAccount {

    private BankAccount account;

    public BankAccountWithFee(final AccountHolder holder, final double balance) {
        this.account = new SimpleBankAccount(holder, balance);
    }

    @Override
    public double getBalance() {
        return 0;
    }

    @Override
    public void deposit(int userID, double amount) {

    }

    @Override
    public void withdraw(int userID, double amount) {

    }
}
