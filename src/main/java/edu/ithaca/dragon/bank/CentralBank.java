package edu.ithaca.dragon.bank;

import java.util.ArrayList;

public class CentralBank {
    
    public static ArrayList<BankAccount> bankAccounts = new ArrayList<BankAccount>();
    public static ArrayList<String> allTransactionHistory = new ArrayList<String>();

    public static void add(BankAccount account){
        bankAccounts.add(account);
    }

    public static void remove(BankAccount account){
        bankAccounts.remove(account);
    }

    public static ArrayList<BankAccount> getAccounts(){
        return bankAccounts;
    }

    public static void addTransaction(String transaction){
        allTransactionHistory.add(transaction);
    }

    public static ArrayList<String> getAllTransactionHistory(){
        return allTransactionHistory;
    }

    public static double getTotalBalance(){
        double totalBalance = 0;

        for(BankAccount account : bankAccounts){
            totalBalance += account.getBalance();
        }

        return totalBalance;
    }


}
