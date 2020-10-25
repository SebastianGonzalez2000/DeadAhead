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

    // EFFECTS: creates an account with no username or password
    public Account(String username, String password, Arsenal arsenal) {
        this.username = username;
        this.password = password;
        this.arsenal = arsenal;
        this.player = arsenal.getPlayer();
    }

    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("password", password);
        json.put("username", username);
        JSONArray arsenal = parseArsenal(this.arsenal);
        json.put("arsenal", arsenal);

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

}
