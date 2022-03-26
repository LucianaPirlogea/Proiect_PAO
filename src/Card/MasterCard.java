package Card;

public class MasterCard extends Card{
    private String tier;  // standard / world / world elite
    private int cellPhoneProtection;
    public MasterCard(String name, String tier, int cellPhoneProtection) {
        super(name);
        this.tier = tier;
        this.cellPhoneProtection = cellPhoneProtection;
    }
    public void setTier(String tier){
        this.tier = tier;
    }
    public void setCellPhoneProtection(int cellPhoneProtection){
        this.cellPhoneProtection = cellPhoneProtection;
    }
    public String getTier(){
        return this.tier;
    }
    public int getCellPhoneProtection(){
        return this.cellPhoneProtection;
    }
}
