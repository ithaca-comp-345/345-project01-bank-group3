package edu.ithaca.dragon.bank;

public class SavingsAccount extends BankAccount{
    private double withdrawLimit;
    private double remainingWithdrawLimit;
    private double interestRate;

    public SavingsAccount(String email, Double startingBalance){
        super(email, startingBalance);
        this.withdrawLimit = 1000;
        this.interestRate = .03;
        this.remainingWithdrawLimit = withdrawLimit;
    }

    protected void applyInterest(){
        double added = balance * this.interestRate;
        //rounds the interest to be added to the hundreths place
        added = Math.round(added * 100.0)/100.0;
        balance += added;
    }

    
}