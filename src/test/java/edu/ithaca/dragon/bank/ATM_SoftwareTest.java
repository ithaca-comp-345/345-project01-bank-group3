import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ATM_SoftwareTest {

    @Test
    void getBalanceTest() {
        ATM_Software atm = new ATM_Software();
        BankAccount account = new BankAccount("a@b.com", 295.95);
        BankAccount account2 = new BankAccount("b@a.com", 99295.95);
        CentralBank.add(account);
        CentralBank.add(account2);
        assertEquals(295.95, atm.getBalance(account.getId()));
        assertEquals(99295.95, atm.getBalance(account2.getId()));
        
        //invalid ID
        assertFalse(atm.getBalance(666));

    
    }

    @Test
    void withdrawTest() {
        ATM_Software atm = new ATM_Software();
        BankAccount account = new BankAccount("a@b.com", 295.95);
        BankAccount account2 = new SavingsAccount("b@a.com", 199295.95);
        CentralBank.add(account);
        CentralBank.add(account2);
        atm.withdraw(account.getId(), 200.05);
        assertEquals(95.9, account.getBalance());

        //Makes sure ATM software handles accounts properly as to not change balance if withdraw limit is maxed out
        atm.withdraw(account2.getId(), 100000);
        assertFalse(99295.95, account2.getBalance());

        //invalid ID
        assertFalse(atm.withdraw(666, 100));
    
    }

    @Test 
    void depositTest() {
        ATM_Software atm = new ATM_Software();
        BankAccount account = new BankAccount("a@b.com", 459.99);
        CentralBank.add(account);
        atm.deposit(account.getId(), 100.02);
        assertEquals(560.01, account.getBalance());

        //invalid ID
        assertFalse(atm.deposit(666, 100));

    }

    @Test
    void transferTest() {
        ATM_Software atm = new ATM_Software();
        BankAccount account = new BankAccount("a@b.com", 295.95);
        BankAccount account2 = new BankAccount("b@a.com", 99295.95);
        CentralBank.add(account);
        CentralBank.add(account2);

        //valid transfer to & from
        atm.transfer("a@b.com","b@a.com", 200);
        assertEquals(atm.getBalance(account.getId(), 95.95));
        assertEquals(atm.getBalance(account2.getId(), 99495.95); 

        atm.transfer("b@a.com","a@b.com", 200);
        assertEquals(atm.getBalance(account.getId(), 295.95));
        assertEquals(atm.getBalance(account2.getId(), 99295.95);

        //invalid ID for either
        assertFalse(atm.transfer("a@b.com","none.com", 200));
        assertEquals(atm.getBalance(account.getId(), 295.95));

        //withdraw more than balance; shouldnt change balances
        atm.transfer("a@b.com","b@a.com", 300);
        assertEquals(atm.getBalance(account.getId(), 295.95));
        assertEquals(atm.getBalance(account2.getId(), 99295.95);

        
    }

    @Test
    void verifyCredentialsTest() {
        ATM_Software atm = new ATM_Software();
        atm.emails.add("a@b.com");
        atm.emailPasswords.put("a@b.com", "z1");

    }

    @Test 
    void verifyIDInBankTest() {
        ATM_Software atm = new ATM_Software();
        BankAccount account = new BankAccount(295.95);
        CentralBank.add(account);
        int x = atm.verifyIDInBank(account.getId());
        assertTrue(x > -1);

    }

    @Test
    void freezeAccountTest() {
        ATM_Software atm = new ATM_Software();
        BankAccount account = new BankAccount(0);
        CentralBank.add(account);
        atm.freezeAccount(account.getId());
        assertEquals(true, account.isFrozen());
    }

    void unfreezeAccountTest() {
        ATM_Software atm = new ATM_Software();
        BankAccount account = new BankAccount(0);
        CentralBank.add(account);
        atm.freezeAccount(account.getId());
        atm.unfreezeAccount(account.getId());
        assertEquals(false, account.isFrozen());
    }


}
