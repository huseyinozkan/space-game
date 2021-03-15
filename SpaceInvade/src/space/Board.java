package space;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Toolkit;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.imageio.ImageIO;
import space.robot.Robot;

/**
 *
 * @author
 */
public class Board extends JPanel implements Runnable, Commons {

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    private Dimension d;
    public ArrayList aliens;
    public Player player;
    public Shot shot;

    private GameOver gameend;
    private Won vunnet;
    private int step_counter = 0;

    private int alienX = 250;
    private int alienY = 0;
    private int playerX = 0;
    public int direction = -1;
    public int deaths = 0;
    public int sleepTime = 1000;

    private boolean ingame = true;
    private boolean havewon = true;
    private final String expl = "/img/explosion.png";
    private final String alienpix = "/img/alien.png";
    private String message = "ILK DEGER";

    private Thread animator;
    int satir = 4;
    int sutun = 8;

    /*
	 * Constructor
     */
    public Board(int alienX, int playerX, int sleepTime) {
        addKeyListener(new TAdapter());
        setFocusable(true);
        d = new Dimension(BOARD_WIDTH, BOARD_HEIGTH);
        setBackground(Color.black);

        // gameInit();
        if (animator == null || !ingame) {
            animator = new Thread(this);
            animator.start();
        }
        setDoubleBuffered(true);
        this.alienX = alienX;
        this.playerX = playerX;
        this.sleepTime = sleepTime;
    }

    public void addNotify() {
        super.addNotify();
        //gameInit();
    }

    public void gameInit() {
        aliens = new ArrayList();

        ImageIcon ii = new ImageIcon(this.getClass().getResource(alienpix));

        for (int i = satir; i > 0; i--) {
            for (int j = sutun; j > 0; j--) {
                Alien alien = new Alien(i - 1, j - 1, alienX + 18 * j, alienY + 18 * i);
                alien.setImage(ii.getImage());
                aliens.add(alien);
            }
        }
        Objects.NUMBER_OF_ALIENS_TO_DESTROY = satir * sutun;
        player = new Player(playerX, 400);
        shot = new Shot();

    }

    public void drawAliens(Graphics g) {
        Iterator it = aliens.iterator();

        while (it.hasNext()) {
            Alien alien = (Alien) it.next();

            if (alien.isVisible()) {
                g.drawImage(alien.getImage(), alien.getX(), alien.getY(), this);
            }

            if (alien.isDying()) {
                alien.die();
            }
        }
    }

    public void drawPlayer(Graphics g) {
        if (player == null) {
            return;
        }
        if (player.isVisible()) {
            g.drawImage(player.getImage(), player.getX(), player.getY(), this);
        }

        if (player.isDying()) {
            player.die();
            havewon = false;
            ingame = false;
        }
    }

    public void drawGameEnd(Graphics g) {
        g.drawImage(gameend.getImage(), 0, 0, this);
    }

    public void drawShot(Graphics g) {
        if (shot == null) {
            return;
        }
        if (shot.isVisible()) {
            g.drawImage(shot.getImage(), shot.getX(), shot.getY(), this);
        }
    }

    public void drawBombing(Graphics g) {
        if (aliens == null) {
            return;
        }
        Iterator i3 = aliens.iterator();

        while (i3.hasNext()) {
            Alien a = (Alien) i3.next();

            Bomb b = a.getBomb();

            if (!b.isDestroyed()) {
                g.drawImage(b.getImage(), b.getX(), b.getY(), this);
            }
        }
    }

    public void paint(Graphics g) {
        super.paint(g);

        g.setColor(Color.black);
        g.fillRect(0, 0, d.width, d.height);
        g.setColor(Color.green);

        if (ingame) {

            g.drawLine(0, GROUND, BOARD_WIDTH, GROUND);
            drawAliens(g);
            drawPlayer(g);
            drawShot(g);
            drawBombing(g);
        }

        Toolkit.getDefaultToolkit().sync();
        g.dispose();
    }

    public void gameOver() {
        Graphics g = this.getGraphics();

        gameend = new GameOver();
        vunnet = new Won();

        // g.setColor(Color.black);
        g.fillRect(0, 0, BOARD_WIDTH, BOARD_HEIGTH);
        if (havewon == true) {
            g.drawImage(vunnet.getImage(), 0, 0, this);
        } else {
            g.drawImage(gameend.getImage(), 0, 0, this);
        }
        g.setColor(new Color(0, 32, 48));
        g.fillRect(50, BOARD_WIDTH / 2 - 30, BOARD_WIDTH - 100, 50);
        g.setColor(Color.white);
        g.drawRect(50, BOARD_WIDTH / 2 - 30, BOARD_WIDTH - 100, 50);

        Font small = new Font("Helvetica", Font.BOLD, 14);
        FontMetrics metr = this.getFontMetrics(small);

        g.setColor(Color.white);
        g.setFont(small);
        g.drawString(message, (BOARD_WIDTH - metr.stringWidth(message)) / 2,
                BOARD_WIDTH / 2);

    }

