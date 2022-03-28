package Main_Service;
import Card.*;

import java.text.ParseException;
import java.util.*;

public class Main {

    public static void main(String[] args) throws ParseException {
        Scanner in = new Scanner(System.in);
        Service service = new Service();
        service.createClient(in);
        String IBAN = service.createAccountForClient(0);
        service.addCardtoAccount(0, IBAN);
        service.addAuthorizedPersontoAccount(0,IBAN);
        service.loadAccount(0, IBAN, 300);
        service.addTransactiontoAccount(0,IBAN, 200);
    }
}
