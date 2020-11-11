package persistence;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Stream;

import model.*;
import org.json.*;
import ui.Account;

// DISCLAIMER: This class was based on the JsonReader class from the WorkRoom Java app
// from CPSC210. I only designed the parsing methods
// Represents a reader that reads account from JSON data stored in file
public class JsonReader {
    private String source;

    // EFFECTS: constructs reader to read from source file
    public JsonReader(String source) {
        this.source = source;
    }

    // EFFECTS: reads account from file and returns it;
    // throws IOException if an error occurs reading data from file
    public Account read() throws IOException {
        String jsonData = readFile(source);
        JSONObject jsonObject = new JSONObject(jsonData);
        return parseAccount(jsonObject);
    }

    // EFFECTS: reads source file as string and returns it
    private String readFile(String source) throws IOException {
        StringBuilder contentBuilder = new StringBuilder();

        try (Stream<String> stream = Files.lines(Paths.get(source), StandardCharsets.UTF_8)) {
            stream.forEach(s -> contentBuilder.append(s));
        }

        return contentBuilder.toString();
    }

    // EFFECTS: parses Account from JSON object and returns it
    private Account parseAccount(JSONObject jsonObject) {
        String username = jsonObject.getString("username");
        String password = jsonObject.getString("password");
        int kills = jsonObject.getInt("kills");
        int health = jsonObject.getInt("health");
        JSONArray arsenalJson = jsonObject.getJSONArray("arsenal");
        Arsenal arsenal = parseArsenal(arsenalJson);
        int highScore = jsonObject.getInt("highScore");
        int currentScore = jsonObject.getInt("currentScore");
        Account acc = new Account(username, password, arsenal, highScore, currentScore, kills, health);
        return acc;
    }

    // EFFECTS: parses a JSONArray with strings representing weapons
    //          to an Arsenal with the respective weapon objects
    private Arsenal parseArsenal(JSONArray arsenalJSon) {
        Player player = new Player();
        Arsenal arsenal = new Arsenal(player);
        player.setArsenal(arsenal);
        for (Object json : arsenalJSon) {
            String nextWeapon = (String) json;
            parseWeapon(arsenal, nextWeapon, player);
        }
        return arsenal;
    }

    // EFFECTS: Parses the given string into a Weapon object and add it to the arsenal
    private void parseWeapon(Arsenal arsenal, String weapon, Player player) {
        if (weapon.equals("HANDGUN")) {
            arsenal.collectWeapon(new HandGun(player));
        } else if (weapon.equals("SHOTGUN")) {
            arsenal.collectWeapon(new ShotGun(player));
        } else if (weapon.equals("UZI")) {
            arsenal.collectWeapon(new Uzi(player));
        } else if (weapon.equals("LAUNCHER")) {
            arsenal.collectWeapon(new RocketLauncher(player));
        } else if (weapon.equals("MINE")) {
            arsenal.collectWeapon(new LandMine(player));
        } else {
            arsenal.collectWeapon(new Wall(player));
        }
    }
}
