package edu.ithaca.dragon.bank;

public abstract class BankAccount {

    private String id;
    private double balance;
    boolean isFrozen;
    

    /**
     * @throws IllegalArgumentException if email is invalid
     */
    public BankAccount(String email, double startingBalance, String id){
        
        if (isEmailValid(email)){
            this.email = email;
            this.balance = startingBalance;
            this.id = id;
            this.isFrozen = false;
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

    /**
     * @post reduces the balance by amount if amount is non-negative and smaller than balance
     */
    public void withdraw (double amount) throws InsufficientFundsException{
        if (amount <= balance){
            balance -= amount;
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
}
