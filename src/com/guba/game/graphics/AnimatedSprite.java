package com.guba.game.graphics;

/**
 * Created with IntelliJ IDEA.
 * User: jurkoguba
 * Date: 2013-12-18
 * Time: 10:17 PM
 * To change this template use File | Settings | File Templates.
 */
public class AnimatedSprite extends Sprite {

    private int frame = 0;
    private Sprite sprite;
    private int rate = 5;
    private int time = 0;
    private int length = -1;


    public AnimatedSprite(SpriteSheet sheet, int width, int height, int length) {
        super(sheet, width, height);
        this.length = length;
        sprite = sheet.getSprites()[0];
        if (length > sheet.getSprites().length) System.err.println("Error! Length of Animation too long!");
    }

    public void update() {

        time++;
        if (time % rate == 0) {
        if (frame >= length - 1) frame = 0;
        else frame++;
        sprite = sheet.getSprites()[frame];
        }
    }

    public Sprite getSprite() {
        return sprite;
    }

    public void setFrameRate(int frames) {
        rate = frames;

    }

    public void setFrame(int i) {
        if (i < sheet.getSprites().length);
        sprite = sheet.getSprites()[i];
    }
}
