package Client;

import Account.*;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.*;
import java.util.*;

public class Client {
    private static int indexId = 1;
    private int clientId;
    private String firstName, lastName;
    private String CNP;
    private Date birthDate;
    private String email, phone;
    private Address address;

    private final Set<Account> accounts = new HashSet<>();

    public Client(String firstName, String lastName, String CNP, Date birthDate, String email, String phone, Address address) {
        this.clientId = indexId++;
        this.firstName = firstName;
        this.lastName = lastName;
        this.CNP = CNP;
        this.birthDate = birthDate;
        this.email = email;
        this.phone = phone;
        this.address = address;
    }

    public Client(Scanner in) throws ParseException {
        this.clientId = indexId++;
        read(in);
    }

    public Client(ResultSet in) throws SQLException{
        this.clientId = in.getInt("clientId");
        this.firstName = in.getString("firstName");
        this.lastName = in.getString("lastName");
        this.CNP = in.getString("CNP");
        this.birthDate = in.getDate("birthDate");
        this.email = in.getString("email");
        this.phone = in.getString("phone");
        this.address = new Address(in);
    }

    public void read(Scanner in) throws ParseException {
        System.out.println("First name: ");
        this.firstName = in.nextLine();
        System.out.println("Last name: ");
        this.lastName = in.nextLine();
        System.out.println("CNP: ");
        this.CNP = in.nextLine();
        System.out.println("Birth Date (yyyy-MM-dd): ");
        this.birthDate = new SimpleDateFormat("yyyy-MM-dd").parse(in.nextLine());
        System.out.println("Email: ");
        this.email = in.nextLine();
        System.out.println("Phone: ");
        this.phone = in.nextLine();
        System.out.println("Address: ");
        this.address = new Address(in);
    }

    public void out(){
        System.out.println("Client Details:");
        System.out.println("Id: " + this.clientId);
        System.out.println("First Name: " + this.firstName);
        System.out.println("Last name: " + this.lastName);
        System.out.println("CNP: " + this.CNP);
        System.out.println("Birthdate: " + this.birthDate);
        System.out.println("Email: " + this.email);
        System.out.println("Phone: " + this.phone);
        this.address.out();
    }

    public Account addAccount(String name){
        Account newAccount = new Account(name);
        accounts.add(newAccount);
        return newAccount;
    }

    public void addAccountCSV(String IBAN, String swift, double amount, String name){
        Account newAccount = new Account(IBAN, swift, amount, name);
        accounts.add(newAccount);
    }

    public void closeAccount(Account deleteAccount){
        accounts.remove(deleteAccount);
    }


    public SavingsAccount addSavingsAccount(String name){
        SavingsAccount newSavingsAccount = new SavingsAccount(name);
        accounts.add(newSavingsAccount);
        return newSavingsAccount;
    }

    public void addSavingsAccountCSV(String IBAN, String swift, double amount, String name, Date startDate, Date endDate){
        SavingsAccount newSavingAccount = new SavingsAccount(IBAN, swift, amount, name, startDate, endDate);
        accounts.add(newSavingAccount);
    }

    public Deposit addDeposit(String name){
        Deposit newDeposit = new Deposit(name);
        accounts.add(newDeposit);
        return newDeposit;
    }

    public void addDepositCSV(String IBAN, String swift, double amount, String name, Date startDate, Date endDate, double interestRate){
        Account newDeposit = new Deposit(IBAN, swift, amount, name, startDate, endDate, interestRate);
        accounts.add(newDeposit);
    }

    public String toCSV(){
        return  firstName +
                "," + lastName +
                "," + CNP +
                "," + (new SimpleDateFormat("yyyy-MM-dd")).format(birthDate) +
                "," + email +
                "," + phone +
                "," + address.toCSV();
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getCNP() {
        return CNP;
    }

    public void setCNP(String CNP) {
        this.CNP = CNP;
    }

    public Date getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public int getClientId(){ return clientId;}

    public void setClientId(int clientId) {
        this.clientId = clientId;
    }

    public Set<Account> getAccounts() {
        return accounts;
    }
}
