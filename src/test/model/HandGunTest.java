package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class HandGunTest {

    HandGun h;
    Player p;

    @BeforeEach
    public void setup() {
        p = new Player();
        h = new HandGun(p);
    }

    @Test
    public void testConstructor() {
        assertEquals(h.MAX_AMMO_CAPACITY, h.getAmmo());
        assertFalse(h.getIsBeingUsed());
        assertEquals(p.getDir(), h.getDir());
        assertEquals(WeaponType.HANDGUN, h.getWeaponType());
    }

    @Test
    public void testShoot() {
        assertEquals(h.MAX_AMMO_CAPACITY, h.getAmmo());

        h.shoot();

        assertEquals(h.MAX_AMMO_CAPACITY-1, h.getAmmo());
    }

    @Test
    public void testShootOutOfAmmo() {
        assertEquals(h.MAX_AMMO_CAPACITY, h.getAmmo());

        for (int i = 0 ; i < h.MAX_AMMO_CAPACITY ; i++) {
            h.shoot();
        }

        assertEquals(0, h.getAmmo());

        h.shoot();

        assertEquals(0, h.getAmmo());
    }

    @Test
    public void testReloadAlreadyFull() {
        assertEquals(h.MAX_AMMO_CAPACITY, h.getAmmo());

        h.reload();

        assertEquals(h.MAX_AMMO_CAPACITY, h.getAmmo());
    }

    @Test
    public void testReloadNotAlreadyFull() {
        h.shoot();
        assertEquals(h.MAX_AMMO_CAPACITY-1, h.getAmmo());

        h.reload();

        assertEquals(h.MAX_AMMO_CAPACITY, h.getAmmo());
    }

}