package com.guba.game.input;

import com.guba.game.Game;
import com.guba.game.entity.Item;
import com.guba.game.entity.mob.Player;
import com.guba.game.graphics.Screen;
import com.guba.game.level.Level;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.IOException;
import java.net.DatagramPacket;

/**
 * Created with IntelliJ IDEA.
 * User: jurkoguba
 * Date: 2013-12-12
 * Time: 11:17 PM
 * To change this template use File | Settings | File Templates.
 */
public class Keyboard implements KeyListener {

    private boolean[] keys = new boolean[65536];
    public boolean up, down, left, right;
            public static boolean inventoryRendering = false;


    public void update() {
        up = keys[KeyEvent.VK_W] || keys[KeyEvent.VK_UP];
        down = keys[KeyEvent.VK_S] || keys[KeyEvent.VK_DOWN];
        left = keys[KeyEvent.VK_A] || keys[KeyEvent.VK_LEFT];
        right = keys[KeyEvent.VK_D] || keys[KeyEvent.VK_RIGHT];
    }

    public void keyPressed(KeyEvent e) {
        keys[e.getKeyCode()] = true;

        if (e.getKeyCode() == KeyEvent.VK_I) {
            inventoryRendering = !inventoryRendering;

        }

        if (e.getKeyCode() == KeyEvent.VK_G) {
            Level.players.get(0).dropItem(Mouse.selectedInventory);
            keys[KeyEvent.VK_G] = false;

    }
           if(e.getKeyCode() == KeyEvent.VK_E) { // Picking up an item
            Level.players.get(0).grabItem();

           }
    }




    public void keyReleased(KeyEvent e) {

     if (up || down || left || right)
     try{
        byte[] sendData = new byte[1024];
        sendData = ("stop/"+Player.name).getBytes();
        DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, Game.IPAddress, 5000);
        Game.sendSocket.send(sendPacket);
    } catch (Exception e1) {
        System.out.println("Couldn't send Move message");
    }

        keys[e.getKeyCode()] = false;
    }


    public void keyTyped(KeyEvent e) {

    }
}
