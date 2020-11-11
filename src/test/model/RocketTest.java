package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ui.DeadAhead;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class RocketTest {
    private Rocket b;
    private DeadAhead game;
    private Player p;

    @BeforeEach
    public void setUp() {
        game = new DeadAhead();
        p = new Player();
        b = new Rocket(game, p);
    }

    @Test
    public void testConstructor() {
        assertEquals(b.getDir(), p.getDir());
        assertEquals(b.getX(), p.getX());
        assertEquals(b.getY(), p.getY());
        assertEquals(b.getWeaponType(), WeaponType.LAUNCHER);
        assertEquals(b.getDamage(), b.damage);
    }

    @Test
    public void testMoveNorth() {
        assertEquals(p.STARTING_XC, b.getX());
        assertEquals(p.STARTING_YC, b.getY());

        b.move();

        assertEquals(p.STARTING_XC, b.getX());
        assertEquals(p.STARTING_YC - b.SPEED, b.getY());
    }

    @Test
    public void testMoveSouth() {
        assertEquals(p.STARTING_XC, b.getX());
        assertEquals(p.STARTING_YC, b.getY());

        b.setDir(Direction.SOUTH);
        b.move();

        assertEquals(p.STARTING_XC, b.getX());
        assertEquals(p.STARTING_YC + b.SPEED, b.getY());
    }

    @Test
    public void testMoveEast() {
        assertEquals(p.STARTING_XC, b.getX());
        assertEquals(p.STARTING_YC, b.getY());

        b.setDir(Direction.EAST);
        b.move();

        assertEquals(p.STARTING_XC + b.SPEED, b.getX());
        assertEquals(p.STARTING_YC, b.getY());
    }

    @Test
    public void testMoveWest() {
        assertEquals(p.STARTING_XC, b.getX());
        assertEquals(p.STARTING_YC, b.getY());

        b.setDir(Direction.WEST);
        b.move();

        assertEquals(p.STARTING_XC - b.SPEED, b.getX());
        assertEquals(p.STARTING_YC, b.getY());
    }

    // tests for getters and setters for completion

    @Test
    public void testSetX() {
        assertEquals(p.STARTING_XC, b.getX());

        b.setX(120);

        assertEquals(120, b.getX());
    }

    @Test
    public void testSetY() {
        assertEquals(p.STARTING_YC, b.getY());

        b.setY(120);

        assertEquals(120, b.getY());
    }
}
