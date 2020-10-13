package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

public class RocketLauncherTest {
    RocketLauncher rl;
    Player p;

    @BeforeEach
    public void setup() {
        p = new Player();
        rl = new RocketLauncher(p);
    }

    @Test
    public void testConstructor() {
        assertEquals(rl.MAX_AMMO_CAPACITY, rl.getAmmo());
        assertFalse(rl.getIsBeingUsed());
        assertEquals(p.getDir(), rl.getDir());
        assertEquals(WeaponType.LAUNCHER, rl.getWeaponType());
    }

    @Test
    public void testShoot() {
        assertEquals(rl.MAX_AMMO_CAPACITY, rl.getAmmo());

        rl.shoot();

        assertEquals(rl.MAX_AMMO_CAPACITY-1, rl.getAmmo());
    }

    @Test
    public void testShootOutOfAmmo() {
        assertEquals(rl.MAX_AMMO_CAPACITY, rl.getAmmo());

        for (int i = 0; i < rl.MAX_AMMO_CAPACITY ; i++) {
            rl.shoot();
        }

        assertEquals(0, rl.getAmmo());

        rl.shoot();

        assertEquals(0, rl.getAmmo());
    }

    @Test
    public void testReloadAlreadyFull() {
        assertEquals(rl.MAX_AMMO_CAPACITY, rl.getAmmo());

        rl.reload();

        assertEquals(rl.MAX_AMMO_CAPACITY, rl.getAmmo());
    }

    @Test
    public void testReloadNotAlreadyFull() {
        rl.shoot();
        assertEquals(rl.MAX_AMMO_CAPACITY-1, rl.getAmmo());

        rl.reload();

        assertEquals(rl.MAX_AMMO_CAPACITY, rl.getAmmo());
    }
}
