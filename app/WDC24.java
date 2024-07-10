package wdc24.app;

import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;
import wdc.pojo.DriversPojo;
import wdc24.dao.DriversDAO;
import wdc24.dbutil.DBConnection;

public class WDC24 {
    static Scanner kb;
    public static void main(String[] args) {
        kb=new Scanner(System.in);
        int choice;
        do{
            System.out.println("Select an operation:");
            System.out.println("1.Add new driver\n2.Update points\n3.Show standings\n4.Update drivers\n5.Quit");
            choice=kb.nextInt();
            switch(choice){
                case 1:
                        addNewDriver();
                        break;
                case 2:
                        updatePoints();
                        break;
                case 3:
                        showAllDrivers();
                        break;
                case 4:
                        updateDrivers();
                        break;
                case 5:
                       System.out.println("Thank you for using the app");
                       DBConnection.closeConnection();
                       break;
                default:
                        System.out.println("Wrong choice! Try again");
            }
        }while(choice!=5);
    }
    public static void addNewDriver(){
        
        try{
            System.out.println("Enter rank:");
            int rank=kb.nextInt();
            
            System.out.println("Enter driver name");
            kb.nextLine();
            String name=kb.nextLine();
            
            System.out.println("Enter wins:");
            int wins=kb.nextInt();
            
            System.out.println("Enter points:");
            int points=kb.nextInt();
            
            DriversPojo driver=new DriversPojo(rank,name,wins,points);
            boolean result=DriversDAO.addNewDriver(driver);
            System.out.println(result==true?"Record inserted":"Record not inserted");
                      
        }catch(SQLException ex){
            System.out.println("Exception:Cannot add the rec:"+ex.getMessage());
           
        }
    }

    private static void updatePoints() {
        int[] pointsAvailable = new int[]{25,18,15,12,10,8,6,4,2,1};
        System.out.println("Enter race result (points scored by drivers):");
        Scanner kb = new Scanner(System.in);
        try{         
            String[] pointsScorer = new String[10];
            for(int i = 0; i < 10; i++){
                System.out.println("Position " + (i+1) +": ");
                pointsScorer[i] = kb.nextLine();
            }
            System.out.println("Who scored fastest lap? ");
            String fastestLapScorer = kb.nextLine();
            DriversDAO.updatePoints(pointsScorer, pointsAvailable);
            DriversDAO.fastestLapScorer(fastestLapScorer);
    }catch(SQLException ex){
            System.out.println("Exception:Cannot search the rec:"+ex.getMessage());
           
        }
    
  }

    private static void showAllDrivers() {
        try{
            List<DriversPojo>driverList=DriversDAO.getAllDrivers();
            if(driverList.size()==0){
                System.out.println("Sorry! No rec in the DB");
            }
            else{
                int rankVar = 1;
                for(DriversPojo driver:driverList){
                    System.out.print("Rank:"+rankVar+" ");
                    System.out.print("Name:"+driver.getName()+" ");
                    System.out.print("Wins:"+driver.getWins()+" ");
                    System.out.println("Points:"+driver.getPoints()+" ");
                    rankVar++;
                    System.out.println("===============================================");
                }
            }
        }catch(SQLException ex){
            System.out.println("Exception:Cannot search the rec:"+ex.getMessage());
           
        }
    }

    private static void updateDrivers() {
        try{
            
            System.out.println("Enter name of driver whose rec is to be updated:");
            kb.nextLine();
            String name=kb.nextLine();
            
            System.out.println("Enter new driver rank: ");
            int rank=kb.nextInt();
            
            System.out.println("Enter no. of wins: ");
            int wins=kb.nextInt();
            
            System.out.println("Enter no. of points: ");
            int points=kb.nextInt();
             DriversPojo dp=new DriversPojo(rank,name,wins,points);
             boolean result=DriversDAO.updateDrivers(dp);
            System.out.println(result==true?"Record updated":"Record not found");
    }catch(SQLException ex){
            System.out.println("Exception:Cannot update the rec:"+ex.getMessage());
           
        }
    }    
}
