package Account;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class DepositDatabase {
    Connection connection;

    public DepositDatabase(Connection connection) {
        this.connection = connection;
    }

    public List<Deposit> read(){
        List<Deposit> deposits = new ArrayList<>();
        try{
            Statement statement = connection.createStatement();
            ResultSet result = statement.executeQuery("SELECT * FROM Deposits");
            while(result.next()) {
                Deposit current = new Deposit(result);
                deposits.add(current);
            }
            statement.close();
        }catch (Exception e){
            System.out.println(e.toString());
        }
        return deposits;
    }

    public void update(Deposit newDeposit){
        try{
            String query = "UPDATE Deposits SET amount = ?, name = ?, startDate = ?, endDate = ?, interestRate = ? WHERE IBAN = ? AND swift = ?";
            PreparedStatement preparedStmt = connection.prepareStatement(query);
            preparedStmt.setDouble(1, newDeposit.getAmount());
            preparedStmt.setString(2, newDeposit.getName());
            preparedStmt.setString(3, (new SimpleDateFormat("yyyy-MM-dd")).format(newDeposit.getStartDate()));
            preparedStmt.setString(4, (new SimpleDateFormat("yyyy-MM-dd")).format(newDeposit.getEndDate()));
            preparedStmt.setDouble(5, newDeposit.getInterestRate());
            preparedStmt.setString(6, newDeposit.getIBAN());
            preparedStmt.setString(7, newDeposit.getSwift());
            preparedStmt.executeUpdate();
            preparedStmt.close();
        }catch (Exception e){
            System.out.println(e.toString());
        }
    }

    public void create(Deposit deposit){
        try{
            String query = "INSERT INTO Deposits (IBAN, swift, amount, name, startDate, endDate, interestRate) VALUES (?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement preparedStmt = connection.prepareStatement(query);
            preparedStmt.setString(1, deposit.getIBAN());
            preparedStmt.setString(2, deposit.getSwift());
            preparedStmt.setDouble(3, deposit.getAmount());
            preparedStmt.setString(4, deposit.getName());
            preparedStmt.setString(5, (new SimpleDateFormat("yyyy-MM-dd")).format(deposit.getStartDate()));
            preparedStmt.setString(6, (new SimpleDateFormat("yyyy-MM-dd")).format(deposit.getEndDate()));
            preparedStmt.setDouble(7, deposit.getInterestRate());
            preparedStmt.execute();
            preparedStmt.close();
        }catch (Exception e){
            System.out.println(e.toString());
        }
    }

    public void delete(Deposit deposit){
        try{
            String query = "DELETE FROM Deposits WHERE IBAN = ?";
            PreparedStatement preparedStmt = connection.prepareStatement(query);
            preparedStmt.setString(1, deposit.getIBAN());
            preparedStmt.execute();
            preparedStmt.close();
        }catch (Exception e){
            System.out.println(e.toString());
        }
    }
}
