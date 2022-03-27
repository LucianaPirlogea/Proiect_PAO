package Card;

public class Visa extends Card{
    private boolean emergencyCardReplacement;
    private int travelAccidentInsurance;

    public Visa(String name, boolean emergencyCardReplacement, int travelAccidentInsurance) {
        super(name);
        this.emergencyCardReplacement = emergencyCardReplacement;
        this.travelAccidentInsurance = travelAccidentInsurance;
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
