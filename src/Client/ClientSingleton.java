package Client;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class ClientSingleton {

    private static ClientSingleton instance = null;

    private List<Client> clients = new ArrayList<Client>();

    private ClientSingleton() {}

    public static ClientSingleton getInstance()
    {
        if (instance == null)
            instance = new ClientSingleton();
        return instance;
    }

    public void setCustomers(List<Client> clients) {
        this.clients = clients;
    }

    public List<Client> getCustomers() {
        return clients;
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
            System.out.println("No saved clients!");
        }

        return columns;
    }

    public void loadFromCSV() {
        try{
            var columns = ClientSingleton.getCSVColumns("data/clients.csv");
            for(var fields : columns) {
                var newClient = new Client(
                        fields[0],
                        fields[1],
                        fields[2],
                        new SimpleDateFormat("yyyy-MM-dd").parse(fields[3]),
                        fields[4],
                        fields[5],
                        new Address(fields[6], Integer.parseInt(fields[7]), fields[8], fields[9], Integer.parseInt(fields[10]))
                );
                clients.add(newClient);
            }
        }catch (ParseException e){
            System.out.println(e.toString());
        }

    }

    public void dumpToCSV(){
        try{
            var writer = new FileWriter("data/clients.csv");
            for(var client : this.clients){
                writer.write(client.toCSV());
                writer.write("\n");
            }
            writer.close();
        }catch (IOException e){
            System.out.println(e.toString());
        }
    }

}
