/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.ithaca.dragon.bank;
import java.util.*;

/**
 *
 * @author thoth
 */
public class Storage {
    private Map<String, String> emailPasswords;
    private Map<String, ArrayList<BankAccount>> emailAccounts;

    public boolean isUser(String ID){
        return(emailPasswords.containsKey(ID));
    }
    
    protected String getPassword(String ID){
        return(this.emailPasswords.get(ID));
    }
    protected ArrayList<BankAccount> getAccounts(String ID){
        return(this.emailAccounts.get(ID));
    }
}
