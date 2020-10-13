package model;

/*
Class representing the main player in the game
 */

public class Player {

    public static final int speed = 5;

    private Direction dir;
    private int xc;
    private int yc;

    // EFFECTS: creates the player facing north in the middle of the field
    public Player() {
        this.dir = Direction.NORTH;
        this.xc = 100;
        this.yc = 100;
    }

    // MODIFIES: this
    // EFFECTS: moves the character in the direction dir by 5 units
    public void move() {
        switch (dir) {
            case NORTH:
                yc += -speed;
                return;
            case SOUTH:
                yc += speed;
                return;
            case WEST:
                xc += -speed;
                return;
            case EAST:
                xc += speed;
                return;
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

    public int getX() {
        return xc;
    }

    public int getY() {
        return yc;
    }

}
