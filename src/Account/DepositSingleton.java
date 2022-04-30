package Account;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class DepositSingleton {
    private static DepositSingleton instance = null;

    private List<Deposit> deposits = new ArrayList<Deposit>();

    private DepositSingleton() {}

    public static DepositSingleton getInstance()
    {
        if (instance == null)
            instance = new DepositSingleton();
        return instance;
    }

    public void setDeposits(List<Deposit> deposits) {
        this.deposits = deposits;
    }

    public List<Deposit> getDeposits() {
        return deposits;
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
            System.out.println("No saved deposits!");
        }

        return columns;
    }

    public void loadFromCSV() {
        try{
            var columns = DepositSingleton.getCSVColumns("data/deposits.csv");
            for(var fields : columns) {
                var newDeposit = new Deposit(
                        fields[0],
                        fields[1],
                        Double.parseDouble(fields[2]),
                        fields[3],
                        new SimpleDateFormat("yyyy-MM-dd").parse(fields[4]),
                        new SimpleDateFormat("yyyy-MM-dd").parse(fields[5]),
                        Double.parseDouble(fields[6])
                );
                deposits.add(newDeposit);
            }
        }catch (ParseException e){
            System.out.println("Cannot load deposits!");
        }


    }

    public void dumpToCSV(){
        try{
            var writer = new FileWriter("data/deposits.csv");
            for(var deposit : this.deposits){
                writer.write(deposit.toCSV());
                writer.write("\n");
            }
            writer.close();
        }catch (IOException e){
            System.out.println(e.toString());
        }
    }
}
