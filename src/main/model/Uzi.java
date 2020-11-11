package model;

/*
Class representing a weapon of type uzi
 */

import ui.DeadAhead;

import java.util.Random;

public class Uzi extends Weapon {
    private Random rand = new Random();
    public static final int MAX_AMMO_CAPACITY = 50;

    // EFFECT: Creates an Uzi with max ammo and not being used
    public Uzi(Player player) {
        this.ammo = MAX_AMMO_CAPACITY;
        this.isBeingUsed = false;
        this.player = player;
        this.dir = player.getDir();
        this.weaponType = WeaponType.UZI;
        xc = rand.nextInt(DeadAhead.WIDTH);
        yc = rand.nextInt(DeadAhead.HEIGHT);
        isCollected = false;
        sizeX = 4;
        sizeY = 16;
    }

    @Override
    public void reload() {
        setAmmo(MAX_AMMO_CAPACITY);
    }
}
