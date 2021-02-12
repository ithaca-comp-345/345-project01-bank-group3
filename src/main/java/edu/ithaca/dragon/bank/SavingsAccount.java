package src.main.java.edu.ithaca.dragon.bank;


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
        added = this.amount * this.interestRate;
        this.amount += added;
    }
}