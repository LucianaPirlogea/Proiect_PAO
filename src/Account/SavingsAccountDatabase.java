package Account;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class SavingsAccountDatabase {
    Connection connection;

    public SavingsAccountDatabase(Connection connection) {
        this.connection = connection;
    }

    public List<SavingsAccount> read(){
        List<SavingsAccount> savingsAccounts = new ArrayList<>();
        try{
            Statement statement = connection.createStatement();
            ResultSet result = statement.executeQuery("SELECT * FROM SavingsAccounts");
            while(result.next()) {
                SavingsAccount current = new SavingsAccount(result);
                savingsAccounts.add(current);
            }
            statement.close();
        }catch (Exception e){
            System.out.println(e.toString());
        }
        return savingsAccounts;
    }

    public void update(SavingsAccount newSavingsAccount){
        try{
            String query = "UPDATE SavingsAccounts SET amount = ?, name = ?, startDate = ?, endDate = ? WHERE IBAN = ? AND swift = ?";
            PreparedStatement preparedStmt = connection.prepareStatement(query);
            preparedStmt.setDouble(1, newSavingsAccount.getAmount());
            preparedStmt.setString(2, newSavingsAccount.getName());
            preparedStmt.setString(3, (new SimpleDateFormat("yyyy-MM-dd")).format(newSavingsAccount.getStartDate()));
            preparedStmt.setString(4, (new SimpleDateFormat("yyyy-MM-dd")).format(newSavingsAccount.getEndDate()));
            preparedStmt.setString(5, newSavingsAccount.getIBAN());
            preparedStmt.setString(6, newSavingsAccount.getSwift());
            preparedStmt.executeUpdate();
            preparedStmt.close();
        }catch (Exception e){
            System.out.println(e.toString());
        }
    }

    public void create(SavingsAccount savingsAccount){
        try{
            String query = "INSERT INTO SavingsAccounts (IBAN, swift, amount, name, startDate, endDate) VALUES (?, ?, ?, ?, ?, ?)";
            PreparedStatement preparedStmt = connection.prepareStatement(query);
            preparedStmt.setString(1, savingsAccount.getIBAN());
            preparedStmt.setString(2, savingsAccount.getSwift());
            preparedStmt.setDouble(3, savingsAccount.getAmount());
            preparedStmt.setString(4, savingsAccount.getName());
            preparedStmt.setString(5, (new SimpleDateFormat("yyyy-MM-dd")).format(savingsAccount.getStartDate()));
            preparedStmt.setString(6, (new SimpleDateFormat("yyyy-MM-dd")).format(savingsAccount.getEndDate()));
            preparedStmt.execute();
            preparedStmt.close();
        }catch (Exception e){
            System.out.println(e.toString());
        }
    }

    public void delete(SavingsAccount savingsAccount){
        try{
            String query = "DELETE FROM SavingsAccounts WHERE IBAN = ?";
            PreparedStatement preparedStmt = connection.prepareStatement(query);
            preparedStmt.setString(1, savingsAccount.getIBAN());
            preparedStmt.execute();
            preparedStmt.close();
        }catch (Exception e){
            System.out.println(e.toString());
        }
    }
}
