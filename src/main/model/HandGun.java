package model;

/*
Class representing a regular handgun
 */

public class HandGun extends Weapon {
    public static final int MAX_AMMO_CAPACITY = 100;


    // EFFECT: Creates a HandGun with max ammo and not being used
    public HandGun(Player player) {
        this.ammo = MAX_AMMO_CAPACITY;
        this.isBeingUsed = false;
        this.player = player;
        this.dir = player.getDir();
        this.weaponType = WeaponType.HANDGUN;
    }

    @Override
    public void reload() {
        setAmmo(MAX_AMMO_CAPACITY);
    }

}
