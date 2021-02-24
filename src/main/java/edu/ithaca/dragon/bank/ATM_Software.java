/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.ithaca.dragon.bank;
import java.util.*;

public class ATM_Software {
   
    public User activeUser;

    public ATM_Software(){
        this.activeUser = null;
    }
    
    public double getBalance(int id){
        idx = verifyIDInBank(id);
        if(idx >= 0){
            return(CentralBank.bankAccounts[idx].getBalance);
        }
         //if id is invalid, return 0
        System.out.println("Invalid ID");
        return(0);
    }
    
    public void withdraw(int id, int amt){
        idx = verifyIDInBank(id);
        if(idx >= 0){
            return(CentralBank.bankAccounts[idx].withdraw(amt));
        }

        System.out.println("Invalid ID");
    }
    
    public void deposit(int id, int amt){
        idx = verifyIDInBank(id);
        if(idx >= 0){
            return(CentralBank.bankAccounts[idx].deposit(amt));
        }

        System.out.println("Invalid ID");
    }
    
    public void transfer(int id, int targetId, int amt){
        int targetIndex = -1;
        
        idx = verifyIDInBank(id);
        tidx = verifyIDInBank(targetId);
        if(idx >= 0 && tidx >= 0){
            CentralBank.bankAccounts[i].withdraw(amt);
            CentralBank.bankAccounts[targetIndex].deposit(amt);
        }

        System.out.println("Invalid ID");
    }

    public void freezeAccount(Integer id){
        idx = verifyIDInBank(id);
        if(idx >= 0){
            Administrator.freeze(CentralBank.bankAccounts[idx]);
            return();
        }
               
        System.out.println("Invalid ID");
    }
    public void unfreezeAccount(Integer id){
        idx = verifyIDInBank(id);
        if(idx >= 0){
            Administrator.unfreeze(CentralBank.bankAccounts[idx]);
            return();
        }
               
        System.out.println("Invalid ID");
    }

    public Integer verifyIDInBank(Integer id){
        for(i = 0; i < CentralBank.bankAccounts.length; i++){
            if(Centralbank.bankAccounts[i].ID == id){
                return(i);
            }
        return(-1);
    }
    
    public void verifyCredentials(){
        Scanner s = new Scanner(System.in)

        System.out.println("Input Username");
        uname = String.nextLine();
        int unameIndex = -1;
        User[] users = CentralBank.users;
        //Go through CentralBank.users and see if email belongs to a user
        for(i=0; i<users.length; i++){
            if(users[i].getEmail.equals(uname)){
                //if the email exists, set unameIndex to its users index
                unameIndex = i;
            }
        }
        //if username exists
        if(unameIndex >= 0){
            System.out.println("Input Password");
            pwd = String.nextLine();
            //if password is valid for user with established email
            if(users[i].isPassword(pwd)){
                this.activeEmail = uname;
                return(true);
            } //else goes to return false below
        } //skips here if username doesnt exist
        return(false);        
    }

    public getActiveUser(){
        return(this.activeUser);
    }

}
