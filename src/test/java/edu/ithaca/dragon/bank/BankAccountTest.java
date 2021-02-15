package edu.ithaca.dragon.bank;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BankAccountTest {

    @Test
    void getBalanceTest() {
        BankAccount bankAccount = new BankAccount("a@b.com", 200);

        assertEquals(200, bankAccount.getBalance());
    }

    @Test
    void withdrawTest() throws InsufficientFundsException{
        BankAccount bankAccount = new BankAccount("a@b.com", 200);
        bankAccount.withdraw(100);

        assertEquals(100, bankAccount.getBalance());
        assertThrows(InsufficientFundsException.class, () -> bankAccount.withdraw(300));
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

    void transferTest() {
        BankAccount bankAccount = new BankAccount("a@b.com", 200); 
        //base case
        bankAccount.transfer(100);
        assertEquals(100, bankAccount.getBalance());
        assertEquals(100, bankAccount.getBalance2());
        //transfer with decimal places
        bankAccount.transfer(1.99);
        assertEquals(98.01, bankAccount.getBalance());
        assertEquals(101.99, bankAccount.getBalance2());
        //transfer with zero value
        bankAccount.transfer(0);
        assertEquals(98.01, bankAccount.getBalance());
        assertEquals(101.99, bankAccount.getBalance2());
        //transfer so balance goes to 0 and balance 2 is starting value
        bankAccount.transfer(98.01);
        assertEquals(0, bankAccount.getBalance());
        assertEquals(200, bankAccount.getBalance2());
        //transfer without sufficient amount in balance
        assertThrows(InsufficientFundsException.class, () -> bankAccount.transfer(100));
        //transfer with negatuve value
        assertThrows(InsufficientFundsException.class, () -> bankAccount.transfer(-100));
        //transfer with more than two decimal places
        assertThrows(InsufficientFundsException.class, () -> bankAccount.transfer(100.001));
        //transfer with negative and more than two decimal places
        assertThrows(InsufficientFundsException.class, () -> bankAccount.transfer(-100.001));
    }

}