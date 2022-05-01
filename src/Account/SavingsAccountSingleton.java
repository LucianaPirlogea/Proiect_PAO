package Account;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class SavingsAccountSingleton {
    private static SavingsAccountSingleton instance = null;

    private List<SavingsAccount> savingsAccounts = new ArrayList<SavingsAccount>();

    private SavingsAccountSingleton() {}

    public static SavingsAccountSingleton getInstance()
    {
        if (instance == null)
            instance = new SavingsAccountSingleton();
        return instance;
    }

    public void setSavingsAccounts(List<SavingsAccount> savingsAccounts) {
        this.savingsAccounts = savingsAccounts;
    }

    public List<SavingsAccount> getSavingsAccounts() {
        return savingsAccounts;
    }

    private static List<String[]> getCSVColumns(String fileName){

        List<String[]> columns = new ArrayList<>();

        try(var in = new BufferedReader(new FileReader(fileName))) {

            String line;

            while((line = in.readLine()) != null ) {
                String[] fields = line.replaceAll(" ", "").split(",");
                columns.add(fields);
            }
        } catch (IOException e) {
            System.out.println("Niciun cont de economii salvat!");
        }

        return columns;
    }

    public void loadFromCSV() {
        try{
            var columns = SavingsAccountSingleton.getCSVColumns("data/savingsAccounts.csv");
            for(var fields : columns) {
                var newAccount = new SavingsAccount(
                        fields[0],
                        fields[1],
                        Double.parseDouble(fields[2]),
                        fields[3],
                        new SimpleDateFormat("yyyy-MM-dd").parse(fields[4]),
                        new SimpleDateFormat("yyyy-MM-dd").parse(fields[5])
                        );
                savingsAccounts.add(newAccount);
            }
        }catch (ParseException e){
            System.out.println("Nu se pot incarca conturile de economii!");
        }


    }

    public void dumpToCSV(){
        try{
            var writer = new FileWriter("data/savingsAccounts.csv");
            for(var account : this.savingsAccounts){
                writer.write(account.toCSV());
                writer.write("\n");
            }
            writer.close();
        }catch (IOException e){
            System.out.println(e.toString());
        }
    }
}
