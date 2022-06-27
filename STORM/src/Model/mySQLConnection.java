package Model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import javax.swing.JOptionPane;

/**
 *
 * @author sonys
 */
public class mySQLConnection {
    protected Connection connection;
    ResultSet resultSet;
    Statement statement;
    public mySQLConnection() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            connection = (Connection) DriverManager.getConnection("jdbc:mysql://Localhost:3306/db_skripsi", "root", "");
        } catch (ClassNotFoundException | SQLException e) {
            System.out.println(e);
        }
    }
    
}
