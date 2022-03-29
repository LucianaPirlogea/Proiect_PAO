package Account;

import Card.*;
import Client.Client;
import Transaction.Transaction;

import java.text.ParseException;
import java.util.*;

public class Account {
    protected String IBAN;
    protected String swift;
    protected double amount;
    protected String name;

    protected List<Card> cards = new ArrayList<>();
    protected List<Client> authorizedPeople = new ArrayList<>();
    protected List<Transaction> transactions = new ArrayList<>();

    public Account(String IBAN, String swift, double amount, String name){
        this.IBAN = IBAN;
        this.swift = swift;
        this.amount = amount;
        this.name = name;
    }

    public Account(String name) {
        this.IBAN = this.generateIBAN();
        this.swift = this.generateSwift();
        this.amount = 0;
        this.name = name;
    }

    public void out(){
        System.out.println("Account Details:");
        System.out.println("Name: " + this.name);
        System.out.println("IBAN: " + this.IBAN);
        System.out.println("SWIFT: " + this.swift);
        System.out.println("Amount: " + this.amount);
        System.out.println("\n");
    }

    public int addSimpleCard(String name){
        Card newCard = new Card(name);
        cards.add(newCard);
        return newCard.getCardId();
    }

    public int addVisaCard(String name) throws ParseException {
        Scanner in = new Scanner(System.in);
        Card newCard = new Visa(name, in);
        cards.add(newCard);
        return newCard.getCardId();
    }

    public int addMasterCard(String name) throws ParseException{
        Scanner in = new Scanner(System.in);
        Card newCard = new MasterCard(name,in);
        cards.add(newCard);
        return newCard.getCardId();
    }

    public void addAuthorizedPerson(Scanner in) throws ParseException {
        Client newAuthorized = new Client(in);
        authorizedPeople.add(newAuthorized);
    }

    public void addTransaction(Scanner in, String fromAccount, double amount) throws ParseException {
        Transaction newTransaction = new Transaction(in, fromAccount, amount);
        transactions.add(newTransaction);
    }


    private String generateIBAN(){
        String generatedIBAN = "RO";
        Random rand = new Random();
        for (int i = 0; i < 2; i++)
        {
            int n = rand.nextInt(10) + 0;
            generatedIBAN += Integer.toString(n);
        }
        generatedIBAN += "BRDE";
        for (int i = 0; i < 12; i++)
        {
            int n = rand.nextInt(10) + 0;
            generatedIBAN += Integer.toString(n);
        }
        return generatedIBAN;
    }

    private String generateSwift(){
        String generatedSwift = "BRDEROBU";
        Random rand = new Random();
        for (int i = 0; i < 3; i++)
        {
            int n = rand.nextInt(10) + 0;
            generatedSwift += Integer.toString(n);
        }
        return generatedSwift;
    }

    public String getIBAN() {
        return IBAN;
    }

    public String getSwift() {
        return swift;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public double getAmount() {
        return amount;
    }

    public String getName() {
        return name;
    }

    public void setCards(List<Card> cards) {
        this.cards = cards;
    }

    public List<Card> getCards() {
        return cards;
    }

    public void setAuthorizedPeople(List<Client> authorizedPeople) {
        this.authorizedPeople = authorizedPeople;
    }

    public List<Client> getAuthorizedPeople() {
        return authorizedPeople;
    }

    public List<Transaction> getTransactions() {
        return transactions;
    }
}
