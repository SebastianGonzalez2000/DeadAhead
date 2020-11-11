package ui;

import model.*;
import persistence.JsonReader;
import persistence.JsonWriter;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

// DeadAhead application
// DISCLAIMER: this class was based on the SpaceInvaders project from the CPSC210 course. Some of the functions
// related to JPanel and drawing the objects to screen were based on that code.

public class DeadAhead extends JFrame {
    public static final int WIDTH = 800;
    public static final int HEIGHT = 600;

    private static final String JSON_STORE = "./data/account.json";
    private static final int INTERVAL = 20;
    private Scanner input;
    private Account displayedAccount;
    private JsonWriter jsonWriter;
    private JsonReader jsonReader;
    private boolean isGameOver;
    public Player player;
    private GamePanel gp;
    private Random rand = new Random();

    // EFFECTS: constructs the account and runs the DeadAhead game
    public DeadAhead() {
        super("Dead Ahead");

        addKeyListener(new KeyHandler());
        jsonWriter = new JsonWriter(JSON_STORE);
        jsonReader = new JsonReader(JSON_STORE);
        setUpGame();

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setUndecorated(true);
        gp = new GamePanel(this, player);

        add(gp);

        pack();
        centreOnScreen();
        setVisible(true);
        addTimer();
    }

    // The init method implementation was extracted from the TellerApp project provided
    // in CPSC210. I did not write this method but it is still fair use according to the de
    // project restrictions.
    // MODIFIES: this
    // EFFECTS: creates a scanner for login process
    private void init() {
        input = new Scanner(System.in);
    }

    // MODIFIES: this
    // EFFECTS: creates a new user account for the game
    public void newGame() {
        Player player = new Player();
        Arsenal arsenal = new Arsenal(player);
        player.setArsenal(arsenal);
        Account acc = new Account("basti",
                "yameolvide",
                arsenal,
                0, 0, 0, player.MAX_HEALTH);
        displayedAccount = acc;
        player.setAccount(acc);
        arsenal.setAccount(acc);
        try {
            jsonWriter.open();
            jsonWriter.write(acc);
            jsonWriter.close();
        } catch (IOException e) {
            System.out.println("Unable to create account.");
        }
        gp = new GamePanel(this, player);
        this.player = player;
        add(gp);
        pack();
        gp.gameState = GamePanel.State.GAME;
        gp.generateZombies();
    }

    // MODIFIES: this
    // EFFECTS: saves the current gameplay
    public void saveGame() {
        Account acc = displayedAccount;
        try {
            jsonWriter.open();
            jsonWriter.write(acc);
            jsonWriter.close();
            displayedAccount = jsonReader.read();
            System.out.println("Your game was saved, " + acc.getUsername());
        } catch (IOException e) {
            System.out.println("Unable to save game.");
        }
    }

    // Sets / resets the game
    // modifies: this
    // effects:  initializes player and sets isGameOver to false
    private void setUpGame() {
        init();
        try {
            displayedAccount = jsonReader.read();
        } catch (IOException e) {
            System.out.println("Unable to find account.");
        }
        player = displayedAccount.getPlayer();
        isGameOver = false;
    }

    public Player getPlayer() {
        return player;
    }

    /*
     * A key handler to respond to key events
     */
    private class KeyHandler extends KeyAdapter {
        @Override
        public void keyPressed(KeyEvent e) {
            handleKeyPressed(e.getKeyCode());
        }
    }

    // Responds to key press codes
    // modifies: this
    // effects:  turns player, fires bullets and resets game in response to
    //           given key pressed code
    public void handleKeyPressed(int keyCode) {
        if (keyCode == KeyEvent.VK_SPACE) {
            gp.fireWeapon();
        } else if (keyCode == KeyEvent.VK_R && isGameOver) {
            setUpGame();
        } else if (keyCode == KeyEvent.VK_S) {
            player.getArsenal().switchWeapon(player.getArsenal().getCurrentWeapon());
        } else if (keyCode == KeyEvent.VK_X) {
            System.exit(0);
        } else if (keyCode == KeyEvent.VK_D) {
            player.getArsenal().dropWeapon(player.getCurrentWeapon());
        } else if (keyCode == KeyEvent.VK_P) {
            gp.pauseGame();
        } else {
            playerControl(keyCode);
        }
    }

    // Controls the player weapons
    // modifies: this
    // effects: turns player in response to key code
    private void playerControl(int keyCode) {
        player.speed = 5;
        if (keyCode == KeyEvent.VK_KP_LEFT || keyCode == KeyEvent.VK_LEFT) {
            player.faceLeft();
        } else if (keyCode == KeyEvent.VK_KP_RIGHT || keyCode == KeyEvent.VK_RIGHT) {
            player.faceRight();
        } else if (keyCode == KeyEvent.VK_KP_UP || keyCode == KeyEvent.VK_UP) {
            player.faceUp();
        } else if (keyCode == KeyEvent.VK_KP_DOWN || keyCode == KeyEvent.VK_DOWN) {
            player.faceDown();
        }
    }

