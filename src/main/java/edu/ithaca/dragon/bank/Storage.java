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
    private HashMap<String, ArrayList<BankAccount>> emailAccounts;
    
    protected String getPassword(String ID){
        return(this.emailPasswords.get(ID));
    }
    protected ArrayList<BankAccount> getAccounts(String ID){
        return(this.emailAccounts.get(ID));
    }
}
