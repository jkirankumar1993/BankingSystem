/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package banksystem2015spring;
import java.sql.*;
import java.text.DecimalFormat;
import java.util.Scanner;

/**
 *
 * @author LinJian
 */
public class AccountCreator {
    
    //static method to create new bank account
    //it is static because it is not related to any object attribute 
    // and it is a common task
    public static void createNewBankAccount()
    {
        //declare the local varaibles
        String ssn = "";
        String accountNum = "";
        double balance = 0.0;
        Scanner input = new Scanner(System.in);
        
        //prompt and input
        System.out.println("Please enter the SSN:");
        ssn = input.next();
        
        System.out.println("Please enter the initial balance:");
        balance = input.nextDouble();
        
        //database operation
        //define database URL
        final String DB_URL = "jdbc:mysql://mis-sql.uhcl.edu/DrLin";
        Connection conn = null;
        Statement stat = null;
        ResultSet rs = null;
        
        try
        {
            //connect to db
            conn = DriverManager.getConnection(DB_URL, "DrLin", "UHCL2014");
            stat = conn.createStatement();
            
            //create bank statement String
            DecimalFormat df = new DecimalFormat("##.00");
            String s = DateAndTime.DateTime() + ": Account opened with an inintial balance $" +df.format(balance) + "\n";
            
            //get the account number
            rs = stat.executeQuery("select * from nextAccountNumber");
            int nextNum = 0;
            if(rs.next()){
               accountNum = "" + rs.getInt(1);
               nextNum = rs.getInt(1)+1;
            }
            
           //update the table with the new next account number
           int t = stat.executeUpdate("update nextAccountNumber set nextID = '"+nextNum+"'");
           
           //insert a record
           
          int r = stat.executeUpdate("insert into BankAccount values('"+accountNum+"','"+ssn+"','"+balance+"','"+s+"')");
        System.out.println("The new bank account is created!");
        System.out.println("The account number is " + accountNum + "!");
        System.out.printf("The initial balance is $%.2f\n", balance);
        }
        catch(SQLException e)
        {
            System.out.println("Account Creation failed");
            e.printStackTrace();
        }
        finally
        {
            try{
                conn.close();
                stat.close();
                rs.close();
            }
            catch(Exception e)
            {
            e.printStackTrace();
            }
            
        }
        
        
        
    }
    
}
