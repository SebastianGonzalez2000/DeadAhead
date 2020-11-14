package model;

/*
Abstract class representing a generic weapon in the game
 */

import java.util.Objects;

public abstract class Weapon {

    protected int ammo;
    protected boolean isBeingUsed;
    protected Direction dir;
    public Player player;
    protected WeaponType weaponType;
    protected boolean isCollected;
    protected int xc;
    protected int yc;
    public int sizeX;
    public int sizeY;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Weapon weapon = (Weapon) o;
        return weaponType == weapon.weaponType;
    }

    @Override
    public int hashCode() {
        return Objects.hash(weaponType);
    }

    public void setIsBeingUsed(boolean isBeingUsed) {
        this.isBeingUsed = isBeingUsed;
    }

    public void setIsCollected(boolean isCollected) {
        this.isCollected = isCollected;
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

    public boolean getIsCollected() {
        return isCollected;
    }

    public int getX() {
        return xc;
    }

    public int getY() {
        return yc;
    }
}
