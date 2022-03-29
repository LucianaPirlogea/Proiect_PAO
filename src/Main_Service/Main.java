package Main_Service;
import Card.*;

import java.text.ParseException;
import java.util.*;

public class Main {

    public static void main(String[] args) throws ParseException {
        Scanner in = new Scanner(System.in);
        Service service = new Service();
        boolean end = false;
        while(!end){
            int idClient;
            String IBAN;
            System.out.println("1  - Creare client");
            System.out.println("2  - Creare cont pentru client");
            System.out.println("3  - Adaugare card la cont");
            System.out.println("4  - Adaugare tranzactie la cont");
            System.out.println("5  - Adaugare persoana imputernicita la cont");
            System.out.println("6  - Incarcare cont client");
            System.out.println("7  - Afisare date client");
            System.out.println("8  - Afisare date card");
            System.out.println("9  - Afisare date cont client");
            System.out.println("10 - Listare tranzactii cont");
            System.out.println("11 - Afisare extras de cont client");
            System.out.println("12 - Inchidere cont client");
            System.out.println("13 - Inchideti aplicatia");
            System.out.println("\n");
            System.out.println("Introduceti comanda: ");
            int command = Integer.parseInt(in.nextLine());
            try{
                switch (command) {
                    case 1:
                        service.createClient(in);
                        break;
                    case 2:
                        System.out.println("Introduceti id-ul clientului: ");
                        idClient = Integer.parseInt(in.nextLine());
                        service.createAccountForClient(idClient);
                        break;
                    case 3:
                        System.out.println("Introduceti id-ul clientului: ");
                        idClient= Integer.parseInt(in.nextLine());
                        System.out.println("Introduceti IBAN-ul contului: ");
                        IBAN = in.nextLine();
                        service.addCardtoAccount(idClient, IBAN);
                        break;
                    case 4:
                        System.out.println("Introduceti id-ul clientului: ");
                        idClient = Integer.parseInt(in.nextLine());
                        System.out.println("Introduceti IBAN-ul contului sursa: ");
                        IBAN = in.nextLine();
                        System.out.println("Introduceti suma transferului: ");
                        double amount_transfer = Double.parseDouble(in.nextLine());
                        service.addTransactiontoAccount(idClient,IBAN,amount_transfer);
                        break;
                    case 5:
                        System.out.println("Introduceti id-ul clientului titular: ");
                        idClient = Integer.parseInt(in.nextLine());
                        System.out.println("Introduceti IBAN-ul contului: ");
                        IBAN = in.nextLine();
                        service.addAuthorizedPersontoAccount(idClient, IBAN);
                        break;
                    case 6:
                        System.out.println("Introduceti id-ul clientului: ");
                        idClient = Integer.parseInt(in.nextLine());
                        System.out.println("Introduceti IBAN-ul contului: ");
                        IBAN = in.nextLine();
                        System.out.println("Introduceti suma incarcata: ");
                        double amount_load = Double.parseDouble(in.nextLine());
                        service.loadAccount(idClient,IBAN,amount_load);
                        break;
                    case 7:
                        System.out.println("Introduceti id-ul clientului: ");
                        idClient = Integer.parseInt(in.nextLine());
                        service.getClientDetails(idClient);
                        break;
                    case 8:
                        System.out.println("Introduceti id-ul clientului: ");
                        idClient = Integer.parseInt(in.nextLine());
                        System.out.println("Introduceti IBAN-ul contului: ");
                        IBAN = in.nextLine();
                        System.out.println("Introduceti id-ul cardului: ");
                        int idCard = Integer.parseInt(in.nextLine());
                        service.getCardDetails(idClient, IBAN, idCard);
                        break;
                    case 9:
                        System.out.println("Introduceti id-ul clientului: ");
                        idClient = Integer.parseInt(in.nextLine());
                        System.out.println("Introduceti IBAN-ul contului: ");
                        IBAN = in.nextLine();
                        service.getAccountDetails(idClient,IBAN);
                        break;
                    case 10:
                        System.out.println("Introduceti id-ul clientului: ");
                        idClient = Integer.parseInt(in.nextLine());
                        System.out.println("Introduceti IBAN-ul contului: ");
                        IBAN = in.nextLine();
                        service.getTransactionsPerAccount(idClient,IBAN);
                        break;
                    case 11:
                        System.out.println("Introduceti id-ul clientului: ");
                        idClient = Integer.parseInt(in.nextLine());
                        service.getCustomerAmount(idClient);
                        break;
                    case 12:
                        System.out.println("Introduceti id-ul clientului: ");
                        idClient = Integer.parseInt(in.nextLine());
                        System.out.println("Introduceti IBAN-ul contului: ");
                        IBAN = in.nextLine();
                        service.closeAccount(idClient,IBAN);
                        break;
                    case 13:
                        end = true;
                        break;
                }
            }catch (Exception e){
                System.out.println(e.toString());
            }
            //System.out.println("\\033[H\\033[2J");
        }
    }
}
