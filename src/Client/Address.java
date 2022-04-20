package Client;

import java.util.*;

public class Address {
    private String street, city, country;
    private int number;
    private int postalCode;

    public Address(String street, int number, String city, String country, int postalCode) {
        this.street = street;
        this.number = number;
        this.city = city;
        this.country = country;
        this.postalCode = postalCode;
    }

    public Address(Scanner in){
        this.read(in);
    }

    public void read(Scanner in){
        System.out.println("Street: ");
        this.street = in.nextLine();
        System.out.println("Number: ");
        this.number = Integer.parseInt(in.nextLine());
        System.out.println("City: ");
        this.city = in.nextLine();
        System.out.println("Country: ");
        this.country = in.nextLine();
        System.out.println("Postal code: ");
        this.postalCode = Integer.parseInt(in.nextLine());
    }

    public void out(){
        System.out.println("Address Details:");
        System.out.println("Street: " + this.street);
        System.out.println("Number: " + this.number);
        System.out.println("City: " + this.city);
        System.out.println("Country: " + this.country);
        System.out.println("Postal code: " + this.postalCode);
        System.out.println("\n");
    }

    public String toCSV() {
        return street +
                "," + number +
                "," + city +
                "," + country+
                "," + postalCode;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public int getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(int postalCode) {
        this.postalCode = postalCode;
    }
}
