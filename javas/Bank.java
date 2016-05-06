import java.io.PrintWriter;

public class Bank {

    /* Declare instance variables here */
    private String branch = null;
    private int capacity = 0;
    private BankAccount[] accounts;
    private int counter = 0;

    /**
     * Constructor
     *
     * @param branchName       The name of the branch.
     * @param maximumCapacity  The max number of BankAccounts this bank can support.
     */
    public Bank(String branchName, int maximumCapacity) {
        branch = branchName;
        capacity = maximumCapacity;
        accounts = new BankAccount[capacity];
    }

    /**
     * Get the accounts.
     *
     * @return    The accounts.
     */
    public String[] getAccounts() {
        String[] temp = new String[counter];
        for (int i = 0; i < counter; i++){
            temp[i] = String.valueOf(accounts[i]);
        }
        return temp;
    }

    /**
     * Saves the accounts.
     *
     * @return    None.
     */
    public void saveAccounts(PrintWriter file) {
        for (int i = 0; i < counter; i++){
            file.print(accounts[i].getSocial());
            file.print(" " + accounts[i].getName());
            file.print(" " + accounts[i].getPassword());
            file.println(" " + accounts[i].getBalance());
        }
        file.close();
    }

    /**
     * Get the branch name.
     *
     * @return    The branch name.
     */
    public String getBranchName() {
        return branch;
    }

    /**
     * Looks up a BankAccount based on a social.
     *
     * @param social  The social number to be looked up.
     * @return        The located BankAccount. If none exists, return null.
     */
    public BankAccount findAccountBySocial(String social) {
        BankAccount check = null;
        for (int i = 0; i < accounts.length - 1; i++){
            if (accounts[i] != null){
                if (accounts[i].getSocial().equals(social)){
                    check = accounts[i];
                }
            }
        }
        return check;
    }

    /**
     * Adds a new account.
     *
     * @param account  The account to be added.
     * @return         True if successfully added, false otherwise.
     */
    public boolean addAccount(BankAccount account) {
        boolean check = true;
        accounts[counter] = account;
        if (accounts[counter] == null){
            check = false;
        }
        counter++;
        return check;
    }
}