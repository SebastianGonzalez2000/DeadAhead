package model;

import ui.DeadAhead;

// class representing a bullet from a handgun
public class HandGunBullet extends Bullet {
    public static final float SIZE_X = 4;
    public static final float SIZE_Y = 4;
    public static final int DAMAGE = 5;


    // EFFECTS: creates a handgun bullet object
    public HandGunBullet(Player player) {
        super(player, SIZE_X, SIZE_Y, WeaponType.HANDGUN, DAMAGE);
    }

}
