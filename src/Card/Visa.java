package Card;

import Client.Address;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Scanner;

public class Visa extends Card{
    private boolean emergencyCardReplacement;
    private int travelAccidentInsurance;

    public Visa(String name, boolean emergencyCardReplacement, int travelAccidentInsurance) {
        super(name);
        this.emergencyCardReplacement = emergencyCardReplacement;
        this.travelAccidentInsurance = travelAccidentInsurance;
    }

    public Visa(String name, Scanner in) throws ParseException {
        super(name);
        read(in);
    }

    public void read(Scanner in) throws ParseException {
        System.out.println("Emergency Card Replacement: ");
        this.emergencyCardReplacement = Boolean.parseBoolean(in.nextLine());
        System.out.println("Travel Accident Insurance: ");
        this.travelAccidentInsurance = Integer.parseInt(in.nextLine());
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
