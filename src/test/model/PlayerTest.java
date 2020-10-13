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
        assertEquals(100, p.getX());
        assertEquals(100, p.getY());
    }

    @Test
    public void testMoveNorth() {
        assertEquals(100, p.getX());
        assertEquals(100, p.getY());

        p.move();

        assertEquals(100, p.getX());
        assertEquals(100-p.speed, p.getY());
    }

    @Test
    public void testMoveSouth() {
        assertEquals(100, p.getX());
        assertEquals(100, p.getY());

        p.setDir(Direction.SOUTH);
        p.move();

        assertEquals(100, p.getX());
        assertEquals(100+p.speed, p.getY());
    }

    @Test
    public void testMoveEast() {
        assertEquals(100, p.getX());
        assertEquals(100, p.getY());

        p.setDir(Direction.EAST);
        p.move();

        assertEquals(100+p.speed, p.getX());
        assertEquals(100, p.getY());
    }

    @Test
    public void testMoveWest() {
        assertEquals(100, p.getX());
        assertEquals(100, p.getY());

        p.setDir(Direction.WEST);
        p.move();

        assertEquals(100-p.speed, p.getX());
        assertEquals(100, p.getY());
    }

    // tests for getters and setters for completion

    @Test
    public void testSetX() {
        assertEquals(100, p.getX());

        p.setX(120);

        assertEquals(120, p.getX());
    }

    @Test
    public void testSetY() {
        assertEquals(100, p.getY());

        p.setY(120);

        assertEquals(120, p.getY());
    }

}