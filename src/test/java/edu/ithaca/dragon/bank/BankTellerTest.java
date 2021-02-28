import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BankTellerTest {

    @Test
    void createBankAccountTest() {
        BankTeller teller = new BankTeller();
        teller.emails.add("a@b.com");
        teller.emailPasswords.put("a@b.com", "z1");
        teller.emailAccountIDs.put("a@b.com", null);

        //test if createBankAccount correctly edits emailAccountIds
        teller.createBankAccount("a@b.com", 0);
        assertEquals(!null, teller.emailAccountIDs.get("a@b.com"));
        //test if it appended id value to emailAccountIds.
        teller.createBankAccount("a@b.com", 0);
        ArrayList<Integer> newIds = teller.emailAccountIDs.get("a@b.com");
        assertEquals(2, newIds.size());

    }

    @Test
    void deleteBankAccounttest() {
        BankTeller teller = new BankTeller();
        teller.emails.add("a@b.com");
        teller.emailPasswords.put("a@b.com", "z1");
        teller.emailAccountIDs.put("a@b.com", null);

        teller.createBankAccount("a@b.com", 0);
        ArrayList<Integer> tmpIds = teller.emailAccountIDs.get("a@b.com");

        teller.deleteBankAccount("a@b.com", tmpIds[0]);
        assertEquals(null, teller.emailAccountIds.get("a@b.com"));
    }

}