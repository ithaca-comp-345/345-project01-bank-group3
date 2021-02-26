package edu.ithaca.dragon.bank;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SavingsAccountTest {


    @Test
    void resetWithdrawLimitTest(){
        SavingsAccount savingsAccount = new SavingsAccount("a@b.com", 2000.0);
        try{        savingsAccount.withdraw(1000); }
        catch(Exception e){}
        assertEquals(1000.00, savingsAccount.getBalance());
        assertThrows(InsufficientFundsException.class, () -> savingsAccount.withdraw(10.00));
        assertEquals(1000.00, savingsAccount.getBalance());
        savingsAccount.resetWithdrawLimit();
        try{        savingsAccount.withdraw(1000); }
        catch(Exception e){}
        assertEquals(0.00, savingsAccount.getBalance());

    }

    @Test
    void constructorTest() {
        SavingsAccount savingsAccount = new SavingsAccount("a@b.com", 200.0);

        assertEquals("a@b.com", savingsAccount.getEmail());
        assertEquals(200, savingsAccount.getBalance());
        //check for exception thrown correctly
        assertThrows(IllegalArgumentException.class, ()-> new BankAccount("", 100));
    }

}