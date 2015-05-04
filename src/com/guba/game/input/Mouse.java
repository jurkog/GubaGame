package com.guba.game.input;

import com.guba.game.Game;
import com.guba.game.entity.Weapon;
import com.guba.game.entity.mob.Player;
import com.guba.game.graphics.Screen;
import com.guba.game.level.Level;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

/**
 * Created with IntelliJ IDEA.
 * User: jurkoguba
 * Date: 2013-12-16
 * Time: 5:01 PM
 * To change this template use File | Settings | File Templates.
 */
public class Mouse implements MouseListener, MouseMotionListener {
     private static int mouseX = -1;
     private static int mouseY = -1;
     private static int mouseB = -1;
     private final static int SCREEN_SCALE = 4;
     public static int selectedInventory = -1;

    public static int getX() {
        return mouseX;
    }

    public static int getY() {
        return mouseY;
    }

    public static int getButton() {
        return mouseB;
    }

    public void mouseClicked(MouseEvent e) {
        mouseX = e.getX();
        mouseY = e.getY();
        System.out.println(mouseX);
            if (mouseX < 300 * SCREEN_SCALE  && mouseX > (300 * SCREEN_SCALE - (16 * 8 * SCREEN_SCALE)) && mouseY < 162 * SCREEN_SCALE && mouseY > (162 * SCREEN_SCALE - (16 * SCREEN_SCALE)) && Keyboard.inventoryRendering) {
                int difference = 300 * SCREEN_SCALE - mouseX;
                selectedInventory = 7 - (difference/(16 * SCREEN_SCALE));
                System.out.println("Selected inventory = " + selectedInventory);
                if (Player.inventory[selectedInventory] instanceof Weapon) {
                    Game.out.println("weapon/"+Player.inventory[selectedInventory].getId());
                    Game.out.flush();
                } else {
                    Game.out.println("weapon/null");
                    Game.out.flush();
                }
            }
    }

    public void mousePressed(MouseEvent e) {
        mouseB = e.getButton();
    }


    public void mouseReleased(MouseEvent e) {
        mouseB = -1;
    }


    public void mouseEntered(MouseEvent e) {

    }


    public void mouseExited(MouseEvent e) {
        //To change body of implemented methods use File | Settings | File Templates.
    }


    public void mouseDragged(MouseEvent e) {
        //To change body of implemented methods use File | Settings | File Templates.
        mouseX = e.getX();
        mouseY = e.getY();
    }


    public void mouseMoved(MouseEvent e) {
        //To change body of implemented methods use File | Settings | File Templates.
        mouseX = e.getX();
        mouseY = e.getY();
    }
}
