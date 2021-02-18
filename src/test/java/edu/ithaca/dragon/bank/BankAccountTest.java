package edu.ithaca.dragon.bank;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BankAccountTest {

    @Test
    void getBalanceTest() {
        BankAccount bankAccount = new BankAccount("a@b.com", 200);

        assertEquals(200, bankAccount.getBalanceC());
    }

    @Test
    void withdrawTest() throws InsufficientFundsException{

        // Normal use, returns, balance is now 100
        BankAccount bankAccount = new BankAccount("a@b.com", 200);
        bankAccount.withdraw(100);

        assertEquals(100, bankAccount.getBalanceC());

        // Testing negative amount, shouldn't do anything
        bankAccount.withdraw(-100);
        assertEquals(100, bankAccount.getBalanceC());

        // Testing exception
        assertThrows(InsufficientFundsException.class, () -> bankAccount.withdraw(300));

        // Testing invalid amount exception
        assertThrows(IllegalArgumentException.class, ()-> bankAccount.withdraw(-100));
        assertThrows(IllegalArgumentException.class, ()-> bankAccount.withdraw(1.023));

        // Withdrawing balance down to 0, after throwing exception
        bankAccount.withdraw(100);
        assertEquals(0, bankAccount.getBalanceC());

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
        assertEquals(200, bankAccount.getBalanceC());
        //check for exception thrown correctly
        assertThrows(IllegalArgumentException.class, ()-> new BankAccount("", 100));
    }

    @Test
    void transferTest() throws InsufficientFundsException, IllegalArgumentException{
        BankAccount bankAccount = new BankAccount("a@b.com", 200); 
        //base case
        bankAccount.transfer(100);
        assertEquals(100, bankAccount.getBalanceC());
        assertEquals(100, bankAccount.getBalanceS());
        //transfer with decimal places
        bankAccount.transfer(1.99);
        assertEquals(98.01, bankAccount.getBalanceC());
        assertEquals(101.99, bankAccount.getBalanceS());
        //transfer so balance goes to 0 and balance 2 is starting value
        bankAccount.transfer(98.01);
        assertEquals(0, bankAccount.getBalanceC());
        assertEquals(200, bankAccount.getBalanceS());
        //transfer with zero value
        assertThrows(IllegalArgumentException.class, () ->bankAccount.transfer(0));
        //transfer without sufficient amount in balance
        assertThrows(InsufficientFundsException.class, () -> bankAccount.transfer(100));
        //transfer with negatuve value
        assertThrows(IllegalArgumentException.class, () -> bankAccount.transfer(-100));
        //transfer with more than two decimal places
        assertThrows(IllegalArgumentException.class, () -> bankAccount.transfer(100.001));
        //transfer with negative and more than two decimal places
        assertThrows(IllegalArgumentException.class, () -> bankAccount.transfer(-100.001));
    }

    @Test
    void depositTest() {
        BankAccount bankAccount = new BankAccount("a@b.com", 200);
        //base case
        bankAccount.deposit(100, "Checking");
        assertEquals(300, bankAccount.getBalanceC());
        //deposit with deciaml places
        bankAccount.deposit(1.99, "Checking");
        assertEquals(301.99, bankAccount.getBalanceC());
        //deposit with zero value
        assertThrows(IllegalArgumentException.class, ()-> bankAccount.deposit(0, "Checking"));
        //negative value deposit
        assertThrows(IllegalArgumentException.class, ()-> bankAccount.deposit(-100, "Checking"));
        //more than two decimal places deposit
        assertThrows(IllegalArgumentException.class, ()-> bankAccount.deposit(100.001, "Checking"));
        //negative and more than two decimal place deposit
        assertThrows(IllegalArgumentException.class, ()-> bankAccount.deposit(-100.001, "Checking"));
        //not character 
        assertThrows(IllegalArguementException.class, ()-> bankAccount.deposit("z", "Checking"));
    }

}