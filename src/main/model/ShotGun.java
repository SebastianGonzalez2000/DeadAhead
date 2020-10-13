package model;

/*
Class representing a weapon of type shotgun
 */

public class ShotGun extends Weapon {
    public static final int MAX_AMMO_CAPACITY = 10;

    // EFFECT: Creates a ShotGun with max ammo and not being used
    public ShotGun(Player player) {
        this.ammo = MAX_AMMO_CAPACITY;
        this.isBeingUsed = false;
        this.player = player;
        this.dir = player.getDir();
        this.weaponType = WeaponType.SHOTGUN;
    }

    @Override
    public void reload() {
        setAmmo(MAX_AMMO_CAPACITY);
    }

}
