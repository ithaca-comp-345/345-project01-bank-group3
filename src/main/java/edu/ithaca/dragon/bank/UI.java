
package edu.ithaca.dragon.bank;
import java.util.*;

public class UI(){
    protected ATM_Software teller;
    public String activeEmail;

    public UI(){
        this.teller = new ATM_Software();
        this.activeEmail = null;
    }

    public void logIn(){
        Scanner reader = new Scanner(System.in);

        //If no active user, log in
        while(this.activeEmail == null){
            this.activeEmail = this.teller.verifyCredentials();
        }
        boolean run = true;
        while(run){
            //If NOT admin
            if(this.emailsWithAdmin.indexOf(this.activeEmail) > -1){
                userIface();                
            }
            //Else: adminIface which will allow full access to admin & bankteller classes

            System.out.println("Input 0 to close, or any other integer to go back to Account Page");
            int closePrompt = reader.nextInt();
            if(closePrompt == 0){
                run = true;
            }
        }
        return()
    }
    public void userIface(){
        ArrayList<Integer> ids = this.teller.emailAccountIDs.get(this.activeEmail);
                //if 1 or more accounts(ids)
                if(ids.size() > 0){
                    //if 1 account, 1 option
                    if(ids.size() >= 1){
                        manageAcct(ids[0]);
                    }
                    //more than 1 accounts
                    else(){
                        boolean validID = false;
                        int idSel;
                        while(!validID){
                            System.out.println("Input Account ID to Select");
                            idSel = reader.nextInt();
                            //check if valid account selection
                            
                            for(int i = 0; i < ids.size() ; i++){
                                if(idSel == ids[i]){
                                    validID = true;
                                }
                            }
                            if(validID){
                                manageAcct(idSel);
                                //after closing one account page, allows user to use another
                                System.out.println("Input 0 to close, or any other Integer to resume");
                                if(!reader.nextInt()){
                                    validID = false;
                                }
                            }
                        }
                        
                    }
                }
        return()
    }


    public void manageAcct(Integer id){
        System.out.println("Input Menu Option");
        System.out.println("0). Close");
        System.out.println("1). Get Balance");
        System.out.println("2). Withdraw");
        System.out.println("3). Deposit");
        System.out.println("4). Transfer");

        Integer ans = reader.nextInt();
        if(ans<= 0 || ans>5){
            return();
        }
        else if(ans == 1){
            System.out.println("Current Balance"+ teller.getBalance(id));
        }
        else if(ans == 2){
            System.out.println("Input Amount to Withdraw");
            double val = reader.nextLine();
            teller.withdraw(id);
            System.out.println("Current Balance"+ teller.getBalance(id, val));
        }
        else if(ans == 3){
            System.out.println("Input Amount to Deposit");
            double val = reader.nextLine();
            teller.deposit(id);
            System.out.println("Current Balance"+ teller.getBalance(id, val));
        }
        else if(ans == 4){
            System.out.println("Input Amount to Transfer");
            double val = reader.nextLine();
            System.out.println("Input Target Account ID");
            Integer tgt = reader.nextLine();
            teller.transfer(id, val, tgt);
            System.out.println("Current Balance"+ teller.getBalance(id));
        }
    }

    
}