package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PlayerTest {

    Arsenal a;
    Player p;

    @BeforeEach
    public void setup() {
        p = new Player();
        a = new Arsenal(p);
        p.setArsenal(a);
    }

    @Test
    public void testConstructor() {
        assertEquals(Direction.NORTH, p.getDir());
        assertEquals(p.STARTING_XC, p.getX());
        assertEquals(p.STARTING_YC, p.getY());
        assertEquals(p.getScore(), 0);
        assertEquals(p.getArsenal(), a);
        assertEquals(p.getCurrentWeapon().getWeaponType(), WeaponType.HANDGUN);
    }

    @Test
    public void testMoveNorth() {
        assertEquals(p.STARTING_XC, p.getX());
        assertEquals(p.STARTING_YC, p.getY());

        p.faceUp();
        p.setDir(Direction.NORTH);
        p.move();

        assertEquals(p.STARTING_XC, p.getX());
        assertEquals(p.STARTING_YC - p.speed, p.getY());
    }

    @Test
    public void testMoveSouth() {
        assertEquals(p.STARTING_XC, p.getX());
        assertEquals(p.STARTING_YC, p.getY());

        p.faceDown();
        p.move();

        assertEquals(p.STARTING_XC, p.getX());
        assertEquals(p.STARTING_YC + p.speed, p.getY());
    }

    @Test
    public void testMoveEast() {
        assertEquals(p.STARTING_XC, p.getX());
        assertEquals(p.STARTING_YC, p.getY());

        p.faceRight();
        p.move();

        assertEquals(p.STARTING_XC + p.speed, p.getX());
        assertEquals(p.STARTING_YC, p.getY());
    }

    @Test
    public void testMoveWest() {
        assertEquals(p.STARTING_XC, p.getX());
        assertEquals(p.STARTING_YC, p.getY());

        p.faceLeft();
        p.move();

        assertEquals(p.STARTING_XC - p.speed, p.getX());
        assertEquals(p.STARTING_YC, p.getY());
    }

    // tests for getters and setters for completion

    @Test
    public void testSetX() {
        assertEquals(p.STARTING_XC, p.getX());

        p.setX(120);

        assertEquals(120, p.getX());
    }

    @Test
    public void testSetY() {
        assertEquals(p.STARTING_YC, p.getY());

        p.setY(120);

        assertEquals(120, p.getY());
    }

}