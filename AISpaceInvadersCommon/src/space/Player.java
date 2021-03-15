package space;

import java.awt.event.KeyEvent;

import javax.swing.ImageIcon;

/**
 *
 * @author
 */
public class Player extends Sprite implements Commons {

    private final int START_Y = 400;
    private final int START_X = 100;

    private final String player = "/img/craft.png";
    private int width;

    /*
	 * Constructor
     */
    public Player(int x, int y) {
        ImageIcon ii = new ImageIcon(this.getClass().getResource(player));

        width = ii.getImage().getWidth(null);

        setImage(ii.getImage());
        setX(x);
        setY(y);
    }

    public Player(int x, int y, boolean image) {
        if (image) {
            ImageIcon ii = new ImageIcon(this.getClass().getResource(player));

            width = ii.getImage().getWidth(null);

            setImage(ii.getImage());
        }
        setX(x);
        setY(y);
    }

    public void act() {
        x += dx;

        if (x <= 2) {
            x = 2;
        }
        if (x >= BOARD_WIDTH - 2 * width) {
            x = BOARD_WIDTH - 2 * width;
        }
    }

    public void keyPressed(KeyEvent e) {
        int key = e.getKeyCode();

        if (key == KeyEvent.VK_LEFT) {
            dx = -2;
        }

        if (key == KeyEvent.VK_RIGHT) {
            dx = 2;
        }

        if (key == KeyEvent.VK_J) {
            dx = 2;
        }
        if (key == KeyEvent.VK_K) {
            dx = 4;
        }
        if (key == KeyEvent.VK_L) {
            dx = 6;
        }

        if (key == KeyEvent.VK_D) {
            dx = -2;
        }
        if (key == KeyEvent.VK_S) {
            dx = -4;
        }
        if (key == KeyEvent.VK_A) {
            dx = -6;
        }

    }

    public void keyReleased(KeyEvent e) {
        int key = e.getKeyCode();

        if (key == KeyEvent.VK_LEFT) {
            dx = 0;
        }

        if (key == KeyEvent.VK_RIGHT) {
            dx = 0;
        }
        if (key == KeyEvent.VK_J) {
            dx = 0;
        }
        if (key == KeyEvent.VK_K) {
            dx = 0;
        }
        if (key == KeyEvent.VK_L) {
            dx = 0;
        }

        if (key == KeyEvent.VK_D) {
            dx = 0;
        }
        if (key == KeyEvent.VK_S) {
            dx = 0;
        }
        if (key == KeyEvent.VK_A) {
            dx = 0;
        }

    }
}
