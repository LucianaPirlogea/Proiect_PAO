package Transaction;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class TransactionDatabase {
    Connection connection;

    public TransactionDatabase(Connection connection) {
        this.connection = connection;
    }

    public List<Transaction> read(){
        List<Transaction> transactions = new ArrayList<>();
        try{
            Statement statement = connection.createStatement();
            ResultSet result = statement.executeQuery("SELECT * FROM Transactions");
            while(result.next()) {
                Transaction current = new Transaction(result);
                transactions.add(current);
            }
            statement.close();
        }catch (Exception e){
            System.out.println(e.toString());
        }
        return transactions;
    }

    public void update(Transaction newTransaction){
        try{
            String query = "UPDATE Transactions SET details = ? WHERE fromAccount = ? AND beneficiary = ? AND creationDate = ?";
            PreparedStatement preparedStmt = connection.prepareStatement(query);
            preparedStmt.setString(1, newTransaction.getDetails());
            preparedStmt.setString(2, newTransaction.getFromAccount());
            preparedStmt.setString(3, newTransaction.getBeneficiary());
            preparedStmt.setString(4, (new SimpleDateFormat("yyyy-MM-dd")).format(newTransaction.getCreationDate()));
            preparedStmt.executeUpdate();
            preparedStmt.close();
        }catch (Exception e){
            System.out.println(e.toString());
        }
    }

    public void create(Transaction transaction){
        try{
            String query = "INSERT INTO Transactions (type, fromAccount, beneficiary, amount, details, creationDate) VALUES (?, ?, ?, ?, ?, ?)";
            PreparedStatement preparedStmt = connection.prepareStatement(query);
            preparedStmt.setString(1, transaction.getType());
            preparedStmt.setString(2, transaction.getFromAccount());
            preparedStmt.setString(3, transaction.getBeneficiary());
            preparedStmt.setDouble(4, transaction.getAmount());
            preparedStmt.setString(5, transaction.getDetails());
            preparedStmt.setString(6, (new SimpleDateFormat("yyyy-MM-dd")).format(transaction.getCreationDate()));
            preparedStmt.execute();
            preparedStmt.close();
        }catch (Exception e){
            System.out.println(e.toString());
        }
    }

    public void delete(Transaction transaction){
        try{
            String query = "DELETE FROM Transactions WHERE fromAccount = ?, beneficiary = ?, creationDate = ?";
            PreparedStatement preparedStmt = connection.prepareStatement(query);
            preparedStmt.setString(1, transaction.getFromAccount());
            preparedStmt.setString(2, transaction.getBeneficiary());
            preparedStmt.setString(3, (new SimpleDateFormat("yyyy-MM-dd")).format(transaction.getCreationDate()));
            preparedStmt.execute();
            preparedStmt.close();
        }catch (Exception e){
            System.out.println(e.toString());
        }
    }
}
