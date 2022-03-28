package Account;

import java.util.*;

public class SavingsAccount extends Account{
    private final Date startDate;
    private final Date endDate;

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

    public Date getStartDate() {
        return startDate;
    }

    public Date getEndDate() {
        return endDate;
    }
}
