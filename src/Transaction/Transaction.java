package Transaction;
import java.util.*;

public class Transaction {
    final private String type; // intrabanking / interbanking
    final private String fromAccount;
    final private String beneficiary;
    final private double amount;
    final private String details;
    final private Date creationDate;

    public Transaction(String type, String fromAccount, String beneficiary, double amount, String details) {
        this.type = type;
        this.fromAccount = fromAccount;
        this.beneficiary = beneficiary;
        this.amount = amount;
        this.details = details;
        this.creationDate = new Date();
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
