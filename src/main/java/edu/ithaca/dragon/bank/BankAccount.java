package edu.ithaca.dragon.bank;

import java.util.ArrayList;

class BankAccount {
    
    protected String email;
    protected static int globalID = 1;
    protected int id;
    protected double balance;
    protected boolean isFrozen;
    protected ArrayList<String> transactionHistory;
    

    /**
     * @throws IllegalArgumentException if email is invalid
     */
    public BankAccount(String email, double startingBalance){
        if (isEmailValid(email)){
            if(isAmountValid(startingBalance)){
                this.email = email;
                this.balance = startingBalance;
                isFrozen = false;
                id = globalID;
                globalID++;
                transactionHistory = new ArrayList<String>();
                CentralBank.add(this);
            }
            else throw new IllegalArgumentException("Starting balance must be positive and have no more than two decimal places");
        }
        else {
            throw new IllegalArgumentException("Email address: " + email + " is invalid, cannot create account");
        }
    }

    public double getBalance(){
        return balance;
    }


    public String getEmail(){
        return email;
    }

    public int getId(){
        return id;
    }

    public ArrayList<String> getTransactionHistory(){
        return transactionHistory;
    }

    /**
     * @return true if amount is positive (amount > 0) and has two or fewer decimal places, otherwise false
     */
    public static boolean isAmountValid(double amount){
        if(amount <= 0){
            return false;
        }
        
        // Check to see if there are two or less decimal places.
        String num = Double.toString(amount);
        int integers = num.indexOf('.');
        int decimals = num.length() - integers - 1;
        if(decimals > 2){
            return false;
        }

        return true;
    }

    /**
     * @post reduces the balance by amount if amount is non-negative and smaller than balance
     */
    public void withdraw (double amount) throws InsufficientFundsException, IllegalArgumentException{
        if(isAmountValid(amount) == false){
            throw new IllegalArgumentException("Invalid amount");
        }
        else if (amount <= balance){
            balance -= amount;
            transactionHistory.add("Withdrew " + amount + " from account " + id + ".");
            CentralBank.addTransaction("Withdrew " + amount + " from account " + id + ".");
        }
        else {
            throw new InsufficientFundsException("Not enough money");
        }
    }

    public static boolean isEmailValid(String email){
        if (email.indexOf('@') == -1){
            return false;
        }
        else {
            return true;
        }
    }
    
    /**
     * transfers a certain amount from one balance to another
     */
    public void transfer(double amount, BankAccount transferTo) throws InsufficientFundsException, IllegalArgumentException{
        withdraw(amount);
        transferTo.deposit(amount);
    }

    
    /**
     * Deposits certain amount into the givent account balance
     */
    public void deposit(double amount) throws IllegalArgumentException{
        if (isAmountValid(amount)){
            balance += amount;
            transactionHistory.add("Deposited " + amount + " to account " + id + ".");
            CentralBank.addTransaction("Deposited " + amount + " to account " + id + ".");
        }
        else{
            throw new IllegalArgumentException("Amount: " + amount + " is an invalid amount to deposit.");
        }
    }

    public boolean isFrozen(){
        return isFrozen;
    }

    public void freeze(){
        if(!this.isFrozen()){
            this.isFrozen = true;
        }
    }

    public void unfreeze(){
        if(this.isFrozen()){
            this.isFrozen = false;
        }
    }
}
