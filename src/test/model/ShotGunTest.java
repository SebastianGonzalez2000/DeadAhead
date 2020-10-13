package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

public class ShotGunTest {
    ShotGun s;
    Player p;

    @BeforeEach
    public void setup() {
        p = new Player();
        s = new ShotGun(p);
    }

    @Test
    public void testConstructor() {
        assertEquals(s.MAX_AMMO_CAPACITY, s.getAmmo());
        assertFalse(s.getIsBeingUsed());
        assertEquals(p.getDir(), s.getDir());
        assertEquals(WeaponType.SHOTGUN, s.getWeaponType());
    }

    @Test
    public void testShoot() {
        assertEquals(s.MAX_AMMO_CAPACITY, s.getAmmo());

        s.shoot();

        assertEquals(s.MAX_AMMO_CAPACITY-1, s.getAmmo());
    }

    @Test
    public void testShootOutOfAmmo() {
        assertEquals(s.MAX_AMMO_CAPACITY, s.getAmmo());

        for (int i = 0; i < s.MAX_AMMO_CAPACITY ; i++) {
            s.shoot();
        }

        assertEquals(0, s.getAmmo());

        s.shoot();

        assertEquals(0, s.getAmmo());
    }

    @Test
    public void testReloadAlreadyFull() {
        assertEquals(s.MAX_AMMO_CAPACITY, s.getAmmo());

        s.reload();

        assertEquals(s.MAX_AMMO_CAPACITY, s.getAmmo());
    }

    @Test
    public void testReloadNotAlreadyFull() {
        s.shoot();
        assertEquals(s.MAX_AMMO_CAPACITY-1, s.getAmmo());

        s.reload();

        assertEquals(s.MAX_AMMO_CAPACITY, s.getAmmo());
    }
}
