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
}