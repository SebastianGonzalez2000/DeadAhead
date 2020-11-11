package ui;

// Class representing a user account

import model.Arsenal;
import model.Player;
import model.Weapon;
import org.json.JSONArray;
import org.json.JSONObject;
import persistence.Writable;

import java.util.List;

public class Account implements Writable {
    private String username;
    private String password;
    private Arsenal arsenal;
    private Player player;
    private int highScore;
    private int currentScore;
    private int currentKills;
    private int currentHealth;

    // EFFECTS: creates an account with given username, password, arsenal, high score, and current score
    public Account(String username, String password, Arsenal arsenal, int highScore, int currentScore, int kills, int health) {
        this.username = username;
        this.password = password;
        this.arsenal = arsenal;
        this.player = arsenal.getPlayer();
        this.player.setAccount(this);
        this.player.setScore(currentScore);
        this.player.setKills(kills);
        this.player.setHealth(health);
        this.highScore = highScore;
        this.currentScore = currentScore;
        this.currentKills = kills;
        this.currentHealth = health;
    }

    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("password", password);
        json.put("health", currentHealth);
        json.put("kills", currentKills);
        json.put("username", username);
        JSONArray arsenal = parseArsenal(this.arsenal);
        json.put("arsenal", arsenal);
        json.put("highScore", highScore);
        json.put("currentScore", currentScore);

        return json;
    }

    // EFFECTS: Parses an arsenal into a JSONArray of strings representing
    //          weapons in player's arsenal
    private JSONArray parseArsenal(Arsenal arsenal) {
        JSONArray jsonArsenal = new JSONArray();
        for (Weapon w : arsenal.getArsenal()) {
            String weaponTag = parseWeapon(w);
            jsonArsenal.put(weaponTag);
        }
        return jsonArsenal;
    }

    // EFFECTS: Returns the weapon type of w as a string
    private String parseWeapon(Weapon w) {
        return w.getWeaponType().toString();
    }

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

    public List<Weapon> getWeapons() {
        return arsenal.getArsenal();
    }

    public Arsenal getArsenal() {
        return arsenal;
    }

    public int getHighScore() {
        return highScore;
    }

    public int getCurrentScore() {
        return currentScore;
    }

    public Player getPlayer() {
        return player;
    }

    public void setScore(int score) {
        currentScore = score;
    }

    public void setHealth(int health) {
        currentHealth = health;
    }

    public void setKills(int kills) {
        currentKills = kills;
    }
}
