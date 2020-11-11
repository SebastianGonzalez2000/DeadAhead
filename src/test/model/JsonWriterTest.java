package model;

import org.junit.jupiter.api.Test;
import persistence.JsonReader;
import persistence.JsonWriter;
import ui.Account;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class JsonWriterTest {

    @Test
    void testWriterInvalidFile() {
        try {
            Player player = new Player();
            Arsenal arsenal = new Arsenal(player);
            player.setArsenal(arsenal);
            Account acc = new Account("Sebastian", "123", arsenal, 0, 0, 0, player.MAX_HEALTH);
            JsonWriter writer = new JsonWriter("./data/my\0illegal:fileName.json");
            writer.open();
            fail("IOException was expected");
        } catch (IOException e) {
            // do nothing
        }
    }

    @Test
    void testWriterHandGunArsenal() {
        try {
            Player player = new Player();
            Arsenal arsenal = new Arsenal(player);
            player.setArsenal(arsenal);
            Account acc = new Account("Sebastian", "123", arsenal, 0, 0, 0, player.MAX_HEALTH);
            JsonWriter writer = new JsonWriter("./data/testWriterHandGunArsenal.json");
            writer.open();
            writer.write(acc);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriterHandGunArsenal.json");
            acc = reader.read();
            assertEquals("Sebastian", acc.getUsername());
            assertEquals("123", acc.getPassword());
            assertEquals(1, acc.getWeapons().size());
            assertEquals(WeaponType.HANDGUN, acc.getWeapons().get(0).getWeaponType());
            assertEquals(0, acc.getHighScore());
            assertEquals(0, acc.getCurrentScore());
            assertEquals(100, acc.getPlayer().getHealth());
            assertEquals(0, acc.getPlayer().getKills());

        } catch (IOException e) {
            fail("IOException was incorrectly caught.");
        }
    }

    @Test
    void testWriterFullArsenal() {
        try {
            Player player = new Player();
            Arsenal arsenal = new Arsenal(player);
            player.setArsenal(arsenal);
            Account acc = new Account("Sebastian", "123", arsenal, 100, 69, 0, player.MAX_HEALTH);
            acc.getArsenal().collectWeapon(new ShotGun(player));
            acc.getArsenal().collectWeapon(new Uzi(player));
            acc.getArsenal().collectWeapon(new RocketLauncher(player));
            acc.getArsenal().collectWeapon(new LandMine(player));

            JsonWriter writer = new JsonWriter("./data/testWriterFullArsenal.json");
            writer.open();
            writer.write(acc);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriterFullArsenal.json");
            acc = reader.read();
            assertEquals("Sebastian", acc.getUsername());
            assertEquals("123", acc.getPassword());
            assertEquals(5, acc.getWeapons().size());
            assertEquals(WeaponType.HANDGUN, acc.getWeapons().get(0).getWeaponType());
            assertEquals(WeaponType.SHOTGUN, acc.getWeapons().get(1).getWeaponType());
            assertEquals(WeaponType.UZI, acc.getWeapons().get(2).getWeaponType());
            assertEquals(WeaponType.LAUNCHER, acc.getWeapons().get(3).getWeaponType());
            assertEquals(WeaponType.MINE, acc.getWeapons().get(4).getWeaponType());
            assertEquals(100, acc.getHighScore());
            assertEquals(69, acc.getCurrentScore());
            assertEquals(100, acc.getPlayer().getHealth());
            assertEquals(0, acc.getPlayer().getKills());

        } catch (IOException e) {
            fail("IOException was incorrectly caught.");
        }
    }

}
