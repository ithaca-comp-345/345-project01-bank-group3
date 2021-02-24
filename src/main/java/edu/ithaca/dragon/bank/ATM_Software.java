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
        for(i=0; i<CentralBank.bankAccounts.length-1; i++){
            if(Centralbank.bankAccounts[i].ID == id){
                return(CentralBank.bankAccounts[i].getBalance());
            }
        }
        System.out.println("Invalid ID");
    }
    
    public void withdraw(int id, int amt){
         for(i = 0; i < CentralBank.bankAccounts.length; i++){
            if(Centralbank.bankAccounts[i].ID == id){
                return(CentralBank.bankAccounts[i].withdraw(amt));
            }
        }
        System.out.println("Invalid ID");
    }
    
    public void deposit(int id, int amt){
         for(i = 0; i < CentralBank.bankAccounts.length; i++){
            if(Centralbank.bankAccounts[i].ID == id){
                return(CentralBank.bankAccounts[i].deposit(amt));
            }
        }
        System.out.println("Invalid ID");
    }
    
    public void transfer(int id, int targetId, int amt){
        int targetIndex = -1;
        
        //Make sure Target Id is Valid
         for(int i = 0; i < CentralBank.bankAccounts.length; i++){
            for(int j = 0; j < CentralBank.bankAccounts.length; j++){
                if(Centralbank.bankAccounts[j].ID == targetId){
                    targetIndex = j;
                }
            }
            //if both accounts are valid
            if(Centralbank.bankAccounts[i].ID == id && targetIndex != -1){
                CentralBank.bankAccounts[i].withdraw(amt);
                CentralBank.bankAccounts[targetIndex].deposit(amt);
                return;
                
            }
        }
        System.out.println("Invalid ID");
    }

    public void freezeAccount(String id){
        for(i = 0; i < CentralBank.bankAccounts.length; i++){
            if(Centralbank.bankAccounts[i].ID == id){
                Administrator.freeze(CentralBank.bankAccounts[i]);
                return();
            }
        }
        System.out.println("Invalid ID");
    }
    public void unfreezeAccount(String id){
        for(i = 0; i < CentralBank.bankAccounts.length; i++){
            if(Centralbank.bankAccounts[i].ID == id){
                Administrator.unfreeze(CentralBank.bankAccounts[i]);
                return();
            }
        }
        System.out.println("Invalid ID");
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
