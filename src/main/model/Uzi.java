package model;

/*
Class representing a weapon of type uzi
 */

public class Uzi extends Weapon {
    public static final int MAX_AMMO_CAPACITY = 50;

    // EFFECT: Creates an Uzi with max ammo and not being used
    public Uzi(Player player) {
        this.ammo = MAX_AMMO_CAPACITY;
        this.isBeingUsed = false;
        this.player = player;
        this.dir = player.getDir();
        this.weaponType = WeaponType.UZI;
    }

    @Override
    public void reload() {
        setAmmo(MAX_AMMO_CAPACITY);
    }
}
