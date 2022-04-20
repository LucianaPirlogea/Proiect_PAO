package Account;

import java.text.SimpleDateFormat;
import java.util.*;

public class SavingsAccount extends Account{
    protected final Date startDate;
    protected final Date endDate;

    public SavingsAccount(String name) {
        super(name);

        this.startDate = new Date();

        Calendar c = Calendar.getInstance();
        c.setTime(new Date());
        c.add(Calendar.MONTH, 6);
        this.endDate = c.getTime();
    }

    public SavingsAccount(String IBAN, String swift, double amount, String name, Date startDate, Date endDate) {
        super(IBAN, swift, amount, name);

        this.startDate = startDate;
        this.endDate = endDate;
    }

    public void out(){
        System.out.println("Savings Account Details:");
        System.out.println("Name: " + this.name);
        System.out.println("IBAN: " + this.IBAN);
        System.out.println("SWIFT: " + this.swift);
        System.out.println("Amount: " + this.amount);
        System.out.println("Start date: " + this.startDate);
        System.out.println("End date:" + this.endDate);
        System.out.println("\n");
    }

    public String toCSV() {
        return IBAN +
                "," + swift +
                "," + amount +
                "," + name +
                "," + (new SimpleDateFormat("yyyy-MM-dd")).format(startDate) +
                "," + (new SimpleDateFormat("yyyy-MM-dd")).format(endDate);
    }

    public Date getStartDate() {
        return startDate;
    }

    public Date getEndDate() {
        return endDate;
    }
}
