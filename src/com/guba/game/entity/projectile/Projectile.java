package com.guba.game.entity.projectile;

import com.guba.game.entity.Entity;
import com.guba.game.graphics.Sprite;

import java.util.Random;

/**
 * Created with IntelliJ IDEA.
 * User: jurkoguba
 * Date: 2013-12-16
 * Time: 8:04 PM
 * To change this template use File | Settings | File Templates.
 */
public class Projectile extends Entity {

    protected final int xOrigin, yOrigin;
    protected double angle;
    protected double x, y;
    protected Sprite sprite;
    protected double nx, ny;
    protected double distance;
    protected String shooter;

    protected double speed, range, damage;

    protected final Random random = new Random();

    public Projectile(int x, int y, String shooter, double dir) {
        xOrigin = x;
        yOrigin = y;
        angle = dir;
        this.x = x;
        this.y = y;
        this.shooter = shooter;
    }

   public Sprite getSprite() {
       return sprite;
   }
    protected void move() {

    }


}
