package Card;

public class MasterCard extends Card{
    private String tier;  // standard / world / world elite
    private int cellPhoneProtection;

    public MasterCard(String name, String tier, int cellPhoneProtection) {
        super(name);
        this.tier = tier;
        this.cellPhoneProtection = cellPhoneProtection;
    }

    public MasterCard addMasterCard(String name, String tier, int cellPhoneProtection){
        return new MasterCard(name, tier, cellPhoneProtection);
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
