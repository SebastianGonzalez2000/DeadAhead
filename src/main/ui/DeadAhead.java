package ui;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

// DeadAhead application

public class DeadAhead {
    private Scanner input;
    private List<Account> createdAccounts;

    // EFFECTS: runs the DeadAhead game
    public DeadAhead() {
        createdAccounts = new ArrayList<>();
        runGame();
    }

    // The runGame method implementation was extracted from the TellerApp project provided
    // in CPSC210. I did not write this method but it is still fair use according to the de
    // project restrictions.
    // MODIFIES: this
    // EFFECTS: processes the user's login details
    public void runGame() {
        boolean keepGoing = true;
        String command = null;

        init();

        while (keepGoing) {
            displayMenu();
            command = input.next();
            command = command.toLowerCase();

            if (command.equals("q")) {
                keepGoing = false;
            } else {
                processCommand(command);
            }
        }

        System.out.println("\nThank you for playing DeadAhead.");
    }

    // The init method implementation was extracted from the TellerApp project provided
    // in CPSC210. I did not write this method but it is still fair use according to the de
    // project restrictions.
    // MODIFIES: this
    // EFFECTS: creates a scanner for login process
    private void init() {
        input = new Scanner(System.in);
    }

    // The displayMenu method implementation was extracted from the TellerApp project provided
    // in CPSC210. I did not write this method but it is still fair use according to the de
    // project restrictions.
    // EFFECTS: displays menu of options to user
    private void displayMenu() {
        System.out.println("\nSelect from:");
        System.out.println("\tn -> create account");
        System.out.println("\tl -> login");
        System.out.println("\tq -> quit");
    }

    // The processCommand method implementation was extracted from the TellerApp project provided
    // in CPSC210. I did not write this method but it is still fair use according to the de
    // project restrictions.
    // MODIFIES: this
    // EFFECTS: processes user command
    private void processCommand(String command) {
        if (command.equals("n")) {
            createNewAccount();
        } else if (command.equals("l")) {
            verifyLogIn();
        } else {
            System.out.println("Selection not valid...");
        }
    }


    // EFFECTS: verifies the username and password the user inputs to see if
    //          user exists and password matches
    private void verifyLogIn() {
        System.out.println("Enter your username: ");

        String username = input.next();

        while (!existingUsername(username)) {
            System.out.println("Invalid username. Please try again: ");
            username = input.next();
        }

        System.out.println("Enter your password: ");
        String password = input.next();

        while (!checkValidPassword(username, password)) {
            System.out.println("Incorrect password. Please try again: ");
            password = input.next();
        }

        System.out.println("Welcome back, " + username + ".");
    }

    private boolean checkValidPassword(String username, String password) {
        for (Account acc : createdAccounts) {
            if (acc.getUsername().equals(username)) {
                return acc.getPassword().equals(password);
            }
        }
        return false;
    }



    private boolean existingUsername(String username) {
        for (Account acc : createdAccounts) {
            if (acc.getUsername().equals(username)) {
                return true;
            }
        }
        return false;
    }

    // MODIFIES: this
    // EFFECTS: creates a new user account for the game
    private void createNewAccount() {
        Account acc = new Account();
        System.out.println("Enter your new username: ");
        String username = input.next();
        acc.setUsername(username);
        System.out.println("Enter your new password: ");
        String password = input.next();
        System.out.println("Enter the same password again: ");
        String secondTry = input.next();
        while (!secondTry.equals(password)) {
            System.out.println("Passwords do not match. Please enter your password again: ");
            secondTry = input.next();
        }
        acc.setPassword(password);
        System.out.println("Your account has been created.");
        createdAccounts.add(acc);
    }
}
