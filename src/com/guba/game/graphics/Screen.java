package com.guba.game.graphics;

import com.guba.game.entity.mob.Mob;
import com.guba.game.entity.mob.Player;
import com.guba.game.entity.projectile.Projectile;
import com.guba.game.level.tile.Tile;

import java.util.Random;

/**
 * Created with IntelliJ IDEA.
 * User: jurkoguba
 * Date: 2013-12-12
 * Time: 6:44 PM
 * To change this template use File | Settings | File Templates.
 */
public class Screen {

    public int width, height;
    public int[] pixels;
    public final int MAP_SIZE = 8;
    public final int MAP_SIZE_MASK = (MAP_SIZE - 1);

    public int xOffset, yOffset;

    public int[] tiles =  new int[MAP_SIZE * MAP_SIZE];

    private Random random = new Random();

    public Screen(int width, int height) {
        this.width = width;
        this.height = height;
        pixels = new int[width * height];

        for (int i = 0; i < MAP_SIZE * MAP_SIZE; i++) {
            tiles[i] = random.nextInt(0xffffff);

        }
    }

    public void clear() {
        for (int i = 0; i < pixels.length; i++) {
            pixels[i] = 0;
        }
    }

    public void renderSprite(int xp, int yp, Sprite sprite, boolean fixed) {
        if (fixed) {
            xp -= xOffset;
            yp -= yOffset;
        }
        for (int y = 0; y < sprite.getHeight(); y++) {
            int ya = y + yp;
            for (int x = 0; x < sprite.getWidth(); x++) {
                int xa = x + xp;
                if (xa < 0 || xa >= width || ya < 0 || ya >= height) continue;
                pixels[xa + ya * width] = sprite.pixels[x + y * sprite.getWidth()];
            }
        }
    }

    public void renderItem(int xp, int yp, Sprite sprite, boolean fixed) {
        if (fixed) {
            xp -= xOffset;
            yp -= yOffset;
        }
        for (int y = 0; y < sprite.getHeight(); y++) {
            int ya = y + yp;
            for (int x = 0; x < sprite.getWidth(); x++) {
                int xa = x + xp;
                if (xa < 0 || xa >= width || ya < 0 || ya >= height) continue;
                int col = sprite.pixels[x + y * sprite.getWidth()];
                if (col != 0xffff00ff && col!= -130818)
                    pixels[xa + ya * width] = col;


            }
        }
    }


    public void renderTile(int xp, int yp, Tile tile) {
        xp -= xOffset;
        yp -= yOffset;
        for (int y = 0; y < tile.sprite.SIZE; y++) {
            int ya = y + yp;

            for (int x = 0; x < tile.sprite.SIZE; x++) {
                int xa = x + xp;
                if (xa < -tile.sprite.SIZE || xa >= width || ya < 0 || ya >= height) break;
                if (xa < 0) xa = 0;
                pixels[xa + ya * width] = tile.sprite.pixels[x + y * tile.sprite.SIZE];
            }
        }
    }

    public void renderProjectile(int xp, int yp, Projectile p) {
        xp -= xOffset;
        yp -= yOffset;
        for (int y = 0; y < p.getSprite().SIZE; y++) {
            int ya = y + yp;

            for (int x = 0; x < p.getSprite().SIZE; x++) {
                int xa = x + xp;
                if (xa < -p.getSprite().SIZE || xa >= width || ya < 0 || ya >= height) break;
                if (xa < 0) xa = 0;
                int col = p.getSprite().pixels[x + y * p.getSprite().SIZE];
                if (col != 0xfffe00fe)
                pixels[xa + ya * width] = col;
            }
        }
    }

    public void renderMob(int xp, int yp, Sprite sprite) {
        xp -= xOffset;
        yp -= yOffset;
        for (int y = 0; y < 32; y++) {
            int ya = y + yp;

            for (int x = 0; x < 32; x++) {
                int xa = x + xp;
                if (xa < -32 || xa >= width || ya < 0 || ya >= height) break;
                if (xa < 0) xa = 0;
                int col = sprite.pixels[x + y * 32];

                if (col != -0x1FF02)
                pixels[xa + ya * width] = col;
            }
        }
    }

    public void renderMob(int xp, int yp, Mob mob) {
        xp -= xOffset;
        yp -= yOffset;
        for (int y = 0; y < 32; y++) {
            int ya = y + yp;

            for (int x = 0; x < 32; x++) {
                int xa = x + xp;
                if (xa < -32 || xa >= width || ya < 0 || ya >= height) break;
                if (xa < 0) xa = 0;
                int col = mob.getSprite().pixels[x + y * 32];
                if (col != -0x1FF02)
                    pixels[xa + ya * width] = col;
            }
        }
    }

    public void setOffset(int xOffset, int yOffset) {
        this.xOffset = xOffset;
        this.yOffset = yOffset;
    }





}
