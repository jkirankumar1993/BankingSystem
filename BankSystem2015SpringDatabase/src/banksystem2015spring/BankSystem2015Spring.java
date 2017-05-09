/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package banksystem2015spring;

import java.util.ArrayList;
import java.util.Scanner;

/**
 *
 * @author LinJian
 */
public class BankSystem2015Spring {

    /**
     * @param args the command line arguments
     */
    
    public static ArrayList<BankAccount> allBankAccounts;
    public static ArrayList<OnlineAccount> allOnlineAccounts;
    
    public static void main(String[] args) {
        // TODO code application logic here
        
        //create the objects of the arrayList
        allBankAccounts = new ArrayList<BankAccount>();
        allOnlineAccounts = new ArrayList<OnlineAccount>();
        
        //display the main menu and get the input
        Scanner input = new Scanner(System.in);
        String selection = "";
        
        //while loop to display menu
        while(!selection.equals("x"))
        {
            //show the menu
            System.out.println();
            System.out.println("Please make your selection");
            System.out.println("1: Open a new bank account");
            System.out.println("2: Go to online account system");
            System.out.println("x: Finish the simulation");
            
            //get the input
            selection = input.nextLine();
            System.out.println();
            
            if(selection.equals("1"))
            {
                //open a new bank account
                AccountCreator.createNewBankAccount();
            }
            else if(selection.equals("2"))
            {
                //go to online system
                new OnlineSystem().showMainPage();
                
                //OnlineSystem newOne = new OnlineSystem();
                //newOne.showMainPage();
                
            }
            else if(selection.equals("x"))
            {
                //go out
                ;
            }
        }
        
    }
    
}
