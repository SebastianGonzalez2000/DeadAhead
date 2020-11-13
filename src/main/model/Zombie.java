package model;

import ui.DeadAhead;

import java.awt.*;
import java.util.Random;

// represents a zombie enemy from the game

public class Zombie {
    public static final Color COLOR = new Color(250, 0, 40);
    public static final int SIZE_X = 16;
    public static final int SIZE_Y = 16;
    public static final int MAX_HEALTH = 20;

    private float xc;
    private float yc;
    private float velX;
    private float velY;
    private ID id = ID.ZOMBIE;
    private Player player;
    private int health;
    Random rand = new Random();

    // EFFECTS: creates a zombie object
    public Zombie(Player player, int width, int height) {
        this.player = player;
        xc = rand.nextInt(width);
        yc = rand.nextInt(height);
        health = MAX_HEALTH;
    }

    // MODIFIES: this
    // EFFECTS: moves zombie in direction of player
    public void move() {
        float diffX = xc - player.getX() - player.SIZE_X / 2;
        float diffY = yc - player.getY() - player.SIZE_Y / 2;
        float distance = (float) Math.sqrt(
                ((xc - player.getX()) * (xc - player.getX())) + ((yc - player.getY()) * (yc - player.getY())));

        velX = (float) ((-1.0 / distance) * diffX);
        velY = (float) ((-1.0 / distance) * diffY);

//        if (yc <= 0 || yc >= game.HEIGHT - SIZE_Y) {
//            velY *= -1;
//        }
//        if (xc <= 0 || xc >= game.WIDTH - SIZE_X) {
//            velX *= -1;
//        }
        xc += velX;
        yc += velY;
    }

    // getters and setters

    public int getHealth() {
        return health;
    }

    public float getX() {
        return xc;
    }

    public float getY() {
        return yc;
    }

    public void setX(float x) {
        xc = x;
    }

    public void setY(float y) {
        yc = y;
    }

    public void setHealth(int health) {
        this.health = health;
    }
}
