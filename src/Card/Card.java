package Card;
import java.util.*;

public class Card {
    protected static int indexId = 0;
    protected final int Id;
    protected final int CVV;
    protected final Date expirationDate;
    protected String number;
    protected String name;

    static private final Set<String> nonAvailableNumbers = new HashSet<>();
    static private final Set<Integer> nonAvailableCVVs = new HashSet<>();

    public Card(String name) {
        this.Id = indexId++;
        this.name = name;
        this.number = this.generateCardNumber();

        while(nonAvailableNumbers.contains(this.number))
            this.number = this.generateCardNumber();
        nonAvailableNumbers.add(this.number);

        int temporary_CVV = this.generateCardCVV();
        while(nonAvailableCVVs.contains(temporary_CVV))
            temporary_CVV = this.generateCardCVV();
        nonAvailableCVVs.add(temporary_CVV);
        this.CVV = temporary_CVV;


        Calendar c = Calendar.getInstance();
        c.setTime(new Date());
        c.add(Calendar.YEAR, 5);
        this.expirationDate = c.getTime();
    }

    public void out(){
        System.out.println("Card Details:");
        System.out.println("Id: " + this.Id);
        System.out.println("Name: " + this.name);
        System.out.println("Number: " + this.number);
        System.out.println("CVV: " + this.CVV);
        System.out.println("Expiration Date: " + this.expirationDate);
    }

    private String generateCardNumber(){
        StringBuilder number = new StringBuilder();
        Random rand = new Random();
        for (int i = 0; i < 16; i++) {
           number.append(rand.nextInt(10));
        }
        return number.toString();
    }

    private int generateCardCVV(){
        var rand = new Random();
        return 100 + rand.nextInt(899);
    }

    public int getCardId() {
        return this.Id;
    }

    public String getNumber() {
        return this.number;
    }

    public void setName(String name){
        this.name = name;
    }

    public String getName() {
        return this.name;
    }

    public int getCVV() {
        return this.CVV;
    }

    public Date getExpirationDate() {
        return this.expirationDate;
    }


}
