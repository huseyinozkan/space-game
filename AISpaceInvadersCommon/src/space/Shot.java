package space;

import javax.swing.ImageIcon;

public class Shot extends Sprite {

    private String shot = "/img/shot.png";
    private final int H_SPACE = 6;
    private final int V_SPACE = 1;

    public Shot() {
        visible = false;
    }

    public Shot(int x, int y) {

        ImageIcon ii = new ImageIcon(this.getClass().getResource(shot));
        setImage(ii.getImage());
        setX(x + H_SPACE);
        setY(y - V_SPACE);
        visible = false;
    }

    public Shot(int x, int y, boolean image) {
        if (image) {
            ImageIcon ii = new ImageIcon(this.getClass().getResource(shot));
            setImage(ii.getImage());
        }
        setX(x + H_SPACE);
        setY(y - V_SPACE);
        visible = false;
    }
}
