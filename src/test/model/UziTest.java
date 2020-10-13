package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

public class UziTest {
    Uzi u;
    Player p;

    @BeforeEach
    public void setup() {
        p = new Player();
        u = new Uzi(p);
    }

    @Test
    public void testConstructor() {
        assertEquals(u.MAX_AMMO_CAPACITY, u.getAmmo());
        assertFalse(u.getIsBeingUsed());
        assertEquals(p.getDir(), u.getDir());
        assertEquals(WeaponType.UZI, u.getWeaponType());
    }

    @Test
    public void testShoot() {
        assertEquals(u.MAX_AMMO_CAPACITY, u.getAmmo());

        u.shoot();

        assertEquals(u.MAX_AMMO_CAPACITY-1, u.getAmmo());
    }

    @Test
    public void testShootOutOfAmmo() {
        assertEquals(u.MAX_AMMO_CAPACITY, u.getAmmo());

        for (int i = 0; i < u.MAX_AMMO_CAPACITY ; i++) {
            u.shoot();
        }

        assertEquals(0, u.getAmmo());

        u.shoot();

        assertEquals(0, u.getAmmo());
    }

    @Test
    public void testReloadAlreadyFull() {
        assertEquals(u.MAX_AMMO_CAPACITY, u.getAmmo());

        u.reload();

        assertEquals(u.MAX_AMMO_CAPACITY, u.getAmmo());
    }

    @Test
    public void testReloadNotAlreadyFull() {
        u.shoot();
        assertEquals(u.MAX_AMMO_CAPACITY-1, u.getAmmo());

        u.reload();

        assertEquals(u.MAX_AMMO_CAPACITY, u.getAmmo());
    }
}
