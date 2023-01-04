 
package Classes;

import Frames.NeedsFrame;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author baransonmez
 */
public class Expenses implements IExpenses{
    
     Statement statement ;
    PreparedStatement state ;
    ResultSet resultSet;
    Connection connection =null;
    dbHelper dbHelper1 = new dbHelper();
  
         
    @Override
    public void calculateExpense(int amount, double price) {
        
    }

    public void addExpenseToDB(String userName, String type,String expenseName, int amount, String date, int price) {
        String sql = "INSERT INTO expenses(User,ExpenseType,ExpenseName,Amount,Date,Price) VALUES(?,?,?,?,?,?)";
        try {
           
            connection = dbHelper1.getConnection();
            state = connection.prepareStatement(sql);
            state.setString(1, userName);
            state.setString(2, type);
             state.setString(3, expenseName);
            state.setInt(4, amount);
            state.setString(5, date);
            state.setInt(6, price);
            
            
            
            
        } catch (SQLException ex) {
            System.out.println(ex);
            Logger.getLogger(NeedsFrame.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                state.executeUpdate();
                state.close();
                connection.close();
            } catch (SQLException ex) {
                Logger.getLogger(NeedsFrame.class.getName()).log(Level.SEVERE, null, ex);
            }
       
        }
    }

    @Override
    public void tableUpdate(DefaultTableModel model) {
        
        
       
        try {
          connection = dbHelper1.getConnection();
            statement = connection.createStatement();
            System.out.println("tableupdate");
            resultSet = statement.executeQuery("select * from expenses");
            
            while(resultSet.next()){
                String UserName = resultSet.getString("User");
                String type = resultSet.getString("ExpenseType");
                int amount =resultSet.getInt("Amount"); 
                String date =resultSet.getString("Date"); 
                int price = resultSet.getInt("Price");;
                model.addRow(new Object[]{UserName,type,amount,date,price});
                
            }
        } catch (Exception e) {
            System.out.println(e);
        } finally {
            
        }
    }

    
    
    
   
}
