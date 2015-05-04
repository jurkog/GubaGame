package com.guba.game.entity.particle;

import com.guba.game.entity.Entity;
import com.guba.game.graphics.Screen;
import com.guba.game.graphics.Sprite;

import java.util.*;


/**
 * Created with IntelliJ IDEA.
 * User: jurkoguba
 * Date: 2013-12-17
 * Time: 3:13 PM
 * To change this template use File | Settings | File Templates.
 */
public class Particle extends Entity {

    private Sprite sprite;
    public String name = "Particle Entity";

    private int life, time = 0;

    protected double xx, yy, zz;
    protected double xa, ya, za;

    public Particle(int x, int y, int life) {
        this.x = x;
        this.y = y;
        this.xx = x;
        this.yy = y;
        this.life = life + (random.nextInt(20) - 10);
        sprite = Sprite.particle_normal;

        this.xa = random.nextGaussian();
        this.ya = random.nextGaussian();
        this.zz = random.nextFloat()+ 2.0;
    }

    public void update() {
        time++;
        if (time >= 7400) time = 0;
        if (time > life) remove();
        za =- 0.1;

        if (zz < 0) {
            zz = 0;
            za *= -0.55;
            xa *= 0.4;
            ya *= 0.4;
        }

        move(xx + xa, (yy + ya) + (zz + za));

    }

    private void move(double x, double y) {
        if (collision(x, y)) {
            this.xa *= -.5;
            this.ya *= -.5;
            this.za *= -.5;
        }
        this.xx += xa;
        this.yy += ya;
        this.zz += za;
    }

    public boolean collision(double x, double y) {
        boolean solid = false;
        for (int c = 0; c < 4; c++) { // C = corners
            double xt = (x - c % 2 * 16) / 16;
            double yt = (y - c / 2 * 16) / 16;
            int ix = (int) Math.ceil(xt);
            int iy = (int) Math.ceil(yt);

            if (c % 2 == 0) ix = (int) Math.floor(xt);
            if (c / 2 == 0) iy = (int) Math.floor(yt);
            if (level.getTile(ix, iy).solid()) solid = true;
        }
        return solid;
    }

    public void render(Screen screen) {
        screen.renderSprite((int)xx - 1, (int)yy - (int)zz - 2, sprite, true);

    }


}
