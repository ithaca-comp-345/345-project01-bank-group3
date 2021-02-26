package edu.ithaca.dragon.bank;
import java.util.*;

public class User(){

    protected String email;
    protected String password;
    protected ArrayList<Integer> accountIds;
    public boolean adminAccess;

    public User(String email, String password){
        this.email = email;
        this.password = password;
        this.accountIds = null;
        this.adminAccess = false;
    }

    //is returns boolean
    public boolean isPassword(String pwd){
        return(pwd.equals(this.password));
    }
    public boolean isAdmin(){
        return(this.adminAccess);
    }

    //getters
    public String getEmail(){
        return(this.email);
    }
    public Integer[] getAccountIds(){
        return(this.accountIds);
    }

    //setters
    public void addAccountId(int id){
        this.accountIds.add(id);
    }
    public void flipAdminAccess(){
        this.adminAccess = !this.adminAccess;
    }
    
    
}