package model;

import org.junit.jupiter.api.Test;
import persistence.JsonReader;
import ui.Account;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class JsonReaderTest {

    @Test
    void testReaderNonExistentFile() {
        JsonReader reader = new JsonReader("./data/noSuchFile.json");

        try {
            Account acc = reader.read();
            fail("IOException was not thrown.");
        } catch (IOException e) {
            // do nothing
        }
    }

    @Test
    void testReaderInitialArsenal() {
        JsonReader reader = new JsonReader("./data/testReaderInitialArsenal.json");

        try {
            Account acc = reader.read();
            assertEquals("Sebastian", acc.getUsername());
            assertEquals("123", acc.getPassword());
            assertEquals(1, acc.getWeapons().size());
            assertEquals(WeaponType.HANDGUN, acc.getWeapons().get(0).getWeaponType());
            assertEquals(100, acc.getHighScore());
            assertEquals(69, acc.getCurrentScore());
        } catch (IOException e) {
            fail("IOException was thrown and caught when it shouldn't have.");
        }
    }

    @Test
    void testReaderFullArsenal() {
        JsonReader reader = new JsonReader("./data/testReaderFullArsenal.json");

        try {
            Account acc = reader.read();
            assertEquals("Sebastian", acc.getUsername());
            assertEquals("123", acc.getPassword());
            assertEquals(5, acc.getWeapons().size());
            assertEquals(WeaponType.HANDGUN, acc.getWeapons().get(0).getWeaponType());
            assertEquals(WeaponType.SHOTGUN, acc.getWeapons().get(1).getWeaponType());
            assertEquals(WeaponType.UZI, acc.getWeapons().get(2).getWeaponType());
            assertEquals(WeaponType.LAUNCHER, acc.getWeapons().get(3).getWeaponType());
            assertEquals(WeaponType.MINE, acc.getWeapons().get(4).getWeaponType());
            assertEquals(0, acc.getHighScore());
            assertEquals(0, acc.getCurrentScore());
        } catch (IOException e) {
            fail("IOException was thrown and caught when it shouldn't have.");
        }
    }

    @Test
    void testReaderGeneralArsenal() {
        JsonReader reader = new JsonReader("./data/testReaderGeneralArsenal.json");

        try {
            Account acc = reader.read();
            assertEquals("Sebastian", acc.getUsername());
            assertEquals("123", acc.getPassword());
            assertEquals(2, acc.getWeapons().size());
            assertEquals(WeaponType.HANDGUN, acc.getWeapons().get(0).getWeaponType());
            assertEquals(WeaponType.WALL, acc.getWeapons().get(1).getWeaponType());
            assertEquals(0, acc.getHighScore());
            assertEquals(0, acc.getCurrentScore());
        } catch (IOException e) {
            fail("IOException was thrown and caught when it shouldn't have.");
        }
    }

}
