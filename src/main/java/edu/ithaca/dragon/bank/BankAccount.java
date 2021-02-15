package edu.ithaca.dragon.bank;

public abstract class BankAccount {

    protected String email;
    protected String id;
    protected double balance;
    protected boolean isFrozen;
    

    /**
     * @throws IllegalArgumentException if email is invalid
     */
    public BankAccount(String email, double startingBalance, String id){
        
        if (isEmailValid(email)){
            this.email = email;
            this.balance = startingBalance;
            this.id = id;
            this.isFrozen = false;
        }
        else {
            throw new IllegalArgumentException("Email address: " + email + " is invalid, cannot create account");
        }
        
    }

    public double getBalance(){
        return balance;
    }

    public String getEmail(){
        return email;
    }

    /**
     * @post reduces the balance by amount if amount is non-negative and smaller than balance
     */
    public void withdraw (double amount) throws InsufficientFundsException{
        //Make sure the amount is rounded to cents if 3 decimal places to the right of '.' or more
        amount = roundToCents(amount);
        //We can assume balance is rounded already. For savings, after interest must call roundToCents
        
        //First, make sure amount is positive number, not negative; amount > 0
        if (amount > 0){
        
            // 0 < amount <= balance and balance >= 0 because can never be < 0
            if (amount <= balance){
                balance -= amount;
            }
            else {
                throw new InsufficientFundsException("Not enough money");
            }
        }
        else {
            throw new InsufficientFundsException("Cannot Withdraw Less Than 0");
        }
    }
    public static double roundToCents(double in){
        //Takes Number and converts it to string
        String stringIn = Double.toString(in); 
        //Takes String and converts it to character array
        char[] inCharArray = stringIn.toCharArray(); 

        boolean belowOne = false;
        int counter = 0;
        for (int i=0; i<inCharArray.length-1; i+=1){
            if(counter >= 3){
                //Cant round up...
                inCharArray[i] = '0';
            }
            else{
                //When '.' is hit start counting to hold distance from the '.'
                if(inCharArray[i]==('.')){
                    belowOne = true;
                }
                //Have if statement instead of else case because this allows for first step once '.' is hit
                if(belowOne){ 
                    counter+=1;
                }
            }
           
            
        }
        //Convert char array to String
        stringIn = String.valueOf(inCharArray);
        //convert string to double
        out = Double.parseDouble(stringIn);
        return out;

    }

    public static boolean isEmailValid(String email){
        if (email.indexOf('@') == -1){
            return false;
        }
        else {
            return true;
        }
    }

    public void transfer(Class from, Class to, double amount) throws InsufficientFundsException{
        from.withdraw(amount);
        to.deposit(amount);
    }
}
