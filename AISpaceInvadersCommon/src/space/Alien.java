package space;

import javax.swing.ImageIcon;

/**
 *
 * @author
 */
public class Alien extends Sprite {

    private Bomb bomb;
    private final String alien = "/img/alien.png";
    public static int SPEED = 2;
    public int sat;
    public int sut;


    /*
     * Constructor
     */
    public Alien(int sat, int sut, int x, int y) {
        this.x = x;
        this.y = y;
        this.sat = sat;
        this.sut = sut;

        bomb = new Bomb(x, y);
        ImageIcon ii = new ImageIcon(this.getClass().getResource(alien));
        setImage(ii.getImage());

    }

    public Alien(int sat, int sut, int x, int y, boolean image) {
        this.x = x;
        this.y = y;
        this.sat = sat;
        this.sut = sut;

        if (image) {
            bomb = new Bomb(x, y);
            ImageIcon ii = new ImageIcon(this.getClass().getResource(alien));
            setImage(ii.getImage());
        }

    }

    public void act(int direction) {
        this.x += direction * SPEED;
        // this.y = this.y + Commons.GO_DOWN;
    }

    /*
     * Getters & Setters
     */
    public Bomb getBomb() {
        return bomb;
    }

}
