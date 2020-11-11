package model;

import ui.DeadAhead;

import java.awt.*;

public abstract class Bullet {

    public static final int SPEED = 10;
    public static final Color COLOR = new Color(255, 255, 255);

    protected float xc;
    protected float yc;
    protected Player player;
    protected Direction dir;
    protected boolean onScreen;
    public float sizeX;
    public float sizeY;
    protected DeadAhead game;
    protected WeaponType type;
    protected int damage;

    // EFFECTS: Creates a generic bullet
    public Bullet(DeadAhead game, Player player, float sizeX, float sizeY, WeaponType type, int damage) {
        this.game = game;
        this.player = player;
        xc = player.getX();
        yc = player.getY();
        dir = player.getDir();
        onScreen = true;
        this.sizeX = sizeX;
        this.sizeY = sizeY;
        this.type = type;
        this.damage = damage;
    }

    // MODIFIES: this
    // EFFECTS: moves the bullet in the direction dir by 10 units
    // and handles boundaries so bullet is deleted if it leaves screen
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

    // getters and setters

    public void setDir(Direction dir) {
        this.dir = dir;
    }

    public void setX(int x) {
        xc = x;
    }

    public void setY(int y) {
        yc = y;
    }

    public Direction getDir() {
        return dir;
    }

    public float getX() {
        return xc;
    }

    public float getY() {
        return yc;
    }

    public WeaponType getWeaponType() {
        return type;
    }

    public int getDamage() {
        return damage;
    }

}
