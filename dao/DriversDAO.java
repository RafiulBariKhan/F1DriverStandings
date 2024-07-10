/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package wdc24.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import wdc.pojo.DriversPojo;
import wdc24.dbutil.DBConnection;

/**
 *
 * @author Rafiul Bari Khan
 */
public class DriversDAO {
    public static boolean addNewDriver(DriversPojo driver) throws SQLException{
        Connection conn = DBConnection.getConnection();
        PreparedStatement ps = conn.prepareStatement("insert into f12024 values(?,?,?,?)");
        ps.setInt(1,driver.getRank());
        ps.setString(2, driver.getName());
        ps.setInt(3, driver.getWins());
        ps.setInt(4, driver.getPoints());
        int ans = ps.executeUpdate();
        return ans == 1;
    }
    
    public static boolean updatePoints(String[] pointsScorer, int[] pointsAvailable) throws SQLException{
        Connection conn = DBConnection.getConnection();
        PreparedStatement ps; 
        int ans = 0;
        for(int i = 0; i < 10; i++){
            ps = conn.prepareStatement("update f12024 set points = points + ? where name = ?");
            ps.setInt(1, pointsAvailable[i]);
            ps.setString(2, pointsScorer[i]);
            ans += ps.executeUpdate();
        }
        return ans == 10;
    }
    
    public static boolean fastestLapScorer(String name) throws SQLException{
        Connection conn = DBConnection.getConnection();
        PreparedStatement ps = conn.prepareStatement("update f12024 set points = points + 1 where name = ?");
        ps.setString(1,name);
        int ans = ps.executeUpdate();
        return ans == 1;
    }
    
    public static List<DriversPojo> getAllDrivers() throws SQLException{
        Connection conn = DBConnection.getConnection();
        Statement st = conn.createStatement();
        ResultSet rs = st.executeQuery("select * from f12024 order by points desc");
        List<DriversPojo> driversList = new ArrayList<>();
        while(rs.next()){
            DriversPojo dp = new DriversPojo();
            dp.setRank(rs.getInt(1));
            dp.setName(rs.getString(2));
            dp.setWins(rs.getInt(3));
            dp.setPoints(rs.getInt(4));
            driversList.add(dp);
        }
        return driversList;
    }
    
    public static boolean updateDrivers(DriversPojo dp) throws SQLException{
        Connection conn = DBConnection.getConnection();
        PreparedStatement ps = conn.prepareStatement("update f12024 set rank = ?,  wins = ?, points = ? where name = ?");
        ps.setInt(1,dp.getRank());
        ps.setInt(2, dp.getWins());
        ps.setInt(3, dp.getPoints());
        ps.setString(4, dp.getName());
        return true;
    }
}
