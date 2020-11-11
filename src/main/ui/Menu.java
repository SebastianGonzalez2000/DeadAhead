package ui;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

// represents the menu screens that can be drawn in the game

public class Menu extends MouseAdapter {



    private static DeadAhead game;
    public static final int BUTTON_X = 5 * game.WIDTH / 14;
    public static final int PLAY_BUTTON_Y = game.HEIGHT / 4;
    public static final int SAVE_BUTTON_Y = 3 * game.HEIGHT / 8;
    public static final int NEW_BUTTON_Y = 2 * game.HEIGHT / 4;
    public static final int RETRY_BUTTON_Y = game.HEIGHT / 2;
    public static final int BUTTON_WIDTH = 200;
    public static final int BUTTON_HEIGHT = 64;

    private GamePanel gp;

    // EFFECTS: creates a Menu object
    public Menu(DeadAhead game, GamePanel gp) {
        this.game = game;
        this.gp = gp;
    }

    // MODIFIES: gp
    // EFFECTS: checks which button was clicked and performs the respective action
    public void mousePressed(MouseEvent e) {
        int mx = e.getX();
        int my = e.getY();
        if (gp.gameState == GamePanel.State.MENU) {
            if (mouseOver(mx, my, BUTTON_X, PLAY_BUTTON_Y, BUTTON_WIDTH, BUTTON_HEIGHT)) {
                gp.gameState = GamePanel.State.GAME;
                return;
            } else if (mouseOver(mx, my, BUTTON_X, SAVE_BUTTON_Y, BUTTON_WIDTH, BUTTON_HEIGHT)) {
                game.saveGame();
                return;
            } else if (mouseOver(mx, my, BUTTON_X, NEW_BUTTON_Y, BUTTON_WIDTH, BUTTON_HEIGHT)) {
                game.newGame();
                return;
            }
        }
        if (gp.gameState == GamePanel.State.END) {
            if (mouseOver(mx, my, BUTTON_X, RETRY_BUTTON_Y, BUTTON_WIDTH, BUTTON_HEIGHT)) {
                game.newGame();
                // reset game
            }
        }
    }

    // EFFECTS: returns true if the click is inside a given space
    private boolean mouseOver(int mx, int my, int x, int y, int width, int height) {
        if (mx > x && mx < x + width) {
            if (my > y && my < y + height) {
                return true;
            } else {
                return false;
            }
        } else {
            return false;
        }
    }
}
