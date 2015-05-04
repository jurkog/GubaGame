package com.guba.game.graphics;


import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;

/**
 * Created with IntelliJ IDEA.
 * User: jurkoguba
 * Date: 2013-12-12
 * Time: 11:57 PM
 * To change this template use File | Settings | File Templates.
 */
public class SpriteSheet {

    private String path;
    public final int SIZE;
    public final int WIDTH, HEIGHT;
    public int[] pixels;


    public static SpriteSheet tiles = new SpriteSheet("/com/guba/game/graphics/res/sheets/spritesheet.png", 256);
    public static SpriteSheet projectiles_wizard = new SpriteSheet("/com/guba/game/graphics/res/sheets/wizard.png", 48);
    public static SpriteSheet gun_glock = new SpriteSheet("/com/guba/game/graphics/res/sheets/gun_glock.png", 32);
    public static SpriteSheet text_sheet = new SpriteSheet("/com/guba/game/graphics/res/sheets/text_sheet.png", 127, 5);

    public static SpriteSheet player = new SpriteSheet("/com/guba/game/graphics/res/sheets/player_sheet.png", 96, 128);
    public static SpriteSheet player_down = new SpriteSheet(player, 0, 0, 3, 1, 32);
    public static SpriteSheet player_left = new SpriteSheet(player, 0, 1, 3, 1, 32);
    public static SpriteSheet player_right = new SpriteSheet(player, 0, 2, 3, 1, 32);
    public static SpriteSheet player_up = new SpriteSheet(player, 0, 3, 3, 1, 32);

    public static SpriteSheet mob_dummy = new SpriteSheet("/com/guba/game/graphics/res/sheets/player_sheet.png", 96, 128);
    public static SpriteSheet dummy_down = new SpriteSheet(mob_dummy, 0, 0, 3, 1, 32);
    public static SpriteSheet dummy_left = new SpriteSheet(mob_dummy, 0, 1, 3, 1, 32);
    public static SpriteSheet dummy_right = new SpriteSheet(mob_dummy, 0, 2, 3, 1, 32);
    public static SpriteSheet dummy_up = new SpriteSheet(mob_dummy, 0, 3, 3, 1, 32);

    private Sprite[] sprites;

    public SpriteSheet(SpriteSheet sheet, int x, int y, int width, int height, int spriteSize) {
        int xx = x * spriteSize;
        int yy = y * spriteSize;
        int w = width * spriteSize;
        int h = height * spriteSize;
        if (width == height) SIZE = width;
        else SIZE = -1;
        WIDTH = w;
        HEIGHT = h;

        pixels = new int[w * h];

        for (int y0 = 0; y0 < h; y0++) {
                int yp = yy + y0;
                    for (int x0 = 0; x0 < w; x0++) {
                int xp = xx + x0;
                    pixels[x0 + y0 * w] = sheet.pixels[xp + yp * sheet.WIDTH];
            }
        }

        // GOOD

        int frame = 0;
        sprites = new Sprite[width * height];
        for (int ya = 0; ya < height; ya++) {
            for (int xa = 0; xa < width; xa++) {

                int[] spritePixels = new int[spriteSize * spriteSize];
                for (int y0 = 0; y0 < spriteSize; y0++) {
                    for (int x0 = 0; x0 < spriteSize; x0++) {
                        spritePixels[x0 + y0 * spriteSize] = pixels[(x0 + xa * spriteSize) + (y0 + ya * spriteSize) * w];
                    }
                }
                Sprite sprite = new Sprite(spritePixels, spriteSize, spriteSize);

                sprites[frame++] = sprite;

            }
        }
    }


    // make a spawn level too?
    public SpriteSheet(String path, int size) {
        this.path = path;
        this.SIZE = size;
        WIDTH = size;
        HEIGHT = size;
        pixels = new int[SIZE * SIZE];
        load();
    }

    public SpriteSheet(String path, int width, int height) {
        this.path = path;
        SIZE = -1;
        WIDTH = width;
        HEIGHT = height;
        pixels = new int[width * height];
        load();
    }

    public Sprite[] getSprites() {
        return sprites;
    }

    private void load() {
        try {
            BufferedImage image = ImageIO.read(SpriteSheet.class.getResource(path));
            int w = image.getWidth();
            int h = image.getHeight();
            image.getRGB(0, 0, w, h, pixels, 0, w);
        } catch (IOException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }
    }

}
