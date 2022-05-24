package MainServices;

import Account.AccountSingleton;
import Account.DepositSingleton;
import Account.SavingsAccountSingleton;
import Client.ClientSingleton;
import Transaction.TransactionSingleton;

import java.text.ParseException;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class MainCSV {

    static List<Integer> availableCommands = Arrays.asList(1,2,3,4,5,6,7,8,9,10,11,12);

    public static void main(String[] args) throws ParseException {

        Scanner in = new Scanner(System.in);
        Service service = new Service();
        AuditService auditService = new AuditService();

        ClientSingleton.getInstance().loadFromCSV();
        AccountSingleton.getInstance().loadFromCSV();
        SavingsAccountSingleton.getInstance().loadFromCSV();
        DepositSingleton.getInstance().loadFromCSV();
        TransactionSingleton.getInstance().loadFromCSV();


        service.setClients(ClientSingleton.getInstance().getClients());
        service.setAccounts(AccountSingleton.getInstance().getAccounts());
        service.setSavingsAccounts(SavingsAccountSingleton.getInstance().getSavingsAccounts());
        service.setDeposits(DepositSingleton.getInstance().getDeposits());
        service.setTransactions(TransactionSingleton.getInstance().getTransactions());

        boolean end = false;
        while(!end){
            int idClient;
            String IBAN;
            System.out.println("\n");
            System.out.println("1  - Creare client");
            System.out.println("2  - Creare cont pentru client");
            System.out.println("3  - Adaugare card la cont");
            System.out.println("4  - Adaugare tranzactie la cont");
            System.out.println("5  - Adaugare persoana imputernicita la cont");
            System.out.println("6  - Incarcare cont client");
            System.out.println("7  - Afisare date client");
            System.out.println("8  - Afisare date card");
            System.out.println("9  - Afisare date cont client");
            System.out.println("10 - Listare tranzactii cont ordonata dupa suma");
            System.out.println("11 - Afisare extras de cont client");
            System.out.println("12 - Inchidere cont client");
            System.out.println("13 - Inchideti aplicatia");
            System.out.println("\n");
            System.out.println("Introduceti comanda: ");
            int command = Integer.parseInt(in.nextLine());
            String commandName = "";
            try{
                switch (command) {
                    case 1:
                        commandName = "Creare client";
                        service.createClient(in);
                        break;
                    case 2:
                        commandName = "Creare cont pentru client";
                        System.out.println("Introduceti id-ul clientului: ");
                        idClient = Integer.parseInt(in.nextLine());
                        service.createAccountForClient(idClient);
                        break;
                    case 3:
                        commandName = "Adaugare card la cont";
                        System.out.println("Introduceti id-ul clientului: ");
                        idClient= Integer.parseInt(in.nextLine());
                        System.out.println("Introduceti IBAN-ul contului: ");
                        IBAN = in.nextLine();
                        service.addCardtoAccount(idClient, IBAN);
                        break;
                    case 4:
                        commandName = "Adaugare tranzactie la cont";
                        System.out.println("Introduceti id-ul clientului: ");
                        idClient = Integer.parseInt(in.nextLine());
                        System.out.println("Introduceti IBAN-ul contului sursa: ");
                        IBAN = in.nextLine();
                        System.out.println("Introduceti suma transferului: ");
                        double amount_transfer = Double.parseDouble(in.nextLine());
                        service.addTransactiontoAccount(idClient,IBAN,amount_transfer);
                        break;
                    case 5:
                        commandName = "Adaugare persoana imputernicita la cont";
                        System.out.println("Introduceti id-ul clientului titular: ");
                        idClient = Integer.parseInt(in.nextLine());
                        System.out.println("Introduceti IBAN-ul contului: ");
                        IBAN = in.nextLine();
                        service.addAuthorizedPersontoAccount(idClient, IBAN);
                        break;
                    case 6:
                        commandName = "Incarcare cont client";
                        System.out.println("Introduceti id-ul clientului: ");
                        idClient = Integer.parseInt(in.nextLine());
                        System.out.println("Introduceti IBAN-ul contului: ");
                        IBAN = in.nextLine();
                        System.out.println("Introduceti suma incarcata: ");
                        double amount_load = Double.parseDouble(in.nextLine());
                        service.loadAccount(idClient,IBAN,amount_load);
                        break;
                    case 7:
                        commandName = "Afisare date client";
                        System.out.println("Introduceti id-ul clientului: ");
                        idClient = Integer.parseInt(in.nextLine());
                        service.getClientDetails(idClient);
                        break;
                    case 8:
                        commandName = "Afisare date card";
                        System.out.println("Introduceti id-ul clientului: ");
                        idClient = Integer.parseInt(in.nextLine());
                        System.out.println("Introduceti IBAN-ul contului: ");
                        IBAN = in.nextLine();
                        System.out.println("Introduceti id-ul cardului: ");
                        int idCard = Integer.parseInt(in.nextLine());
                        service.getCardDetails(idClient, IBAN, idCard);
                        break;
                    case 9:
                        commandName = "Afisare date cont client";
                        System.out.println("Introduceti id-ul clientului: ");
                        idClient = Integer.parseInt(in.nextLine());
                        System.out.println("Introduceti IBAN-ul contului: ");
                        IBAN = in.nextLine();
                        service.getAccountDetails(idClient,IBAN);
                        break;
                    case 10:
                        commandName = "Listare tranzactii cont ordonata dupa suma";
                        System.out.println("Introduceti id-ul clientului: ");
                        idClient = Integer.parseInt(in.nextLine());
                        System.out.println("Introduceti IBAN-ul contului: ");
                        IBAN = in.nextLine();
                        service.getTransactionsPerAccount(idClient,IBAN);
                        break;
                    case 11:
                        commandName = "Afisare extras de cont client";
                        System.out.println("Introduceti id-ul clientului: ");
                        idClient = Integer.parseInt(in.nextLine());
                        service.getClientAmount(idClient);
                        break;
                    case 12:
                        commandName = "Inchidere cont client";
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
                if(availableCommands.contains(command)){
                    auditService.logAction(commandName);
                }

            }catch (Exception e){
                System.out.println(e.toString());
            }

        }

        ClientSingleton.getInstance().setClients(service.getClients());
        AccountSingleton.getInstance().setAccounts(service.getAccounts());
        SavingsAccountSingleton.getInstance().setSavingsAccounts(service.getSavingsAccounts());
        DepositSingleton.getInstance().setDeposits(service.getDeposits());
        TransactionSingleton.getInstance().setTransactions(service.getTransactions());

        ClientSingleton.getInstance().dumpToCSV();
        AccountSingleton.getInstance().dumpToCSV();
        SavingsAccountSingleton.getInstance().dumpToCSV();
        DepositSingleton.getInstance().dumpToCSV();
        TransactionSingleton.getInstance().dumpToCSV();

    }
}
