 
package Classes;

import java.sql.*;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author baransonmez
 */
public interface IExpenses {
    
    void calculateExpense(int amount, double price); 
    void addExpenseToDB (String userName, String type,String ExpenseName ,int amount, String date, int price );
    void tableUpdate (DefaultTableModel model);
    
}
