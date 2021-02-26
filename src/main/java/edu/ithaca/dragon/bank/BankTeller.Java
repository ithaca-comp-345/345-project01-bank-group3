package edu.ithaca.dragon.bank;
import java.util.*;

public class BankTeller extends ATM_Software(){
    public BankTeller(){
        super();
    }

    public void createBankAccount(balance){
        //initialize new account
        BankAccount newacct = new BankAccount(balance);
        //get local ArrayList value for key
        ArrayList<Integer> myIds = this.emailAccountIDs.get(this.activeUser);
        myIds.add(newacct.ID);
        //replace old value with updated ArrayList
        this.emailAccountIDs.put(this.activeUser, myIds);
        //Add newacct to CentralBanks Bank Account ArrayList
        CentralBank.bankAccounts.add(newacct);
    }
    public void createBankAccount(email, balance){
        //initialize new account
        BankAccount newacct = new BankAccount(balance);
        //If account exists
        if(this.emailAccountIDs.containsKey(email)){
            //get local ArrayList value for key
            ArrayList<Integer> myIds = this.emailAccountIDs.get(email);
            myIds.add(newacct.ID);
            //replace old value with updated ArrayList
            this.emailAccountIDs.put(email, myIds);
            //Add newacct to CentralBanks Bank Account ArrayList
            CentralBank.bankAccounts.add(newacct);
        }
        else{
            System.out.println("Email: "+email+" does not belong to any User");
        }
        
    }
    public void deleteBankAccount(String email, int id){
        //Iterates through BankAccount Objects
        for(i = 0; i<CentralBank.bankAccounts.size(); i++){
            //If current BankAccount has matching ID
            if(CentralBank.bankAccounts[i].ID == id){
                CentralBank.bankAccounts.remove(i);
            }
        }
        //Accounts can only belong to one User; if in map, it is value for key(user)
        if(this.emailAccountIDs.containsValue(id)){
            //Copy
            ArrayList<Integer> tmpIds = this.emailAccountIDs.get(email);
            //Edit
            tmpIds.remove(id);
            //Replace
            this.emailAccountIDs.put(email, tmpIds)
        }
    }
}