package Main_Service;

import Card.Card;
import Client.*;
import Account.*;
import Transaction.*;

import java.text.ParseException;
import java.util.*;

public class Service {
    private List<Client> clients = new ArrayList<>();

    public void createClient(Scanner in) throws ParseException {
        System.out.println("Introduceti datele:");
        Client newClient = new Client(in);
        clients.add(newClient);
        System.out.println("Clientul a fost adaugat cu succes!");
        System.out.println("Id Client: " + newClient.getClientId());
    }

    public void createAccountForClient(int idClient){
        System.out.println("Ce tip de cont doriti sa facem?");
        System.out.println("1-Cont curent");
        System.out.println("2-Cont de economii");
        System.out.println("3-Depozit");
        int foundClient = 0;
        Scanner in = new Scanner(System.in);
        int optiune = Integer.parseInt(in.nextLine());
        for(int i=0; i<clients.size();i++){
            if(clients.get(i).getClientId() == idClient){
                foundClient = 1;
                if (optiune == 1){
                    System.out.println("Contul a fost adaugat cu succes!");
                    String IBAN = clients.get(i).addAccount(clients.get(i).getLastName() + " " + clients.get(i).getFirstName());
                    System.out.println("IBAN: " + IBAN);
                }
                if (optiune == 2){
                    System.out.println("Contul a fost adaugat cu succes!");
                    String IBAN = clients.get(i).addSavingsAccount(clients.get(i).getLastName() + " " +clients.get(i).getFirstName());
                    System.out.println("IBAN: " + IBAN);
                }
                if (optiune == 3){
                    System.out.println("Contul a fost adaugat cu succes!");
                    String IBAN = clients.get(i).addDeposit(clients.get(i).getLastName() + " " +clients.get(i).getFirstName());
                    System.out.println("IBAN: " + IBAN);
                }
            }
        }
        if(foundClient==0){
            System.out.println("Clientul nu exista!");
        }

    }

    public void addCardtoAccount(int idClient, String IBAN) throws ParseException {
        System.out.println("Ce tip de card doriti sa facem?");
        System.out.println("1-Card simplu");
        System.out.println("2-Master Card");
        System.out.println("3-Card Visa");
        Scanner in = new Scanner(System.in);
        int foundAccount = 0;
        int optiune = Integer.parseInt(in.nextLine());
        for(int i=0; i<clients.size();i++){
            if(clients.get(i).getClientId() == idClient){
                Iterator<Account> it = clients.get(i).getAccounts().iterator();
                while(it.hasNext()) {
                    Account account = it.next();
                    if(account.getIBAN().equals(IBAN) && account.getClass() == Account.class){ // se poate adauga un card doar la contul curent
                        foundAccount = 1;
                        if (optiune == 1){
                            System.out.println("Cardul a fost adaugat cu succes!");
                            int idCard = account.addSimpleCard(clients.get(i).getLastName() + " " +clients.get(i).getFirstName());
                            System.out.println("Id card: " + idCard);
                        }
                        if (optiune == 2){
                            System.out.println("Cardul a fost adaugat cu succes!");
                            int idCard =account.addMasterCard(clients.get(i).getLastName() + " " +clients.get(i).getFirstName());
                            System.out.println("Id card: " + idCard);
                        }
                        if (optiune == 3){
                            System.out.println("Cardul a fost adaugat cu succes!");
                            int idCard = account.addVisaCard(clients.get(i).getLastName() + " " +clients.get(i).getFirstName());
                            System.out.println("Id card: " + idCard);
                        }
                        break;
                    }
                }
                break;

            }
        }
        if(foundAccount == 0)
            System.out.println("Cardul nu a fost adaugat! Adaugati un cont curent!");

    }

