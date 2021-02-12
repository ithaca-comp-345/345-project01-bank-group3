package src.main.java.edu.ithaca.dragon.bank;

import edu.ithaca.dragon.bank.BankAccount;

public class SavingsAccount extends BankAccount{
    private double withdrawLimit;
    private double remainingWithdrawLimit;
    private double interestRate;

    public SavingsAccount(String email,String startingBalance,String id){
        super(email, startingBalance, id);
        this.withdrawLimit = 1000;
        this.interestRate = .03;
        this.remainingWithdrawLimit = withdrawLimit;
    }
    protected void applyInterest(){
        added = this.balance * this.interestRate;
        //rounds the interest to be added to the hundreths place
        added = roundToCents(added);
        this.balance += added;
    }
}