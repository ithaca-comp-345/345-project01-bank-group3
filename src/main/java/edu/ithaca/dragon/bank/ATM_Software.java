/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.ithaca.dragon.bank;
import java.util.*;

public class ATM_Software {
   
    
    protected static Map<String, String> emailPasswords;
    protected static Map<String, ArrayList<Integer>> emailAccountIDs;
    protected static ArrayList<String> emails; //will hold key vals for ^ and ^^
    protected static ArrayList<String> emailsWithAdmin;

    public ATM_Software(){
       
    }
    
    public double getBalance(int id){
        int idx = verifyIDInBank(id);
        if(idx >= 0){
            return(CentralBank.bankAccounts[idx].getBalance());
        }
         //if id is invalid, return 0
        System.out.println("Invalid ID");
        return(0);
    }
    
    public void withdraw(int id, int amt){
        int idx = verifyIDInBank(id);
        if(idx >= 0){
            return(CentralBank.bankAccounts[idx].withdraw(amt));
        }

        System.out.println("Invalid ID");
    }
    
    public void deposit(int id, int amt){
        int idx = verifyIDInBank(id);
        if(idx >= 0){
            return(CentralBank.bankAccounts[idx].deposit(amt));
        }

        System.out.println("Invalid ID");
    }
    
    public void transfer(int id, int targetId, int amt){
        int targetIndex = -1;
        
        int idx = verifyIDInBank(id);
        int tidx = verifyIDInBank(targetId);
        if(idx >= 0 && tidx >= 0){
            CentralBank.bankAccounts[i].withdraw(amt);
            CentralBank.bankAccounts[targetIndex].deposit(amt);
        }

        System.out.println("Invalid ID");
    }

    public void freezeAccount(Integer id){
        int idx = verifyIDInBank(id);
        if(idx >= 0){
            Administrator.freeze(CentralBank.bankAccounts[idx]);
            return();
        }
               
        System.out.println("Invalid ID");
    }
    public void unfreezeAccount(Integer id){
        Integer idx = verifyIDInBank(id);
        if(idx >= 0){
            Administrator.unfreeze(CentralBank.bankAccounts[idx]);
            return();
        }
               
        System.out.println("Invalid ID");
    }

    public Integer verifyIDInBank(Integer id){
        for(i = 0; i < CentralBank.bankAccounts.size(); i++){
            if(Centralbank.bankAccounts[i].ID == id){
                return(i);
            }
        return(-1);
    }
    
    public String verifyCredentials(){
        Scanner s = new Scanner(System.in)

        System.out.println("Input Username");
        String emailIn = s.nextLine();
        int emailIndex = -1;
        //Go through users and see if email belongs to a user
        for(i=0; i<this.emails.size(); i++){
            if(this.emails[i].equals(emailIn)){
                //if the email exists, set emailIndex to its users index
                emailIndex = i;
            }
        }
        //if username exists
        if(emailIndex >= 0){
            System.out.println("Input Password");
            String pwd = s.nextLine();
            //key(emailIn) in emailPasswords has value(pwd)
            if(this.emailPasswords.get(emailIn).equals(pwd)){
                return(emailIn);
            } //else goes to return false below
        } //skips here if username doesnt exist
        return(null);        
    }


}