    public void addTransactiontoAccount(int idClient, String IBAN, double amount) throws ParseException {
        int foundAccount = 0;
        for(int i=0; i<clients.size();i++){
            if(clients.get(i).getClientId() == idClient){
                Iterator<Account> it = clients.get(i).getAccounts().iterator();
                while(it.hasNext()) {
                    Account account = it.next();
                    if(account.getIBAN().equals(IBAN) && account.getClass() == Account.class) { // se poate face tranzactie doar din contul curent
                        foundAccount = 1;
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
        if(foundAccount == 0){
            System.out.println("Tranzactia nu a fost facuta! Adaugati un cont curent!");
        }

    }

    public void addAuthorizedPersontoAccount(int idClientPrincipal, String IBAN) throws ParseException {
        for(int i=0; i<clients.size();i++){
            if(clients.get(i).getClientId() == idClientPrincipal){
                Iterator<Account> it = clients.get(i).getAccounts().iterator();
                while(it.hasNext()) {
                    Account account = it.next();
                    if(account.getIBAN().equals(IBAN)){
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
                    if(account.getIBAN().equals(IBAN)){
                        account.setAmount(account.getAmount() + amount);
                        System.out.println("Contul a fost incarcat cu succes!");
                        break;
                    }
                }
                break;
            }
        }
    }

    public void closeAccount(int idClient, String IBAN){
        int foundAccount = 0;
        for(int i=0; i<clients.size();i++){
            if(clients.get(i).getClientId() == idClient){
                Iterator<Account> it = clients.get(i).getAccounts().iterator();
                while(it.hasNext()) {
                    Account account = it.next();
                    if(account.getIBAN().equals(IBAN)){
                        foundAccount = 1;
                        clients.get(i).closeAccount(account);
                        System.out.println("Contul a fost inchis cu succes!");
                        break;
                    }
                }
                break;
            }
        }

        if(foundAccount == 0){
            System.out.println("Contul nu a fost gasit!");
        }

    }

    public void getCustomerAmount(int idClient){
        for(int i=0; i<clients.size();i++){
            if(clients.get(i).getClientId() == idClient){
                Iterator<Account> it = clients.get(i).getAccounts().iterator();
                while(it.hasNext()) {
                    Account account = it.next();
                    if(account.getClass() == Account.class){
                        System.out.println("Suma cont curent: " + account.getAmount());
                    }
                    if(account.getClass() == SavingsAccount.class){
                        System.out.println("Suma cont de economii: " + account.getAmount());
                    }
                    if(account.getClass() == Deposit.class){
                        System.out.println("Suma depozit: " + account.getAmount());
                    }
                }
                break;
            }
        }
    }

    public void getTransactionsPerAccount(int idClient, String IBAN){
        int foundAccount = 0;
        for(int i=0; i<clients.size();i++){
            if(clients.get(i).getClientId() == idClient){
                Iterator<Account> it = clients.get(i).getAccounts().iterator();
                while(it.hasNext()) {
                    Account account = it.next();
                    if(account.getIBAN().equals(IBAN)){
                        foundAccount = 1;
                        List<Transaction> transactions = account.getTransactions();
                        Comparator<Transaction> amountSorter = Comparator.comparingDouble((Transaction o) -> o.getAmount());

                        Collections.sort(transactions, amountSorter);
                        for(int j=0; j < account.getTransactions().size(); j++){
                            transactions.get(j).out();
                            System.out.println("\n");
                        }
                        break;
                    }
                }
                break;
            }
        }

        if(foundAccount == 0){
            System.out.println("Contul nu a fost gasit!");
        }
    }


    public void getCardDetails(int idClient, String IBAN, int idCard){
        int foundAccount = 0;
        for(int i=0; i<clients.size();i++){
            if(clients.get(i).getClientId() == idClient){
                Iterator<Account> it = clients.get(i).getAccounts().iterator();
                while(it.hasNext()) {
                    Account account = it.next();
                    if(account.getIBAN().equals(IBAN)){
                        foundAccount = 1;
                        List<Card> cards = account.getCards();
                        for(int j=0; j < cards.size(); j++){
                            if(cards.get(j).getCardId() == idCard){
                                cards.get(j).out();
                            }
                        }
                        break;
                    }
                }
                break;
            }
        }

        if(foundAccount == 0){
            System.out.println("Contul nu a fost gasit!");
        }
    }

    public void getClientDetails(int idClient){
        int foundClient = 0;
        for(int i=0; i<clients.size();i++){
            if(clients.get(i).getClientId() == idClient){
                foundClient = 1;
                clients.get(i).out();
            }
        }

        if(foundClient == 0){
            System.out.println("Clientul nu a fost gasit!");
        }
    }

    public void getAccountDetails(int idClient, String IBAN){
        int foundAccount = 0;
        for(int i=0; i<clients.size();i++){
            if(clients.get(i).getClientId() == idClient){
                Iterator<Account> it = clients.get(i).getAccounts().iterator();
                while(it.hasNext()) {
                    Account account = it.next();
                    if(account.getIBAN().equals(IBAN)){
                        foundAccount = 1;
                        account.out();
                        break;
                    }
                }
                break;
            }
        }

        if(foundAccount == 0){
            System.out.println("Contul nu a fost gasit!");
        }
    }


}
