package model;

/*
Class representing a weapon of type Land mine
 */

public class LandMine extends Weapon {
    public static final int MAX_AMMO_CAPACITY = 10;

    // EFFECT: Creates a LandMine with max ammo and not being used
    public LandMine(Player player) {
        this.ammo = MAX_AMMO_CAPACITY;
        this.isBeingUsed = false;
        this.player = player;
        this.dir = player.getDir();
        this.weaponType = WeaponType.MINE;
    }

    @Override
    public void reload() {
        setAmmo(MAX_AMMO_CAPACITY);
    }

}
