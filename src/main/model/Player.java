package model;

/*
Class representing the main player in the game
 */

import ui.Account;
import ui.DeadAhead;

import java.awt.*;

public class Player {


    public static final Color COLOR = new Color(102, 255, 0);
    public static final int SIZE_X = 16;
    public static final int SIZE_Y = 16;
    public static final int MAX_HEALTH = 100;
    public static final int DAMAGE_PER_HIT = 1;
    public static final int SCORE_PER_HIT = 10;
    public static final int STARTING_XC = 250;
    public static final int STARTING_YC = 250;

    public static int speed;
    private Direction dir;
    private float xc;
    private float yc;
    private int health;
    private Arsenal arsenal;
    private Weapon currentWeapon;
    private Account acc;
    private int score;
    private int kills;

    // EFFECTS: creates the player facing north in the middle of the field
    public Player() {
        this.speed = 0;
        this.dir = Direction.NORTH;
        this.xc = STARTING_XC;
        this.yc = STARTING_YC;
        this.health = MAX_HEALTH;
        score = 0;
        kills = 0;
    }

    // MODIFIES: this
    // EFFECTS: moves the character in the direction dir by 5 units
    // and handles boundaries so player does not leave screen
    public void move() {
        switch (dir) {
            case NORTH:
                yc += -speed;
                break;
            case SOUTH:
                yc += speed;
                break;
            case WEST:
                xc += -speed;
                break;
            default:
                xc += speed;
        }

        if (xc < SIZE_X / 2) {
            xc = SIZE_X / 2;
        } else if (xc > DeadAhead.WIDTH - SIZE_X / 2) {
            xc = DeadAhead.WIDTH - SIZE_X / 2;
        }

        if (yc < SIZE_Y / 2) {
            yc = SIZE_Y / 2;
        } else if (yc > DeadAhead.HEIGHT - SIZE_Y / 2) {
            yc = DeadAhead.HEIGHT - SIZE_Y / 2;
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

    public void setAccount(Account acc) {
        this.acc = acc;
    }

    public void setArsenal(Arsenal arsenal) {
        this.arsenal = arsenal;
        setCurrentWeapon(arsenal.getCurrentWeapon());
    }

    public void setScore(int score) {
        this.score = score;
        acc.setScore(score);
    }

    public void setCurrentWeapon(Weapon w) {
        currentWeapon = w;
    }

    public void setHealth(int health) {
        this.health = health;
        acc.setHealth(health);
    }

    public void setKills(int kills) {
        this.kills = kills;
        acc.setKills(kills);
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

    public Arsenal getArsenal() {
        return arsenal;
    }

    public int getScore() {
        return score;
    }

    // MODIFIES: this
    // EFFECTS: makes the player face in the west direction
    public void faceLeft() {
        dir = Direction.WEST;
    }

    // MODIFIES: this
    // EFFECTS: makes the player face in the east direction
    public void faceRight() {
        dir = Direction.EAST;
    }

    // MODIFIES: this
    // EFFECTS: makes the player face in the north direction
    public void faceUp() {
        dir = Direction.NORTH;
    }

    // MODIFIES: this
    // EFFECTS: makes the player face in the south direction
    public void faceDown() {
        dir = Direction.SOUTH;
    }

    public int getHealth() {
        return health;
    }

    public int getKills() {
        return kills;
    }

    public Weapon getCurrentWeapon() {
        for (Weapon w : arsenal.getArsenal()) {
            if (w.isBeingUsed) {
                return w;
            }
        }
        return null;
    }
}
