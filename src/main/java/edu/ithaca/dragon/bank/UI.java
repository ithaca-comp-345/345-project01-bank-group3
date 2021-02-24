
package edu.ithaca.dragon.bank;
import java.util.*;

public class UI(){
    protected ATM_Software teller;

    public UI(){
        this.teller = new ATM_Software();
    }

    public void logIn(){
        Scanner reader = new Scanner(System.in);
        while(this.teller.getActiveUser == null){
            this.teller.verifyCredentials();
        }
        boolean run = true;
        while(run){
            //If NOT admin
            if(!teller.activeUser.adminAccess){
                Integer[] ids = teller.activeUser.getAccountIds();
                //if 1 or more accounts(ids)
                if(ids != null){
                    //if 1 account, 1 option
                    if(ids.length == 1){
                        manageAcct(ids[0]);
                    }
                    //multiple accounts
                    else(){
                        System.out.println("Input Account ID to Select");
                        int IdSel = reader.nextInt();
                        //check if valid
                        boolean validID = false;
                        for(int i = 0; i<ids.length ; i++){
                            if(idSel == ids[i]){
                                manageAcct(idSel);
                            }
                        }
                    }
                }
                
            }
            System.out.println("Input 0 to close, or any other integer to go back to Account Page");
            int closePrompt = reader.nextInt();
            if(closePrompt == 0){
                run = true;
            }
        }
    }
    public void manageAcct(Integer id){
        System.out.println("Input Menu Option");
        System.out.println("0). Close");
        System.out.println("1). Get Balance");
        System.out.println("2). Withdraw");
        System.out.println("3). Deposit");
        System.out.println("4). Transfer");

        ans = reader.nextInt();
        if(ans<= 0 || ans>5){
            return()
        }
        else if(ans == 1){
            System.out.println("Current Balance"+ teller.getBalance(id));
        }
        else if(ans == 2){
            System.out.println("Input Amount to Withdraw");
            val = reader.nextLine();
            teller.withdraw(id);
            System.out.println("Current Balance"+ teller.getBalance(id, val));
        }
        else if(ans == 3){
            System.out.println("Input Amount to Deposit");
            val = reader.nextLine();
            teller.deposit(id);
            System.out.println("Current Balance"+ teller.getBalance(id, val));
        }
        else if(ans == 4){
            System.out.println("Input Amount to Transfer");
            val = reader.nextLine();
            System.out.println("Input Target Account ID");
            tgt = reader.nextLine();
            teller.transfer(id, val, tgt);
            System.out.println("Current Balance"+ teller.getBalance(id));
        }
    }
}