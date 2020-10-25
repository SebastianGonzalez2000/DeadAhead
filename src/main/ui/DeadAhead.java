package ui;

import model.Arsenal;
import model.Player;
import model.Weapon;
import persistence.JsonReader;
import persistence.JsonWriter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

// DeadAhead application

public class DeadAhead {
    private static final String JSON_STORE = "./data/account.json";
    private Scanner input;
    private Account displayedAccount;
    private List<Account> createdAccounts;
    private JsonWriter jsonWriter;
    private JsonReader jsonReader;

    // EFFECTS: constructs the account and runs the DeadAhead game
    public DeadAhead() {
        createdAccounts = new ArrayList<>();
        jsonWriter = new JsonWriter(JSON_STORE);
        jsonReader = new JsonReader(JSON_STORE);
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
        System.out.println("\tn -> New Game");
        System.out.println("\tl -> Load Game");
        System.out.println("\tp -> Saved Gameplay specifications");
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
        } else if (command.equals("p")) {
            printSpecifications();
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

        while (!checkValidPassword(password)) {
            System.out.println("Incorrect password. Please try again: ");
            password = input.next();
        }

        try {
            displayedAccount = jsonReader.read();
            System.out.println("Welcome back, " + username + ".");
        } catch (IOException e) {
            System.out.println("Unable to find account.");
        }
    }

    // EFFECTS: returns true if password matches the password set for
    //          the current displayed account.
    private boolean checkValidPassword(String password) {

        try {
            Account account = jsonReader.read();
            return account.getPassword().equals(password);
        } catch (IOException e) {
            System.out.println("Unable to find account.");
        }

        return false;
    }

    // EFFECTS: returns true if the username given matches the username set
    //          for the displayed account
    private boolean existingUsername(String username) {

        try {
            Account account = jsonReader.read();
            return account.getUsername().equals(username);
        } catch (IOException e) {
            System.out.println("Unable to find account.");
        }
        return false;
    }

    // MODIFIES: this
    // EFFECTS: creates a new user account for the game
    private void createNewAccount() {

        System.out.println("Enter your new username: ");
        String username = input.next();

        System.out.println("Enter your new password: ");
        String password = input.next();
        System.out.println("Enter the same password again: ");
        String secondTry = input.next();
        while (!secondTry.equals(password)) {
            System.out.println("Passwords do not match. Please enter your password again: ");
            secondTry = input.next();
        }

        Account acc = new Account(username, password, new Arsenal(new Player()));


        System.out.println("Your account has been created, " + username);
        createdAccounts.add(acc);

        try {
            jsonWriter.open();
            jsonWriter.write(acc);
            jsonWriter.close();
        } catch (IOException e) {
            System.out.println("Unable to create account.");
        }
    }

    // EFFECTS: prints to the screen the game specifications of the current
    //          displayed account
    private void printSpecifications() {
        System.out.println(displayedAccount.getUsername());
        System.out.println(displayedAccount.getPassword());

        System.out.println("Weapons in arsenal: ");

        for (Weapon w : displayedAccount.getWeapons()) {
            System.out.println(w.getWeaponType().toString());
        }

        // consider printing difficulty or weapons available
    }
}
