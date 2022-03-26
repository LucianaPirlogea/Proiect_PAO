package Card;
import java.util.*;

public class Card {
    private final int Id;
    private final int CVV;
    private final Date expirationDate;
    private String number;
    private String name;

    static private final Set<String> nonAvailableNumbers = new HashSet<>();
    static private final Set<Integer> nonAvailableCVVs = new HashSet<>();
    static private final Set<Integer> nonAvailableIds = new HashSet<>();

    public Card(String name) {

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

        int temporary_id = this.generateCardId();
        while(nonAvailableIds.contains(temporary_id))
            temporary_id = this.generateCardId();
        nonAvailableIds.add(temporary_id);
        this.Id = temporary_id;

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

    private int generateCardId(){
        var rand = new Random();
        return rand.nextInt(9999);
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
