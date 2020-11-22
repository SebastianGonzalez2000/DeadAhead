package model;

import exceptions.WeaponNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ui.Account;

import static org.junit.jupiter.api.Assertions.*;

class ArsenalTest {
    Arsenal a;
    Player p;
    Account acc;

    Arsenal a2;
    Player p2;

    @BeforeEach
    public void setup() {
        p = new Player();
        a = new Arsenal(p);
        acc = new Account(
                "basti", "yameolvide", a, 0, 0, 0, p.MAX_HEALTH);
        p.setArsenal(a);
        a.setPlayer(p);
        a.setAccount(acc);

        p2 = new Player();
        a2 = new Arsenal(p2);
        p2.setArsenal(a2);
        a2.setPlayer(p2);
        a2.getCurrentWeapon().setIsBeingUsed(false);
    }

    @Test
    public void testConstructor() {
        assertEquals(1, a.getSize());
        assertEquals(WeaponType.HANDGUN, a.getWeapon(0).getWeaponType());
        assertEquals(a.getPlayer(), p);
        assertEquals(a2.getCurrentWeapon(), null);
    }

    @Test
    public void testSwitchWeapon() {
        assertTrue(a.getWeapon(0).getIsBeingUsed());

        a.collectWeapon(new ShotGun(p));

        assertFalse(a.getWeapon(0).getIsBeingUsed());
        assertTrue(a.getWeapon(1).getIsBeingUsed());

        a.switchWeapon(a.getWeapon(1));

        assertTrue(a.getWeapon(0).getIsBeingUsed());
        assertFalse(a.getWeapon(1).getIsBeingUsed());
    }

    @Test
    public void testSwitchWeaponGoRound() {
        a.collectWeapon(new ShotGun(p));

        assertTrue(a.getWeapon(1).getIsBeingUsed());
        assertFalse(a.getWeapon(0).getIsBeingUsed());

        a.switchWeapon(a.getWeapon(1));

        assertTrue(a.getWeapon(0).getIsBeingUsed());
        assertFalse(a.getWeapon(1).getIsBeingUsed());

        a.switchWeapon(a.getWeapon(0));

        assertTrue(a.getWeapon(1).getIsBeingUsed());
        assertFalse(a.getWeapon(0).getIsBeingUsed());
    }

    @Test
    public void testSwitchWeaponGoRoundMaxSize() {
        a.collectWeapon(new ShotGun(p));
        a.collectWeapon(new RocketLauncher(p));
        a.collectWeapon(new LandMine(p));
        a.collectWeapon(new Uzi(p));

        assertFalse(a.getWeapon(0).getIsBeingUsed());
        assertFalse(a.getWeapon(1).getIsBeingUsed());
        assertFalse(a.getWeapon(2).getIsBeingUsed());
        assertFalse(a.getWeapon(3).getIsBeingUsed());
        assertTrue(a.getWeapon(4).getIsBeingUsed());

        a.switchWeapon(a.getWeapon(4));

        assertTrue(a.getWeapon(0).getIsBeingUsed());
        assertFalse(a.getWeapon(1).getIsBeingUsed());
        assertFalse(a.getWeapon(2).getIsBeingUsed());
        assertFalse(a.getWeapon(3).getIsBeingUsed());
        assertFalse(a.getWeapon(4).getIsBeingUsed());

        a.switchWeapon(a.getWeapon(0));

        assertFalse(a.getWeapon(0).getIsBeingUsed());
        assertTrue(a.getWeapon(1).getIsBeingUsed());
        assertFalse(a.getWeapon(2).getIsBeingUsed());
        assertFalse(a.getWeapon(3).getIsBeingUsed());
        assertFalse(a.getWeapon(4).getIsBeingUsed());

        a.switchWeapon(a.getWeapon(1));

        assertFalse(a.getWeapon(0).getIsBeingUsed());
        assertFalse(a.getWeapon(1).getIsBeingUsed());
        assertTrue(a.getWeapon(2).getIsBeingUsed());
        assertFalse(a.getWeapon(3).getIsBeingUsed());
        assertFalse(a.getWeapon(4).getIsBeingUsed());

        a.switchWeapon(a.getWeapon(2));

        assertFalse(a.getWeapon(0).getIsBeingUsed());
        assertFalse(a.getWeapon(1).getIsBeingUsed());
        assertFalse(a.getWeapon(2).getIsBeingUsed());
        assertTrue(a.getWeapon(3).getIsBeingUsed());
        assertFalse(a.getWeapon(4).getIsBeingUsed());

        a.switchWeapon(a.getWeapon(3));

        assertFalse(a.getWeapon(0).getIsBeingUsed());
        assertFalse(a.getWeapon(1).getIsBeingUsed());
        assertFalse(a.getWeapon(2).getIsBeingUsed());
        assertFalse(a.getWeapon(3).getIsBeingUsed());
        assertTrue(a.getWeapon(4).getIsBeingUsed());
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
        try {
            a.dropWeapon(a.getWeapon(1));
        } catch (WeaponNotFoundException e) {
            fail("Exception was thrown when it should not have been thrown.");
        }

        assertEquals(1, a.getSize());
        assertEquals(WeaponType.HANDGUN, a.getWeapon(0).getWeaponType());
    }

    @Test
    public void testDropWeaponHandGun() {
        try {
            a.dropWeapon(a.getWeapon(0));
        } catch (WeaponNotFoundException e) {
            fail("Exception was thrown when it should not have been thrown.");
        }

        assertEquals(1, a.getSize());
        assertEquals(WeaponType.HANDGUN, a.getWeapon(0).getWeaponType());
    }

    @Test
    public void testDropWeaponNotThere() {
        try {
            a.dropWeapon(new ShotGun(p));
            fail("Exception should have been thrown.");
        } catch (WeaponNotFoundException e) {
            // pass
        }
    }

}