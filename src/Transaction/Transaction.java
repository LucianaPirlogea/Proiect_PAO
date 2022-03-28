package Transaction;

import java.text.ParseException;
import java.util.*;

public class Transaction {
    private String type; // intrabanking / interbanking
    private String fromAccount;
    private String beneficiary;
    private double amount;
    private String details;
    private Date creationDate;

    public Transaction(String type, String fromAccount, String beneficiary, double amount, String details) {
        this.type = type;
        this.fromAccount = fromAccount;
        this.beneficiary = beneficiary;
        this.amount = amount;
        this.details = details;
        this.creationDate = new Date();
    }

    public Transaction(Scanner in, String fromAccount, double amount) throws ParseException {
        read(in);
        this.fromAccount = fromAccount;
        this.amount = amount;
        this.creationDate = new Date();
    }

    public void read(Scanner in) throws ParseException {
        System.out.println("Type(intrabanking/interbanking): ");
        this.type = in.nextLine();
        System.out.println("Beneficiary Account: ");
        this.beneficiary = in.nextLine();
        System.out.println("Details: ");
        this.details = in.nextLine();
    }

    public String getType(){
        return this.type;
    }

    public String getFromAccount() {
        return this.fromAccount;
    }

    public String getBeneficiary() {
        return this.beneficiary;
    }

    public double getAmount() {
        return this.amount;
    }

    public String getDetails() {
        return this.details;
    }

    public Date getCreationDate() {
        return this.creationDate;
    }
}
