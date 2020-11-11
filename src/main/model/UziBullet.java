package model;

import ui.DeadAhead;

// represents a bullet shot from an uzi

public class UziBullet extends Bullet {
    public static final float SIZE_X = 5;
    public static final float SIZE_Y = 6;
    public static final int DAMAGE = 25;

    // EFFECTS: creates an uzi bullet object
    public UziBullet(DeadAhead game, Player player) {
        super(game, player, SIZE_X, SIZE_Y, WeaponType.UZI, DAMAGE);
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
