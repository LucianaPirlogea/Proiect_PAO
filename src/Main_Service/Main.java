package Main_Service;
import Card.*;

import java.util.*;

public class Main {

    public static void main(String[] args) {
        Card c1 = new Card("Alexandra");
        System.out.println(c1.getCardId());
        System.out.println(c1.getCVV());
        System.out.println(c1.getExpirationDate());
        System.out.println(c1.getName());
        System.out.println(c1.getNumber());

        Visa c2 = new Visa("Alexandra", true, 1000);
        System.out.println(c2.getCardId());
        System.out.println(c2.getCVV());
        System.out.println(c2.getExpirationDate());
        System.out.println(c2.getName());
        System.out.println(c2.getNumber());
        System.out.println(c2.getEmergencyCardReplacement());
        System.out.println(c2.getTravelAccidentInsurance());

        MasterCard c3 = new MasterCard("Alexandra", "world", 3500);
        System.out.println(c3.getCardId());
        System.out.println(c3.getCVV());
        System.out.println(c3.getExpirationDate());
        System.out.println(c3.getName());
        System.out.println(c3.getNumber());
        System.out.println(c3.getTier());
        System.out.println(c3.getCellPhoneProtection());
    }
}
