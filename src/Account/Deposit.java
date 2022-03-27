package Account;

import java.util.Date;

public class Deposit extends SavingsAccount{
    private double interestRate;

    public Deposit(String name) {
        super(name);
        this.interestRate = 0.5;
    }

    public Deposit(String IBAN, String swift, double amount, String name, Date startDate, Date endDate, double interestRate) {
        super(IBAN, swift, amount, name, startDate, endDate);
        this.interestRate = interestRate;
    }

    public double getInterestRate() {
        return interestRate;
    }

    public void setInterestRate(double interestRate) {
        this.interestRate = interestRate;
    }
}
