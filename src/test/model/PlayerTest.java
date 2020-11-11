package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PlayerTest {

    Player p;

    @BeforeEach
    public void setup() {
        p = new Player();
    }

    @Test
    public void testConstructor() {
        assertEquals(Direction.NORTH, p.getDir());
        assertEquals(p.STARTING_XC, p.getX());
        assertEquals(p.STARTING_YC, p.getY());
    }

    @Test
    public void testMoveNorth() {
        assertEquals(p.STARTING_XC, p.getX());
        assertEquals(p.STARTING_YC, p.getY());

        p.move();

        assertEquals(p.STARTING_XC, p.getX());
        assertEquals(p.STARTING_YC - p.speed, p.getY());
    }

    @Test
    public void testMoveSouth() {
        assertEquals(p.STARTING_XC, p.getX());
        assertEquals(p.STARTING_YC, p.getY());

        p.setDir(Direction.SOUTH);
        p.move();

        assertEquals(p.STARTING_XC, p.getX());
        assertEquals(p.STARTING_YC + p.speed, p.getY());
    }

    @Test
    public void testMoveEast() {
        assertEquals(p.STARTING_XC, p.getX());
        assertEquals(p.STARTING_YC, p.getY());

        p.setDir(Direction.EAST);
        p.move();

        assertEquals(p.STARTING_XC + p.speed, p.getX());
        assertEquals(p.STARTING_YC, p.getY());
    }

    @Test
    public void testMoveWest() {
        assertEquals(p.STARTING_XC, p.getX());
        assertEquals(p.STARTING_YC, p.getY());

        p.setDir(Direction.WEST);
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