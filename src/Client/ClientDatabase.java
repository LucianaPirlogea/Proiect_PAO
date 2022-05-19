package Client;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class ClientDatabase {
    Connection connection;

    public ClientDatabase(Connection connection) {
        this.connection = connection;
    }

    public List<Client> read(){
        List<Client> clients = new ArrayList<>();
        try{
            Statement statement = connection.createStatement();
            ResultSet result = statement.executeQuery("SELECT * FROM Clients");
            while(result.next()) {
                Client current = new Client(result);
                clients.add(current);
            }
            statement.close();
        }catch (Exception e){
            System.out.println(e.toString());
        }
        return clients;
    }

    public void update(Client newClient){
        try{
            String query = "UPDATE Clients SET firstName = ?, lastName = ?, CNP = ?, birthDate = ?, email = ?, phone = ?, street = ?, number = ?, city = ?, country = ?, postalCode = ? WHERE clientId = ?";
            PreparedStatement preparedStmt = connection.prepareStatement(query);
            preparedStmt.setString(1, newClient.getFirstName());
            preparedStmt.setString(2, newClient.getLastName());
            preparedStmt.setString(3, newClient.getCNP());
            preparedStmt.setString(4, (new SimpleDateFormat("yyyy-MM-dd")).format(newClient.getBirthDate()));
            preparedStmt.setString(5, newClient.getEmail());
            preparedStmt.setString(6, newClient.getPhone());
            preparedStmt.setString(7, newClient.getAddress().getStreet());
            preparedStmt.setInt(8, newClient.getAddress().getNumber());
            preparedStmt.setString(9, newClient.getAddress().getCity());
            preparedStmt.setString(10, newClient.getAddress().getCountry());
            preparedStmt.setInt(11, newClient.getAddress().getPostalCode());
            preparedStmt.setInt(12, newClient.getClientId());
            preparedStmt.executeUpdate();
            preparedStmt.close();
        }catch (Exception e){
            System.out.println(e.toString());
        }
    }

    public void create(Client client){
        try{
            String query = "INSERT INTO Clients (clientId, firstName, lastName, CNP, birthDate, email, phone, street, number, city, country, postalCode) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement preparedStmt = connection.prepareStatement(query);
            preparedStmt.setInt(1, client.getClientId());
            preparedStmt.setString(2, client.getFirstName());
            preparedStmt.setString(3, client.getLastName());
            preparedStmt.setString(4, client.getCNP());
            preparedStmt.setString(5, (new SimpleDateFormat("yyyy-MM-dd")).format(client.getBirthDate()));
            preparedStmt.setString(6, client.getEmail());
            preparedStmt.setString(7, client.getPhone());
            preparedStmt.setString(8, client.getAddress().getStreet());
            preparedStmt.setInt(9, client.getAddress().getNumber());
            preparedStmt.setString(10, client.getAddress().getCity());
            preparedStmt.setString(11, client.getAddress().getCountry());
            preparedStmt.setInt(12, client.getAddress().getPostalCode());
            preparedStmt.execute();
            preparedStmt.close();
        }catch (Exception e){
            System.out.println(e.toString());
        }
    }

    public void delete(Client client){
        try{
            String query = "DELETE FROM Clients WHERE clientId = ?";
            PreparedStatement preparedStmt = connection.prepareStatement(query);
            preparedStmt.setInt(1, client.getClientId());
            preparedStmt.execute();
            preparedStmt.close();
        }catch (Exception e){
            System.out.println(e.toString());
        }
    }

}
