import java.util.Scanner;
import java.util.InputMismatchException;
import java.io.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.File;

public class BankProject {

    public static void main(String[] args) {
        try {
            File f = new File("BankAccounts.txt");
            if (f.exists()) {
                PrintWriter file =  new PrintWriter(new FileWriter(f, true));
                doDat(file);
            } else {
                PrintWriter file = new PrintWriter("BankAccounts.txt");
                System.out.println("File does not exist, creating it.");
                doDat(file);
            }
            
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public static void doDat(PrintWriter file){
        Bank bankOfJava = new Bank("Bank of Java", 10);
        Scanner scanner = new Scanner(System.in).useDelimiter("\n");
        int counter = 0;
        System.out.println();
        
        try {
            File f = new File("BankAccounts.txt");
            Scanner input = new Scanner(f);
            while (input.hasNextLine()){
                String response = input.nextLine();
                String[] rCU = response.split(" ");
                try {
                    if (bankOfJava.addAccount(new BankAccount(rCU[0],rCU[1],rCU[2],Double.parseDouble(rCU[3]))) == true){
                        System.out.println(rCU[1] + "'s account has been added!");
                    } else {
                        System.out.println("Funny how this lines never show up!");
                    }
                   counter++;
                } catch (NumberFormatException e){
                    System.out.println("There is an error at line " + (counter + 1) + " in the file \"BankAccounts.txt\"");
                }
            }
            System.out.println(counter + " account(s) added");
        } catch (IOException e) {
            e.printStackTrace();
        }

        String option = "0";

        while(!option.equals("3")) {
            System.out.println("\nWhat do you want to do?");
            System.out.println("1: add a new account");
            System.out.println("2: sign into an existing acount");
            System.out.println("3: quit");

            option = scanner.next();

            System.out.println();

            if (option.equals("1") || option.equals("2") || option.equals("3")){
                if (option.equals("1")) {
                    addNewAccount(bankOfJava, scanner, file);
                } else if (option.equals("2")) {
                    printSignIn(bankOfJava, scanner);
                }
            } else {
                System.out.println("Bad Input");
            }
        }
        try {
            file = new PrintWriter("BankAccounts.txt");
            bankOfJava.saveAccounts(file);
        } catch (IOException e){
            e.printStackTrace();
        }
        

        System.out.println("Have a nice day!");
    }

    public static void pause(){
        try {
            Thread.sleep(1000);                 //1000 milliseconds is one second.
        } catch(InterruptedException ex) {
            Thread.currentThread().interrupt();
        }
    }

    public static void addNewAccount(Bank bank, Scanner scanner, PrintWriter newAccount) {

        System.out.println("Enter your name:");
        String newname = scanner.next();
        System.out.println("Enter your SSN (not your real one :P):");
        String newssn = scanner.next();
        System.out.println("Starting amount of money:");
        int newmoney = scanner.nextInt();
        System.out.println("Type in a new password:");
        String attemptone = scanner.next();
        System.out.println("Type in a new password again:");
        String attempttwo = scanner.next();

        if (attemptone.equals(attempttwo)){

            if (bank.findAccountBySocial(newssn) == null){

                if (bank.addAccount(new BankAccount(newssn,newname,attempttwo,newmoney)) == true){
                    /**
                    *   Add the info to the file. w/ space ever line to sperate it
                    *   Every next line is a new account!
                    */
                    //newAccount.print(newssn + " " + newname + " " + attempttwo + " " + newmoney);

                    System.out.println("Account added successfuly!");
                } else {
                    System.out.println("Can't add an account for some random reason! .-.");
                }
            } else {

                System.out.println("You can't have multiple accounts with the same Social number!");
            }
        } else {

            System.out.println("Passwords didn't match!");
        }
    }

    public static void printSignIn(Bank bank, Scanner scanner) {
        /*
         1. Ask the user for input: the social.
         2. Look up the BankAccount in bank.
         3. Process the account if found, otherwise inform the user it could not be found.
        */
        System.out.print("Please enter your SSN: ");
        String wutssn = scanner.next();
        BankAccount account = bank.findAccountBySocial(wutssn);
        if (account != null){
            System.out.print("Please enter your password: ");
            String wutpassword = scanner.next();
            if (wutssn.equals(account.getSocial())){
                if (wutpassword.equals(account.getPassword())) {
                    processAccount(account,scanner);
                }
            } else {
                System.out.println("Incorrect Password!");
            }
        } else {
            System.out.println("Sorry, but we I can't find an account by that SSN!");
        }
    }

    public static void processAccount(BankAccount account, Scanner scanner) {
        System.out.println("Hi " + account.getName());
        System.out.println("Your current balance is " + account.getBalance());
        System.out.println("What would you like to do");
        System.out.println("1: make a deposit");
        System.out.println("2: withdraw money");
        System.out.println("3: change password");

        String option = scanner.next();

        System.out.println();

        if (Integer.parseInt(option) == 1) {
            processDeposit(account, scanner);
        } else if (Integer.parseInt(option) == 2) {
            processWithdrawal(account, scanner);
        } else if (Integer.parseInt(option) == 3) {
            account.changePassword();
        } else {
            System.out.println("Unknown command!");
            processAccount(account,scanner);
        }
    }

    public static void processDeposit(BankAccount account, Scanner scanner) {
        /*
         1. Ask the user for input: the amount to deposit.
         2. Deposit the money into the BankAccount
         3. Print the updated balance.
        */
        System.out.print("How much do you want to deposit?: ");
        int input = scanner.nextInt();
        account.depositMoney(input);
        System.out.print("Your balance is now " + account.getBalance());

    }

    public static void processWithdrawal(BankAccount account, Scanner scanner) {
        /*
         1. Ask the user for input: the amount to withdraw.
         2. Try to withdraw the money from the BankAccount
         3. On success inform user of the new balance, otherwise inform the user that they can't withdraw.
         4. Print the updated balance.
        */
        System.out.print("How much do you want to withdraw?: ");
        int input = scanner.nextInt();
        if (input > -0){
            if (account.withdrawMoney(input) == true) {
                System.out.print("Your balance is now " + account.getBalance());
            } else {
                System.out.println("You don't have enough money to take out!");
            }
        } else {
            System.out.println("value has to be positive!");
        }
    }
}