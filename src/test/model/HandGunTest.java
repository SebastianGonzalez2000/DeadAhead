package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class HandGunTest {

    HandGun h1;
    HandGun h2;
    Player p;

    @BeforeEach
    public void setup() {
        p = new Player();
        h1 = new HandGun(p);
        h2 = new HandGun(p);
        h1.setIsCollected(true);
    }

    @Test
    public void testConstructor() {
        assertEquals(h1.MAX_AMMO_CAPACITY, h1.getAmmo());
        assertFalse(h1.getIsBeingUsed());
        assertEquals(p.getDir(), h1.getDir());
        assertEquals(WeaponType.HANDGUN, h1.getWeaponType());
        assertTrue(h1.equals(h2));
        assertEquals(h1.hashCode(), h2.hashCode());
    }

    @Test
    public void testShoot() {
        assertEquals(h1.MAX_AMMO_CAPACITY, h1.getAmmo());

        h1.shoot();

        assertEquals(h1.MAX_AMMO_CAPACITY-1, h1.getAmmo());
    }

    @Test
    public void testShootOutOfAmmo() {
        assertEquals(h1.MAX_AMMO_CAPACITY, h1.getAmmo());

        for (int i = 0; i < h1.MAX_AMMO_CAPACITY ; i++) {
            h1.shoot();
        }

        assertEquals(0, h1.getAmmo());

        h1.shoot();

        assertEquals(0, h1.getAmmo());
    }

    @Test
    public void testReloadAlreadyFull() {
        assertEquals(h1.MAX_AMMO_CAPACITY, h1.getAmmo());

        h1.reload();

        assertEquals(h1.MAX_AMMO_CAPACITY, h1.getAmmo());
    }

    @Test
    public void testReloadNotAlreadyFull() {
        h1.shoot();
        assertEquals(h1.MAX_AMMO_CAPACITY-1, h1.getAmmo());

        h1.reload();

        assertEquals(h1.MAX_AMMO_CAPACITY, h1.getAmmo());
    }

}