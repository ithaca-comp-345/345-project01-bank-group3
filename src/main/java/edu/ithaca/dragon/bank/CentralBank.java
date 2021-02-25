package edu.ithaca.dragon.bank;

import java.util.ArrayList;

public class CentralBank {
    
    ArrayList<BankAccount> bankAccounts;

    public CentralBank(){
        bankAccounts = new ArrayList<BankAccount>();
    }

    public ArrayList<BankAccount> getAccounts(){
        return bankAccounts;
    }

    public ArrayList<String> getAllTransactionHistory(){
        ArrayList<String> allTransactionHistory = new ArrayList<String>();

        for(BankAccount account : bankAccounts){
            for(String transaction : account.getTransactionHistory()){
                allTransactionHistory.add(transaction);
            }
        }

        return allTransactionHistory;
    }

    public double getTotalBalance(){
        double totalBalance = 0;

        for(BankAccount account : bankAccounts){
            totalBalance += account.getBalance();
        }

        return totalBalance;
    }


}
