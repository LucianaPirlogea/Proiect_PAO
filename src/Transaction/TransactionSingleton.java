package Transaction;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class TransactionSingleton {
    private static TransactionSingleton instance = null;

    private List<Transaction> transactions = new ArrayList<Transaction>();

    private TransactionSingleton() {}

    public static TransactionSingleton getInstance()
    {
        if (instance == null)
            instance = new TransactionSingleton();
        return instance;
    }

    public void setTransactions(List<Transaction> transactions) {
        this.transactions = transactions;
    }

    public List<Transaction> getTransactions() {
        return transactions;
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
            System.out.println("No saved transactions!");
        }

        return columns;
    }

    public void loadFromCSV() {
        try{
            var columns = TransactionSingleton.getCSVColumns("data/transactions.csv");
            for(var fields : columns) {
                var newTransaction = new Transaction(
                        fields[0],
                        fields[1],
                        fields[2],
                        Double.parseDouble(fields[3]),
                        fields[4],
                        new SimpleDateFormat("yyyy-MM-dd+HH:mm:ss").parse(fields[5])
                );
                transactions.add(newTransaction);
            }
        }catch (ParseException e){
            System.out.println("Cannot load transactions! - parse exception");
        } catch (Exception e) {
            System.out.println("Cannot parse transaction - invalid format");
        }


    }

    public void dumpToCSV(){
        try{
            var writer = new FileWriter("data/transactions.csv");
            for(var transaction : this.transactions){
                writer.write(transaction.toCSV());
                writer.write("\n");
            }
            writer.close();
        }catch (IOException e){
            System.out.println(e.toString());
        }
    }
}
