package edu.ithaca.dragon.bank;

class BankAccount {
    
    private String email;
    private static int globalID = 1;
    private int id;
    private double balanceC;
    private double balanceS;
    private boolean isFrozen;
    

    /**
     * @throws IllegalArgumentException if email is invalid
     */
    public BankAccount(String email, double startingBalance){
        if (isEmailValid(email)){
            if(isAmountValid(startingBalance)){
                this.email = email;
                this.balanceC = startingBalance;
                isFrozen = false;
                id = globalID;
                globalID++;
            }
            else throw new IllegalArgumentException("Starting balance must be positive and have no more than two decimal places");
        }
        else {
            throw new IllegalArgumentException("Email address: " + email + " is invalid, cannot create account");
        }
    }

    public double getBalanceC(){
        return balanceC;
    }

    public double getBalanceS(){
        return balanceS;
    }

    public String getEmail(){
        return email;
    }

    /**
     * @return true if amount is positive (amount > 0) and has two or fewer decimal places, otherwise false
     */
    public static boolean isAmountValid(double amount){
        if(amount <= 0){
            return false;
        }
        
        // Check to see if there are two or less decimal places.
        String num = Double.toString(amount);
        int integers = num.indexOf('.');
        int decimals = num.length() - integers - 1;
        if(decimals > 2){
            return false;
        }

        return true;
    }

    /**
     * @post reduces the balance by amount if amount is non-negative and smaller than balance
     */
    public void withdraw (double amount) throws InsufficientFundsException, IllegalArgumentException{
        if(isAmountValid(amount) == false){
            throw new IllegalArgumentException("Invalid amount");
        }
        else if (amount <= balanceC){
            balanceC -= amount;
        }
        else {
            throw new InsufficientFundsException("Not enough money");
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
        double out = Double.parseDouble(stringIn);
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
    
    /**
     * transfers a certain amount from one balance to another
     */
    public void transfer(double amount) throws InsufficientFundsException, IllegalArgumentException{
        withdraw(amount);
        balanceS += amount;
    }

    
    /**
     * Deposits certain amount into the givent account balance
     */
    public void deposit(double amount, String depositTo) throws IllegalArgumentException{
        if (isAmountValid(amount)){
            if (depositTo.equals("Checking")){
            balanceC += amount;
            }
            else{
                balanceS += amount;
            }
        }
        else{
            throw new IllegalArgumentException("Amount: " + amount + " is an invalid amount to deposit.");
        }
    }
}
