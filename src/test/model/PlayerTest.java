package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PlayerTest {

    Arsenal a1;
    Player p1;
    Arsenal a2;
    Player p2;

    @BeforeEach
    public void setup() {
        p1 = new Player();
        a1 = new Arsenal(p1);
        p1.setArsenal(a1);

        p2 = new Player();
        a2 = new Arsenal(p2);
        p2.setArsenal(a2);
        a2.getCurrentWeapon().setIsBeingUsed(false);
    }

    @Test
    public void testConstructor() {
        assertEquals(Direction.NORTH, p1.getDir());
        assertEquals(p1.STARTING_XC, p1.getX());
        assertEquals(p1.STARTING_YC, p1.getY());
        assertEquals(p1.getScore(), 0);
        assertEquals(p1.getArsenal(), a1);
        assertEquals(p1.getCurrentWeapon().getWeaponType(), WeaponType.HANDGUN);
        assertEquals(p2.getCurrentWeapon(), null);
    }

    @Test
    public void testMoveNorth() {
        assertEquals(p1.STARTING_XC, p1.getX());
        assertEquals(p1.STARTING_YC, p1.getY());

        p1.faceUp();
        p1.setDir(Direction.NORTH);
        p1.move();

        assertEquals(p1.STARTING_XC, p1.getX());
        assertEquals(p1.STARTING_YC - p1.speed, p1.getY());
    }

    @Test
    public void testMoveSouth() {
        assertEquals(p1.STARTING_XC, p1.getX());
        assertEquals(p1.STARTING_YC, p1.getY());

        p1.faceDown();
        p1.move();

        assertEquals(p1.STARTING_XC, p1.getX());
        assertEquals(p1.STARTING_YC + p1.speed, p1.getY());
    }

    @Test
    public void testMoveEast() {
        assertEquals(p1.STARTING_XC, p1.getX());
        assertEquals(p1.STARTING_YC, p1.getY());

        p1.faceRight();
        p1.move();

        assertEquals(p1.STARTING_XC + p1.speed, p1.getX());
        assertEquals(p1.STARTING_YC, p1.getY());
    }

    @Test
    public void testMoveWest() {
        assertEquals(p1.STARTING_XC, p1.getX());
        assertEquals(p1.STARTING_YC, p1.getY());

        p1.faceLeft();
        p1.move();

        assertEquals(p1.STARTING_XC - p1.speed, p1.getX());
        assertEquals(p1.STARTING_YC, p1.getY());
    }

    // tests for getters and setters for completion

    @Test
    public void testSetX() {
        assertEquals(p1.STARTING_XC, p1.getX());

        p1.setX(120);

        assertEquals(120, p1.getX());
    }

    @Test
    public void testSetY() {
        assertEquals(p1.STARTING_YC, p1.getY());

        p1.setY(120);

        assertEquals(120, p1.getY());
    }

    @Test
    public void testReachBounds() {
        p1.setX(p1.SIZE_X / 2 - 1);
        p1.faceLeft();
        p1.move();

        assertEquals(p1.getX(), p1.SIZE_X / 2);

        p1.setX(800 + 1);
        p1.faceRight();
        p1.move();

        assertEquals(p1.getX(), 800 - p1.SIZE_X / 2);

        p1.setY(p1.SIZE_Y / 2 - 1);
        p1.faceUp();
        p1.move();

        assertEquals(p1.getY(), p1.SIZE_Y / 2);

        p1.setY(600 + 1);
        p1.faceDown();
        p1.move();

        assertEquals(p1.getY(), 600 - p1.SIZE_Y / 2);
    }

}