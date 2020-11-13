package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ui.DeadAhead;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ZombieTest {

    private Zombie z;
    private Player player;
    private DeadAhead game;

    @BeforeEach
    public void setUpMethod() {
        player = new Player();
        game = new DeadAhead("test");
        game.setPlayer(player);
        z = new Zombie(game);
        z.setX(1);
        z.setY(2);
        z.setHealth(Zombie.MAX_HEALTH);
    }

    @Test
    public void testConstructorZombie() {
        assertEquals(1, z.getX());
        assertEquals(2, z.getY());
        assertEquals(Zombie.MAX_HEALTH, z.getHealth());
    }

    @Test
    public void testMoveZombie() {
        float xc = z.getX();
        float yc = z.getY();

        float diffX = xc - player.getX() - (float) Player.SIZE_X / 2;
        float diffY = yc - player.getY() - (float) Player.SIZE_Y / 2;
        float distance = (float) Math.sqrt(
                ((xc - player.getX()) * (xc - player.getX())) + ((yc - player.getY()) * (yc - player.getY())));

        float velX = (float) ((-1.0 / distance) * diffX);
        float velY = (float) ((-1.0 / distance) * diffY);

        z.move();

        assertEquals(z.getX(), xc + velX);
        assertEquals(z.getY(), yc + velY);
    }

    @Test
    public void testSetX() {
        assertEquals(Player.STARTING_XC, player.getX());

        player.setX(120);

        assertEquals(120, player.getX());
    }

    @Test
    public void testSetY() {
        assertEquals(Player.STARTING_YC, player.getY());

        player.setY(120);

        assertEquals(120, player.getY());
    }
}
