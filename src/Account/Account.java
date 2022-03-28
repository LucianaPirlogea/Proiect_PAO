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

    public List<Transaction> filterTransactionsByYear(int year){
        List<Transaction> transactionsYear = new ArrayList<>();
        for(var transaction: transactions)
            if(transaction.getFromAccount().equals(this.IBAN) && transaction.getCreationDate().getYear()==year)
                transactionsYear.add(transaction);
        return transactionsYear;
    }

    public void addSimpleCard(String name){
        Card newCard = new Card(name);
        cards.add(newCard);
    }

    public void addVisaCard(String name, boolean emergencyCardReplacement, int travelAccidentInsurance){
        Card newCard = new Visa(name, emergencyCardReplacement, travelAccidentInsurance);
        cards.add(newCard);
    }

    public void addMasterCard(String name, String tier, int cellPhoneProtection){
        Card newCard = new MasterCard(name, tier, cellPhoneProtection);
        cards.add(newCard);
    }

    public void addAuthorizedPerson(Scanner in) throws ParseException {
        Client newAuthorized = new Client(in);
        authorizedPeople.add(newAuthorized);
    }

    public void addTransaction(String type, String fromAccount, String beneficiary, double amount, String details){
        Transaction newTransaction = new Transaction(type, fromAccount, beneficiary, amount, details);
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
