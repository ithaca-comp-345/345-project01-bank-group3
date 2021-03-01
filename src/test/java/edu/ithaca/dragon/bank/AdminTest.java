package edu.ithaca.dragon.bank;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AdminTest {

    @Test
    void freezeTest(){
        Admin admin = new Admin("password");
        CheckingAccount account1 = new CheckingAccount("a@b.com", 200.00);

        admin.freeze(account1);
        assertTrue(account1.isFrozen);
    }

    @Test
    void unfreezeTest(){
        Admin admin = new Admin("password");
        CheckingAccount account1 = new CheckingAccount("a@b.com", 200.00);

        admin.freeze(account1);
        assertTrue(account1.isFrozen);
        admin.unfreeze(account1);
        assertFalse(account1.isFrozen);
    }
    
}
