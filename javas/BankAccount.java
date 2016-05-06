import java.util.Scanner;

public class BankAccount {

    /* Declare instance variables here */
    private String mySocial = null;
    private String myName = null;
    private double myMoney = 0;
    private String myPassword = null;
    private int myTaxCounter = 0;

    /**
     * Constructor
     *
     * @param social        The social of the owner of the account.
     * @param name          The name of the owner of the account.
     * @param initialAmount The amount of money initially in the account.
     */
    public BankAccount(String social, String name, String password, double initialAmount) {
        mySocial = social;
        myName = name;
        myMoney = initialAmount;
        myPassword = password;
    }

    /**
     * Get the balance of the account.
     *
     * @return    The balance.
     */
    public double getBalance() {
        return myMoney;
    }

    /**
     * Get the balance of the account.
     *
     * @return    The balance.
     */
    public String getName() {
        return myName;
    }

    /**
     * Get the social security number of the owner.
     *
     * @return    The social security number.
     */
    public String getSocial() {
        return mySocial;
    }

    /**
     * Get the password of the owner.
     *
     * @return    The password.
     */
    public String getPassword() {
        return myPassword;
    }

    /**
    * Allows user to change their password.
    *
    * @param Scanner.
    */
    public void changePassword() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Please type in your password:");
        String answerone = scanner.next();
        if (answerone.equals(myPassword)){
            System.out.println("Enter your new password.");
            String answertwo = scanner.next();
            System.out.println("Now confirm it.");
            String answerthere = scanner.next();
            if (answertwo.equals(answerthere)){

                System.out.println("Password reset!");
            } else {
                System.out.println("They don't match!");
            }
        } else {
            System.out.println("Wrong Password!");
        }
    }

    /**
     * Adds the money to the balance.
     *
     * @param amount  The money being deposited.
     */
    public void depositMoney(double amount) {
        myMoney += amount;
    }

    /**
     * Withdraws money out of the account.
     *
     * @param amount  The amount withdrawn.
     * @return        True if successful, false otherwise.
     */
    public boolean withdrawMoney(double amount) {
        boolean result = false;
        if (myMoney - amount >= 0){
            result = true;
            myMoney -= amount;
        }
        return result;
    }
}
