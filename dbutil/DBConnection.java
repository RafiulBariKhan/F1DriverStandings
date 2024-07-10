/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package wdc24.dbutil;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {
    
    private static Connection conn;
    static{
        try{
            conn=DriverManager.getConnection("jdbc:oracle:thin:@//DESKTOP-533TJJV:1521/xe", "user2", "abcd");
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
