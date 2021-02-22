package edu.ithaca.dragon.bank;
import java.util.*;

public class BankTeller extends ATM_Software(){
    public BankTeller(){
        super()
    }

    public void createBankAccount(){
        newacct = CentralBank.newAccount(this.activeEmail, 0);
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