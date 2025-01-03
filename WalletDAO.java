
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.UUID;


/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author HC
 */
public class WalletDAO {
  private Connection connection;
  public WalletDAO() {
    try{
       Class.forName("com.mysql.cj.djbc.Driver");
       connection=DriverManager.getConnection("jdbc:mysql//localhost:3306/walletdb","root","");
    }catch (ClassNotFoundException | SQLException e){
        System.out.println("Erroer connection to database" + e.getMessage());
    }
}
  public String generateWalletId(){
     return UUID.randomUUID().toString(); 
  }
    public void createWallet(Wallet wallet){
        try(PreparedStatement statement=connection.prepareStatement("INSERT INTO wallets(id,name,descriptiontype,currency) VALUES (?,?,?,?,?)")){
            String walletId = null;
            statement.setString(1, walletId);
            statement.setString(2, wallet.getName());
            statement.setString(3, wallet.getDescription());
            statement.setString(4, wallet.getType());
            statement.setString(5, wallet.getCurrency());
            
            statement.executeUpdate();
            //Send user notification
            NotificationService notificationService=NotificationService();
            notificationService.sendNotification("Wallet created successfully! Your wallet ID id:" + walletId);
        } catch (SQLException e) {
          System.out.println("Error creating wallet:" + e.getMessage());
      }
    }

    private NotificationService NotificationService() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
}
