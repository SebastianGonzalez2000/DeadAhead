package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class HandGunBulletTest {

    private HandGunBullet b1;
    private Player p;

    @BeforeEach
    public void setUp() {
        p = new Player();
        b1 = new HandGunBullet(p);
    }

    @Test
    public void testConstructor() {
        assertEquals(b1.getDir(), p.getDir());
        assertEquals(b1.getX(), p.getX());
        assertEquals(b1.getY(), p.getY());
        assertEquals(b1.getWeaponType(), WeaponType.HANDGUN);
        assertEquals(b1.getDamage(), b1.damage);
    }

    @Test
    public void testMoveNorth() {
        assertEquals(p.STARTING_XC, b1.getX());
        assertEquals(p.STARTING_YC, b1.getY());

        b1.move();

        assertEquals(p.STARTING_XC, b1.getX());
        assertEquals(p.STARTING_YC - b1.SPEED, b1.getY());
    }

    @Test
    public void testMoveSouth() {
        assertEquals(p.STARTING_XC, b1.getX());
        assertEquals(p.STARTING_YC, b1.getY());

        b1.setDir(Direction.SOUTH);
        b1.move();

        assertEquals(p.STARTING_XC, b1.getX());
        assertEquals(p.STARTING_YC + b1.SPEED, b1.getY());
    }

    @Test
    public void testMoveEast() {
        assertEquals(p.STARTING_XC, b1.getX());
        assertEquals(p.STARTING_YC, b1.getY());

        b1.setDir(Direction.EAST);
        b1.move();

        assertEquals(p.STARTING_XC + b1.SPEED, b1.getX());
        assertEquals(p.STARTING_YC, b1.getY());
    }

    @Test
    public void testMoveWest() {
        assertEquals(p.STARTING_XC, b1.getX());
        assertEquals(p.STARTING_YC, b1.getY());

        b1.setDir(Direction.WEST);
        b1.move();

        assertEquals(p.STARTING_XC - b1.SPEED, b1.getX());
        assertEquals(p.STARTING_YC, b1.getY());
    }

    // tests for getters and setters for completion

    @Test
    public void testSetX() {
        assertEquals(p.STARTING_XC, b1.getX());

        b1.setX(120);

        assertEquals(120, b1.getX());
    }

    @Test
    public void testSetY() {
        assertEquals(p.STARTING_YC, b1.getY());

        b1.setY(120);

        assertEquals(120, b1.getY());
    }
}
