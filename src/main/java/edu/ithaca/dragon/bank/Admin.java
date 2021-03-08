package edu.ithaca.dragon.bank;

public class Admin {

    private String password;

    public Admin(String password){
        this.password = password;
    }

    public void freeze(BankAccount account){
        account.freeze();
    }

    public void unfreeze(BankAccount account){
        account.unfreeze();
    }
    
}
