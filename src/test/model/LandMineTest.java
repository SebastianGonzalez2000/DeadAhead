package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

public class LandMineTest {
    LandMine lm;
    Player p;

    @BeforeEach
    public void setup() {
        p = new Player();
        lm = new LandMine(p);
    }

    @Test
    public void testConstructor() {
        assertEquals(lm.MAX_AMMO_CAPACITY, lm.getAmmo());
        assertFalse(lm.getIsBeingUsed());
        assertEquals(p.getDir(), lm.getDir());
        assertEquals(WeaponType.MINE, lm.getWeaponType());
    }

    @Test
    public void testShoot() {
        assertEquals(lm.MAX_AMMO_CAPACITY, lm.getAmmo());

        lm.shoot();

        assertEquals(lm.MAX_AMMO_CAPACITY-1, lm.getAmmo());
    }

    @Test
    public void testShootOutOfAmmo() {
        assertEquals(lm.MAX_AMMO_CAPACITY, lm.getAmmo());

        for (int i = 0 ; i < lm.MAX_AMMO_CAPACITY ; i++) {
            lm.shoot();
        }

        assertEquals(0, lm.getAmmo());

        lm.shoot();

        assertEquals(0, lm.getAmmo());
    }

    @Test
    public void testReloadAlreadyFull() {
        assertEquals(lm.MAX_AMMO_CAPACITY, lm.getAmmo());

        lm.reload();

        assertEquals(lm.MAX_AMMO_CAPACITY, lm.getAmmo());
    }

    @Test
    public void testReloadNotAlreadyFull() {
        lm.shoot();
        assertEquals(lm.MAX_AMMO_CAPACITY-1, lm.getAmmo());

        lm.reload();

        assertEquals(lm.MAX_AMMO_CAPACITY, lm.getAmmo());
    }
}
