package Card;

import java.text.ParseException;
import java.util.Scanner;

public class MasterCard extends Card{
    private String tier;  // standard / world / world elite
    private int cellPhoneProtection;

    public MasterCard(String name, String tier, int cellPhoneProtection) {
        super(name);
        this.tier = tier;
        this.cellPhoneProtection = cellPhoneProtection;
    }

    public MasterCard(String name, Scanner in) throws ParseException {
        super(name);
        read(in);
    }

    public void read(Scanner in) throws ParseException {
        System.out.println("Tier(standard/world/world elite): ");
        this.tier = in.nextLine();
        System.out.println("Cell Phone Protection: ");
        this.cellPhoneProtection = Integer.parseInt(in.nextLine());
    }

    public void out(){
        super.out();
        System.out.println("Tier: " + this.tier);
        System.out.println("Cell Phone Protection: " + this.cellPhoneProtection);
        System.out.println("\n");
    }

    public void setTier(String tier){
        this.tier = tier;
    }

    public String getTier(){
        return this.tier;
    }

    public void setCellPhoneProtection(int cellPhoneProtection){
        this.cellPhoneProtection = cellPhoneProtection;
    }

    public int getCellPhoneProtection(){
        return this.cellPhoneProtection;
    }
}
