package model;

/*
Arsenal representing the collection of weapons the player has available for use.
 */

import java.util.LinkedList;
import java.util.List;

public class Arsenal {
    private static final int MAX_ARSENAL_CAPACITY = 5;

    private List<Weapon> arsenal;
    private Player player;

    // EFFECTS: creates the player's arsenal starting with only a HandGun
    public Arsenal(Player player) {
        this.player = player;
        this.arsenal = new LinkedList<>();
        HandGun firstWeapon = new HandGun(this.player);
        firstWeapon.setIsBeingUsed(true);
        arsenal.add(firstWeapon);
    }

    // MODIFIES: currentWeapon and the new weapon in use
    // EFFECTS: sets currentWeapon.isBeingUsed to false and sets
    //          this field of the next weapon in the list to true.
    public void switchWeapon(Weapon currentWeapon) {
        currentWeapon.setIsBeingUsed(false);
        Weapon nextWeapon;
        if (MAX_ARSENAL_CAPACITY == arsenal.size()) {
            nextWeapon = arsenal.get((arsenal.indexOf(currentWeapon) + 1) % MAX_ARSENAL_CAPACITY);
        } else {
            nextWeapon = arsenal.get((arsenal.indexOf(currentWeapon) + 1) % arsenal.size());
        }
        nextWeapon.setIsBeingUsed(true);
    }

    // MODIFIES: this
    // EFFECTS: adds collected weapon to arsenal if there is still space
    //          available. If the weapon is already there, reload the
    //          respective weapon to max ammo
    public void collectWeapon(Weapon w) {
        for (Weapon tempW : arsenal) {
            if (w.getWeaponType() == tempW.getWeaponType()) {
                tempW.reload();
                return;
            }
        }
        if (getSize() < MAX_ARSENAL_CAPACITY) {
            arsenal.add(w);
        }
    }

    // REQUIRES: w has to be in arsenal
    // MODIFIES: this, w
    // EFFECTS: removes w from arsenal and sets w.isBeingUsed to false. Unless it is a handgun
    //          because this one cannot be dropped
    public void dropWeapon(Weapon w) {
        if (w.getWeaponType() != WeaponType.HANDGUN) {
            arsenal.remove(w);
            w.setIsBeingUsed(false);
        }
    }

    // getters and setters

    public int getSize() {
        return arsenal.size();
    }

    public Weapon getWeapon(int i) {
        return arsenal.get(i);
    }


    public Player getPlayer() {
        return player;
    }

    public List<Weapon> getArsenal() {
        return arsenal;
    }
}