    // Centres frame on desktop
    // modifies: this
    // effects:  location of frame is set so frame is centred on desktop
    private void centreOnScreen() {
        Dimension scrn = Toolkit.getDefaultToolkit().getScreenSize();
        setLocation((scrn.width - getWidth()) / 2, (scrn.height - getHeight()) / 2);
    }

    // Set up timer
    // modifies: none
    // effects:  initializes a timer that updates game each
    //           INTERVAL milliseconds
    private void addTimer() {
        Timer t = new Timer(INTERVAL,  new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                if (gp.gameState == GamePanel.State.GAME) {
                    update();
                }
                gp.repaint();
            }
        });

        t.start();
    }

    // Updates the game on clock tick
    // modifies: this
    // effects:  updates player
    public void update() {
        player.move();
        gp.moveBullets();
        gp.moveZombies();
        gp.addCollectableWeapons();

        checkCollisions();
        checkGameOver();

        int factor = player.getScore() / 100;
        if (factor == 0) {
            factor = 1;
        }
        if (rand.nextInt(60 / factor) == 1) {
            gp.zombies.add(new Zombie(this));
        }
    }

    // Checks for collisions between an zombies and a bullet or player
    // modifies: this
    // effects:  removes any zombie that has been shot with a bullet
    //           and removes corresponding bullets from play
    private void checkCollisions() {
        checkCollectWeapon();
        checkZombieInteractions();
    }

    // MODIFIES: gp
    // EFFECTS: check if zombies have been hit by a bullet and removes dead zombies,
    // checks if player has been hit by a zombie and decreases players health
    private void checkZombieInteractions() {
        List<Zombie> dead = new ArrayList<>();
        for (Zombie z : gp.zombies) {
            Rectangle zombieBoundary = new Rectangle(
                    (int) z.getX() - z.SIZE_X / 2,
                    (int) z.getY() - z.SIZE_Y / 2,
                    z.SIZE_X,
                    z.SIZE_Y);
            checkBulletHits(dead, z, zombieBoundary);
            Rectangle playerBoundary = new Rectangle(
                    (int) (player.getX() - Player.SIZE_X / 2),
                    (int) player.getY() - Player.SIZE_Y / 2,
                    Player.SIZE_X,
                    Player.SIZE_Y);
            if (zombieBoundary.intersects(playerBoundary)) {
                player.setHealth(player.getHealth() - player.DAMAGE_PER_HIT);
                if (player.getHealth() <= 0) {
                    gp.gameState = GamePanel.State.END;
                }
            }
        }
        for (Zombie z : dead) {
            gp.zombies.remove(z);
        }
    }

    // MODIFIES: player, gp
    // EFFECTS: checks if the bullets on screen have hit a zombie,
    // remove dead zombies and bullets that have hit an enemy
    private void checkBulletHits(List<Zombie> dead, Zombie z, Rectangle zombieBoundary) {
        List<Bullet> hits = new ArrayList<>();
        for (Bullet b : gp.bullets) {
            Rectangle bulletBoundary = new Rectangle(
                    (int) (b.getX() - b.sizeX / 2),
                    (int) b.getY() - (int) b.sizeX / 2,
                    (int) b.sizeX,
                    (int) b.sizeX);
            if (zombieBoundary.intersects(bulletBoundary)) {
                if (!(b.getWeaponType() == WeaponType.LAUNCHER)) {
                    hits.add(b);
                }
                z.setHealth(z.getHealth() - b.getDamage());
                player.setScore(player.getScore() + Player.SCORE_PER_HIT);
                if (z.getHealth() <= 0) {
                    dead.add(z);
                    player.setKills(player.getKills() + 1);
                }
            }
        }
        for (Bullet b : hits) {
            gp.bullets.remove(b);
        }
    }

    // MODIFIES: gp, player
    // EFFECTS: checks if player has come into contact with a weapon
    // on the arena and collects the weapon
    private void checkCollectWeapon() {
        Weapon collected = null;
        for (Weapon w : gp.collectableWeapons) {
            Rectangle weaponBoundary = new Rectangle(
                    w.getX() - w.sizeX / 2,
                    w.getY() - w.sizeY / 2,
                    w.sizeX,
                    w.sizeY);
            Rectangle playerBoundary = new Rectangle(
                    (int) (player.getX() - Player.SIZE_X / 2),
                    (int) player.getY() - Player.SIZE_Y / 2,
                    Player.SIZE_X,
                    Player.SIZE_Y);
            if (weaponBoundary.intersects(playerBoundary)) {
                collected = w;
                w.setIsCollected(true);
                player.getArsenal().collectWeapon(w);
            }
        }
        try {
            gp.collectableWeapons.remove(collected);
        } catch (Exception e) {
            // pass
        }
    }

    // Is game over? (Did the player die?)
    // modifies: this
    // effects:  if player has health zero, game is marked as
    //           over and lists of zombies and bullets cleared
    private void checkGameOver() {
        if (player.getHealth() <= 0) {
            isGameOver = true;
        }
    }
}
