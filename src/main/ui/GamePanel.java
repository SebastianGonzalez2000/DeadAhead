package ui;

import model.*;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

// represents the canvas used to draw the game on screen
// DISCLAIMER: this class was based on the SpaceInvaders project from the CPSC210 course. Some of the functions
// related to JPanel and drawing the objects to screen were based on that code.

public class GamePanel extends JPanel {
    public static State gameState = State.MENU;

    private DeadAhead game;
    public List<Bullet> bullets;
    private Player player;
    public List<Weapon> collectableWeapons;
    public List<Zombie> zombies;
    private Menu menu;
    private Random rand = new Random();

    public enum State {
        MENU,
        GAME,
        END
    }

    // Constructs a game panel
    // effects:  sets size and background colour of panel,
    //           updates this with the game to be displayed
    public GamePanel(DeadAhead g, Player player) {
        setPreferredSize(new Dimension(DeadAhead.WIDTH, DeadAhead.HEIGHT));
        setBackground(Color.BLACK);
        this.game = g;
        bullets = new ArrayList<>();
        this.player = player;
        collectableWeapons = new ArrayList<>();
        generateZombies();
        this.menu = new Menu(game, this);
        this.addMouseListener(menu);
    }

    // MODIFIES: this
    // EFFECTS: generates the first 10 zombies on screen
    public void generateZombies() {
        zombies = new ArrayList<>();
        for (int i = 0;i < 10;i++) {
            zombies.add(new Zombie(game));
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        drawGame(g);
    }

    // Draws the game
    // modifies: g
    // effects:  draws the game onto g
    private void drawGame(Graphics g) {
        if (gameState == State.GAME) {
            drawPlayer(g);
            drawBullets(g);
            drawCollectableWeapons(g);
            drawScorePanel(g);
            drawZombies(g);
        } else if (gameState == State.MENU) {
            drawMenu(g);
        } else if (gameState == State.END) {
            drawEndMenu(g);
        }
    }

    // MODIFIES: g
    // EFFECTS: draws the health bar, points, and kills to the screen
    private void drawScorePanel(Graphics g) {
        g.setColor(Color.gray);
        g.fillRect(15, 15, 200, 32);
        g.setColor(Color.getHSBColor((1f * player.getHealth()) / 360, 1f, 1f));
        g.fillRect(15, 15, (int) player.getHealth() * 2, 32);
        g.setColor(Color.white);
        g.drawRect(15, 15, 200, 32);
        g.drawString("Score: " + player.getScore(), 15, 64);
        g.drawString("Kills: " + player.getKills(), 15, 80);
        g.drawString("Current Weapon: " + player.getCurrentWeapon().getWeaponType(), 15, 96);
        g.drawString("Ammo: " + player.getCurrentWeapon().getAmmo(), 200, 96);
        String arsenal = "";
        for (Weapon w : player.getArsenal().getArsenal()) {
            if (player.getArsenal().getArsenal().indexOf(w) == player.getArsenal().getArsenal().size() - 1) {
                arsenal = arsenal + w.getWeaponType();
            } else {
                arsenal = arsenal + w.getWeaponType() + " - ";
            }
        }
        g.drawString("Weapons: " + arsenal, 15, 112);
    }

    // MODIFIES: g
    // EFFECTS: draws the menu buttons and title on screen
    private void drawMenu(Graphics g) {
        Font title = new Font("arial", 1, 70);
        Font button = new Font("arial", 1, 30);
        g.setFont(title);
        g.setColor(Color.GREEN);
        g.drawString("Dead Ahead", 3 * game.WIDTH / 12, game.HEIGHT / 6);
        g.setFont(button);
        g.setColor(Color.white);
        g.drawRect(menu.BUTTON_X, menu.PLAY_BUTTON_Y, menu.BUTTON_WIDTH, menu.BUTTON_HEIGHT);
        g.drawString(
                "Continue",
                6 * game.WIDTH / 15,
                menu.PLAY_BUTTON_Y + (2 * menu.BUTTON_HEIGHT / 3));
        g.drawRect(menu.BUTTON_X, menu.SAVE_BUTTON_Y, menu.BUTTON_WIDTH, menu.BUTTON_HEIGHT);
        g.drawString(
                "Save",
                33 * game.WIDTH / 75,
                menu.SAVE_BUTTON_Y + (2 * menu.BUTTON_HEIGHT / 3));
        g.drawRect(menu.BUTTON_X, menu.NEW_BUTTON_Y, menu.BUTTON_WIDTH, menu.BUTTON_HEIGHT);
        g.drawString(
                "New Game",
                20 * game.WIDTH / 52,
                menu.NEW_BUTTON_Y + (2 * menu.BUTTON_HEIGHT / 3));
        drawControls(g);
    }

    // MODIFIES: g
    // EFFECTS: draws the control instructions on the menu screen
    private void drawControls(Graphics g) {
        Font controlsTitle = new Font("arial", 1, 20);
        Font instructions = new Font("arial", 1, 15);
        g.setColor(Color.white);
        g.setFont(controlsTitle);
        g.drawString("Controls: ", game.WIDTH / 10, 13 * game.HEIGHT / 20);
        g.setFont(instructions);
        g.drawString("Arrow Keys -> Move Character", game.WIDTH / 10, 14 * game.HEIGHT / 20);
        g.drawString("Space Bar -> Shoot Weapon", game.WIDTH / 10, 15 * game.HEIGHT / 20);
        g.drawString("S -> Switch Weapon", game.WIDTH / 10, 16 * game.HEIGHT / 20);
        g.drawString("D -> Drop Weapon", game.WIDTH / 10, 17 * game.HEIGHT / 20);
        g.drawString("P -> Pause Game", game.WIDTH / 10, 18 * game.HEIGHT / 20);
        g.drawString("X -> Exit Game", game.WIDTH / 10, 19 * game.HEIGHT / 20);
    }

    // MODIFIES: g
    // EFFECTS: draws the end menu when the player dies
    private void drawEndMenu(Graphics g) {
        Font title = new Font("arial", 1, 80);
        Font button = new Font("arial", 1, 30);
        g.setFont(title);
        g.setColor(Color.GREEN);
        g.drawString("Dead Ahead", 2 * game.WIDTH / 10, game.HEIGHT / 3);
        g.setFont(button);
        g.setColor(Color.white);
        g.drawRect(menu.BUTTON_X, menu.SAVE_BUTTON_Y, menu.BUTTON_WIDTH, menu.BUTTON_HEIGHT);
        g.drawString("Retry",
                33 * game.WIDTH / 75,
                menu.SAVE_BUTTON_Y + (2 * menu.BUTTON_HEIGHT / 3));
    }

    // EFFECTS: Draws weapons that the player can collect on the floor
    private void drawCollectableWeapons(Graphics g) {
        for (Weapon w : collectableWeapons) {
            if (!w.getIsCollected()) {

                Color savedCol = g.getColor();
                g.setColor(Bullet.COLOR);
                g.fillRect(
                        w.getX() - w.sizeX / 2,
                        w.getY() - w.sizeY / 2,
                        w.sizeX,
                        w.sizeY);

                g.setColor(savedCol);

            }
        }
    }

    // MODIFIES: g
    // EFFECTS: draws all of the zombies that are currently active on screen
    private void drawZombies(Graphics g) {
        for (Zombie z : zombies) {
            Color savedCol = Zombie.COLOR;
            g.setColor(savedCol);
            g.fillRect((int) z.getX() - z.SIZE_X / 2, (int) z.getY() - z.SIZE_Y / 2, z.SIZE_X, z.SIZE_Y);
        }
    }

    // EFFECTS: draws the bullets that have been shot to the panel
    public void drawBullets(Graphics g) {
        for (Bullet b : bullets) {
            if (b.getWeaponType() == WeaponType.SHOTGUN) {
                drawShotGunBullets(g, b);
            } else if (b.getWeaponType() == WeaponType.UZI) {
                drawUziBullet(g, b);
            } else {
                drawBullet(g, b);
            }
        }
    }

    // MODIFIES: g
    // EFFECTS: draws the bullets shot from an uzi to the panel
    private void drawUziBullet(Graphics g, Bullet b) {
        g.setColor(Bullet.COLOR);
        g.fillRect(
                (int) b.getX(),
                (int) b.getY(),
                (int) b.sizeX,
                (int) b.sizeY);

        g.fillRect(
                (int) (b.getX() - b.sizeX),
                (int) (b.getY() - 2 * b.sizeY),
                (int) b.sizeX,
                (int) b.sizeY);

        g.fillRect(
                (int) ((int) b.getX() + b.sizeX / 2),
                (int) ((int) b.getY() + 2 * b.sizeY),
                (int) b.sizeX / 4,
                (int) b.sizeY);
    }

    // Draw the shotgun bullet
    // modifies: g
    // effects:  draws the shotgun bullet onto g
    private void drawShotGunBullets(Graphics g, Bullet b) {
        g.setColor(Bullet.COLOR);
        g.fillRect(
                (int) b.getX(),
                (int) b.getY(),
                (int) b.sizeX / 4,
                (int) b.sizeY);

        g.fillRect(
                (int) ((int) b.getX() - b.sizeX / 2),
                (int) ((int) b.getY() - 2 * b.sizeY),
                (int) b.sizeX / 4,
                (int) b.sizeY);

        g.fillRect(
                (int) ((int) b.getX() + b.sizeX / 2),
                (int) ((int) b.getY() + 2 * b.sizeY),
                (int) b.sizeX / 4,
                (int) b.sizeY);
    }

    // Draw the bullet
    // modifies: g
    // effects:  draws the bullet onto g
    public void drawBullet(Graphics g, Bullet b) {
        g.setColor(Bullet.COLOR);
        g.fillRect(
                (int) ((int) b.getX() - b.sizeX / 2),
                (int) ((int) b.getY() - b.sizeY / 2),
                (int) b.sizeX,
                (int) b.sizeY);
    }

    // Draw the player
    // modifies: g
    // effects:  draws the player onto g
    private void drawPlayer(Graphics g) {
        Player p = game.getPlayer();
        Color savedCol = g.getColor();
        g.setColor(Player.COLOR);
        g.fillRect(
                (int) p.getX() - Player.SIZE_X / 2,
                (int) p.getY() - Player.SIZE_Y / 2,
                Player.SIZE_X,
                Player.SIZE_Y);

        g.setColor(savedCol);
    }

    // EFFECTS: moves each bullet
    public void moveBullets() {
        for (Bullet b : bullets) {
            b.move();
        }
    }

    // EFFECTS: moves each zombie
    public void moveZombies() {
        for (Zombie z : zombies) {
            z.move();
        }
    }

    // MODIFIES: this
    // EFFECTS: depending on the current weapon used,
    // add a bullet to the screen
    public void fireWeapon() {
        Weapon currentWeapon = player.getCurrentWeapon();
        if (currentWeapon.getAmmo() <= 0) {
            return;
        }
        currentWeapon.shoot();
        if (currentWeapon.getWeaponType() == WeaponType.HANDGUN) {
            bullets.add(new HandGunBullet(game, player));
        } else if (currentWeapon.getWeaponType() == WeaponType.SHOTGUN) {
            bullets.add(new ShotGunBullet(game, player));
        } else if (currentWeapon.getWeaponType() == WeaponType.UZI) {
            bullets.add(new UziBullet(game, player));
        } else if (currentWeapon.getWeaponType() == WeaponType.LAUNCHER) {
            bullets.add(new Rocket(game, player));
        }
    }

    // MODIFIES: this
    // EFFECTS: pauses the game and shows the menu
    public void pauseGame() {
        gameState = State.MENU;
    }

    // MODIFIES: this
    // EFFECTS: adds weapons to the arena that can be collected by the player
    public void addCollectableWeapons() {
        if (player.getScore() == 0) {
            return;
        }
        if (player.getScore() % 100 == 0) {
            if (!collectableWeapons.contains(new ShotGun(player))) {
                collectableWeapons.add(new ShotGun(player));
            }
        }
        if (player.getScore() % 200 == 0) {
            if (!collectableWeapons.contains(new Uzi(player))) {
                collectableWeapons.add(new Uzi(player));
            }
        }
        if (player.getScore() % 400 == 0) {
            if (!collectableWeapons.contains(new RocketLauncher(player))) {
                collectableWeapons.add(new RocketLauncher(player));
            }
        }
    }
}
