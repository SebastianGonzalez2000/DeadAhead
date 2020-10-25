package persistence;

import org.json.JSONObject;

// Interface representing a writable piece of information to the JSON file
public interface Writable {

    // EFFECTS: returns this as JSON object
    JSONObject toJson();
}