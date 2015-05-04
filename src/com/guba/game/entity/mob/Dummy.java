package com.guba.game.entity.mob;

import com.guba.game.graphics.AnimatedSprite;
import com.guba.game.graphics.Screen;
import com.guba.game.graphics.Sprite;
import com.guba.game.graphics.SpriteSheet;
import org.omg.CORBA._IDLTypeStub;

import java.awt.*;

/**
 * Created with IntelliJ IDEA.
 * User: jurkoguba
 * Date: 2013-12-19
 * Time: 12:36 PM
 * To change this template use File | Settings | File Templates.
 */
public class Dummy extends Mob {

    private AnimatedSprite down = new AnimatedSprite(SpriteSheet.dummy_down, 32, 32, 3);
    private AnimatedSprite left = new AnimatedSprite(SpriteSheet.dummy_left, 32, 32, 3);
    private AnimatedSprite right = new AnimatedSprite(SpriteSheet.dummy_right, 32, 32, 3);
    private AnimatedSprite up = new AnimatedSprite(SpriteSheet.dummy_up, 32, 32, 3);

    private AnimatedSprite animSprite = down;
    private int xa = 0;
    private int ya = 0;

    private int time = 0;

    public Dummy(int x, int y) {

        this.x = x << 4;
        this.y = y << 4;
        sprite = Sprite.dummy;
    }

    @Override
    public void update() {
        time++;
        if (time % (random.nextInt(50) + 30) == 0) {
            xa = random.nextInt(3) - 1;
            ya = random.nextInt(3) - 1;
            if (random.nextInt(4) == 0) {
                xa = 0;
                ya = 0;
            }
        }


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
        if (xa != 0 || ya != 0) {
            move(xa, ya, new Rectangle(x, y, 32, 32));
            moving = true;
        } else {
            moving = false;
        }
    }

    @Override
    public void render(Screen screen) {
        sprite = animSprite.getSprite();
        screen.renderMob(x - 16, y - 16, sprite);


    }
}
