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

    public void out(){
        System.out.println("Deposit Details:");
        System.out.println("Name: " + this.name);
        System.out.println("IBAN: " + this.IBAN);
        System.out.println("SWIFT: " + this.swift);
        System.out.println("Amount: " + this.amount);
        System.out.println("Start date: " + this.startDate);
        System.out.println("End date:" + this.endDate);
        System.out.println("Interest Rate: " + this.interestRate);
        System.out.println("\n");
    }
}
