package edu.ithaca.dragon.bank;
import java.util.*;

public class BankTeller extends ATM_Software(){
    public BankTeller(){
        super();
    }

    public void createBankAccount(balance){
        //initialize new account
        newacct = new BankAccount(balance);
        //get local ArrayList value for key
        myIds = this.emailAccountIDs.get(this.activeUser);
        myIds.add(newacct.ID);
        //replace old value with updated ArrayList
        this.emailAccountIDs.put(this.activeUser, myIds);
        //Add newacct to CentralBanks Bank Account ArrayList
        CentralBank.bankAccounts.add(newacct);
    }
    public void createBankAccount(email, balance){
        newacct = CentralBank.newAccount(email, 0);
        Storage.emailAccounts.get(this.activeEmail).add(newacct);
    }
    public void deleteBankAccount(int id){
        for(i = 0; i<CentralBank.bankAccounts.length; i++){
            if(CentralBank.bankAccounts[i].ID == id){
                CentralBank.bankAccounts.remove(i);
            }
        }
    }
}