    boolean wtest = true;
    Robot r = new Robot();

    public void animationCycle() {

        if (aliens.size() != Objects.NUMBER_OF_ALIENS_TO_DESTROY) {
            return;
        }
        if (deaths == Objects.NUMBER_OF_ALIENS_TO_DESTROY) {
            ingame = false;
            message = "Tebrikler";
        }
        step_counter++;

        if (SpaceInvaders.autoplot) {
            int hamle = 10000;
            int xs[] = new int[sutun];

            Objects objects = new Objects(aliens, shot, deaths, direction, player, shot.visible);
            r.act(objects);
            hamle = r.globalHamle;

            if (hamle == 100) {
                //  System.out.println("SHOOOOOOT-\n\n");
                if (!shot.isVisible()) {
                    shot = new Shot(player.x, player.y + Objects.SHOT_SPEED);
                    shot.visible = true;
                }
            } else if (hamle == 0) {

            } else {
                player.dx = Alien.SPEED * hamle;
                player.act();
            }
        } else {
            player.act();

        }
        int y;
        if (shot.visible) {
            y = shot.getY();
            y -= Objects.SHOT_SPEED;
            if (y < -5) {
                shot.die();
            } else {
                shot.setY(y);
            }
        }

        /*else {
                boardLog("WAIT");
            }*/
        // shot
        if (shot.isVisible() && shot.getY() != player.y) {
            Iterator it = aliens.iterator();
            int shotX = shot.getX();
            int shotY = shot.getY();
            // System.out.println("************************************************");
            while (it.hasNext()) {
                Alien alien = (Alien) it.next();
                int alienX = alien.getX();
                int alienY = alien.getY();

                if (alien.isVisible() && shot.isVisible()) {
                    //   System.out.println("---------------------------- " + shotX + " " + shotY + " " + alienX + " " + alienY);
                    if (shotX >= (alienX - ALIEN_WIDTH / 3) && shotX <= (alienX + ALIEN_WIDTH / 3)
                            && shotY >= -200 && shotY <= (alienY + ALIEN_HEIGHT)) {
                        ImageIcon ii = new ImageIcon(getClass().getResource(
                                expl));
                        // alien.setImage(ii.getImage());
                        // alien.setDying(true);
                        alien.setVisible(false);

                        deaths++;
                        shot.die();
                        break;
                    }
                }
            }

        }

        // aliens
        Iterator it1 = aliens.iterator();

        while (it1.hasNext()) {
            Alien a1 = (Alien) it1.next();
            int x = a1.getX();

            if (x >= BOARD_WIDTH - BORDER_RIGHT && direction != -1) {
                direction = -1;
                Iterator i1 = aliens.iterator();
                while (i1.hasNext()) {
                    Alien a2 = (Alien) i1.next();
                    a2.setY(a2.getY() + GO_DOWN);
                }
            }

            if (x <= BORDER_LEFT && direction != 1) {
                direction = 1;

                Iterator i2 = aliens.iterator();
                while (i2.hasNext()) {
                    Alien a = (Alien) i2.next();
                    a.setY(a.getY() + GO_DOWN);
                }
            }
        }

        Iterator it = aliens.iterator();

        while (it.hasNext()) {
            Alien alien = (Alien) it.next();
            if (alien.isVisible()) {

                y = alien.getY();

                if (y > GROUND - ALIEN_HEIGHT) {
                    havewon = false;
                    ingame = false;
                    message = "Olmadi";
                    message = message + " Sure  " + (System.currentTimeMillis() - beforeTime) + " ms Step = " + step_counter;

                }

                alien.act(direction);
            }
        }

    }
    long beforeTime, timeDiff, sleep;

    public void run() {

        beforeTime = System.currentTimeMillis();

        alienY = 0;
        direction = -1;
        deaths = 0;
        ingame = true;

        gameInit();

        while (ingame) {
            repaint();
            if (sleepTime != 0) {
                try {
                    Thread.sleep(sleepTime);
                } catch (InterruptedException ex) {

                }
            }
            animationCycle();
        }

        message = message + " Sure  " + (System.currentTimeMillis() - beforeTime) + " ms Step = " + step_counter;
        gameOver();
    }

    private class TAdapter extends KeyAdapter {

        public void keyReleased(KeyEvent e) {
            player.keyReleased(e);
        }

        public void keyPressed(KeyEvent e) {

            player.keyPressed(e);

            int x = player.getX();
            int y = player.getY();

            if (ingame) {
                int key = e.getKeyCode();
                if (key == KeyEvent.VK_SPACE) {

                    if (!shot.isVisible()) {
                        shot = new Shot(x, y);
                        shot.visible = true;

                    }
                }
            }
        }
    }
}
