package ui;

// Class representing a user account

public class Account {
    String username;
    String password;

    // EFFECTS: creates an account with no username or password
    public Account() {}

    // getters and setters

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }
}
