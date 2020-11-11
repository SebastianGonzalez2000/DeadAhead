package model;

import ui.DeadAhead;

// represents a bullet shot from a shotgun

public class ShotGunBullet extends Bullet {
    public static final float SIZE_X = 16;
    public static final float SIZE_Y = 4;
    public static final int DAMAGE = 10;


    public ShotGunBullet(DeadAhead game, Player player) {
        super(game, player, SIZE_X, SIZE_Y, WeaponType.SHOTGUN, DAMAGE);
    }

    @Override
    public void move() {
        switch (dir) {
            case NORTH:
                yc += -SPEED;
                break;
            case SOUTH:
                yc += SPEED;
                break;
            case WEST:
                xc += -SPEED;
                break;
            default:
                xc += SPEED;
        }

        if (xc < sizeX / 2) {
            onScreen = false;
        } else if (xc > DeadAhead.WIDTH - sizeX / 2) {
            onScreen = false;
        }

        if (yc < sizeY / 2) {
            onScreen = false;
        } else if (yc > DeadAhead.HEIGHT - sizeY / 2) {
            onScreen = false;
        }
    }
}
