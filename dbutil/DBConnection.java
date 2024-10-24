package wdc24.dbutil;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {
    
    private static Connection conn;
    static{
        try{
            conn=DriverManager.getConnection("jdbc:oracle:thin:@//localhost/xe", "user2", "abcd");
            System.out.println("Connected successfully!");
        }catch(SQLException ex){
            System.out.println("Cannot connect to the DB:"+ex.getMessage());
            System.exit(0);
        }
    }
    public static Connection getConnection(){
        return conn;
    }
    public static void closeConnection(){
        try{
            conn.close();
            System.out.println("Disconnected successfully!");
        }catch(SQLException ex){
            System.out.println("Cannot disconnect to the DB:"+ex.getMessage());
           
        }
    }
}
