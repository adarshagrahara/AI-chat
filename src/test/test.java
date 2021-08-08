package test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


public class test {

    

    
    
    
    public static Connection connect() throws SQLException {
        return DriverManager.getConnection("jdbc:postgresql://localhost:1889/postgres", "postgres", "password");
    }

}