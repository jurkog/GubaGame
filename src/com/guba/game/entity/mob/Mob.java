package com.guba.game.entity.mob;

import com.guba.game.Game;
import com.guba.game.entity.Entity;
import com.guba.game.entity.projectile.Projectile;
import com.guba.game.entity.projectile.WizardProjectile;
import com.guba.game.graphics.Screen;
import com.guba.game.level.Level;

import java.awt.*;
import java.io.IOException;
import java.net.DatagramPacket;

/**
 * Created with IntelliJ IDEA.
 * User: jurkoguba
 * Date: 2013-12-14
 * Time: 9:10 PM
 * To change this template use File | Settings | File Templates.
 */
public abstract class Mob extends Entity{

    protected Direction dir;
    protected int directionNumb = 0;
    protected boolean moving = false;
    private int tick = 0;


    protected enum Direction {
        UP, DOWN, LEFT, RIGHT;
    }

    public void move(int xa, int ya, Rectangle r2) {
        if (xa != 0 || ya != 0) {


        if (xa > 0) {
            dir = Direction.RIGHT;
            directionNumb = 1;
        }
        if (xa < 0) {
            dir = Direction.LEFT;
            directionNumb = 3;
        }
        if (ya > 0 && xa == 0) {
            dir = Direction.DOWN;
            directionNumb = 2;
        }
        if (ya < 0 && xa == 0) {
            dir = Direction.UP;
            directionNumb = 0;
        }


        if (!collision(xa, ya, r2) && Game.sendSocket != null) {
            x += (xa * 1);
            y += (ya * 1);
            try {

            byte[] sendData = new byte[1024];
            sendData = ("move" + "/" + x + "/" + y + "/" + Player.name + "/" + directionNumb).getBytes();
            DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, Game.IPAddress, 5000);
            Game.sendSocket.send(sendPacket);
            } catch (IOException e) {
                System.out.println("Couldn't send Move message");
            }

        }
    }

}

    public abstract void update();
    public abstract void render(Screen screen);

    protected void shoot(int x, int y, double dir) {
        Projectile p = new WizardProjectile(x, y, Player.name,dir);
        level.add(p);
        try {

            byte[] sendData = new byte[1024];
            sendData = ("shoot" + "/" + x + "/" + y + "/" + Player.name + "/" + dir).getBytes();
            DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, Game.IPAddress, 5000);
            Game.sendSocket.send(sendPacket);
        } catch (IOException e) {
            System.out.println("Couldn't send Move message");
        }


    }



    private boolean collision(int xa, int ya, Rectangle r2) {
        boolean solid = false;
        for (int c = 0; c < 4; c++) {
            int xt = ((x + xa) + c % 2 * 14 - 8 );
            int yt = ((y + ya) + c / 2 * 12 + 3 );
//            System.out.println("XT: " + xt + " YT: " + yt);

            if (level.getTile(xt / 16, yt / 16).solid())
                solid = true;

            Rectangle r3 = new Rectangle(xt, yt, 1, 1);
            for (OnlinePlayer player : Level.onlinePlayers) {
                Rectangle r1 = player.bounds();
                if (r3.intersects(r1)) {
                    solid = true;
                }
            }



        }
        return solid;
    }



}
