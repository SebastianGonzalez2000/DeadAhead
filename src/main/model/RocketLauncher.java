package model;

/*
Class representing a weapon of type Rocket launcher
 */

public class RocketLauncher extends Weapon {
    public static final int MAX_AMMO_CAPACITY = 10;

    // EFFECT: Creates a RocketLauncher with max ammo and not being used
    public RocketLauncher(Player player) {
        this.ammo = MAX_AMMO_CAPACITY;
        this.isBeingUsed = false;
        this.player = player;
        this.dir = player.getDir();
        this.weaponType = WeaponType.LAUNCHER;
    }

    @Override
    public void reload() {
        setAmmo(MAX_AMMO_CAPACITY);
    }

}
