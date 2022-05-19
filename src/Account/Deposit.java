package Account;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
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

    public Deposit(ResultSet in) throws SQLException {
        super(in);
        this.interestRate = in.getDouble("interestRate");
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

    public String toCSV() {
        return IBAN +
                "," + swift +
                "," + amount +
                "," + name +
                "," + (new SimpleDateFormat("yyyy-MM-dd")).format(startDate) +
                "," + (new SimpleDateFormat("yyyy-MM-dd")).format(endDate) +
                "," + interestRate;
    }
}
