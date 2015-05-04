package com.guba.game.entity;

import com.guba.game.graphics.Sprite;

/**
 * Created by jurkoguba on 2013-12-25.
 */
public class Weapon extends Item {

    public static Weapon gun_glock = new Weapon(Sprite.gun_glock_right, "gun_glock_right", Sprite.gun_glock_left);
    public static Weapon fist = new Weapon("null");
    Sprite oppositeSprite;

    public Weapon(Sprite sprite, String id, Sprite oppositeSprite) {
        super(sprite, id);
        this.oppositeSprite = oppositeSprite;
    }

    public Weapon(String id) {
        super(id);
    }

    public Sprite getOppositeSprite() {
        return oppositeSprite;
    }

}
