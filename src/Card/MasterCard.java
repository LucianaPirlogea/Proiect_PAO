package Card;

public class MasterCard extends Card{
    private String tier;  // standard / world / world elite
    private int cellPhoneProtection;

    public MasterCard(int Id, String name, String tier, int cellPhoneProtection) {
        super(Id, name);
        this.tier = tier;
        this.cellPhoneProtection = cellPhoneProtection;
    }

    public MasterCard addMasterCard(String name, String tier, int cellPhoneProtection){
        return new MasterCard(indexId++, name, tier, cellPhoneProtection);
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
