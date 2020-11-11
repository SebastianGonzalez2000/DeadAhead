package model;

import ui.DeadAhead;

// class representing a rocket launched from a rocket launcher

public class Rocket extends Bullet {
    public static final float SIZE_X = 10;
    public static final float SIZE_Y = 20;
    public static final int DAMAGE = 50;

    // EFFECTS: creates a rocket object
    public Rocket(DeadAhead game, Player player) {
        super(game, player, SIZE_X, SIZE_Y, WeaponType.LAUNCHER, DAMAGE);
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
