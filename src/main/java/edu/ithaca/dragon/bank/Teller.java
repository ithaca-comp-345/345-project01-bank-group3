/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.ithaca.dragon.bank;
import java.util.*;
/**
 *
 * @author thoth
 */
public class Teller {
    //Stores ID as Key with all bankaccounts
    //public Map<Integer, ArrayList<BankAccount>> IdConnect = new HashMap<>();
    
    
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
    
}
