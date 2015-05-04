package com.guba.game.entity;

import com.guba.game.graphics.Screen;
import com.guba.game.graphics.Sprite;
import com.guba.game.level.Level;

import java.awt.*;
import java.util.Random;

/**
 * Created with IntelliJ IDEA.
 * User: jurkoguba
 * Date: 2013-12-14
 * Time: 9:02 PM
 * To change this template use File | Settings | File Templates.
 */
public class Entity {

    protected int x, y;
    private boolean removed = false;
    protected Level level;
    protected final Random random = new Random();
    public String name = "Normal Entity";
    protected Sprite sprite;


    public void update() {

    }

    public void render(Screen screen) {

    }

    public void remove() {
        // Remove from level
        removed = true;

    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public Sprite getSprite() {
        return sprite;
    }

    public boolean isRemoved() {
        return removed;
    }

    public void init(Level level) {
        this.level = level;


    }

}
