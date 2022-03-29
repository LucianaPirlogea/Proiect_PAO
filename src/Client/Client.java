package Client;

import Account.*;
import Transaction.Transaction;

import java.text.*;
import java.util.*;

public class Client {
    private static int indexId = 0;
    private final int clientId;
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

    public String addAccount(String name){
        Account newAccount = new Account(name);
        accounts.add(newAccount);
        return newAccount.getIBAN();
    }

    public void closeAccount(Account deleteAccount){
        accounts.remove(deleteAccount);
    }


    public String addSavingsAccount(String name){
        Account newSavingsAccount = new SavingsAccount(name);
        accounts.add(newSavingsAccount);
        return newSavingsAccount.getIBAN();
    }

    public String addDeposit(String name){
        Account newDeposit = new Deposit(name);
        accounts.add(newDeposit);
        return newDeposit.getIBAN();
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

    public Set<Account> getAccounts() {
        return accounts;
    }
}
