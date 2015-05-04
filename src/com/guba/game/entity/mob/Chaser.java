package com.guba.game.entity.mob;

import com.guba.game.graphics.AnimatedSprite;
import com.guba.game.graphics.Screen;
import com.guba.game.graphics.Sprite;
import com.guba.game.graphics.SpriteSheet;

import java.awt.*;

/**
 * Created with IntelliJ IDEA.
 * User: jurkoguba
 * Date: 2013-12-21
 * Time: 2:43 PM
 * To change this template use File | Settings | File Templates.
 */
public class Chaser extends Mob {

    private AnimatedSprite down = new AnimatedSprite(SpriteSheet.dummy_down, 32, 32, 3);
    private AnimatedSprite left = new AnimatedSprite(SpriteSheet.dummy_left, 32, 32, 3);
    private AnimatedSprite right = new AnimatedSprite(SpriteSheet.dummy_right, 32, 32, 3);
    private AnimatedSprite up = new AnimatedSprite(SpriteSheet.dummy_up, 32, 32, 3);

    private AnimatedSprite animSprite = down;
    private int xa = 0;
    private int ya = 0;
    private int speed = 2, currentTick = 0; // Greater speed variable actually means slower.

    public Chaser(int x, int y) {
        this.x = x << 4;
        this.y = y << 4;
        sprite = Sprite.dummy;

    }

    private void move() {
        xa = 0;
        ya = 0;

        Player player = level.getClientPlayer();

        if (x < player.getX()) xa++;
        if (x > player.getX()) xa--;
        if (y < player.getY()) ya++;
        if (y > player.getY()) ya--;

        if (xa != 0 || ya != 0) {
            move(xa, ya, new Rectangle(x, y, 32, 32));
            moving = true;
        } else {
            moving = false;
        }

    }

    public void update() {
        currentTick++;
        if (currentTick % speed == 0)
            move();


        if (moving) animSprite.update();
        else animSprite.setFrame(0);

        if (ya > 0) {
            animSprite = down;
            dir = Direction.DOWN;


        } else if (ya < 0) {
            animSprite = up;
            dir = Direction.UP;

        }
        if (xa < 0) {
            animSprite = left;
            dir = Direction.LEFT;

        } else if (xa > 0) {
            animSprite = right;
            dir = Direction.RIGHT;

        }




    }

    public void render(Screen screen) {

        sprite = animSprite.getSprite();
        screen.renderMob(x - 16, y - 16, sprite);
    }

}