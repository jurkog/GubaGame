package com.guba.game.entity.mob;

import com.guba.game.entity.Weapon;
import com.guba.game.graphics.Screen;
import com.guba.game.graphics.Sprite;

import java.awt.*;

/**
 * Created by jurkoguba on 2013-12-27.
 */
public class OnlinePlayer extends Player {

    private int dir;
    protected String username;


    public OnlinePlayer(int x, int y, String name, String weapon, int dir) {
        super(x, y, weapon);
        this.username = name;
        this.dir = dir;
        int[] spriteInt = convert.createPixelTextArray(this.username);
        nameSprite = new Sprite(spriteInt, (spriteInt.length/5), 5);

    }

    @Override
    public String getName() {
        return this.username;
    }

    public void setX(int x) {
        this.x = x;

    }

    public void moveLocation(int x, int y, int dir) {
        if (this.x < x && this.y != y) setDirection(1); // moved right and up/down
   else if (this.x > x && this.y != y) setDirection(3);
        else setDirection(dir);
        setX(x);
        setY(y);
        animSpriteUpdate();

    }

    public void setY(int y) {
        this.y = y;
    }

    public void setDirection(int dir) {
        this.dir = dir;
    }

    public void animSpriteUpdate() {
        animSprite.update();
        walking = false;
    }

    @Override
    public void update() {

//        if (walking) animSpriteUpdate();
//            else animSprite.setFrame(1);

            if (dir == 0) {
                animSprite = up;
            }
            if (dir == 2) {
                animSprite = down;
            }
            if (dir == 3) {
                animSprite = left;
            }
            if (dir == 1) {
                animSprite = right;
            }
    }

    @Override
    public void render(Screen screen) {
        sprite = animSprite.getSprite();
        screen.renderItem(x - nameSprite.getWidth()/2, y - 24, nameSprite, true);

        if (!currentWeapon.equals(Weapon.fist)) { // Renders gun and player
            if (animSprite == up) {
                screen.renderItem(x, y, currentWeapon.getSprite(), true);
                screen.renderMob(x - 16, y - 16, sprite);
            } else if (animSprite == right) {
                screen.renderMob(x - 16, y - 16, sprite);
                screen.renderItem(x - 4, y, currentWeapon.getSprite(), true);
            } else if (animSprite == down) {
                screen.renderMob(x - 16, y - 16, sprite);
                screen.renderItem(x - 20, y, currentWeapon.getOppositeSprite(), true);
            } else if (animSprite == left) {
                screen.renderItem(x - 16, y, currentWeapon.getOppositeSprite(), true);
                screen.renderMob(x - 16, y - 16, sprite);
            }

        } else {
            screen.renderMob(x - 16, y - 16, sprite);
        }
    }

    public void stopWalking() {
        animSprite.setFrame(1);
    }
}
