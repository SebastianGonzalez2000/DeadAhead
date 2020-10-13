package model;

/*
Abstract class representing a generic weapon in the game
 */

public abstract class Weapon {

    protected int ammo;
    protected boolean isBeingUsed;
    protected Direction dir;
    public Player player;
    protected WeaponType weaponType;

    // MODIFIES: this
    // EFFECTS: If ammo is non-zero, shoot a bullet from the weapon and decrease the ammo by one.
    //         Otherwise, do nothing.
    public void shoot() {
        if (ammo > 0) {
            ammo--;
        }
    }

    // MODIFIES: this
    // EFFECTS: Reloads the ammo of thr gun up to its max capacity
    abstract void reload();

    // Setters and Getters

    public void setAmmo(int i) {
        this.ammo = i;
    }

    public void setIsBeingUsed(boolean isBeingUsed) {
        this.isBeingUsed = isBeingUsed;
    }

    public int getAmmo() {
        return this.ammo;
    }

    public boolean getIsBeingUsed() {
        return this.isBeingUsed;
    }

    public Direction getDir() {
        return dir;
    }

    public WeaponType getWeaponType() {
        return weaponType;
    }
}
