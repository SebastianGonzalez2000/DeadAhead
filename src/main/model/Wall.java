package model;

/*
Class representing a weapon of type Wall
 */

public class Wall extends Weapon {
    public static final int MAX_AMMO_CAPACITY = 10;

    // EFFECT: Creates a Wall with max ammo and not being used
    public Wall(Player player) {
        this.ammo = MAX_AMMO_CAPACITY;
        this.isBeingUsed = false;
        this.player = player;
        this.dir = player.getDir();
        this.weaponType = WeaponType.WALL;
    }

    @Override
    public void reload() {
        setAmmo(MAX_AMMO_CAPACITY);
    }
}
