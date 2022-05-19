package Transaction;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class Transaction {
    private String type; // intrabanking / interbanking
    private String fromAccount;
    private String beneficiary;
    private double amount;
    private String details;
    private Date creationDate;

    public Transaction(String type, String fromAccount, String beneficiary, double amount, String details, Date creationDate) {
        this.type = type;
        this.fromAccount = fromAccount;
        this.beneficiary = beneficiary;
        this.amount = amount;
        this.details = details;
        this.creationDate = creationDate;
    }

    public Transaction(Scanner in, String fromAccount, double amount) throws ParseException {
        read(in);
        this.fromAccount = fromAccount;
        this.amount = amount;
        this.creationDate = new Date();
    }

    public Transaction(ResultSet in) throws SQLException {
        this.type = in.getString("type");
        this.fromAccount = in.getString("fromAccount");
        this.beneficiary = in.getString("beneficiary");
        this.amount = in.getDouble("amount");
        this.details = in.getString("details");
        this.creationDate = in.getDate("creationDate");
    }

    public void read(Scanner in) throws ParseException {
        System.out.println("Type(intrabanking/interbanking): ");
        this.type = in.nextLine();
        System.out.println("Beneficiary Account(IBAN): ");
        this.beneficiary = in.nextLine();
        System.out.println("Details: ");
        this.details = in.nextLine();
    }

    public void out(){
        System.out.println("Transaction Details:");
        System.out.println("Type(intrabanking/interbanking): " + this.type);
        System.out.println("From Account: " + this.fromAccount);
        System.out.println("Beneficiary Account(IBAN): " + this.beneficiary);
        System.out.println("Amount: " + this.amount);
        System.out.println("Details: " + this.details);
        System.out.println("Creation Date: " + this.creationDate);
        System.out.println("\n");
    }

    public String toCSV() {
        return type +
                "," + fromAccount +
                "," + beneficiary +
                "," + amount +
                "," + details +
                "," + (new SimpleDateFormat("yyyy-MM-dd")).format(creationDate);
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
