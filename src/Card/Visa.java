package Card;

public class Visa extends Card{
    private boolean emergencyCardReplacement;
    private int travelAccidentInsurance;

    public Visa(int Id, String name, boolean emergencyCardReplacement, int travelAccidentInsurance) {
        super(Id,name);
        this.emergencyCardReplacement = emergencyCardReplacement;
        this.travelAccidentInsurance = travelAccidentInsurance;
    }

    public Visa addVisaCard(String name, boolean emergencyCardReplacement, int travelAccidentInsurance){
        return new Visa(indexId++, name, emergencyCardReplacement, travelAccidentInsurance);
    }

    public void setEmergencyCardReplacement(boolean emergencyCardReplacement){
        this.emergencyCardReplacement = emergencyCardReplacement;
    }

    public void setTravelAccidentInsurance(int travelAccidentInsurance){
        this.travelAccidentInsurance = travelAccidentInsurance;
    }

    public boolean getEmergencyCardReplacement(){
        return this.emergencyCardReplacement;
    }

    public int getTravelAccidentInsurance(){
        return this.travelAccidentInsurance;
    }


}
