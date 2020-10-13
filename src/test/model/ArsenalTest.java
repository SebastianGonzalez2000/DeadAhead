package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ArsenalTest {
    Arsenal a;
    Player p;

    @BeforeEach
    public void setup() {
        p = new Player();
        a = new Arsenal(p);
    }

    @Test
    public void testConstructor() {
        assertEquals(1, a.getSize());
        assertEquals(WeaponType.HANDGUN, a.getWeapon(0).getWeaponType());
    }

    @Test
    public void testSwitchWeapon() {
        a.collectWeapon(new ShotGun(p));

        assertTrue(a.getWeapon(0).getIsBeingUsed());
        assertFalse(a.getWeapon(1).getIsBeingUsed());

        a.switchWeapon(a.getWeapon(0));

        assertTrue(a.getWeapon(1).getIsBeingUsed());
        assertFalse(a.getWeapon(0).getIsBeingUsed());
    }

    @Test
    public void testSwitchWeaponGoRound() {
        a.collectWeapon(new ShotGun(p));

        assertTrue(a.getWeapon(0).getIsBeingUsed());
        assertFalse(a.getWeapon(1).getIsBeingUsed());

        a.switchWeapon(a.getWeapon(0));

        assertTrue(a.getWeapon(1).getIsBeingUsed());
        assertFalse(a.getWeapon(0).getIsBeingUsed());

        a.switchWeapon(a.getWeapon(1));

        assertTrue(a.getWeapon(0).getIsBeingUsed());
        assertFalse(a.getWeapon(1).getIsBeingUsed());
    }

    @Test
    public void testSwitchWeaponGoRoundMaxSize() {
        a.collectWeapon(new ShotGun(p));
        a.collectWeapon(new RocketLauncher(p));
        a.collectWeapon(new LandMine(p));
        a.collectWeapon(new Uzi(p));

        assertTrue(a.getWeapon(0).getIsBeingUsed());
        assertFalse(a.getWeapon(1).getIsBeingUsed());
        assertFalse(a.getWeapon(2).getIsBeingUsed());
        assertFalse(a.getWeapon(3).getIsBeingUsed());
        assertFalse(a.getWeapon(4).getIsBeingUsed());

        a.switchWeapon(a.getWeapon(0));

        assertTrue(a.getWeapon(1).getIsBeingUsed());
        assertFalse(a.getWeapon(0).getIsBeingUsed());
        assertFalse(a.getWeapon(2).getIsBeingUsed());
        assertFalse(a.getWeapon(3).getIsBeingUsed());
        assertFalse(a.getWeapon(4).getIsBeingUsed());

        a.switchWeapon(a.getWeapon(1));

        assertTrue(a.getWeapon(2).getIsBeingUsed());
        assertFalse(a.getWeapon(0).getIsBeingUsed());
        assertFalse(a.getWeapon(1).getIsBeingUsed());
        assertFalse(a.getWeapon(3).getIsBeingUsed());
        assertFalse(a.getWeapon(4).getIsBeingUsed());

        a.switchWeapon(a.getWeapon(2));

        assertTrue(a.getWeapon(3).getIsBeingUsed());
        assertFalse(a.getWeapon(0).getIsBeingUsed());
        assertFalse(a.getWeapon(1).getIsBeingUsed());
        assertFalse(a.getWeapon(2).getIsBeingUsed());
        assertFalse(a.getWeapon(4).getIsBeingUsed());

        a.switchWeapon(a.getWeapon(3));

        assertTrue(a.getWeapon(4).getIsBeingUsed());
        assertFalse(a.getWeapon(0).getIsBeingUsed());
        assertFalse(a.getWeapon(1).getIsBeingUsed());
        assertFalse(a.getWeapon(2).getIsBeingUsed());
        assertFalse(a.getWeapon(3).getIsBeingUsed());

        a.switchWeapon(a.getWeapon(4));

        assertTrue(a.getWeapon(0).getIsBeingUsed());
        assertFalse(a.getWeapon(4).getIsBeingUsed());
        assertFalse(a.getWeapon(1).getIsBeingUsed());
        assertFalse(a.getWeapon(2).getIsBeingUsed());
        assertFalse(a.getWeapon(3).getIsBeingUsed());
    }

    @Test
    public void notThereEnoughSpace() {
        a.collectWeapon(new ShotGun(p));

        assertEquals(2, a.getSize());
        assertEquals(WeaponType.HANDGUN, a.getWeapon(0).getWeaponType());
        assertEquals(WeaponType.SHOTGUN, a.getWeapon(1).getWeaponType());

    }

    @Test
    public void notThereNotEnoughSpace() {
        a.collectWeapon(new ShotGun(p));
        a.collectWeapon(new RocketLauncher(p));
        a.collectWeapon(new LandMine(p));
        a.collectWeapon(new Uzi(p));

        assertEquals(5, a.getSize());
        assertEquals(WeaponType.HANDGUN, a.getWeapon(0).getWeaponType());
        assertEquals(WeaponType.SHOTGUN, a.getWeapon(1).getWeaponType());
        assertEquals(WeaponType.LAUNCHER, a.getWeapon(2).getWeaponType());
        assertEquals(WeaponType.MINE, a.getWeapon(3).getWeaponType());
        assertEquals(WeaponType.UZI, a.getWeapon(4).getWeaponType());

        a.collectWeapon(new Wall(p));

        assertEquals(5, a.getSize());
        assertEquals(WeaponType.HANDGUN, a.getWeapon(0).getWeaponType());
        assertEquals(WeaponType.SHOTGUN, a.getWeapon(1).getWeaponType());
        assertEquals(WeaponType.LAUNCHER, a.getWeapon(2).getWeaponType());
        assertEquals(WeaponType.MINE, a.getWeapon(3).getWeaponType());
        assertEquals(WeaponType.UZI, a.getWeapon(4).getWeaponType());
    }

    @Test
    public void alreadyThere() {
        a.collectWeapon(new ShotGun(p));

        assertEquals(2, a.getSize());
        assertEquals(WeaponType.HANDGUN, a.getWeapon(0).getWeaponType());
        assertEquals(WeaponType.SHOTGUN, a.getWeapon(1).getWeaponType());

        a.getWeapon(1).shoot();
        assertEquals(ShotGun.MAX_AMMO_CAPACITY-1, a.getWeapon(1).getAmmo());

        a.collectWeapon(new ShotGun(p));

        assertEquals(2, a.getSize());
        assertEquals(WeaponType.HANDGUN, a.getWeapon(0).getWeaponType());
        assertEquals(WeaponType.SHOTGUN, a.getWeapon(1).getWeaponType());
        assertEquals(ShotGun.MAX_AMMO_CAPACITY, a.getWeapon(1).getAmmo());

    }

    @Test
    public void testDropWeaponNotHandGun() {
        a.collectWeapon(new ShotGun(p));
        a.dropWeapon(a.getWeapon(1));

        assertEquals(1, a.getSize());
        assertEquals(WeaponType.HANDGUN, a.getWeapon(0).getWeaponType());
    }

    @Test
    public void testDropWeaponHandGun() {
        a.dropWeapon(a.getWeapon(0));

        assertEquals(1, a.getSize());
        assertEquals(WeaponType.HANDGUN, a.getWeapon(0).getWeaponType());
    }

}