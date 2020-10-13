package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

public class WallTest {
    Wall w;
    Player p;

    @BeforeEach
    public void setup() {
        p = new Player();
        w = new Wall(p);
    }

    @Test
    public void testConstructor() {
        assertEquals(w.MAX_AMMO_CAPACITY, w.getAmmo());
        assertFalse(w.getIsBeingUsed());
        assertEquals(p.getDir(), w.getDir());
        assertEquals(WeaponType.WALL, w.getWeaponType());
    }

    @Test
    public void testShoot() {
        assertEquals(w.MAX_AMMO_CAPACITY, w.getAmmo());

        w.shoot();

        assertEquals(w.MAX_AMMO_CAPACITY-1, w.getAmmo());
    }

    @Test
    public void testShootOutOfAmmo() {
        assertEquals(w.MAX_AMMO_CAPACITY, w.getAmmo());

        for (int i = 0; i < w.MAX_AMMO_CAPACITY ; i++) {
            w.shoot();
        }

        assertEquals(0, w.getAmmo());

        w.shoot();

        assertEquals(0, w.getAmmo());
    }

    @Test
    public void testReloadAlreadyFull() {
        assertEquals(w.MAX_AMMO_CAPACITY, w.getAmmo());

        w.reload();

        assertEquals(w.MAX_AMMO_CAPACITY, w.getAmmo());
    }

    @Test
    public void testReloadNotAlreadyFull() {
        w.shoot();
        assertEquals(w.MAX_AMMO_CAPACITY-1, w.getAmmo());

        w.reload();

        assertEquals(w.MAX_AMMO_CAPACITY, w.getAmmo());
    }
}
