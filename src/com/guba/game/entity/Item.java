package com.guba.game.entity;

import com.guba.game.graphics.Screen;
import com.guba.game.graphics.Sprite;

/**
 * Created by jurkoguba on 2013-12-24.
 */
public class Item extends Entity {
    public static String id;

    // Declaring static items


    public Item(Item item,int x, int y) {
        this.sprite = item.getSprite();
        this.id = item.getId();
        this.x = x;
        this.y = y;
    }

    public Item(Item item) {
        this.sprite = item.getSprite();
        this.id = item.getId();
    }

    public Item (Sprite sprite, String id) {
        this.sprite = sprite;
        this.id = id;
    }

    public Item (String id) {
        this.id = id;
        if (this.id.equals("gun_glock_right")) {
            this.sprite = Sprite.gun_glock_right;
        }
    }

    public Item(int x, int y, String s) {
        this.x = x;
        this.y = y;
        this.id = s;
        if (this.id.equals("gun_glock_right")) {
            this.sprite = Sprite.gun_glock_right;
        }
    }

    public String getId() {
        return id;
    }

    @Override
    public Sprite getSprite() {
        return super.getSprite();
    }

    @Override
    public void update() {
        super.update();
    }

    @Override
    public void render(Screen screen) {
        screen.renderItem(x, y, sprite, true);
//        System.out.println("X: " + x + " Y: " + y);
    }

    public void setX(int x) {
        this.x = x;

    }

    public void setY(int y) {
        this.y = y;


    }
}
