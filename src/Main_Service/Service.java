package Main_Service;

import Client.*;
import Account.*;

import java.text.ParseException;
import java.util.*;

public class Service {
    private List<Client> clients = new ArrayList<>();

    public void createClient(Scanner in) throws ParseException {
        Client newClient = new Client(in);
        clients.add(newClient);
        System.out.println("Clientul a fost adaugat cu succes!");
    }

    public String createAccountForClient(int idClient){
        System.out.println("Ce tip de cont doriti sa facem?");
        System.out.println("1-Cont curent");
        System.out.println("2-Cont de economii");
        System.out.println("3-Depozit");
        Scanner in = new Scanner(System.in);
        int optiune = Integer.parseInt(in.nextLine());
        for(int i=0; i<clients.size();i++){
            if(clients.get(i).getClientId() == idClient){
                if (optiune == 1){
                    System.out.println("Contul a fost adaugat cu succes!");
                    return clients.get(i).addAccount(clients.get(i).getLastName() + clients.get(i).getFirstName());
                }
                if (optiune == 2){
                    System.out.println("Contul a fost adaugat cu succes!");
                    return clients.get(i).addSavingsAccount(clients.get(i).getLastName() + clients.get(i).getFirstName());
                }
                if (optiune == 3){
                    System.out.println("Contul a fost adaugat cu succes!");
                    return clients.get(i).addDeposit(clients.get(i).getLastName() + clients.get(i).getFirstName());
                }
            }
        }
        System.out.println("Contul nu a fost adaugat!");
        return "";
    }

    public void addCardtoAccount(int idClient, String IBAN) throws ParseException {
        System.out.println("Ce tip de card doriti sa facem?");
        System.out.println("1-Card simplu");
        System.out.println("2-Master Card");
        System.out.println("3-Card Visa");
        Scanner in = new Scanner(System.in);
        int optiune = Integer.parseInt(in.nextLine());
        for(int i=0; i<clients.size();i++){
            if(clients.get(i).getClientId() == idClient){
                Iterator<Account> it = clients.get(i).getAccounts().iterator();
                while(it.hasNext()) {
                    Account account = it.next();
                    if(account.getIBAN() == IBAN && account.getClass() == Account.class){ // se poate adauga un card doar la contul curent
                        if (optiune == 1){
                            System.out.println("Cardul a fost adaugat cu succes!");
                            account.addSimpleCard(clients.get(i).getLastName() + clients.get(i).getFirstName());
                        }
                        if (optiune == 2){
                            System.out.println("Cardul a fost adaugat cu succes!");
                            account.addMasterCard(clients.get(i).getLastName() + clients.get(i).getFirstName());
                        }
                        if (optiune == 3){
                            System.out.println("Cardul a fost adaugat cu succes!");
                            account.addVisaCard(clients.get(i).getLastName() + clients.get(i).getFirstName());
                        }
                        break;
                    }
                    else{
                        System.out.println("Cardul nu a fost adaugat! Adaugati un cont curent!");
                    }
                }
                break;

            }
        }

    }

    public void addTransactiontoAccount(int idClient, String IBAN, double amount) throws ParseException {
        for(int i=0; i<clients.size();i++){
            if(clients.get(i).getClientId() == idClient){
                Iterator<Account> it = clients.get(i).getAccounts().iterator();
                while(it.hasNext()) {
                    Account account = it.next();
                    if(account.getIBAN() == IBAN && account.getClass() == Account.class) { // se poate face tranzactie doar din contul curent
                        if(amount < account.getAmount()){
                            Scanner in = new Scanner(System.in);
                            account.setAmount(account.getAmount() - amount);
                            account.addTransaction(in, IBAN, amount);
                            System.out.println("Tranzactia a fost facuta cu succes!");
                        }
                        else{
                            System.out.println("Prea putini bani in cont!");
                        }
                        break;
                    }
                }
                break;

            }
        }

    }

    public void addAuthorizedPersontoAccount(int idClientPrincipal, String IBAN) throws ParseException {
        for(int i=0; i<clients.size();i++){
            if(clients.get(i).getClientId() == idClientPrincipal){
                Iterator<Account> it = clients.get(i).getAccounts().iterator();
                while(it.hasNext()) {
                    Account account = it.next();
                    if(account.getIBAN() == IBAN){
                        Scanner in = new Scanner(System.in);
                        account.addAuthorizedPerson(in);
                        System.out.println("Persoana imputernicita a fost adaugata cu succes!");
                        break;
                    }
                }
                break;

            }
        }

    }

    public void loadAccount(int idClient, String IBAN, double amount){
        for(int i=0; i<clients.size();i++){
            if(clients.get(i).getClientId() == idClient){
                Iterator<Account> it = clients.get(i).getAccounts().iterator();
                while(it.hasNext()) {
                    Account account = it.next();
                    if(account.getIBAN() == IBAN){
                        account.setAmount(account.getAmount() + amount);
                        System.out.println("Contul a fost incarcat cu succes!");
                        break;
                    }
                }
                break;
            }
        }
    }

    //public void closeAccount()

}
