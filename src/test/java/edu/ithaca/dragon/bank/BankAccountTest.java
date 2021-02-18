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
    void depositTest() {
        BankAccount bankAccount = new BankAccount("a@b.com", 100);

        bankAccount.deposit(100);

        // Normal use test
        assertEquals(200, bankAccount.getBalance());

        // Exception test
        assertThrows(IllegalArgumentException.class, () -> bankAccount.deposit(-100));

        // The rest of the tests will be redundant if there is a working isAmountValid method.

    }

    @Test
    void withdrawTest() throws InsufficientFundsException{

        // Normal use, returns, balance is now 100
        BankAccount bankAccount = new BankAccount("a@b.com", 200);
        bankAccount.withdraw(100);

        assertEquals(100, bankAccount.getBalance());

        // Testing negative amount, shouldn't do anything
        bankAccount.withdraw(-100);
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
        assertThrows(InsufficientFundsException.class, () ->  bankAccount.withdraw(100));


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
/** 
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
*/
}