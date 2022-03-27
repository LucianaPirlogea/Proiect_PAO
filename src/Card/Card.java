package Card;
import java.util.*;

public class Card {
    protected static int indexId = 0;
    private final int Id;
    private final int CVV;
    private final Date expirationDate;
    private String number;
    private String name;

    static private final Set<String> nonAvailableNumbers = new HashSet<>();
    static private final Set<Integer> nonAvailableCVVs = new HashSet<>();

    public Card(int Id, String name) {
        this.Id = Id;
        this.name = name;
        this.number = this.generateCardNumber();

        /* Generate Card Number */
        while(nonAvailableNumbers.contains(this.number))
            this.number = this.generateCardNumber();
        nonAvailableNumbers.add(this.number);

        /* Generate CVV */
        int temporary_CVV = this.generateCardCVV();
        while(nonAvailableCVVs.contains(temporary_CVV))
            temporary_CVV = this.generateCardCVV();
        nonAvailableCVVs.add(temporary_CVV);
        this.CVV = temporary_CVV;


        /* Generate expiration date */
        Calendar c = Calendar.getInstance();
        c.setTime(new Date());
        c.add(Calendar.YEAR, 5);
        this.expirationDate = c.getTime();
    }

    private String generateCardNumber(){
        int[] array = new int[16];
        Random rand = new Random();
        for (int i = 0; i < array.length; i++) {
           array[i] = rand.nextInt(10);
        }
        return Arrays.toString(array);
    }

    private int generateCardCVV(){
        var rand = new Random();
        return 100 + rand.nextInt(899);
    }

    public Card addSimpleCard(String name){
        return new Card(indexId++, name);
    }

    public void setName(String name){
        this.name = name;
    }

    public int getCardId() {
        return this.Id;
    }

    public String getNumber() {
        return this.number;
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
