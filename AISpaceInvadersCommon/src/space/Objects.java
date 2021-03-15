/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package space;

import java.util.ArrayList;

/**
 *
 * @author Cengiz
 */
public class Objects {

    public ArrayList aliens;
    public Shot shot;
    public int deaths = 0;
    public int direction = -1;
    public Player player;
    public boolean realShot;
    public static int NUMBER_OF_ALIENS_TO_DESTROY;
    public static int SHOT_SPEED = 199;

    public Objects(ArrayList aliens, Shot shot, int deaths, int direction, Player player, boolean realShot) {

        this.aliens = new ArrayList();
        for (int i = 0; i < aliens.size(); i++) {
            Alien a = (Alien) aliens.get(i);
            if (a.isVisible()) {
                this.aliens.add(new Alien(a.sat,a.sut, a.x, a.y, false));
            }
        }
        this.realShot = realShot;

        this.shot = new Shot();
        this.shot.x = shot.x;
        this.shot.y = shot.y;

        this.shot.visible = shot.visible;
        this.deaths = deaths;
        this.direction = direction;
        this.player = new Player(player.x,player.y, false);
   

    }
}
