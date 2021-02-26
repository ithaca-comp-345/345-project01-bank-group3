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

    @Override
    public void withdraw (double amount) throws InsufficientFundsException, IllegalArgumentException{
        if(isAmountValid(amount) == false){
            throw new IllegalArgumentException("Invalid amount");
        }
        else if (amount <= balance && remainingWithdrawLimit - amount >= 0){
            remainingWithdrawLimit -= amount;
            balance -= amount;
        }
        else {
            throw new InsufficientFundsException("Not enough money");
        }
    }

    protected void resetWithdrawLimit(){
        remainingWithdrawLimit = withdrawLimit;
    }

    
}