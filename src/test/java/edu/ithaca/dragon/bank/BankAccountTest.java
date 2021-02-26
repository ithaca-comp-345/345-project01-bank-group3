package edu.ithaca.dragon.bank;

import org.junit.jupiter.api.Test;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class BankAccountTest {

    @Test
    void getBalanceTest() {
        BankAccount bankAccount = new BankAccount("a@b.com", 200);

        assertEquals(200, bankAccount.getBalance());
    }

    @Test
    void withdrawTest() throws InsufficientFundsException{
        // Normal use, returns, balance is now 100
        BankAccount bankAccount = new BankAccount("a@b.com", 200);
        bankAccount.withdraw(100);
        assertEquals(100, bankAccount.getBalance());

        // Testing exception
        assertThrows(InsufficientFundsException.class, () -> bankAccount.withdraw(300));

        // Testing invalid amount exception
        assertThrows(IllegalArgumentException.class, ()-> bankAccount.withdraw(-100));
        assertThrows(IllegalArgumentException.class, ()-> bankAccount.withdraw(1.023));

        // Withdrawing balance down to 0, after throwing exception
        bankAccount.withdraw(100);
        assertEquals(0, bankAccount.getBalance());

        // Amount > balance, should throw exception.
        assertThrows(InsufficientFundsException.class, () -> bankAccount.withdraw(100));
    }

    @Test
    void isAmountValidTest() {

        // Valid amount with no decimals
        assertTrue(BankAccount.isAmountValid(100));

        // Valid amount with 1 decimal
        assertTrue(BankAccount.isAmountValid(100.1));

        // Valid amount with 2 decimals
        assertTrue(BankAccount.isAmountValid(100.01));

        // Invalid amount with 3 decimals
        assertFalse(BankAccount.isAmountValid(100.001));

        // Invalid negative amount
        assertFalse(BankAccount.isAmountValid(-100));

        // Invalid 0 amount
        assertFalse(BankAccount.isAmountValid(0));

        // Valid decimal amount
        assertTrue(BankAccount.isAmountValid(0.01));

        // Additional middle tests
        assertTrue(BankAccount.isAmountValid(1.1));
        assertTrue(BankAccount.isAmountValid(2.03));
        assertTrue(BankAccount.isAmountValid(4000.25));
    }

    @Test
    void isEmailValidTest(){
        assertTrue(BankAccount.isEmailValid( "a@b.com"));
        assertFalse( BankAccount.isEmailValid(""));
    }

    @Test
    void constructorTest() {
        BankAccount bankAccount = new BankAccount("a@b.com", 200);

        assertEquals("a@b.com", bankAccount.getEmail());
        assertEquals(200, bankAccount.getBalance());
        //check for exception thrown correctly
        assertThrows(IllegalArgumentException.class, ()-> new BankAccount("", 100));
    }

    @Test
    void transferTest() throws InsufficientFundsException, IllegalArgumentException{
        BankAccount bankAccount1 = new BankAccount("a@b.com", 200); 
        BankAccount bankAccount2 = new BankAccount("a@c.com", 200);
        //base case
        bankAccount1.transfer(100, bankAccount2);
        assertEquals(100, bankAccount1.getBalance());
        assertEquals(300, bankAccount2.getBalance());
        //transfer with decimal places
        bankAccount1.transfer(1.99, bankAccount2);
        assertEquals(98.01, bankAccount1.getBalance());
        assertEquals(301.99, bankAccount2.getBalance());
        //transfer so balance goes to 0 and balance 2 is starting value
        bankAccount1.transfer(98.01, bankAccount2);
        assertEquals(0, bankAccount1.getBalance());
        assertEquals(400, bankAccount2.getBalance());
        //transfer with zero value
        assertThrows(IllegalArgumentException.class, () ->bankAccount1.transfer(0, bankAccount2));
        //transfer without sufficient amount in balance
        assertThrows(InsufficientFundsException.class, () -> bankAccount1.transfer(100, bankAccount2));
        //transfer with negatuve value
        assertThrows(IllegalArgumentException.class, () -> bankAccount1.transfer(-100, bankAccount2));
        //transfer with more than two decimal places
        assertThrows(IllegalArgumentException.class, () -> bankAccount1.transfer(100.001, bankAccount2));
        //transfer with negative and more than two decimal places
        assertThrows(IllegalArgumentException.class, () -> bankAccount1.transfer(-100.001, bankAccount2));
    }

    @Test
    void depositTest() {
        BankAccount bankAccount = new BankAccount("a@b.com", 200);
        //base case
        bankAccount.deposit(100);
        assertEquals(300, bankAccount.getBalance());
        //deposit with deciaml places
        bankAccount.deposit(1.99);
        assertEquals(301.99, bankAccount.getBalance());
        //deposit with zero value
        assertThrows(IllegalArgumentException.class, ()-> bankAccount.deposit(0));
        //negative value deposit
        assertThrows(IllegalArgumentException.class, ()-> bankAccount.deposit(-100));
        //more than two decimal places deposit
        assertThrows(IllegalArgumentException.class, ()-> bankAccount.deposit(100.001));
        //negative and more than two decimal place deposit
        assertThrows(IllegalArgumentException.class, ()-> bankAccount.deposit(-100.001));
    }

    @Test
    void getTransactionHistoryTest(){
        BankAccount bankAccount = new BankAccount("a@b.com", 1);

        bankAccount.deposit(100);
        try{        bankAccount.withdraw(50); }
        catch(Exception e){}
        assertThrows(InsufficientFundsException.class, () -> bankAccount.withdraw(52));

        ArrayList<String> transactionHistory = bankAccount.getTransactionHistory();
        assertTrue(transactionHistory.get(0).equals("Deposited 100.0 to " + bankAccount.getId() + "."));
        assertTrue(transactionHistory.get(1).equals("Withdrew 50.0 from " + bankAccount.getId() + "."));
        assertEquals(2, transactionHistory.size());
    }

    @Test
    void freezeTest(){
        BankAccount bankAccount = new BankAccount("a@b.com", 500);
        assertFalse(bankAccount.isFrozen());
        bankAccount.freeze();
        assertTrue(bankAccount.isFrozen());
        bankAccount.freeze();
        assertTrue(bankAccount.isFrozen());
        bankAccount.unfreeze();
        assertFalse(bankAccount.isFrozen());
    }

    @Test
    void idTest(){
        BankAccount bankAccount1 = new BankAccount("a@b.com", 100);
        BankAccount bankAccount2 = new BankAccount("b@a.com", 100);

        assertEquals(1, bankAccount1.getId());
        assertEquals(2, bankAccount2.getId());
    }

}