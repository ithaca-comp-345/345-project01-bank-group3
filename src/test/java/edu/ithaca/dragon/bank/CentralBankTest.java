package edu.ithaca.dragon.bank;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

class CentralBankTest {

    @Test
    void getTotalBalanceTest(){

        CheckingAccount account1 = new CheckingAccount("a@b.com", 200.00);
        SavingsAccount account2 = new SavingsAccount("b@a.com", 500.00);

        assertEquals(700.0, CentralBank.getTotalBalance());

        account2.deposit(20);
        assertEquals(720.0, CentralBank.getTotalBalance());
    }

    @Test
    void getAllTransactionHistoryTest(){
        CheckingAccount account1 = new CheckingAccount("a@b.com", 200.00);
        SavingsAccount account2 = new SavingsAccount("b@a.com", 500.00);

        account2.deposit(20);
        try{ account1.withdraw(20); }
        catch(Exception e){}
        try{ account2.transfer(50, account1); }
        catch(Exception e){}

        ArrayList<String> allTransactionHistory = CentralBank.getAllTransactionHistory();
        
        assertEquals(4, allTransactionHistory.size());
        assertEquals("Deposited 20.0 to account 2.", allTransactionHistory.get(0));
        assertEquals("Withdrew 20.0 from account 1.", allTransactionHistory.get(1));
        assertEquals("Withdrew 50.0 from account 2.", allTransactionHistory.get(2));
        assertEquals("Deposited 50.0 to account 1.", allTransactionHistory.get(3));
    }

}
