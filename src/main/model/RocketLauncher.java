package model;

/*
Class representing a weapon of type Rocket launcher
 */

import ui.DeadAhead;

import java.util.Random;

public class RocketLauncher extends Weapon {
    private Random rand = new Random();
    public static final int MAX_AMMO_CAPACITY = 10;

    // EFFECT: Creates a RocketLauncher with max ammo and not being used
    public RocketLauncher(Player player) {
        this.ammo = MAX_AMMO_CAPACITY;
        this.isBeingUsed = false;
        this.player = player;
        this.dir = player.getDir();
        this.weaponType = WeaponType.LAUNCHER;
        xc = rand.nextInt(DeadAhead.WIDTH);
        yc = rand.nextInt(DeadAhead.HEIGHT);
        isCollected = false;
        sizeX = 12;
        sizeY = 32;
    }

    @Override
    public void reload() {
        setAmmo(MAX_AMMO_CAPACITY);
    }

}
